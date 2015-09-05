package Packages;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.format.DateTimeFormatter.ISO_DATE;
import java.util.List;
//import Packages.biddata;

public class Bidcheck {

    connection gtcon = new connection();
    biddata bidamt = new biddata();
    login log = new login();
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Statement stm = null;

    //
    private float cLimit = 0;
    private float rPrice = 0;
    private float mPrice = 0;
    private float newCreditLimit;
    private int acctNo;
    private int Cvv;
    private String Ud;

    public String getUd() {
        return Ud;
    }

    public void setUd(String Ud) {
        this.Ud = Ud;
    }
    private String CCNo;

    public String getCCNo() {
        return CCNo;
    }

    public void setCCNo(String CCNo) {
        this.CCNo = CCNo;
    }

    public int getCvv() {
        return Cvv;
    }

    public void setCvv(int Cvv) {
        this.Cvv = Cvv;
    }

    public int getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(int acctNo) {
        this.acctNo = acctNo;
    }

    public float getNewCreditLimit() {
        return newCreditLimit;
    }

    public void setNewCreditLimit(float newCreditLimit) {
        this.newCreditLimit = newCreditLimit;
    }

    public float getcLimit() {
        return cLimit;
    }

    public void setcLimit(float cLimit) {
        this.cLimit = cLimit;
    }

    public float getrPrice() {
        return rPrice;
    }

    public void setrPrice(float rPrice) {
        this.rPrice = rPrice;
    }

    public float getmPrice() {
        return mPrice;
    }

    public void setmPrice(float mPrice) {
        this.mPrice = mPrice;
    }

    public int getUser(String sesName) throws SQLException {

        //String UId =null;
        String ses = sesName;
        int rtn;
        String findUser = "select AccountNO, CreditLimit, CVV_No,Credit_card_number_encrypt, UserId  from User_Account where UserId=?";
        pstm = gtcon.connect().prepareStatement(findUser);
        pstm.setString(1, ses);
        //  pstm.executeQuery();
        rs = pstm.executeQuery();
        //
        //String UId;
        while (rs.next()) {
            // acctNo = rs.getInt(1);
            this.setcLimit(rs.getFloat("CreditLimit"));
            this.setAcctNo(rs.getInt("AccountNO"));
            this.setCvv(rs.getInt("CVV_No"));
            this.setCCNo(rs.getString("Credit_card_number_encrypt"));
            this.setUd(rs.getString("UserId"));
        }
        String usr = this.getUd();
        if (ses.equals(usr) && usr != null) {
            return 1;

        } else {
            return -1;
        }

    }

    //call this if getuser check is a success IE has an account
    public boolean Bidcheck(float amount) {
        // this.bidamt.setAmount(amount);
        float amnt = amount;//bidamt.getAmount();
        boolean price = false;

        //ckeck valid amount
        price = valBid(amnt); //System.out.println("bid is not valid");
        return price;
    }

    //gets credit limit,reserved price,and minpaid price
    public float cimt;

