package fcu.android.backend.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Type implements Serializable {
	
	/*
	 * 1 麵
	 * 2 飯
	 * 3 粥
	 * 4 飲料
	 * 5 冰品甜品
	 * 6 炸物
	 * 7 夜市小吃
	 * 8 火鍋
	 * 9 西式餐點
	 * 10 麻辣
	 * 11 便當
	 * 12 宵夜
	 * 13 早餐
	 * 14 朋友聚會
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
