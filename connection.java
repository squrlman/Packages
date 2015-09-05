package Packages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {

    private String Item;
    private String Desc;
    private String model;
    private float MinPrice;
    private float Resprice;
    private String Start;
    private int duration;
    private String status;
    private int Commission;
    private String Uid;
    private float mybidf;
    private int acId;

    public int getAcId() {
        return acId;
    }

    public void setAcId(int acId) {
        this.acId = acId;
    }

    public float getMybidf() {
        return mybidf;
    }

    public void setMybidf(float mybidf) {
        this.mybidf = mybidf;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String Uid) {
        this.Uid = Uid;
    }

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
        // String str=Start.substring(0, 9);
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
    //for connection
    Connection con = null;

    public Connection connect() {
        //Connection con=null;
        String url = "jdbc:sqlserver://localhost:1994;user=sakwe;password=Sakwe@1;databaseName=ECommerceDB;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return con;

    }

}
