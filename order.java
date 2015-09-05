/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Packages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ENOW
 */
public class order {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        /*float[] nums= new float[7];
         int i;
         float temp=0;
         connection con1 = new connection();
         List<Float> list = new ArrayList<>();
       
         System.out.println("List "+ list.get(2).toString());
         */
        Accounts gtmthd = new Accounts();
        int inside = gtmthd.iNewUser("hooper", "hyai", "emailshere", "buea", "wee", "5485555", "wee");
        if (inside == 1) {
            System.out.println("yea logging in");
        } else {
            System.out.println("error man");
        }

        /*}
         float step, temp, tmp; int i;    
         GetAllData getAuc = new GetAllData();
         List<connection> list = getAuc.GetUIsers();
         List<connection> ls= new ArrayList<>();
         ls = getAuc.GetUIsers();
         // list.add();
         //Arrays.sort(ls);
         Iterator it = ls.iterator();
         float[] nums;
         for(connection con : ls)
         {
         int rng=ls.size();
         ls.toArray();
         //System.out.println(con.getMinPrice());
         float f= con.getMinPrice();
         nums=new float[rng];
      
         for(i=0; i <5; i++)
         {
         nums[i]= f;
         if(nums[i+1] > nums[i])
         {
         temp=nums[i];
         nums[i]=nums[i+1];
         nums[i+1]=nums[i+2];
         nums[i+2]=temp;
                 
         }
         System.out.println(nums[i]);
         }      
         }   
         */
    }

    public static List getoder(List list) {
        List<connection> lst;
        connection gtsrch = new connection();
        int lSize = list.size();
        Iterator ls = list.iterator();
        if (ls.hasNext()) {
            list.add(ls);
        }
        return list;
    }

    public static List<connection> getMin(connection connec) throws SQLException {
        connection con = new connection();
        List<connection> list = new ArrayList<>();
        Statement s;
        ResultSet rs;
        s = con.connect().createStatement();
        String Sql = "Select Bid_Price from Bid";
        rs = s.executeQuery(Sql);
        while (rs.next()) {

            connec.setMinPrice(rs.getFloat("Bid_Price"));

            list.add(connec);

        }
        return list;
    }
}
