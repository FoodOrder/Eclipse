package fcu.android.backend.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Menu implements Serializable {

	private int id;
	private int MenuPrice;
	private int  ShopID;
	private String MenuName;
	private String ShopEmail;
	private String photo;

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getShopEmail() {
		return ShopEmail;
	}

	public void setShopEmail(String shopEmail) {
		ShopEmail = shopEmail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShopID() {
		return ShopID;
	}

	public void setShopID(int shopID) {
		ShopID = shopID;
	}

	public String getMenuName() {
		return MenuName;
	}

	public void setMenuName(String menuName) {
		MenuName = menuName;
	}

	public int getMenuPrice() {
		return MenuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		MenuPrice = menuPrice;
	}

}
