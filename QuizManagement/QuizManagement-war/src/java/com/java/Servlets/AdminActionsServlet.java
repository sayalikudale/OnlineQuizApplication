/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.Servlets;

import com.java.Bean.AdminBeanLocal;
import com.java.Entity.Question;
import com.java.Entity.Quiz;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
@WebServlet(urlPatterns = {"/AdminAction", "/StudentResults"})
public class AdminActionsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private String selectedExam;
    private static final String QUIZ_APP_BEAN_SESION_KEY = "AdminActionApp";

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
        AdminBeanLocal adminBean
                = (AdminBeanLocal) request.getSession()
                        .getAttribute(QUIZ_APP_BEAN_SESION_KEY);

        if (adminBean == null) {
            // EJB is not present in the HTTP session
            // so let's fetch a new one from the container
            try {
                InitialContext ic = new InitialContext();
                adminBean = (AdminBeanLocal) ic.lookup("java:comp/env/ejb/AdminBean");

                // put EJB in HTTP session for future servlet calls
                request.getSession().setAttribute(QUIZ_APP_BEAN_SESION_KEY, adminBean);

            } catch (NamingException e) {
                throw new ServletException(e);
            }
        }

        String applicationContextPath = request.getContextPath();
        HttpSession session = request.getSession();

        if (request.getRequestURI().equals(
                applicationContextPath + "/AdminAction")) {

            boolean finish = false;
            try {
                if (session.getAttribute("currentExam") == null) {
                    session = request.getSession();
                    selectedExam = (String) request.getSession().getAttribute("exam");
                    Quiz quiz = new Quiz(selectedExam);
                    session.setAttribute("currentExam", quiz);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            //create quiz option and add questions to it 
            //question is created using request data
            Quiz quiz = (Quiz) request.getSession().getAttribute("currentExam");

            String action = request.getParameter("adminAction");

            String title = request.getParameter("question");
            String option1 = request.getParameter("option1");
            String option2 = request.getParameter("option2");
            String option3 = request.getParameter("option3");
            String option4 = request.getParameter("option4");
            String correct = request.getParameter("correct");

            Question q = new Question(title, new String[]{option1, option2, option3, option4}, Integer.parseInt(correct) - 1);

            quiz.addQuestion(q);

            if ("Add and Finish".equals(action)) {
                finish = true;

                //add api to save the quiz
                Boolean status = adminBean.addQuizData(quiz);

                request.getSession().setAttribute("currentExam", null);
                request.getRequestDispatcher("adminHome.jsp").forward(request, response);

            }

            if (finish != true) {
                request.getRequestDispatcher("AddQuestion.jsp").forward(request, response);
            }

        } else if (request.getRequestURI().equals(
                applicationContextPath + "/StudentResults")) {

            //get the all student result
            Map<String, Map<String, Integer>> studentResult = adminBean.getStudentResults();
            request.setAttribute("studentResults", studentResult);

            request.getRequestDispatcher("adminPanel.jsp").forward(request, response);

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
