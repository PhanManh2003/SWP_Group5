<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-lg-3">
    <div class="dashboard__sidebar-wrap">
        <div class="dashboard__sidebar-title mb-20">
            <h6 class="title">Welcome, ${teacherLoginHeader.name}</h6>
        </div>
        <nav class="dashboard__sidebar-menu">
            <ul class="list-wrap">
                <li>
                    <a href="${pageContext.request.contextPath}/TeacherClassController">
                        <i class="fas fa-home"></i>
                        Dashboard
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/TeacherAssignmentController">
                        <i class="skillgro-avatar"></i>
                        Assignment
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/TeacherClassController">
                        <i class="skillgro-avatar"></i>
                        Classes
                    </a>
                </li>
            </ul>
        </nav>
        <div class="dashboard__sidebar-title mt-30 mb-20">
            <h6 class="title">User</h6>
        </div>
        <nav class="dashboard__sidebar-menu">
            <ul class="list-wrap">
                <li>
                    <a href="${pageContext.request.contextPath}/TeacherLogoutController"onclick="return confirm('Are you sure to logout?')">
                        <i class="skillgro-logout"></i>
                        Logout
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</div>