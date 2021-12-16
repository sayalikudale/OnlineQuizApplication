/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.Servlets;

import com.java.Bean.UserBeanLocal;
import com.java.Entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sayali
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})

public class RegisterServlet extends HttpServlet {

    private static final String QUIZ_APP_BEAN_SESION_KEY = "LoginQuizApp";

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

        // Obtain the EJB from the HTTP session
        UserBeanLocal UserBeanBean
                = (UserBeanLocal) request.getSession()
                        .getAttribute(QUIZ_APP_BEAN_SESION_KEY);

        if (UserBeanBean == null) {
            // EJB is not present in the HTTP session
            // so let's fetch a new one from the container
            try {
                InitialContext ic = new InitialContext();
                UserBeanBean = (UserBeanLocal) ic.lookup("java:comp/env/ejb/UserBean");

                // put EJB in HTTP session for future servlet calls
                request.getSession().setAttribute(QUIZ_APP_BEAN_SESION_KEY, UserBeanBean);

            } catch (NamingException e) {
                throw new ServletException(e);
            }
        }

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String admin = request.getParameter("admin");


        if (userName != null && password != null) {
            User user = new User(userName, password );
            
            if(admin.equals("yes"))
                user.setIsAdmin(Boolean.TRUE);
            else
                user.setIsAdmin(Boolean.FALSE);


            Boolean success = UserBeanBean.register(user);
            
            request.getRequestDispatcher("Login.jsp").include(request, response);


            // getServletContext().getRequestDispatcher("/LoginResponse.jsp").forward(request, response);
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
