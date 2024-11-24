<%-- 
    Document   : header
    Created on : Jun 7, 2024, 7:43:51 PM
    Author     : HP
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="getTeacherHeader" class="DAO.TeacherDAO" />
<c:set value="${getTeacherHeader.getTeacher(sessionScope.teacherID)}" var="teacherLoginHeader"/>
<c:if test="${teacherLoginHeader == null || teacherLoginHeader.status == 0}">
    <c:redirect url="LoginController" />
</c:if>
<link rel="stylesheet" href="https://cdn.datatables.net/2.0.8/css/dataTables.bootstrap4.css"/>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.datatables.net/2.0.8/js/dataTables.js"></script>
<script src="https://cdn.datatables.net/2.0.8/js/dataTables.bootstrap5.js"></script>
<script>
    $(document).ready(function () {
        $("#data-table").DataTable();
    });
</script>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">
        <img src="https://upload.wikimedia.org/wikipedia/commons/6/68/Logo_FPT_Education.png" width="100px" alt="alt"/>
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="TeacherClassController">Classes</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="TeacherAssignmentController">Assignment</a>
            </li>
        </ul>
        <ul class="navbar-nav">
            <li class="nav-item">
                <span class="nav-link">Hello, ${teacherLoginHeader.name}</span>
            </li>
            <li class="nav-item">
                <a onclick="return confirm('Are you sure to logout?')" class="nav-link" href="TeacherLogoutController">Logout</a>
            </li>
        </ul>
    </div>
</nav>

