package fcu.android.backend.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OrderItem implements Serializable {

	private int foodId;
	private int amount;
	
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

}