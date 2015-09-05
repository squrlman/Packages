package Packages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class login {

    private String name;
    private String username;
    private String password;
    private String password2;
    private String email;
    private String contact;
    private String address;
    //acount
    private int    CVV_No;
    private String CardNum;
    private float  Creditlimit;
    private int    AcctNum;
    //getter n setters
    public int getCVV_No() {
        return CVV_No;
    }

    public void setCVV_No(int CVV_No) {
        this.CVV_No = CVV_No;
    }

    public String getCardNum() {
        return CardNum;
    }

    public void setCardNum(String CardNum) {
        this.CardNum = CardNum;
    }

    public float getCreditlimit() {
        return Creditlimit;
    }

    public void setCreditlimit(float Creditlimit) {
        this.Creditlimit = Creditlimit;
    }

    public int getAcctNum() {
        return AcctNum;
    }

    public void setAcctNum(int AcctNum) {
        this.AcctNum = AcctNum;
    }
   
    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
