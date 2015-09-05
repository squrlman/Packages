package Packages;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin implements AdminInterface {
     //classes called
    connection Con = new connection();
    AdminSetters setters = new AdminSetters();
    login log = new login();
    PreparedStatement pstm = null;
    ResultSet rs = null;
    float price = 0;
    Date dt;

    @Override
    public void CalcPercentage() {

       // Bidcheck getbid = new Bidcheck();
        try {
            price = (BidWinner("kilo", "claris"));
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        setters.setAmount(price);
        // System.out.println(" wiiner price is:" + price);
        if (price != 0.0) {
            String gETPERCENT = "select Commission_Rate from Auction_Item where Auction_id=? ";
            try {
                pstm = Con.connect().prepareStatement(gETPERCENT);
                pstm.setString(1, "kilo");
                rs = pstm.executeQuery();
                while (rs.next()) {
                    setters.setPer(rs.getInt("Commission_Rate"));
                }

            } catch (SQLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void doCalc(float bid) {
        //float amt = setters.getAmount();
        int percent = setters.getPer();

        float toAdmin = (bid / 100) * 10;
        System.out.println("percent :" + percent);
        System.out.println("amount to take from amt is  :" + toAdmin + "Rs");
    }

    @Override
    public int createAdmin(AdminSetters setters) throws SQLException {
        // AdminSetters sET_gET= new AdminSetters();
        int persisted = 0;
        String Query = "insert into Admins (Admin_Name,Admin_Password,Phone_number,Account_number,Cvv,Card_num,Email)"
                + "values(?,?,?,?,?,?,?)";
        try {
            pstm = Con.connect().prepareStatement(Query);
            pstm.setString(1, setters.getName());
            pstm.setString(2, setters.getPassword());
            pstm.setString(3, setters.getNumber());
            pstm.setInt(4, setters.getAccount());
            pstm.setInt(5, setters.getCvv());
            pstm.setString(6, setters.getCard_no());
            pstm.setString(7, setters.getEmail());

            int persist = pstm.executeUpdate();
            if (persist > 1) {
                persisted = 1;
            } else {
                persisted = -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }

        pstm.close();
        Con.connect().close();
        return persisted;
    }

    @Override
    public boolean loginAdmin(AdminSetters setters) throws SQLException {

        String name = setters.getName();
        String password = setters.getPassword();
        String query = "select Admin_Name, Admin_Password from Admins where Admin_Name=? and Admin_Password=?";

        pstm = Con.connect().prepareStatement(query);
        pstm.setString(1, name);
        pstm.setString(2, password);

        rs = pstm.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("Admin_Name"));
            rs.getString("Admin_Password");
        }
        rs.close();
        pstm.close();
        Con.connect().close();
        return true;
    }

    //gets all auctions with paending status

    public List ViewPendingAuctions(String pending) throws SQLException {
        List list = new ArrayList<>();
        String Query = "select * from Auction_Item where Auction_Status=?";
        pstm = Con.connect().prepareStatement(Query);
        pstm.setString(1, pending);
        rs = pstm.executeQuery();
        while (rs.next()) {
            connection GetAll = new connection();
            GetAll.setItem(rs.getString("Auction_id"));
            GetAll.setCommission(rs.getInt("Commission_Rate"));
            GetAll.setStart(rs.getString("Auction_Start_Date"));
            GetAll.setDuration(rs.getInt("Auction_Duration"));
            GetAll.setStatus(rs.getString("Auction_Status"));

            list.add(GetAll);
        }
        rs.close();
        pstm.close();
        Con.connect().close();
        return list;
    }

    @Override
    public void ApproveAuction(String AuctionId) throws SQLException {
        String Query = "update Auction_Item set Auction_Status='true' where Auction_id=?";
        pstm = Con.connect().prepareStatement(Query);
        pstm.setString(1, AuctionId);
        pstm.executeUpdate();
        rs.close();
        
        pstm.close();
        Con.connect().close();
    }

    @Override
    public void DeleteUser() {

    }

    @Override
    public void ApproveBid() {
   
    }
public float BidWinner(String itemId, String ses) throws SQLException {
        float bidprc=0;
        String Ncrdt = "select Auction_Start_Date, Auction_Duration from Auction_Item where  Auction_id =?";
        pstm = Con.connect().prepareStatement(Ncrdt);
        pstm.setString(1, itemId);
        rs = pstm.executeQuery();
        while (rs.next()) {
            Con.setStart(rs.getString("Auction_Start_Date"));
            Con.setDuration(rs.getInt("Auction_Duration"));
            this.dt = rs.getDate("Auction_Start_Date");
        }
       // System.out.println("duration: "+gtcon.getDuration());
        pstm.close();
        Con.connect().close();
        //get date n duration from
        int dur = Con.getDuration();
        LocalDate strt = dt.toLocalDate();
        LocalDate end = strt.plusDays(dur);
        LocalDate curdt = LocalDate.now();
        /*
        System.out.println(" current date is: "+curdt);
        System.out.println(" current date is: "+curdt);
         change format of date here 
         String conv=ISO_DATE.format(curdt);
         String en=plus.format(ISO_DATE);
         */
        
        if (curdt.isEqual(strt)) {
            String getbidt = "select Bid_Price,Auction_id ,UserId from Bid where Bid_Price = (SELECT MAX(Bid_Price) FROM Bid) and UserId=? and Auction_id =? ";
            pstm = Con.connect().prepareStatement(getbidt);
            pstm.setString(1, ses);
            pstm.setString(2, itemId);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Con.setMinPrice(rs.getFloat("Bid_Price"));
                Con.setItem(rs.getString("Auction_id"));
                log.setUsername(rs.getString("UserId"));
            }
            bidprc = Con.getMinPrice();
            String itmId = Con.getItem();
            String usr = log.getUsername();
           
        } else {
            //wiat till end date reaches   
            System.out.println("nothing here man check again");
        }
   return bidprc;
    }
}
