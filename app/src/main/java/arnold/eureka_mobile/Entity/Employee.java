package arnold.eureka_mobile.Entity;

import java.util.Date;
import java.util.Set;

/**
 * Created by Arnold on 8/17/2015.
 */
public class Employee {

    private int employeeId;
    private String username, password, name, bankAcc;
    private long contactNo;
    private Company company;
    private Set<Food> favouriteList;
    private Date createDate;

    public Employee(String username, String password, String name, String bankAcc, long contactNo, Company company, Set<Food> favouriteList, Date createDate) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.bankAcc = bankAcc;
        this.contactNo = contactNo;
        this.company = company;
        this.favouriteList = favouriteList;
        this.createDate = createDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    public String getBankAcc() {
        return bankAcc;
    }

    public void setBankAcc(String bankAcc) {
        this.bankAcc = bankAcc;
    }

    public long getContactNo() {
        return contactNo;
    }

    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Food> getFavouriteList() {
        return favouriteList;
    }

    public void setFavouriteList(Set<Food> favouriteList) {
        this.favouriteList = favouriteList;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
