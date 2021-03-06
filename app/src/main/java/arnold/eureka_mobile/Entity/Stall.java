package arnold.eureka_mobile.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class Stall {
	private int stallId;
	private String username;
	private String password;
	private String name;
	private long contactNo;
	private Canteen canteen;
	private Date createDate;
	private ArrayList<Food> foodList;

	public Stall(){}

	public Stall(String username, String password, String name,
				 long contactNo, Canteen canteen, Date createDate,
				 ArrayList<Food> foodList) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.contactNo = contactNo;
		this.canteen = canteen;
		this.createDate = createDate;
		this.foodList = foodList;
	}

	public int getStallId() {
		return stallId;
	}

	public void setStallId(int hawkerId) {
		this.stallId = stallId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Canteen getCanteen() {
		return canteen;
	}

	public void setCanteen(Canteen canteen) {
		this.canteen = canteen;
	}

	public ArrayList<Food> getFoodList() {
		return foodList;
	}

	public void setFoodList(ArrayList<Food> foodList) {
		this.foodList = foodList;
	}

}
