
package Packages;

import java.sql.SQLException;


public class dateshere {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Admin gtdates=new Admin();
       float win= gtdates.BidWinner("kilo","claris");
        System.out.println("winner's price is "+win);
    }
    
}
