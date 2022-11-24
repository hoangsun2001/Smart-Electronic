/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsc.smart_electronic.models;

import java.util.Date;

/**
 *
 * @author K Luyen
 */
public class IcomeDetail {

    private Double totalPrice;
    Date date;

    public IcomeDetail(Double totalPrice, Date date) {
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
}
