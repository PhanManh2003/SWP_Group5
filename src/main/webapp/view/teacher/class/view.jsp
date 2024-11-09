<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="getTeacher" class="DAO.TeacherDAO" />
<jsp:useBean id="getCourse" class="DAO.CourseDAO" />
<jsp:include page="../../commonTeacher/head.jsp"></jsp:include>

<div class="container">
    <!-- Thông tin lớp học -->
    <div class="card mt-4">
        <div class="card-header bg-primary text-white">
            <h4>Class Details: ${currentClass.className}</h4>
        </div>
        <div class="card-body">
            <form action="AddClassController" method="post">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="className">Class Name:</label>
                            <input type="text" class="form-control" value="${currentClass.className}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="status">Status:</label>
                            <input type="text" class="form-control" value="${currentClass.status == 1 ? "Active" : "Inactive"}" readonly>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="teacherID">Teacher:</label>
                            <c:set value="${getTeacher.getTeacher(currentClass.teacherID)}" var="teacher" />
                            <input type="text" class="form-control" value="${teacher.name}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="courseID">Course Code:</label>
                            <c:set value="${getCourse.getCourse(currentClass.courseID)}" var="course" />
                            <input type="text" class="form-control" value="${course.courseCode}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="courseID">Course Name:</label>
                            <input type="text" class="form-control" value="${course.courseName}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="semester">Semester:</label>
                            <input type="text" class="form-control" value="${currentClass.semesterName}" readonly>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="mt-4">
        <a href="TeacherClassController?action=assignment&classID=${currentClass.classID}" class="btn btn-info">
            <i class="fas fa-tasks"></i> View Assignment
        </a>
        <a href="TeacherAssignmentController?action=add&classID=${currentClass.classID}" class="btn btn-primary">
            <i class="fas fa-plus"></i> Add Assignment
        </a>
    </div>

    <div class="mt-5">
        <h3>List of Students in ${currentClass.className}</h3>
        <table class="table table-striped table-bordered" id="data-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Status</th>
                    <th>Join Status</th>
                    <th>Requested At</th>
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
                        <td>
                            <span class="badge ${student.status == 1 ? 'badge-success' : 'badge-secondary'}">
                                ${student.status == 1 ? 'Active' : 'Inactive'}
                            </span>
                        </td>
                        <td>${student.joinStatus == 1 ? 'Accepted' : 'Waiting'}</td>
                        <td>
                            <span class="badge badge-info">${student.joinAt}</span>
                        </td>
                        <td>
                            <c:if test="${student.joinStatus == 1}">
                                <button class="btn btn-danger btn-sm" onclick="RemoveStudent(${student.studentID}, ${currentClass.classID})">
                                    <i class="fas fa-user-times"></i> Remove
                                </button>
                            </c:if>
                            <c:if test="${student.joinStatus == 0}">
                                <button class="btn btn-success btn-sm" onclick="AcceptStudent(${student.studentID}, ${currentClass.classID})">
                                    <i class="fas fa-check"></i> Accept
                                </button>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- JavaScript Functions -->
<script>
    const AcceptStudent = (studentId, classId) => {
        var xhr = new XMLHttpRequest();
        var url = "${pageContext.request.contextPath}/TeacherClassController?action=accept";
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status == 200) {
                    var response = xhr.responseText;
                    if (response == "success") {
                        alert("Student accepted successfully.");
                        setTimeout(function () {
                            location.reload();
                        }, 200);
                    } else {
                        alert("Failed to accept student. Try again.");
                    }
                } else {
                    alert("Failed to accept student. Try again.");
                }
            }
        };
        var params = "studentId=" + studentId + "&classId=" + classId;
        xhr.send(params);
    };

    const RemoveStudent = (studentId, classId) => {
        if (confirm('Are you sure you want to remove this student?') == false) {
            return;
        }
        var xhr = new XMLHttpRequest();
        var url = "${pageContext.request.contextPath}/TeacherClassController?action=remove";
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status == 200) {
                    var response = xhr.responseText;
                    if (response == "success") {
                        alert("Student removed successfully.");
                        setTimeout(function () {
                            location.reload();
                        }, 200);
                    } else {
                        alert("Failed to remove student. Try again.");
                    }
                } else {
                    alert("Failed to remove student. Try again.");
                }
            }
        };
        var params = "studentId=" + studentId + "&classId=" + classId;
        xhr.send(params);
    };
</script>

<%@include file="../../commonTeacher/footer.jsp" %>
