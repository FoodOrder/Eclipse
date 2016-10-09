package fcu.android.backend.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Order implements Serializable {

	private int id;
	private String orderTime;
	private String confirmTime;
	private String outsetTime;
	private String arriveTime;
	private String status;
	
	private String shopEmail;
	private int shopId;
	private String userEmail;
	private int userId;
	
	private int foodId;
	private int amount;
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getShopEmail() {
		return shopEmail;
	}
	public void setShopEmail(String shopEmail) {
		this.shopEmail = shopEmail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}
	public String getOutsetTime() {
		return outsetTime;
	}
	public void setOutsetTime(String outsetTime) {
		this.outsetTime = outsetTime;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
