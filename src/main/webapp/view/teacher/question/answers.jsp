<%-- 
    Document   : answers
    Created on : Jul 17, 2024, 9:00:46 PM
    Author     : HP
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../../commonTeacher/head.jsp"></jsp:include>
        <div class="container">
            <h2>Answer Details</h2>

            <div class="card mt-4">
                <div class="card-body">
                    <h3>Question:</h3>
                    <p>ID: ${question.id}</p>
                    <p>Question: ${question.question}</p>
                    <p>Status: ${question.status == 1 ? 'Active' : 'Inactive'}</p>
                    <p>Slot: ${question.slot}</p>
                    <p>Class: ${question.classInfo.className}</p>
                </div>
            </div>
            <h3 class="mt-4">Answers:</h3>
            <c:forEach var="answer" items="${answers}">
                <c:set var="totalGrade" value="${0}" />
                <c:forEach var="gradeA" items="${answer.grade}">
                    <c:set var="totalGrade" value="${totalGrade + gradeA.grade}" />
                </c:forEach>
                <div class="card mt-3">
                    <div class="card-body">
                        <h3 class="card-text"><small>Answer by </small>${answer.student.name}</h3>
                        <p class="card-text">Answer: ${answer.answer}</p>
                        <p class="card-text">Date: ${answer.date}</p>
                        <p class="card-text">Total grade <span class="badge badge-warning">${totalGrade} star</span></p>
                        <h6>Grades:</h6>
                        <c:forEach var="gradeA" items="${answer.grade}">
                            <div class="card mb-2">
                                <div class="card-body">
                                    <p class="card-text">Grade by: <span class="badge badge-secondary">Incognito</span></p>
                                    <p class="card-text">Grade: ${gradeA.grade} star</p>
                                    <p class="card-text">Date: ${gradeA.date}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
            <a href="teacher-question?action=list&classID=${classId}" class="btn btn-secondary mt-4">Back to Questions List</a>
        </div>
      <%@include file="../../commonTeacher/footer.jsp" %>
