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
public class Tester {
    public static void main(String[] args) throws SQLException {
        /*
        GetValuse getValuse = new GetValuse();
        getValuse.setItem("hello");
        System.out.println("The value is "+ getValuse.getItem());
                */
       // Bidcheck ck=new Bidcheck();
      //  ck.BidWinner("okfdlskf", "claris");
       // System.out.println(price);
        
        Admin com= new Admin();
       com.CalcPercentage();
        com.doCalc();
                
    }
    
}
