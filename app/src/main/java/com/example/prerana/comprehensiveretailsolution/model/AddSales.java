package com.example.prerana.comprehensiveretailsolution.model;

public class AddSales {

    private String _id;
    private String customerName;
    private String customerPhone;
    private String category;
    private String billNumber;
    private String amount;
    private String salesStatus;
    private String salesDate;
    private String remarks;

    public AddSales(String customerName, String customerPhone, String category, String billNumber, String amount, String salesStatus, String salesDate, String remarks) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.category = category;
        this.billNumber = billNumber;
        this.amount = amount;
        this.salesStatus = salesStatus;
        this.salesDate = salesDate;
        this.remarks = remarks;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getSalesStatus() {
        return salesStatus;
    }

    public void setSalesStatus(String salesStatus) {
        this.salesStatus = salesStatus;
    }

    public String getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public AddSales(String _id, String customerName, String customerPhone, String category, String billNumber, String amount, String salesStatus, String salesDate, String remarks) {
        this._id = _id;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.category = category;
        this.billNumber = billNumber;
        this.amount = amount;
        this.salesStatus = salesStatus;
        this.salesDate = salesDate;
        this.remarks = remarks;
    }
}
