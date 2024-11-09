<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Answer Details</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .card-body {
                padding: 1.25rem;
            }
        </style>
    </head>
    <body>
         <%@ include file="../component/header.jsp" %>
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
            <hr>
            <h3 class="mt-4">Submit Your Answer:</h3>
            <form action="student-question" method="post">
                <input type="hidden" value="submit-answer" name="action" />
                <input type="hidden" value="${classId}" name="classId" />
                <input type="hidden" name="questionId" value="${question.id}">
                <div class="form-group">
                    <label for="answer">Your Answer:</label>
                    <textarea class="form-control" id="answer" name="answer" rows="3"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Submit Answer</button>
            </form>
            <hr>
            <h3 class="mt-4">Answers:</h3>
            <c:forEach var="answer" items="${answers}">
                <div class="card mt-3">
                    <div class="card-body">
                        <h3 class="card-text"><small>Answer by </small> ${answer.student.name}</h3>
                        <p class="card-text">Answer: ${answer.answer}</p>
                        <p class="card-text">Date: ${answer.date}</p>
                        <c:set var="graded" value="false" />
                        <c:set var="gradedCurrent" value="0" />
                        <c:forEach var="grade" items="${answer.grade}">
                            <c:set var="totalGrade" value="${totalGrade + grade.grade}" />
                            <c:if test="${sessionScope.studentID == grade.studentId}">
                                <c:set var="graded" value="true" />
                                <c:set var="gradedCurrent" value="${grade.grade}" />
                                <c:set var="gradeId" value="${grade.id}" />
                            </c:if>
                        </c:forEach>
                        <form action="student-question" method="post">
                            <input type="hidden" value="${graded == true ? "edit-grade" : "grade-answer"}" name="action" />
                            <c:if test="${graded == true}">
                                <input type="hidden" value="${gradeId}" name="gradeId" />
                            </c:if>
                            <input type="hidden" value="${classId}" name="classId" />
                            <input type="hidden" value="${question.id}" name="questionId" />
                            <input type="hidden" name="answerId" value="${answer.id}">
                            <div class="form-group">
                                <label for="grade">${graded == true ? "Edit " : ""}Grade:</label>
                                <select class="form-control" id="grade" name="grade">
                                    <option value="1" ${gradedCurrent == 1 ? "selected" : ""}>1 star</option>
                                    <option value="2" ${gradedCurrent == 2 ? "selected" : ""}>2 stars</option> 
                                    <option value="3" ${gradedCurrent == 3 ? "selected" : ""}>3 stars</option>
                                    <option value="4" ${gradedCurrent == 4 ? "selected" : ""}>4 stars</option>
                                    <option value="5" ${gradedCurrent == 5 ? "selected" : ""}>5 stars</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Grade</button>
                        </form>
                        <h6 class="mt-3">Grades:</h6>
                        <c:set var="totalGrade" value="0" />
                        <c:forEach var="grade" items="${answer.grade}">
                            <c:set var="totalGrade" value="${totalGrade + grade.grade}" />
                        </c:forEach>
                        <div class="card mb-2">
                            <div class="card-body">
                                <p class="card-text">Total grade: <span class="badge badge-warning">${totalGrade}</span></p>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <a href="student-question?action=list&classID=${classId}" class="btn btn-secondary mt-4">Back to Questions List</a>
        </div>
    </body>
</html>
