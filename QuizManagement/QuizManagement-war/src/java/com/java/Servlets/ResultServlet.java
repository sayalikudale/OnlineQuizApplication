/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.Servlets;

import com.java.Bean.QuizBeanLocal;
import java.io.IOException;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sayali
 */
@WebServlet("/userPanel")
public class ResultServlet extends HttpServlet {

    private static final String QUIZ_APP_BEAN_SESION_KEY = "ResultApp";

    private static final long serialVersionUID = 1L;

    public ResultServlet() {
        super();
    }

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
        QuizBeanLocal quizBean
                = (QuizBeanLocal) request.getSession()
                        .getAttribute(QUIZ_APP_BEAN_SESION_KEY);

        if (quizBean == null) {
            // EJB is not present in the HTTP session
            // so let's fetch a new one from the container
            try {
                InitialContext ic = new InitialContext();
                quizBean = (QuizBeanLocal) ic.lookup("java:comp/env/ejb/QuizBean");

                // put EJB in HTTP session for future servlet calls
                request.getSession().setAttribute(QUIZ_APP_BEAN_SESION_KEY, quizBean);

            } catch (NamingException e) {
                throw new ServletException(e);
            }
        }
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("userName");
        session.setAttribute("userName", username);

        Map<String, Integer> tests = quizBean.getAllResultsForUser(username);

        request.setAttribute("userResults", tests);

        RequestDispatcher rd = request.getRequestDispatcher("userPanel.jsp");
        rd.forward(request, response);

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
