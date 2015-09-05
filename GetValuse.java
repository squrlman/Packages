/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Packages;

/**
 *
 * @author ENOW
 */
public class GetValuse {
   private String Item;
    private String Desc;
    private String model;
    private float MinPrice;
    private float Resprice;
    private String Start;
    private int duration;
    private String status;
    private int Commission;
   

    public String getItem() {
        return Item;
    }

    public void setItem(String Item) {
        this.Item = Item;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String Desc) {
        this.Desc = Desc;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getMinPrice() {
        return MinPrice;
    }

    public void setMinPrice(float MinPrice) {
        this.MinPrice = MinPrice;
    }

    public float getResprice() {
        return Resprice;
    }

    public void setResprice(float Resprice) {
        this.Resprice = Resprice;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String Start) {
        this.Start = Start;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCommission() {
        return Commission;
    }

    public void setCommission(int Commission) {
        this.Commission = Commission;
    }
    
    
}
