/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rz;

import java.util.*;
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
@WebServlet(name = "refreshMsg", urlPatterns = {"/refreshMsg"})
public class refreshMsg extends HttpServlet {

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
        response.setContentType("text;charset=UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        HttpSession session = request.getSession(true);
        
        PrintWriter out = response.getWriter();
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String connectionURL = "jdbc:derby://localhost:1527/user";
            conn = DriverManager.getConnection(connectionURL, "root", "root");
            st = conn.createStatement();
            String q1 = new String("SELECT MSGS.ID, USER_ID,USERNAME,CONTENT,MSG_TIME FROM ROOT.MSGS LEFT OUTER JOIN ROOT.USERS ON ROOT.MSGS.USER_ID=ROOT.USERS.ID ORDER BY MSG_TIME DESC FETCH FIRST 100 ROWS ONLY");
            rs =  st.executeQuery(q1);
            Vector msgs = new Vector();
            while(rs.next()){
                Map tmp = new HashMap();
                tmp.put("msg_id", rs.getInt("ID"));
                tmp.put("user_id",rs.getInt("USER_ID"));
                tmp.put("username",rs.getString("USERNAME"));
                tmp.put("msg",rs.getString("CONTENT"));
                tmp.put("msg_time", rs.getTimestamp("MSG_TIME"));
                msgs.add(new String("{\"msg_id\":"+rs.getInt("ID")
                        +",\"user_id\":"+rs.getInt("USER_ID")
                        +",\"username\":\""+rs.getString("USERNAME")
                        +"\",\"msg\":\""+rs.getString("CONTENT")
                        +"\",\"msg_time\":\""+rs.getTimestamp("MSG_TIME").toString()
                        +"\"}"
                ));
            }
            out.print(msgs.toString());
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
