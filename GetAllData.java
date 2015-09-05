package Packages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetAllData {

    connection getcon = new connection();
    Statement stm = null;
    ResultSet rs = null;

    public List<connection> GetUIsers() throws SQLException {

        List<connection> list = new ArrayList<>();

        stm = getcon.connect().createStatement();
        String Sql = "Select * from Auction_Item ";
        rs = stm.executeQuery(Sql);
        while (rs.next()) {
            //connect
            connection GetAll = new connection();
            //get here
            GetAll.setAcId(rs.getInt("Ac_id"));
            GetAll.setItem(rs.getString("Auction_id"));
            GetAll.setModel(rs.getString("ItemName"));
            GetAll.setDesc(rs.getString("Item_Description"));
            GetAll.setMinPrice(rs.getFloat("Minimum_Paid_Prcie"));
            GetAll.setResprice(rs.getFloat("Reserved_Price"));
            GetAll.setStart(rs.getString("Auction_Start_Date"));
            GetAll.setDuration(rs.getInt("Auction_Duration"));
            GetAll.setCommission(rs.getInt("Commission_Rate"));
            GetAll.setStatus(rs.getString("Auction_Status"));
            // GetAll.setMybidf(rs.getFloat("Bid_Price"));
            GetAll.setUid(rs.getString("UserId"));

            list.add(GetAll);

        }
        return list;
    }

    public List<connection> Getprdcts() throws SQLException {

        List<connection> list = new ArrayList<>();

        stm = getcon.connect().createStatement();
        String Sql = "Select * from Auction_Item where Auction_Status='false'";
        rs = stm.executeQuery(Sql);
        while (rs.next()) {
            //connect
            connection GetAll = new connection();
            //get here
            GetAll.setAcId(rs.getInt("Ac_id"));
            GetAll.setItem(rs.getString("Auction_id"));
            GetAll.setModel(rs.getString("ItemName"));
            GetAll.setDesc(rs.getString("Item_Description"));
            GetAll.setMinPrice(rs.getFloat("Minimum_Paid_Prcie"));
            GetAll.setResprice(rs.getFloat("Reserved_Price"));
            GetAll.setStart(rs.getString("Auction_Start_Date"));
            GetAll.setDuration(rs.getInt("Auction_Duration"));
            GetAll.setCommission(rs.getInt("Commission_Rate"));
            GetAll.setStatus(rs.getString("Auction_Status"));
            GetAll.setUid(rs.getString("UserId"));
            list.add(GetAll);

        }
        return list;
    }
}
