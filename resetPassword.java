
package Packages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class resetPassword {
    connection Con = new connection();
    Statement stm=null;
    PreparedStatement pstm=null;
    ResultSet rs=null;

    /**
     *
     * @param email
     * @param log
     * @return
     */
    public int rESET(String email,login log)
    {
        int rtn=0;
     
        try {
            String gtEM="select emailid from Users where emailid =?";
            pstm =Con.connect().prepareStatement(gtEM);
            pstm.setString(1,email );
            rs=pstm.executeQuery();
            while (rs.next())
            {
                log.setEmail(rs.getString("emailid"));
            }
            pstm.close();
            rs.close();
            Con.connect().close();
        } catch (SQLException ex) {
            System.out.println("Could not select!!");
        }
        String rMAIL=log.getEmail();
        String pass1=log.getPassword();
        String pass2=log.getPassword2();
        if(email.equals(rMAIL) && pass1.equals(pass2))
        {   
            try {
            //write update password here
            String rSET="update Users set passwrd =? where emailid=?";
            pstm=Con.connect().prepareStatement(rSET);
            pstm.setString(1, pass1);
            pstm.setString(2, rMAIL);
            pstm.executeUpdate();
            pstm.close();
            Con.connect().close();
            } catch (SQLException ex) {
                    System.out.println(ex);
            }
            rtn=1;
        }
        return rtn;
    }
    
}
