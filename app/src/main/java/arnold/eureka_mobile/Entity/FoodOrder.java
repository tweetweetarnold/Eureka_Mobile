package arnold.eureka_mobile.Entity;

import java.util.Date;
import java.util.Set;

public class FoodOrder {
	private int foodOrderId;
	private String status;
	private Employee employee;
	private Driver driver;
	private Set<FoodOrderItem> foodOrderList;
	private Date createDate;

	public FoodOrder(String status, Employee employee, Driver driver,
			Set<FoodOrderItem> foodOrderList, Date createDate) {
		super();
		this.status = status;
		this.employee = employee;
		this.driver = driver;
		this.foodOrderList = foodOrderList;
		this.createDate = createDate;
	}

	public Set<FoodOrderItem> getFoodOrderList() {
		return foodOrderList;
	}

	public void setFoodOrderList(Set<FoodOrderItem> foodOrderList) {
		this.foodOrderList = foodOrderList;
	}

	public int getFoodOrderId() {
		return foodOrderId;
	}

	public void setFoodOrderId(int foodOrderId) {
		this.foodOrderId = foodOrderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
