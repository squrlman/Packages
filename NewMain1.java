/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Packages;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ENOW
 */
public class NewMain1 {

    /**
     * @param inputArr
     * @param key
     * @return
     */
    public int binarySearch(int[] inputArr, int key) {

        int start = 0;
        int end = inputArr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (key == inputArr[mid]) {
                return mid;
            }
            if (key < inputArr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        /*
         MainSearch srch = new  MainSearch();
         List<connection> nwq=srch.GetDataFrmDB(10, "");
         for(connection gtsrch :nwq){
         System.out.println(gtsrch.getDesc());
         System.out.println(gtsrch.getItem());
         System.out.println(gtsrch.getStart());
         System.out.println(gtsrch.getMinPrice()+"\n");
         }
         */
        System.out.println("*****************************************");
        Views vtran = new Views();
        List<SearchVars> mtran = vtran.Vtansactions("mike");
        for (SearchVars gettran : mtran) {
            System.out.println(gettran.getTranName());
            System.out.println(gettran.getTransprc());
        }
        System.out.println("******************************");
        GetAllData getAuc = new GetAllData();
        List<connection> list = getAuc.GetUIsers();
        for (connection get : list) {
            System.out.println(get.getAcId());
            System.out.println(get.getMybidf());
            System.out.println(get.getCommission());
            System.out.println(get.getModel());
        }
        /*     
         NewMain1 mbs = new NewMain1();
         int[] arr = {2, 4, 6, 8, 10, 12, 14, 16};
         System.out.println("Key 14's position: "+mbs.binarySearch(arr, 14));
         int[] arr1 = {6,34,78,123,432,900};
         System.out.println("Key 432's position: "+mbs.binarySearch(arr1, 432));
    
         */
    }
}
