

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="getClass" class="DAO.ClassDAO" />
<jsp:useBean id="getTeacher" class="DAO.TeacherDAO" />
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
            <h2>List of assignments </h2>
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
            <div class="alert alert-warning mt-3">
    <strong>Important:</strong> Please ensure to keep a copy of your submission on your device. ZIP files submitted will be stored and cannot be accessed or deleted until the end of the course.
</div>

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
                        <c:set value="${getClass.getClass(assign.classID)}" var="classAss" />
                        <c:set value="${getTeacher.getTeacher(classAss.teacherID)}" var="teacher" />
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${assign.title}</td>
                            <td><span class="badge badge-warning">${assign.dueDate}</span></td>
                            <td><span class="badge badge-default">${classAss.className}</span></td>
                            <td><span class="badge badge-default">${teacher.teacherName}</span></td>
                            <td>
                                <span class="badge badge-success">
                                    <c:choose>
                                        <c:when test="${assign.type == 3}">Caption</c:when>
                                        <c:when test="${assign.type == 2}">Assignment</c:when>
                                        <c:otherwise>Lab</c:otherwise>
                                    </c:choose>
                                </span>
                            </td>
                            <td>Active</td>
                            <td>
                                <a href="StudentClassController?action=submit&classID=${assign.classID}" class="btn btn-info">Submissions</a>

                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#descriptionModal${status.index}">
                                    View details
                                </button>
                            </td>
                        </tr>
                    <div class="modal fade" id="descriptionModal${status.index}" tabindex="-1" role="dialog" aria-labelledby="descriptionModalLabel${status.index}" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="descriptionModalLabel${status.index}">Description Assignment</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    ${assign.description}
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                </tbody>

            </table>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>

