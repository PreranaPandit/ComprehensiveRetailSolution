package com.example.prerana.comprehensiveretailsolution.model;

public class Purchases {


    private String _id;
    private String supplierName;
    private String supplierPhone;
    private String supplierBillNumber;
    private String supplierAmount;
    private String supplierStatus;
    private String supplyDate;
    private String supplierRemarks;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    public String getSupplierBillNumber() {
        return supplierBillNumber;
    }

    public void setSupplierBillNumber(String supplierBillNumber) {
        this.supplierBillNumber = supplierBillNumber;
    }

    public String getSupplierAmount() {
        return supplierAmount;
    }

    public void setSupplierAmount(String supplierAmount) {
        this.supplierAmount = supplierAmount;
    }

    public String getSupplierStatus() {
        return supplierStatus;
    }

    public void setSupplierStatus(String supplierStatus) {
        this.supplierStatus = supplierStatus;
    }

    public String getSupplyDate() {
        return supplyDate;
    }

    public void setSupplyDate(String supplyDate) {
        this.supplyDate = supplyDate;
    }

    public String getSupplierRemarks() {
        return supplierRemarks;
    }

    public void setSupplierRemarks(String supplierRemarks) {
        this.supplierRemarks = supplierRemarks;
    }

    public Purchases(String supplierName, String supplierPhone, String supplierBillNumber, String supplierAmount, String supplierStatus, String supplyDate, String supplierRemarks) {
        this.supplierName = supplierName;
        this.supplierPhone = supplierPhone;
        this.supplierBillNumber = supplierBillNumber;
        this.supplierAmount = supplierAmount;
        this.supplierStatus = supplierStatus;
        this.supplyDate = supplyDate;
        this.supplierRemarks = supplierRemarks;
    }

    public Purchases(String _id, String supplierName, String supplierPhone, String supplierBillNumber, String supplierAmount, String supplierStatus, String supplyDate, String supplierRemarks) {
        this._id = _id;
        this.supplierName = supplierName;
        this.supplierPhone = supplierPhone;
        this.supplierBillNumber = supplierBillNumber;
        this.supplierAmount = supplierAmount;
        this.supplierStatus = supplierStatus;
        this.supplyDate = supplyDate;
        this.supplierRemarks = supplierRemarks;
    }
}
