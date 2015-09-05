package arnold.eureka_mobile.Entity;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;


public class Canteen {
	private int canteenId;
	private String name;
	private String address;
	private LatLng location;
	private Date createDate;
	private ArrayList<Hawker> hawkerList;

	public Canteen(String name, String address, LatLng location,
			Date createDate, ArrayList<Hawker> hawkerList) {
		super();
		this.name = name;
		this.address = address;
		this.location = location;
		this.createDate = createDate;
		this.hawkerList = hawkerList;
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

	public ArrayList<Hawker> getHawkerList() {
		return hawkerList;
	}

	public void setHawkerList(ArrayList<Hawker> hawkerList) {
		this.hawkerList = hawkerList;
	}

}
