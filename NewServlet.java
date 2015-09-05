
package Packages;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize = 16177215)
public class NewServlet extends HttpServlet {
private static final long serialVersionUID = -1623656324694499109L;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
      
        String name = request.getParameter("Itemname");
        String mod = request.getParameter("model");
        String des = request.getParameter("Desc");
        float min = Float.valueOf(request.getParameter("MinPrice"));
        float res = Float.valueOf(request.getParameter("ResPrice"));
        String str = request.getParameter("start");
        int dur = Integer.valueOf(request.getParameter("Duration"));
        int com = Integer.valueOf(request.getParameter("ComRate"));
        String sts = request.getParameter("status");

        InputStream inputStream = null;

        // obtains the upload file part in this multipart request  
        Part filePart = request.getPart("image");
        if (filePart != null) {
            // debug messages  
            System.out.println("flprt" + filePart.getName());
            System.out.println("size" + filePart.getSize());
            System.out.println("cont" + filePart.getContentType());

            // obtains input stream of the upload file  
            inputStream = filePart.getInputStream();
        }
        Connection con = null;
        String message = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1994;user=sakwe;password=Sakwe@1;databaseName=ECommerceDB";
            con = DriverManager.getConnection(url);
            Statement st = con.createStatement();
            String sql = "insert into Auction_Item (Auction_id,ItemName,Item_Description,Minimum_Paid_Prcie,Reserved_Price,"
                    + "Auction_Start_Date,Auction_Duration,Commission_Rate,Auction_Status,UserId,picture) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, mod);
            statement.setString(3, des);
            statement.setFloat(4, min);
            statement.setFloat(5, res);
            statement.setString(6, str);
            statement.setInt(7, dur);
            statement.setInt(8, com);
            statement.setString(9, sts);
            statement.setString(10, "claris");
            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column  
                statement.setBlob(11, inputStream);
            }
            int row = statement.executeUpdate();
            if (row > 0) {
                message = "Image is uploaded successfully into the Database";
            }
        } catch (Exception ex) {
            Logger.getLogger(UplaodServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        // sets the message in request scope  
        request.setAttribute("Message", message);
        getServletContext().getRequestDispatcher("/submit.jsp").forward(request, response);
                }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
