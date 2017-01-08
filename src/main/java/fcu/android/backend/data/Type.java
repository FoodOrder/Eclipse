package fcu.android.backend.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Type implements Serializable {
	
	/*
	 * 1 麵
	 * 2 飯
	 * 3 粥
	 * 4 飲品
	 * 5 冰
	 * 6 炸
	 * 7 小吃
	 * 8 火鍋
	 * 9 西式餐點
	 * 10 辣
	 * 11 便當
	*/
	
	private int id;
	private int shopId;
	private int type;
	
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

}
