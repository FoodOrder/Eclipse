package fcu.android.backend.data;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Order implements Serializable {

	private int id;
	private String orderTime;
	private String confirmTime;
	private String outsetTime;
	private String arriveTime;
	private int status;
	private double longitude;
	private double latitude;
	private String address;
	private String remark;

	private String userEmail;
	private int userId;
	private int shopId;

	private ArrayList<OrderItem> items = new ArrayList<OrderItem>();

	public ArrayList<OrderItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<OrderItem> items) {
		this.items = items;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getChineseStatus(int status) {
		String ChineseStatus = "";
		switch (status) {
		case 0: {
			ChineseStatus = "待確認";
			break;
		}
		case 1: {
			ChineseStatus = "製作中";
			break;
		}
		case 2: {
			ChineseStatus = "外送中";
			break;
		}
		case 3: {
			ChineseStatus = "已送達";
			break;
		}
		case 4: {
			ChineseStatus = "已拒絕";
			break;
		}
		default: {
			ChineseStatus = "狀態錯誤";
			break;
		}
		}
		return ChineseStatus;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	private static final double EARTH_RADIUS = 6378.137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public double GetDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 100);
		return s * 10;
	}
}