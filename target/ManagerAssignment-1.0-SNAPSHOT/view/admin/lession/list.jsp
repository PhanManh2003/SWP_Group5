<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../commonAdmin/head.jsp"></jsp:include>
    <div class="container">
        <h1 class="mt-5">Lession List</h1>
        <div class="mb-3">
            <label for="courseFilter" class="form-label">Filter by Course:</label>
            <select id="courseFilter" class="form-control" name="courseId">
                <option value="">All Courses</option>
            <c:forEach var="course" items="${courses}">
                <option value="${course.courseID}" ${param.courseId == course.courseID ? 'selected' : ''}>
                    ${course.courseName}
                </option>
            </c:forEach>
        </select>
    </div>

    <a href="lessions?action=new" class="btn btn-primary mb-3">Add New Lession</a>

    <table id="lessionTable" class="table table-striped">
        <thead>
            <tr>
                <th>Title</th>
                <th>Description</th>
                <th>Video</th>
                <th>Document</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="lession" items="${listLession}">
                <tr>
                    <td>${lession.title}</td>
                    <td>${lession.description}</td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty lession.videoUrl}">
                                <a href="${lession.videoUrl}" target="_blank">Watch Video</a>
                            </c:when>
                            <c:otherwise>N/A</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty lession.docsLink}">
                                <a href="${lession.docsLink}" target="_blank">View Document</a>
                            </c:when>
                            <c:otherwise>N/A</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <span class="badge badge-${lession.status == 'active' ? 'success' : 'secondary'}">
                            ${lession.status == 'active' ? 'Active' : 'Inactive'}
                        </span>
                    </td>
                    <td>
                        <a href="lessions?action=edit&id=${lession.lessionId}" class="btn btn-warning btn-sm">Edit</a>
                        <button class="btn btn-danger btn-sm" onclick="confirmDelete(${lession.lessionId})">Delete</button>
                    </td>
                </tr>
            </c:forEach>
       
        <script>
                            $(document).ready(function () {
                                $('#lessionTable').DataTable();

                                $('#courseFilter').on('change', function () {
                                    var selectedCourseId = $(this).val();
                                    window.location.href = 'lessions?action=list&courseId=' + selectedCourseId;
                                });
                            });

                            function confirmDelete(lessionId) {
                                if (confirm("Are you sure you want to delete this lession?")) {
                                    window.location.href = 'lessions?action=delete&id=' + lessionId;
                                }
                            }
        </script>
        </tbody>
    </table>
</div>
<%@include file="../../commonAdmin/footer.jsp" %>
