package Packages;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.Part;

public class Auctions {

    connection connection1 = new connection();
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Statement stm = null;

    public int cREATEiTEM(String ses) throws SQLException, Exception {
        //int len;
        int num = 0;
        String sql = "insert into Auction_Item (Auction_id,ItemName,Item_Description,"
                + "Minimum_Paid_Prcie,Reserved_Price,Auction_Start_Date,Auction_Duration,Commission_Rate,"
                + "Auction_Status,UserId,picture) values(?,?,?,?,?,?,?,?,?,?,?)";

        pstm = connection1.connect().prepareStatement(sql);
       GetValuse getValuse = new GetValuse();
        pstm.setString(1, getValuse.getItem());
        pstm.setString(2, getValuse.getModel());
        pstm.setString(3, getValuse.getDesc());
        pstm.setFloat(4, getValuse.getMinPrice());
        pstm.setFloat(5, getValuse.getResprice());
        pstm.setString(6, getValuse.getStart());
        pstm.setInt(7, getValuse.getDuration());
        pstm.setInt(8, getValuse.getCommission());
        pstm.setString(9, "pending");
        pstm.setString(10, ses);

        int send = pstm.executeUpdate();
        if (send > 1) {
            num = 1;
        } else {
            num = 0;
        }
        pstm.close();

        return num;
    }
}
