package cn.bling.domain;

public class Product {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((proName == null) ? 0 : proName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (proName == null) {
			if (other.proName != null)
				return false;
		} else if (!proName.equals(other.proName))
			return false;
		return true;
	}
	private String id;
	private String proName;
	private double proPrice;
	private String category;
	private int proNum;
	private String imgUrl;
	private String proDec;
	public String getImgUrls(){
		String before = imgUrl.substring(0,imgUrl.lastIndexOf("."));
		String last = imgUrl.substring(imgUrl.lastIndexOf("."));
		String path=before+"_s"+last;
		return path;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public double getProPrice() {
		return proPrice;
	}
	public void setProPrice(double proPrice) {
		this.proPrice = proPrice;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getProNum() {
		return proNum;
	}
	public void setProNum(int proNum) {
		this.proNum = proNum;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getProDec() {
		return proDec;
	}
	public void setProDec(String proDec) {
		this.proDec = proDec;
	}
}
