/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Packages;

import java.sql.SQLException;

/**
 *
 * @author ENOW
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        biddata bdta = new biddata();
        //get bid amt frm user
        bdta.setAmount(200);

        Bidcheck mainbid = new Bidcheck();
        //get userid from session
        //  String sesname=(String)session.getAttribute("name");
        String sesname = "claris";
        float mybids = bdta.getAmount();
        int acctck = mainbid.getUser(sesname);

        //check if account is forun
        if (acctck == -1) {
            Accounts nw=new Accounts();
            nw.iSNSERTacct(mainbid.getAcctNo(), mainbid.getcLimit(),mainbid.getCvv(), sesname,"4585-8794-2112");
            System.out.println("user not here");
            /// response.sendRedirect("AcctCreate.jsp");
        } else {
            boolean nbid = mainbid.Bidcheck(mybids);
            //if number if valid ie greater than 0.0
            if (nbid == true) {
                int ready = mainbid.getToCheck(mybids, 1);

                  //if bid >rprice and bid >minprice andl also if price is within range of credit limit ie 1
                if (ready == 1 ) {
                    //then insert bid 
                    mainbid.insertBid(mybids, sesname,"kilo");
                    //write success msg
                   mainbid.deductCrdt(mybids, sesname);// CALL DEDUCT WHEN USER HAS BEEN SELCTED AS NEW OWNER OF PRODUCT
                    System.out.println("gud ur bid inserted");
                    System.out.println("gretar");
                  
                } else if (ready == 2) {
                    //if bid <rprice and bid <minprice andl also if price is not within range of credit limit
                    System.out.println("ur bid is less  insert crrt amount");
                } else if (ready == -1)
               //if min price >climit and climit is zero and minprice is  zero
                {
                    //response.sendRedirect("CreditLimit.jsp");
                   // SearchVars nw= new SearchVars();
                  // nw.setNewlimit(40000);
                   mainbid.insertNewCredit( 200,sesname,123);
                    System.out.println("ur credit is finished add here!!");
                }

            } else {
                //if amt <0.0
                System.out.println("amount is ,less it shouldnt be sero");
                // response.sendRedirect("SignupUser.jsp");
            }
        }
    }

}
