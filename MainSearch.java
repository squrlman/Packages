package Packages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainSearch {

    SearchVars vars = new SearchVars();
    connection Con = new connection();
    PreparedStatement PrepStm=null;
    ResultSet Rsult=null;
    
    public List<connection> GetDataFrmDB(float price,String Item) throws SQLException {
        
        List<connection> ITMS= new ArrayList<>();
        float Items=vars.getPrice();
        String prc=vars.getName();
        //Items=price;
       // List<connection> MPR =new ArrayList<>();
        String Query="select * from Auction_Item where ItemName like %?%  or  Minimum_Paid_Prcie like %?% ";
        PrepStm=Con.connect().prepareStatement(Query);
        PrepStm.setString(1, Item);
        PrepStm.setFloat(2, price);
        Rsult=PrepStm.executeQuery();
        while(Rsult.next())
        {
         connection Svars =new connection();
         Svars.setItem(Rsult.getString("ItemName"));
         Svars.setDesc(Rsult.getString("Item_Description"));
         Svars.setStart(Rsult.getString("Auction_Start_Date"));
         Svars.setMinPrice(Rsult.getFloat("Minimum_Paid_Prcie"));
         Svars.setStatus(Rsult.getString("Auction_Status"));       
         ITMS.add(Svars);
        }
        return ITMS;
    }

    public void LoopData() {

    }
    public void ShowRslt()
    {
        
    }

}
