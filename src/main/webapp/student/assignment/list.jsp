<%-- 
    Document   : view
    Created on : Jun 11, 2024, 12:10:20 PM
    Author     : HP
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="getTeacher" class="DAO.TeacherDAO" />
<jsp:useBean id="getActiveAssignment" class="Utils.GetActiveAssignment" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>List of assignment</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .container {
                margin-top: 50px;
            }
        </style>
    </head>
    <body>
        <%@ include file="../component/header.jsp" %>
        <div class="container">
            <h2>List of assignments for ${currentClass.className}</h2>
            <c:if test="${param.error != null}">
                <div class="alert alert-danger" role="alert">
                    ${param.error}
                </div>
            </c:if>
            <c:if test="${param.success != null}">
                <div class="alert alert-success" role="alert">
                    ${param.success}
                </div>
            </c:if>
            <table class="table table-bordered" id="data-table">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Due Date</th>
                        <th>Class</th>
                        <th>Assign by</th>
                        <th>Type </th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="assign" items="${assignments}" varStatus="status">
                        <c:set value="${getTeacher.getTeacher(currentClass.teacherID)}" var="teacher" />
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${assign.title}</td>
                            <td>
                                ${getActiveAssignment.getAssignmentBadge(assign.dueDate)}
                            </td>
                            <td>
                                <span class="badge badge-default">${currentClass.className}</span>
                            </td>
                            <td>
                                <span class="badge badge-default">${teacher.name}</span>
                            </td>
                            <td>
                                <span class="badge badge-success">
                                    <c:choose>
                                        <c:when test="${assign.type == 3}">
                                            Caption
                                        </c:when>
                                        <c:when test="${assign.type == 3}">
                                            Assignment
                                        </c:when>
                                        <c:otherwise>
                                            Lab
                                        </c:otherwise>
                                    </c:choose>
                                </span>
                            </td>
                            <td>Active</td>
                            <td>
                                <c:if test="${getActiveAssignment.getAssignmentIsDue(assign.dueDate) == false}">
                                    <a href="StudentAssignmentController?action=submit&classID=${assign.classID}&assignmentID=${assign.assignmentID}" class="btn btn-info">Submition</a>
                                </c:if>
                                <a href="StudentAssignmentController?action=view-submition&classID=${assign.classID}&assignmentID=${assign.assignmentID}" class="btn btn-primary">View submition</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>