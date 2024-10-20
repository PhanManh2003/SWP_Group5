<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-lg-3">
    <div class="dashboard__sidebar-wrap">
        <div class="dashboard__sidebar-title mb-20">
            <h6 class="title">Welcome, ${sessionScope.adminLogin.name}</h6>
        </div>
        <nav class="dashboard__sidebar-menu">
            <ul class="list-wrap">
                <li>
                    <a href="${pageContext.request.contextPath}/student-dashboard.html">
                        <i class="fas fa-home"></i>
                        Dashboard
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/AdminController?action=update-profile">
                        <i class="skillgro-avatar"></i>
                        My Profile
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/ListSemestersController">
                        <i class="skillgro-avatar"></i>
                        Semesters
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/ListClassController">
                        <i class="skillgro-avatar"></i>
                        Class
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/ListCourseController">
                        <i class="skillgro-avatar"></i>
                        Courses
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/lessions">
                        <i class="skillgro-avatar"></i>
                        Lessions
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/AdminController">
                        <i class="skillgro-book"></i>
                        Manage Admin
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/ListTeachersController">
                        <i class="skillgro-book"></i>
                        Manage Teacher
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/ListStudentsController">
                        <i class="skillgro-book"></i>
                        Manage Student
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
                    <a href="${pageContext.request.contextPath}/student-setting.html">
                        <i class="skillgro-settings"></i>
                        Settings
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/index.html">
                        <i class="skillgro-logout"></i>
                        Logout
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</div>