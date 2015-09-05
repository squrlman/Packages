package Packages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ENOW
 */
@WebServlet(name = "CreateAdmin", urlPatterns = {"/CreateAdmin"})
public class CreateAdmin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //get params
        AdminSetters setters = new AdminSetters();
        setters.setName(request.getParameter("ID"));
        setters.setPassword(request.getParameter("password"));
        setters.setEmail(request.getParameter("email"));
        setters.setNumber(request.getParameter("number"));
        setters.setAccount(Integer.valueOf(request.getParameter("account_no")));
        setters.setCvv(Integer.valueOf(request.getParameter("cvv")));
        setters.setCard_no(request.getParameter("card"));

            //persist
        Admin create = new Admin();
        int persisted = create.createAdmin(setters);
        if (persisted < 0) {
            // request.getRequestDispatcher("/loginAdmin.jsp").include(request, response);
            RequestDispatcher rd = request.getRequestDispatcher("./loginAdmin.jsp");
            rd.include(request, response);
        } else {
//                request.getRequestDispatcher("Auction.jsp").include(request, response);
            out.println("i am having errors");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CreateAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CreateAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
