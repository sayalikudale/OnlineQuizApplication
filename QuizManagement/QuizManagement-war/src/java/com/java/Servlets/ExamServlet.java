/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.Servlets;

import com.java.Bean.QuizBeanLocal;
import com.java.Bean.UserBeanLocal;
import com.java.Entity.Question;
import com.java.Entity.Quiz;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
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
@WebServlet("/exam")
public class ExamServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private String selectedExam;
    private static final String QUIZ_APP_BEAN_SESION_KEY = "QuizApp";

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ExamServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ExamServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        doPost(request, response);

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

        boolean finish = false;

        HttpSession session = request.getSession();
        try {
            if (session.getAttribute("currentExam") == null) {
                session = request.getSession();
                selectedExam = (String) request.getSession().getAttribute("exam");
                Quiz quiz = new Quiz(selectedExam);

                quizBean.getQuizData(quiz);

                session.setAttribute("currentExam", quiz);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");
                Date date = new Date();
                String started = dateFormat.format(date);
                session.setAttribute("started", started);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // save the user response in quiz object

        Quiz quiz = (Quiz) request.getSession().getAttribute("currentExam");
        int NOQ = quiz.getNumberofQuestions();

        request.setAttribute("NOQ", NOQ);

        if (quiz.currentQuestion == 0) {
            Question q = quiz.getQuestionList().get(quiz.currentQuestion);
            session.setAttribute("quest", q);
        }

        String action = request.getParameter("action");

        String radio = request.getParameter("answer");
        int selectedRadio = -1;
        quiz.selections.put(quiz.currentQuestion, selectedRadio);
        if ("1".equals(radio)) {
            selectedRadio = 1;
            quiz.selections.put(quiz.currentQuestion, selectedRadio);
        } else if ("2".equals(radio)) {
            selectedRadio = 2;
            quiz.selections.put(quiz.currentQuestion, selectedRadio);
        } else if ("3".equals(radio)) {
            selectedRadio = 3;
            quiz.selections.put(quiz.currentQuestion, selectedRadio);
        } else if ("4".equals(radio)) {
            selectedRadio = 4;
            quiz.selections.put(quiz.currentQuestion, selectedRadio);
        }

        if ("Next".equals(action)) {
            quiz.currentQuestion++;
            Question q = quiz.getQuestionList().get(quiz.currentQuestion);
            session.setAttribute("quest", q);
        } else if ("Previous".equals(action)) {
            System.out.println("You clicked Previous Button");
            quiz.currentQuestion--;
            Question q = quiz.questionList.get(quiz.currentQuestion);
            session.setAttribute("quest", q);
        } else if ("Save and Finish Exam".equals(action)) {
            finish = true;
            String user = (String) session.getAttribute("userName");
            int result = quizBean.getResult(user, quiz, selectedExam);
            request.setAttribute("result", result);
            request.getSession().setAttribute("currentExam", null);
            request.getRequestDispatcher("result.jsp").forward(request, response);

        }

        if (finish != true) {
            request.getRequestDispatcher("exam.jsp").forward(request, response);
        }

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
