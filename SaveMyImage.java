package packages;

import java.sql.*;
import java.io.*;
 
public class SaveMyImage 
{
        public static void main(String[] args) 
        {
             String url = "jdbc:sqlserver://localhost:1994;user=sakwe;password=Sakwe@1;databaseName=homework";
                DB db = new DB();
                Connection conn=db.dbConnect(url);
              //  db.insertImage(conn,"D://one.jpg");
                db.getImageData(conn);
        }
 
}
class DB
{
        public DB() {}
 
        public Connection dbConnect(String db_connect_string)
        {
                try
                {
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        Connection conn = DriverManager.getConnection(db_connect_string);
 
                        System.out.println("connected");
                        return conn;
                         
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        return null;
                }
        }
 
        public void insertImage(Connection conn,String img)
        {
                int len;
                String query;
                PreparedStatement pstmt;
                 
                try
                {
                        File file = new File(img);
                        FileInputStream fis = new FileInputStream(file);
                        len = (int)file.length();
 
                        query = ("insert into images VALUES(?,?,?)");
                        pstmt = conn.prepareStatement(query);
                        pstmt.setString(1,file.getName());
                        pstmt.setInt(2, len);
   
                        // Method used to insert a stream of bytes
                        pstmt.setBinaryStream(3, fis, len); 
                        pstmt.executeUpdate();
 
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
        }
 
        public void getImageData(Connection conn)
        {
                 
                 byte[] fileBytes;
                 String query;
                 try
                 {
                         query = "select image from images";
                         Statement state = conn.createStatement();
                         ResultSet rs = state.executeQuery(query);
                         if (rs.next())
                        {
                                  fileBytes = rs.getBytes(1);
                                  OutputStream targetFile=  
                                  new FileOutputStream(
                                       "D://news.JPG");
 
                                  targetFile.write(fileBytes);
                                  targetFile.close();
                        }        
                         
                 }
                 catch (Exception e)
                 {
                         e.printStackTrace();
                 }
        }
};