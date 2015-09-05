
package Packages;


public class SearchVars {
     private String category;
     private float price;
     private String name; 
     //getters for new credit limit
     private float newlimit;
     private int accnt;
     private String tranName;
     private float transprc;
    public String getTranName() {
        return tranName;
    }

    public void setTranName(String tranName) {
        this.tranName = tranName;
    }
    
     //for

    public float getTransprc() {
        return transprc;
    }

    public void setTransprc(float transprc) {
        this.transprc = transprc;
    }

    public int getAccnt() {
        return accnt;
    }

    public void setAccnt(int accnt) {
        this.accnt = accnt;
    }
  
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //getters n setters for credit limits
    public float getNewlimit() {
        return newlimit;
    }

    public void setNewlimit(float newlimit) {
        this.newlimit = newlimit;
    }
}
