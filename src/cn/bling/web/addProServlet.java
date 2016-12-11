package cn.bling.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;








import cn.bling.domain.Product;
import cn.bling.factory.BaseFactory;
import cn.bling.service.ProService;
import cn.bling.utils.IOUtils;
import cn.bling.utils.PicUtils;


/**
 * Servlet implementation class addProServlet
 */
@WebServlet("/servlet/addProServlet")
public class addProServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addProServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String temp = "/WEB-INF/temp";
		String realTemp = this.getServletContext().getRealPath(temp);
		String upload = "/WEB-INF/upload";
		String realUpload = this.getServletContext().getRealPath(upload);
		Map<String , String> map = new HashMap<String,String>(); 
		//图片上传
		DiskFileItemFactory fac = new DiskFileItemFactory(1024*100,new File(realTemp));
		ServletFileUpload fileUpload = new ServletFileUpload(fac);
		fileUpload.setFileSizeMax(1024*100*10);
		fileUpload.setSizeMax(100*1024*20);
		fileUpload.setHeaderEncoding("utf-8");

		if(!fileUpload.isMultipartContent(request)){
			throw new RuntimeException();
		}
		
		try {
			List<FileItem> lists = fileUpload.parseRequest(request);
			for(FileItem fI : lists){
				if(fI.isFormField()){
					String name = fI.getFieldName();
					String value = fI.getString("utf-8");
					map.put(name, value);
				}else{
					//防止文件名重复
					String realName = fI.getName();
					String uuidName = UUID.randomUUID()+"_"+realName;
					//文件分目录存储
					String hash = Integer.toHexString(uuidName.hashCode());
					char[] hs = hash.toCharArray();
					for(char c : hs){
						upload=upload+"/"+c;
						realUpload=realUpload+"/"+c;
					}
					map.put("imgUrl", upload+"/"+uuidName);
					new File(realUpload).mkdirs();
					OutputStream out = new FileOutputStream(realUpload+"/"+uuidName);
					InputStream in = fI.getInputStream();
					
					IOUtils.in2out(in, out);
					IOUtils.close(in, out);
					
					PicUtils pic = new PicUtils(realUpload+"/"+uuidName);
					pic.resize(180, 140);
					
					fI.delete();
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//将数据存到数据库中
		Product pro = new Product();
		try {
			map.put("id", UUID.randomUUID().toString());
			BeanUtils.populate(pro, map);
			ProService pros = BaseFactory.getFactory().getService(ProService.class);
			pros.addPro(pro);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//重点向到主页
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
