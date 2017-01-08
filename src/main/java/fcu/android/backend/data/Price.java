package fcu.android.backend.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Price implements Serializable {
	
	/*
	 * 1 低<80
	 * 2 中80-180
	 * 3 高>180
	 */
	
	private int id;
	private int shopId;
	private int price;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}
