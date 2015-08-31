package arnold.eureka_mobile.Entity;

import java.util.Date;

public class FoodOrderItem {
	private int foodOrderItemId;
	private FoodOrder foodOrder;
	private Food food;
	private int quantity;
	private double price;
	private String remarks;
	private Date createDate;

	public FoodOrderItem(FoodOrder foodOrder, Food food, int quantity,
			double price, String remarks, Date createDate) {
		super();
		this.foodOrder = foodOrder;
		this.food = food;
		this.quantity = quantity;
		this.price = price;
		this.remarks = remarks;
		this.createDate = createDate;
	}

	public int getFoodOrderItemId() {
		return foodOrderItemId;
	}

	public void setFoodOrderItemId(int foodOrderItemId) {
		this.foodOrderItemId = foodOrderItemId;
	}

	public FoodOrder getFoodOrder() {
		return foodOrder;
	}

	public void setFoodOrder(FoodOrder foodOrder) {
		this.foodOrder = foodOrder;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
