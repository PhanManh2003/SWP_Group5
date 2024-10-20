/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Student;

import DAO.AnswerDAO;
import DAO.GradeDAO;
import DAO.QuestionDAO;
import DAO.StudentDAO;
import Model.Answer;
import Model.Grade;
import Model.Question;
import Model.Student;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author HP
 */
@WebServlet(name = "StudentQuestionController", urlPatterns = {"/student-question"})
public class StudentQuestionController extends HttpServlet {

    private QuestionDAO questionDAO;
    private int studentId = 0;

    @Override
    public void init() {
        questionDAO = new QuestionDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        studentId = session.getAttribute("studentID") == null ? -1 : (int) session.getAttribute("studentID");
        StudentDAO studentDao = new StudentDAO();
        Student studentLogin = studentDao.getStudent(studentId);
        if (session.getAttribute("studentID") == null || studentLogin == null) {
            response.sendRedirect("LoginController?error=Your account can not login here");
            return;
        }
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "answer":
                this.showAnswer(request, response);
                break;
            default:
                listQuestions(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        studentId = session.getAttribute("studentID") == null ? -1 : (int) session.getAttribute("studentID");
        StudentDAO studentDao = new StudentDAO();
        Student studentLogin = studentDao.getStudent(studentId);
        if (session.getAttribute("studentID") == null || studentLogin == null) {
            response.sendRedirect("LoginController?error=Your account can not login here");
            return;
        }
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "submit-answer":
                submitAnswer(request, response);
                break;
            case "grade-answer":
                gradeAnswer(request, response);
                break;
            case "edit-grade":
                this.gradeEditAnswer(request, response);
                break;
            default:
                listQuestions(request, response);
                break;
        }
    }

    private void submitAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int questionId = Integer.parseInt(request.getParameter("questionId"));
            String answerText = request.getParameter("answer");
            String classId = request.getParameter("classId");
            Timestamp date = new Timestamp(System.currentTimeMillis());
            Answer answer = new Answer();
            answer.setAnswer(answerText);
            answer.setQuestionId(questionId);
            answer.setStudentId(studentId);
            answer.setDate(date);
            AnswerDAO answerDAO = new AnswerDAO();
            answerDAO.addAnswer(answer);
            response.sendRedirect("student-question?action=answer&id="+questionId+"&classID=" + classId);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            response.sendRedirect("404");
        }
    }
    
    
    private void gradeAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int answerId = Integer.parseInt(request.getParameter("answerId"));
            int questionId = Integer.parseInt(request.getParameter("questionId"));
            int gradeValue = Integer.parseInt(request.getParameter("grade"));
            String classId = request.getParameter("classId");
            Timestamp date = new Timestamp(System.currentTimeMillis());
            Grade grade = new Grade();
            grade.setGrade(gradeValue);
            grade.setStudentId(studentId);
            grade.setAnswerId(answerId);
            grade.setDate(date);
            GradeDAO gradeDAO = new GradeDAO();
            gradeDAO.addGrade(grade);
            response.sendRedirect("student-question?action=answer&id="+questionId+"&classID=" + classId);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            response.sendRedirect("404");
        }
    }
    
    private void gradeEditAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int gradeId = Integer.parseInt(request.getParameter("gradeId"));
            int answerId = Integer.parseInt(request.getParameter("answerId"));
            int questionId = Integer.parseInt(request.getParameter("questionId"));
            int gradeValue = Integer.parseInt(request.getParameter("grade"));
            String classId = request.getParameter("classId");
            Timestamp date = new Timestamp(System.currentTimeMillis());
            Grade grade = new Grade();
            grade.setId(gradeId);
            grade.setGrade(gradeValue);
            grade.setStudentId(studentId);
            grade.setAnswerId(answerId);
            grade.setDate(date);
            GradeDAO gradeDAO = new GradeDAO();
            gradeDAO.updateGrade(grade);
            response.sendRedirect("student-question?action=answer&id="+questionId+"&classID=" + classId);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            response.sendRedirect("404");
        }
    }

    private void listQuestions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String classId = request.getParameter("classID");
        try {
            int classIdNumber = Integer.parseInt(classId);
            List<Question> listQuestions = questionDAO.getAllQuestionsByClassIdByStudent(classIdNumber);
            request.setAttribute("listQuestions", listQuestions);
            request.setAttribute("classId", classIdNumber);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./student/question/question-list.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            response.sendRedirect("404");
        }
    }

    private void showAnswer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String classId = request.getParameter("classID");
        try {
            int classIdNumber = Integer.parseInt(classId);
            int id = Integer.parseInt(request.getParameter("id"));
            AnswerDAO answerDao = new AnswerDAO();
            List<Answer> answers = answerDao.getAllAnswersByQuestionId(id);
            Question existingQuestion = questionDAO.getQuestion(id);
            request.setAttribute("answers", answers);
            request.setAttribute("classId", classIdNumber);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./student/question/answers.jsp");
            request.setAttribute("question", existingQuestion);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            response.sendRedirect("404");
        }
    }
}
