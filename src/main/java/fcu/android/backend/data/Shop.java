package fcu.android.backend.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Shop implements Serializable {

	private int ID;

	private String shopName;

	private String password;

	private String phone;

	private String email;
	
	private String photo;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto(){
		return photo;
	}
	
	public void setPhoto(String photo){
		this.photo = photo;
	}
}
