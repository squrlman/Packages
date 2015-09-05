
package Packages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Accounts {
    connection Con= new connection();
    login vars=new login();
    PreparedStatement pstm=null;
    ResultSet rs=null;
    
    public int iSNSERTacct(int cnntNO,float cLMT,int cvv,String uid,String crdnum) throws SQLException
    {
       String Query=" OPEN SYMMETRIC KEY symmetric1\n" +
                      "DECRYPTION BY CERTIFICATE sakwecert1;"
               + "INSERT INTO User_Account (AccountNO, CreditLimit,CVV_No ,UserId,Credit_card_number_encrypt) VALUES "
               + "(?,?,?,?, EncryptByKey( Key_GUID('symmetric1'), CONVERT(varchar,'"+crdnum+"') ) ); ";
       pstm= Con.connect().prepareStatement(Query);
       pstm.setInt(1,cnntNO);
       pstm.setFloat(2, cLMT);
       pstm.setInt(3, cvv);
       pstm.setString(4, uid.trim());
      
       
       pstm.executeUpdate();
       pstm.close();
       
       return 1;
    }
     public int iNewUser(String usr,String nme,String eml,String add,String psw,String cont,String p2) throws SQLException
    {
       
        if( p2.equals(psw)){
       String Query="INSERT INTO  Users (UserId,name,emailid,addresss,passwrd,contact) values(?,?,?,?,?,?)";
       pstm= Con.connect().prepareStatement(Query);
       pstm.setString(1,usr.trim());
       pstm.setString(2, nme.trim());
       pstm.setString(3, eml);
       pstm.setString(4,add);
       pstm.setString(5, psw);
       pstm.setString(6, cont);
       
       pstm.executeUpdate();
       pstm.close();
       
       return 1;
        }else{
            return  -1;
        }
    }
}

