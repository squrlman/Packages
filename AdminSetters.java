package Packages;

public class AdminSetters {

    private String name;
    private String password;
    private String email;
    private String number;
    private int account;
    private int cvv;
    private String card_no;
    //userd to do the commision calculations
    private String AuctionID;
    private int per;
    private float amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getAuctionID() {
        return AuctionID;
    }

    public void setAuctionID(String AuctionID) {
        this.AuctionID = AuctionID;
    }

    public int getPer() {
        return per;
    }

    public void setPer(int per) {
        this.per = per;
    }
}
