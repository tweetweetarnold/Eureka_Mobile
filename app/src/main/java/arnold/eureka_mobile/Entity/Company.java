package arnold.eureka_mobile.Entity;

import java.util.Date;
import java.util.Set;

public class Company {
	private int companyId;
	private String name;
	private Set<Employee> employeeList;
	private Date createDate;

	public Company(String name, Set<Employee> employeeList, Date createDate) {
		super();
		this.name = name;
		this.employeeList = employeeList;
		this.createDate = createDate;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(Set<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
