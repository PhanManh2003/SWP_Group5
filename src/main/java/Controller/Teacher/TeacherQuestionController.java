/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Teacher;

import DAO.AnswerDAO;
import DAO.QuestionDAO;
import Model.Answer;
import Model.Question;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author HP
 */
@WebServlet(name = "TeacherQuestionController", urlPatterns = {"/teacher-question"})
public class TeacherQuestionController extends HttpServlet {

    private QuestionDAO questionDAO;

    @Override
    public void init() {
        questionDAO = new QuestionDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteQuestion(request, response);
                break;
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
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "insert":
                insertQuestion(request, response);
                break;
            case "update":
                updateQuestion(request, response);
                break;
            default:
                listQuestions(request, response);
                break;
        }
    }

    private void listQuestions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String classId = request.getParameter("classID");
        try {
            int classIdNumber = Integer.parseInt(classId);
            List<Question> listQuestions = questionDAO.getAllQuestionsByClassId(classIdNumber);
            request.setAttribute("listQuestions", listQuestions);
            request.setAttribute("classId", classIdNumber);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./view/teacher/question/question-list.jsp");
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("./view/teacher/question/answers.jsp");
            request.setAttribute("question", existingQuestion);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            response.sendRedirect("404");
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String classId = request.getParameter("classID");
        try {
            int classIdNumber = Integer.parseInt(classId);
            request.setAttribute("classId", classIdNumber);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./view/teacher/question/question-form.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            response.sendRedirect("404");
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String classId = request.getParameter("classID");
        try {
            int classIdNumber = Integer.parseInt(classId);
            int id = Integer.parseInt(request.getParameter("id"));
            Question existingQuestion = questionDAO.getQuestion(id);
            request.setAttribute("classId", classIdNumber);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./view/teacher/question/question-form.jsp");
            request.setAttribute("question", existingQuestion);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            response.sendRedirect("404");
        }
    }

    private void insertQuestion(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String questionText = request.getParameter("question");
        int status = Integer.parseInt(request.getParameter("status"));
        int slot = Integer.parseInt(request.getParameter("slot"));
        int classId = Integer.parseInt(request.getParameter("classId"));
        Question newQuestion = new Question(0, questionText, status, slot, classId);
        questionDAO.addQuestion(newQuestion);
        response.sendRedirect("teacher-question?action=list&classID=" + classId);
    }

    private void updateQuestion(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String questionText = request.getParameter("question");
        int status = Integer.parseInt(request.getParameter("status"));
        int slot = Integer.parseInt(request.getParameter("slot"));
        int classId = Integer.parseInt(request.getParameter("classId"));
        Question question = new Question(id, questionText, status, slot, classId);
        questionDAO.updateQuestion(question);
        response.sendRedirect("teacher-question?action=list&classID=" + classId);
    }

    private void deleteQuestion(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        questionDAO.deleteQuestion(id);
        response.sendRedirect("teacher-question");
    }

}
