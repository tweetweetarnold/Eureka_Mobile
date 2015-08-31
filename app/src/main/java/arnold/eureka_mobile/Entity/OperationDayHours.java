package arnold.eureka_mobile.Entity;

import org.joda.time.LocalTime;

public class OperationDayHours {
	private int operationDayHoursId;
	private int day;
	private LocalTime openingTime;
	private LocalTime closingTime;
	private Hawker hawker;

	public OperationDayHours(int day, LocalTime openingTime,
			LocalTime closingTime) {
		super();
		this.day = day;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
	}

	public int getOperationDayHoursId() {
		return operationDayHoursId;
	}

	public void setOperationDayHoursId(int operationDayHoursId) {
		this.operationDayHoursId = operationDayHoursId;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public LocalTime getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(LocalTime openingTime) {
		this.openingTime = openingTime;
	}

	public LocalTime getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(LocalTime closingTime) {
		this.closingTime = closingTime;
	}

}
