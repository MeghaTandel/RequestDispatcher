package com.gecp.servlets;

import com.gecp.beans.LoginBean;
import com.gecp.helper.LoginHelper;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;

public class WelcomeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int i = 1;

        out.println("<html><head><title>Admin Panel</title></head><body>");
        out.println("<center>");
        out.println("<h1>User List</h1>");
        out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Password</th><th>Age</th><th>Actions</th></tr>");

        LoginHelper lh = new LoginHelper();
        ArrayList<LoginBean> allUsers = lh.fetchAllUsers();

        for (Iterator<LoginBean> iterator = allUsers.iterator(); iterator.hasNext();) {
            LoginBean lb = iterator.next();

            out.println("<tr>");
            out.println("<td>" + lb.getId() + "</td>");
            out.println("<td>" + lb.getUserName() + "</td>");
            out.println("<td>" + lb.getPassword() + "</td>");
            out.println("<td>" + lb.getAge() + "</td>");

            out.println("<td><a href='edit?id=" + lb.getId() + "'>Edit</a> | <a href='delete?id=" + lb.getId() + "' onclick=\"return confirm('Are you sure?')\">Delete</a></td>");
            out.println("</tr>");

        }

        out.println("</table>");
        out.println("<center>");
        out.println("</body></html>");

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