    public int getToCheck(float price, int itm) throws SQLException {
        int result = 0;
        //check if price should be inserted
        String selectjoins = "select  Reserved_Price, Minimum_Paid_Prcie  from Auction_Item where Ac_id =?";
        pstm = gtcon.connect().prepareStatement(selectjoins);
        pstm.setInt(1, itm);
        rs = pstm.executeQuery();
        while (rs.next()) {
            //this.setcLimit(rs.getFloat(3));
            this.setrPrice(rs.getFloat(1));
            this.setmPrice(rs.getFloat(2));
        }
        System.out.println("limit" + this.getcLimit());
        System.out.println("reserv" + this.getrPrice());
        System.out.println("min" + this.getmPrice());
        //cLimit=this.climt;
        //if min price <climit and climit is not zero and minprice is not zero( true)
        if ((this.getmPrice() < this.getcLimit()) && !(this.getcLimit() == -0.0) && !(this.getmPrice() == 0.0)
                && (this.getcLimit() > getrPrice())) {
            //if bid >rprice and bid >minprice andl also if price is within range of credit limit
            //also ckeck if price is > climit tell user pice is more than credit limit
            if ((price > this.getrPrice() || price > this.getmPrice()) && price <= this.getcLimit()) {

                result = 1;
            } else {
                result = 2;
            }
        } else {
            result = -1;
        }
        return result;
    }
//THIS IS LEFT FOR THE ADMIN
    public void deductCrdt(float bid, String ses) throws SQLException {
        String gtclmt = "select CreditLimit from User_Account where UserId=?";
        pstm = gtcon.connect().prepareStatement(gtclmt);
        pstm.setString(1, ses);
        rs = pstm.executeQuery();
        while (rs.next()) {
            this.setcLimit(rs.getFloat("CreditLimit"));
        }
        float newCreditLt = this.getcLimit() - bid;
        if ((newCreditLt > 0.0) && (newCreditLt != -0.0)) {
            this.setNewCreditLimit(newCreditLt);
            float mnLmt = this.getNewCreditLimit();
            //do conncections
            System.out.println("new credit to insert: " + mnLmt);
            String Ncrdt = "update User_Account set CreditLimit=? where User_Account.UserId=?";

            pstm = gtcon.connect().prepareStatement(Ncrdt);
            pstm.setFloat(1, mnLmt);
            pstm.setString(2, ses);
            //send
            pstm.executeUpdate();
            pstm.close();
        } else {
            System.out.println("ur bid ur account will be -ve");
        }
    }
    SearchVars Nlmt = new SearchVars();

    //insert new credit
    public int insertNewCredit(float mins, String sesN, int acct) throws SQLException {
        //Nlmt.setNewlimit(mins);
        float addLimit = mins;//Nlmt.getNewlimit();
        String gtclmt = "select CreditLimit from User_Account where UserId=?";
        pstm = gtcon.connect().prepareStatement(gtclmt);
        pstm.setString(1, sesN);
        rs = pstm.executeQuery();
        while (rs.next()) {
            this.setNewCreditLimit(rs.getFloat("CreditLimit"));
        }

        float prevclmit;
        prevclmit = this.getNewCreditLimit();
        // boolean isval= valBid(addLimit);
        //if(isval)
        float addLimits = prevclmit + addLimit;
        System.out.println(addLimit + "fist limit ");
        System.out.println(addLimits + "sum to insert");
        System.out.println(prevclmit + " last limit");
        //float newAddLmt= addLimit;
        //get current limit then add new one to it the update table column
        String Ncrdt = "update User_Account set CreditLimit=? where User_Account.UserId=? and AccountNO =?";

        pstm = gtcon.connect().prepareStatement(Ncrdt);
        pstm.setFloat(1, addLimits);
        pstm.setString(2, sesN);
        //pstm.setInt(3, this.getCvv());
        pstm.setInt(3, acct);
        pstm.executeUpdate();
        pstm.close();

        return 1;
    }

    public void insertBid(Float price, String ses, String itemname) throws SQLException {
        
        biddata bd = new biddata();
        bd.setAmount(price);
        Float Prices = bd.getAmount();
        String insBid = "insert into Bid(Bid_Status,Bid_Price,UserId,Auction_id,Encrypt_key) values(?,?,?,?,?)";
        pstm = gtcon.connect().prepareStatement(insBid);
        pstm.setFloat(1, 200);
        pstm.setFloat(2, Prices);
        pstm.setString(3, ses);
        pstm.setString(4, itemname);
        pstm.setString(5, "0949irjkfnvkjfvfd");
        //insert now
        pstm.executeUpdate();

        gtcon.connect().close();
    }

    private boolean valBid(float price) {
        boolean val = false;
        //make sure bid price is valid
        if (price < 0.0 || Float.isNaN(price) || Float.isInfinite(price)) {
            val = false;
        } else {
            val = true;
        }

        return val;
    }
    
}
