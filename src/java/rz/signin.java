/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rz;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wrz19
 */
@WebServlet(name = "signin", urlPatterns = {"/signin"})
public class signin extends HttpServlet {
    private Connection conn;
    private Statement st;
    private ResultSet rs=null;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        HttpSession session = request.getSession(true);
        
        PrintWriter out = response.getWriter();
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String connectionURL = "jdbc:derby://localhost:1527/user";
            conn = DriverManager.getConnection(connectionURL, "root", "root");
            st = conn.createStatement();
            String q1 = new String("SELECT * FROM USERS WHERE email='"+email+"' AND PWD='"+password+"'");
            rs =  st.executeQuery(q1);
            if (!rs.next()){
                out.println("<html>");
                    out.println("<head>");
                        out.println("<title>Login failed</title>");
                    out.println("</head>");
                    out.println("<body>");
                        out.println("<h1>Username and password do not match</h1>");
                        out.println("<a href=\"javascript:history.back()\">Back</a>");
                    out.println("</body>");
                out.println("</html>");
            }
            else{
                UserBean ub = new UserBean();
                String username = rs.getString("username");
                int userid = rs.getInt("id");
                ub.setusername(username);
                ub.setuserid(userid);
                session.setAttribute("ub", ub);
                response.sendRedirect(request.getContextPath());
            }
        }
        catch (SQLException se)
        {
            se.printStackTrace();  
        } catch (ClassNotFoundException ex) {
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
