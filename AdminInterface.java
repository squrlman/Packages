
package Packages;

import java.sql.SQLException;


public interface AdminInterface {
    //to get percentage to be deducted from final sale of item

    /**
     *create the admin 
     * @param setters
     * @return 
     * @throws SQLException
     */
      int createAdmin( AdminSetters setters)throws SQLException;

    /**
     *logs in admin and redirects them to main board 
     * @param setters
     * @return
     * @throws java.sql.SQLException
     */
    boolean loginAdmin(AdminSetters setters)throws SQLException ;
    //calculates commision rate and diducts percentage from final bid price
      void CalcPercentage();     
      void doCalc(float bid);
      
      //approves an auction
      void ApproveAuction(String AuctionId) throws SQLException;
      
      //deletes user from web application
      void DeleteUser();
      
      //approves bid and sends mail 
      void ApproveBid();
          
}
