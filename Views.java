
package Packages;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Views {
    SearchVars Svars= new SearchVars();
    connection Con =new connection();
    PreparedStatement pstm=null;
    Statement stm=null;
    ResultSet rs=null;

    public List<SearchVars> VcurrentCdtLimit(String Sesn) throws SQLException
    {
        List<SearchVars> Curlist=new ArrayList<>();
        SearchVars AllCur =new SearchVars ();
        String viw ="Select CreditLimit ,AccountNO from User_Account where UserId='"+Sesn+"' ";
        pstm=Con.connect().prepareStatement(viw);
        rs=pstm.executeQuery();
        while(rs.next())
        {
           AllCur.setNewlimit(rs.getFloat("CreditLimit"));
           AllCur.setAccnt(rs.getInt("AccountNO"));
           Curlist.add(AllCur);
        }
        rs.close();
        pstm.close();
        return Curlist;
    }
    //----------------------------------------+//
    
    public List<SearchVars> Vtansactions(String Sesn) throws SQLException
    { 
        List<SearchVars> Translist=new ArrayList<>();
        SearchVars Alltrans =new SearchVars ();
        String viw ="Select Auction_id,Bid_Price from Bid where UserId='"+Sesn+"' ";
        pstm=Con.connect().prepareStatement(viw);
        rs=pstm.executeQuery();
        while(rs.next())
        {
            Alltrans.setTranName(rs.getString("Auction_id"));
            Alltrans.setTransprc(rs.getFloat("Bid_Price"));
            Translist.add(Alltrans);
        }
        rs.close();
        pstm.close();
        return Translist;
    }
   
    public List<connection> Vauctions(String Sesn) throws SQLException
    {
        List<connection> Auclist = new ArrayList<>();
        String viw ="Select * from Auction_Item where UserId='"+Sesn+"' ";
        pstm=Con.connect().prepareStatement(viw);
        rs=pstm.executeQuery();
       while(rs.next())
        {
            connection ALLhere= new connection();
            
            ALLhere.setItem(rs.getString("ItemName"));
            ALLhere.setDesc(rs.getString("Item_Description"));
            ALLhere.setMinPrice(rs.getFloat("Minimum_Paid_Prcie"));
            ALLhere.setResprice(rs.getFloat("Reserved_Price"));
            ALLhere.setCommission(rs.getInt("Commission_Rate"));
            Auclist.add(ALLhere);
        }
        return Auclist;
    }

     public List<connection> Vitem(int Itemid) throws SQLException
    {
        Date dt;
        List<connection> itemList = new ArrayList<>();
        String viw ="Select ItemName,Item_Description,Auction_Start_Date,Reserved_Price,Bid_Price from Auction_Item,Bid where Auction_Item.Auction_id='"+Itemid+"' ";
        pstm=Con.connect().prepareStatement(viw);
        rs=pstm.executeQuery();
       while(rs.next())
        {
            connection ALLhere= new connection();
            ALLhere.setMybidf(rs.getFloat("Bid_Price"));
            ALLhere.setAcId(rs.getInt("Ac_id"));
            ALLhere.setItem(rs.getString("ItemName"));
            ALLhere.setDesc(rs.getString("Item_Description"));
            ALLhere.setMinPrice(rs.getFloat("Minimum_Paid_Prcie"));
            ALLhere.setResprice(rs.getFloat("Reserved_Price"));
            ALLhere.setStart(rs.getString("Auction_Start_Date"));
           // ALLhere.setCommission(rs.getInt("Commission_Rate"));
           // ALLhere.dt=rs.getDate("Auction_Start_Date");
            itemList.add(ALLhere);
        }
        return itemList;
    }
   
}
