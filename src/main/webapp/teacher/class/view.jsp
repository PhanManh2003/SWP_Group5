

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="getTeacher" class="DAO.TeacherDAO" />
<jsp:useBean id="getCourse" class="DAO.CourseDAO" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Class Management</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file="../component/header.jsp" %>
        <div class="container">
            <h2>View Classs</h2>
            <a href="TeacherClassController?action=assignment&classID=${currentClass.classID}" class="btn btn-info">View assignment</a>
            <a href="TeacherAssignmentController?action=add&classID=${currentClass.classID}" class="btn btn-primary">Add assignment</a>
            <a href="teacher-question?action=list&classID=${currentClass.classID}" class="btn btn-primary">Add question</a>
            <div class="mt-3">
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
            </div>
            <form action="AddClassController" method="post" class="mb-4">
                <div class="form-group">
                    <label for="className">Class Name:</label>
                    <input type="text" class="form-control" value="${currentClass.className}" readonly>
                </div>
                <div class="form-group">
                    <label for="status">Status:</label>
                    <input type="text" class="form-control" value="${currentClass.status == 1 ? "Active" : "In active"}" readonly>
                </div>
                <div class="form-group">
                    <label for="teacherID">Teacher:</label>
                    <c:set value="${getTeacher.getTeacher(currentClass.teacherID)}" var="teacher" />
                    <input type="text" class="form-control" value="${teacher.name}" readonly>
                </div>
                <div class="form-group">
                    <label for="courseID">Course code:</label>
                    <c:set value="${getCourse.getCourse(currentClass.courseID)}" var="course" />
                    <input type="text" class="form-control" value="${course.courseCode}" readonly>
                </div>
                <div class="form-group">
                    <label for="courseID">Course name: </label>
                    <c:set value="${getCourse.getCourse(currentClass.courseID)}" var="course" />
                    <input type="text" class="form-control" value="${course.courseName}" readonly>
                </div>
                <div class="form-group">
                    <label for="courseID">Semester: </label>
                    <input type="text" class="form-control" value="${currentClass.semesterName}" readonly>
                </div>
            </form>
        </div>
        <div class="container">
            <h2>List Students for ${currentClass.className}</h2>
            <table class="table table-striped" id="data-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Status account</th>
                        <th>Status join</th>
                        <th>Request at</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="student" items="${students}">
                        <tr>
                            <td>${student.studentID}</td>
                            <td>${student.name}</td>
                            <td>${student.email}</td>
                            <td>${student.phone}</td>
                            <td>${student.status == 1 ? "Active" : "Inactive"}</td>
                            <td>${student.joinStatus == 1 ? 'Accept' : 'Waiting'}</td>
                            <td>
                                <span class="badge badge-info">
                                    ${student.joinAt}
                                </span>
                            </td>
                            <td>
                                <c:if test="${student.joinStatus == 1}">
                                    <a onclick="RemoveStudent(${student.studentID}, ${currentClass.classID})" class="btn btn-danger">Remove</a>
                                </c:if>
                                <c:if test="${student.joinStatus == 0}">
                                    <a onclick="AcceptStudent(${student.studentID}, ${currentClass.classID})" class="btn btn-success">Accept</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <script>
            const AcceptStudent = (studentId, classId) => {
                var xhr = new XMLHttpRequest();
                var url = "http://localhost:8080/Edunext/TeacherClassController?action=accept";
                xhr.open("POST", url, true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4) {
                        if (xhr.status == 200) {
                            var response = xhr.responseText;
                            if (response == "success") {
                                alert("Accept successfully.");
                                setTimeout(function () {
                                    location.reload();
                                }, 200);
                            } else {
                                showError("Accept fail. Try again.");
                            }
                        } else {
                            showError("Accept fail. Try again.");
                        }
                    }
                };
                var params = "studentId=" + studentId + "&classId=" + classId;
                xhr.send(params);
            };

            const RemoveStudent = (studentId, classId) => {
                if (confirm('Are you sure to remove this student?') == false) {
                    return false;
                }
                var xhr = new XMLHttpRequest();
                var url = "http://localhost:8080/Edunext/TeacherClassController?action=remove";
                xhr.open("POST", url, true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4) {
                        if (xhr.status == 200) {
                            var response = xhr.responseText;
                            if (response == "success") {
                                alert("Remove successfully.");
                                setTimeout(function () {
                                    location.reload();
                                }, 200);
                            } else {
                                showError("Remove fail. Try again.");
                            }
                        } else {
                            showError("Remove fail. Try again.");
                        }
                    }
                };
                var params = "studentId=" + studentId + "&classId=" + classId;
                xhr.send(params);
            };
        </script>
    </body>
</html>
