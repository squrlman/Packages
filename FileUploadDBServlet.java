package packages;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
 
//@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class FileUploadDBServlet extends HttpServlet {
     
    // database connection settings
     String url = "jdbc:sqlserver://localhost:1994;user=sakwe;password=Sakwe@1;databaseName=ECommerceDB;";
     
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets values of text fields
        
        GetValuse getValuse = new GetValuse();
        getValuse.setItem(request.getParameter("Itemname"));
        getValuse.setModel(request.getParameter("model"));
        getValuse.setDesc(request.getParameter("Desc"));
        getValuse.setMinPrice(Float.valueOf(request.getParameter("MinPrice")));
        getValuse.setResprice(Float.valueOf(request.getParameter("ResPrice")));
        getValuse.setStart(request.getParameter("start"));
        getValuse.setDuration(Integer.valueOf(request.getParameter("Duration")));
        getValuse.setCommission(Integer.valueOf(request.getParameter("ComRate")));
        getValuse.setStatus(request.getParameter("status"));
         
        InputStream inputStream = null; // input stream of the upload file
         
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
         
        Connection conn = null; // connection to the database
        String message = null;  // message will be sent back to client
         
        try {
            // connects to the database
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url);
 
            // constructs SQL statement
            String sql =  "insert into Auction_Item (Auction_id,ItemName,Item_Description,"
                + "Minimum_Paid_Prcie,Reserved_Price,Auction_Start_Date,Auction_Duration,Commission_Rate,"
                + "Auction_Status,UserId,picture) values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
           
        statement.setString(1, getValuse.getItem());
       statement.setString(2, getValuse.getModel());
       statement.setString(3, getValuse.getDesc());
       statement.setFloat(4, getValuse.getMinPrice());
        statement.setFloat(5, getValuse.getResprice());
        statement.setString(6, getValuse.getStart());
        statement.setInt(7, getValuse.getDuration());
       statement.setInt(8, getValuse.getCommission());
        statement.setString(9, getValuse.getStatus());
       statement.setString(10, "claris");
             
            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                statement.setBlob(3, inputStream);
            }
 
            // sends the statement to the database server
            int row = statement.executeUpdate();
            if (row > 0) {
                message = "File uploaded and saved into database";
            }
        } catch (SQLException ex) {
            message = "ERROR: " + ex.getMessage();
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(FileUploadDBServlet.class.getName()).log(Level.SEVERE, null, ex);
         } finally {
            if (conn != null) {
                // closes the database connection
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            // sets the message in request scope
            request.setAttribute("Message", message);
             
            // forwards to the message page
            getServletContext().getRequestDispatcher("/Message.jsp").forward(request, response);
        }
    }
}