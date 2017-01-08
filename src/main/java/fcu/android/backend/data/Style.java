package fcu.android.backend.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Style implements Serializable {
	/*
	 * 1 中
	 * 2 日
	 * 3 韓
	 * 4 美
	 * 5 義
	*/
	
	private int id;
	private int shopId;
	private int style;
	
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
	public int getStyle() {
		return style;
	}
	public void setStyle(int style) {
		this.style = style;
	}

}
