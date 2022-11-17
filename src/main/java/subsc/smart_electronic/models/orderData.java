/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsc.smart_electronic.models;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class orderData {
    private Integer customer_id;
    private String customer_name;
    private String customer_address;
    private String customer_contact;
    private String customer_email;
    private String customer_cate;
    private String customer_prodName;
    private String customer_model;
    private String customer_brand;
    private Integer customer_quantity;
    private Double customer_totalPrice;
    private Double Discount;
    private Date Date;
    private Integer Delivery;

    public orderData(Integer customer_id, String customer_name, String customer_address, String customer_contact, String customer_email, String customer_cate, String customer_prodName, String customer_model, String customer_brand, Integer customer_quantity, Double customer_totalPrice, Double Discount, Date Date, Integer Delivery) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_address = customer_address;
        this.customer_contact = customer_contact;
        this.customer_email = customer_email;
        this.customer_cate = customer_cate;
        this.customer_prodName = customer_prodName;
        this.customer_model = customer_model;
        this.customer_brand = customer_brand;
        this.customer_quantity = customer_quantity;
        this.customer_totalPrice = customer_totalPrice;
        this.Discount = Discount;
        this.Date = Date;
        this.Delivery = Delivery;
    }

    public orderData(int aInt, String string, String string0, int aInt0, double aDouble, String string1, String string2, String string3, String string4, int aInt1, double aDouble0, double aDouble1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_contact() {
        return customer_contact;
    }

    public void setCustomer_contact(String customer_contact) {
        this.customer_contact = customer_contact;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_cate() {
        return customer_cate;
    }

    public void setCustomer_cate(String customer_cate) {
        this.customer_cate = customer_cate;
    }

    public String getCustomer_prodName() {
        return customer_prodName;
    }

    public void setCustomer_prodName(String customer_prodName) {
        this.customer_prodName = customer_prodName;
    }

    public String getCustomer_model() {
        return customer_model;
    }

    public void setCustomer_model(String customer_model) {
        this.customer_model = customer_model;
    }

    public String getCustomer_brand() {
        return customer_brand;
    }

    public void setCustomer_brand(String customer_brand) {
        this.customer_brand = customer_brand;
    }

    public Integer getCustomer_quantity() {
        return customer_quantity;
    }

    public void setCustomer_quantity(Integer customer_quantity) {
        this.customer_quantity = customer_quantity;
    }

    public Double getCustomer_totalPrice() {
        return customer_totalPrice;
    }

    public void setCustomer_totalPrice(Double customer_totalPrice) {
        this.customer_totalPrice = customer_totalPrice;
    }

    public Double getDiscount() {
        return Discount;
    }

    public void setDiscount(Double Discount) {
        this.Discount = Discount;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public Integer getDelivery() {
        return Delivery;
    }

    public void setDelivery(Integer Delivery) {
        this.Delivery = Delivery;
    }
    
}
