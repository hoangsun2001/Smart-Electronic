/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsc.smart_electronic.models;

/**
 *
 * @author K Luyen
 */
public class IcomeDM {

    private Double totalPriceM;
    private Integer month;

    public IcomeDM(Double totalPriceM, Integer month) {
        this.totalPriceM = totalPriceM;
        this.month = month;
    }

    public Double getTotalPriceM() {
        return totalPriceM;
    }

    public void setTotalPriceM(Double totalPriceM) {
        this.totalPriceM = totalPriceM;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

}
