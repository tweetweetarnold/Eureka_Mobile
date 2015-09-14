package arnold.eureka_mobile.Entity;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;


public class Canteen {
	private int canteenId;
	private String name;
	private String address;
	private LatLng location;
	private Date createDate;
	private ArrayList<Stall> stallList;

	public Canteen(String name, String address, LatLng location,
			Date createDate, ArrayList<Stall> stallList) {
		super();
		this.name = name;
		this.address = address;
		this.location = location;
		this.createDate = createDate;
		this.stallList = stallList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LatLng getLocation() {
		return location;
	}

	public void setLocation(LatLng location) {
		this.location = location;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public ArrayList<Stall> getStallList() {
		return stallList;
	}

	public void setStallList(ArrayList<Stall> stallList) {
		this.stallList = stallList;
	}

}
