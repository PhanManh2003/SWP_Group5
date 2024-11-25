<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="getTeacher" class="DAO.TeacherDAO" />
<jsp:useBean id="getClass" class="DAO.ClassDAO" />
<jsp:useBean id="getActiveAssignment" class="Utils.GetActiveAssignment" />
<jsp:include page="../../commonTeacher/head.jsp"></jsp:include>

    <div class="container mt-4">
        <h2 class="mb-4">List of Assignments</h2>

        <!-- Hiển thị thông báo lỗi hoặc thành công nếu có -->
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

    <!-- Bảng danh sách bài tập -->
    <table class="table table-striped table-hover" id="assignmentTable">
        <thead class="thead-dark">
            <tr>
                <th>No.</th>
                <th>Title</th>
                <th>Due Date</th>
                <th>Class</th>
                <th>Type</th>
                <th>Status</th>
                <th>Assign by</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="assign" items="${assignments}" varStatus="status">
                <c:set value="${getClass.getClass(assign.classID)}" var="classAss" />
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${assign.title}</td>

                    <!-- Due Date với badge -->
                    <td>
                        <span class="badge badge-warning">${assign.dueDate}</span>
                    </td>

                    <!-- Class Name với badge -->
                    <td>
                        <span class="badge badge-info">${classAss.className}</span>
                    </td>

                    <!-- Type với badge phân loại -->
                    <td>
                        <span class="badge badge-success">
                            <c:choose>
                                <c:when test="${assign.type == 3}">Caption</c:when>
                                <c:when test="${assign.type == 2}">Assignment</c:when>
                                <c:otherwise>Lab</c:otherwise>
                            </c:choose>
                        </span>
                    </td>

                    <!-- Status với badge màu sắc -->
                    <td>
                        <span class="badge ${assign.status == 1 ? 'badge-success' : 'badge-secondary'}">
                            ${assign.status == 1 ? 'Active' : 'In Active'}
                        </span>
                    </td>
                    <td>
                        <span class="badge badge-info">${assign.teacher != null ? assign.teacher.name : "NULL"}</span>
                    </td>
                    <!-- Nút hành động -->
                    <td>
                        <div class="btn-group" role="group">
                            <a href="TeacherAssignmentController?action=submission&classID=${assign.classID}&assignmentID=${assign.assignmentID}" class="btn btn-info btn-sm">
                                <i class="fas fa-file-alt"></i> Submissions
                            </a>
                            <a href="TeacherAssignmentController?action=edit&assignmentID=${assign.assignmentID}" class="btn btn-warning btn-sm">
                                <i class="fas fa-edit"></i> Edit
                            </a>
                            <button onclick="deleteAssignment(${assign.assignmentID}, ${assign.classID})" class="btn btn-danger btn-sm">
                                <i class="fas fa-trash"></i> Delete
                            </button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script>
    $(document).ready(function () {
        $('#assignmentTable').DataTable({
            "order": [[0, "asc"]],
            "paging": true,
            "searching": true
        });
    });

    const deleteAssignment = (assignmentId, classID) => {
        if (!confirm('Are you sure to remove this assignment? All submissions will be deleted as well.')) {
            return;
        }

        var xhr = new XMLHttpRequest();
        var url = "${pageContext.request.contextPath}/TeacherClassController?action=delete-assignment";
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var response = xhr.responseText;
                if (response === "success") {
                    alert("Deleted successfully.");
                    setTimeout(() => location.reload(), 200);
                } else {
                    alert("Failed to delete. Try again.");
                }
            } else if (xhr.readyState === 4) {
                alert("Failed to delete. Try again.");
            }
        };

        xhr.send("assignmentId=" + assignmentId + "&classId=" + classID);
    };
</script>

<%@include file="../../commonTeacher/footer.jsp" %>
