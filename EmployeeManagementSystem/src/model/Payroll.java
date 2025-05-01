package model;

import java.util.Date;

public class Payroll {
    private int id;
    private int employeeId;
    private Date payDate;
    private double amount;

    // Constructors
    public Payroll() {}

    public Payroll(int id, int employeeId, Date payDate, double amount) {
        this.id = id;
        this.employeeId = employeeId;
        this.payDate = payDate;
        this.amount = amount;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
