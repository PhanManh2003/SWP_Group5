<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="getTeacher" class="DAO.TeacherDAO" />
<jsp:useBean id="getActiveAssignment" class="Utils.GetActiveAssignment" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>List of Assignments</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .container {
                margin-top: 50px;
            }
            .custom-modal {
                display: none;
                position: fixed;
                z-index: 1000;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                justify-content: center;
                align-items: center;
            }

            .modal-content {
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                width: 400px;
                max-width: 90%;
                text-align: center;
            }

            .close-modal {
                float: right;
                font-size: 24px;
                cursor: pointer;
                color: #aaa;
            }

            .close-modal:hover {
                color: #000;
            }
        </style>
    </head>
    <body>
        <%@ include file="../../commonStudent/head.jsp" %>

        <div class="container">
            <h2 class="mb-4">List of Assignments for ${currentClass.className}</h2>

            <!-- Hiển thị thông báo -->
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
            <div class="alert alert-warning mt-3">
                <strong>Important:</strong> ZIP files submitted will be stored and cannot be deleted until the end of the course. Please ensure your submission is correct before submitting.
            </div>
            <!-- Hiển thị bảng danh sách assignments -->
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Due Date</th>
                        <th>Class</th>
                        <th>Assign by</th>
                        <th>Type</th>
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
                            <!-- Lấy ngày giao -->
                            <td>
                                ${getActiveAssignment.getAssignmentBadge(assign.dueDate)}
                            </td>
                            <td>
                                <span class="badge badge-secondary">${currentClass.className}</span>
                            </td>
                            <td>
                                <span class="badge badge-info">${assign.teacher != null ? assign.teacher.name : "NULL"}</span>
                            </td>
                            <td>
                                <span class="badge badge-success">
                                    <c:choose>
                                        <c:when test="${assign.type == 3}">
                                            Caption
                                        </c:when>
                                        <c:when test="${assign.type == 2}">
                                            Assignment
                                        </c:when>
                                        <c:otherwise>
                                            Lab
                                        </c:otherwise>
                                    </c:choose>
                                </span>
                            </td>
                            <td><span class="badge badge-primary">Active</span></td>
                            <td>
                                <!-- Hiển thị nút hành động dựa trên tình trạng deadline -->
                                <c:if test="${getActiveAssignment.getAssignmentIsDue(assign.dueDate) == false}">
                                    <a href="StudentAssignmentController?action=submit&classID=${assign.classID}&assignmentID=${assign.assignmentID}" class="btn btn-info btn-sm">Submit</a>
                                </c:if>

                                <a href="StudentAssignmentController?action=view-submition&classID=${assign.classID}&assignmentID=${assign.assignmentID}" class="btn btn-primary btn-sm">View Submission</a>


                                <button type="button" class="btn btn-primary" onclick="openModal('modal${status.index}')">
                                    View descriptions
                                </button>
                            </td>
                        </tr>
                    <div id="modal${status.index}" class="custom-modal">
                        <div class="modal-content">
                            <span class="close-modal" onclick="closeModal('modal${status.index}')">&times;</span>
                            <h5>Descriptions Assignment</h5>
                            <p>${assign.description}</p>
                        </div>
                    </div>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <script>
            // Function to open modal
            function openModal(modalId) {
                document.getElementById(modalId).style.display = 'flex';
            }

            // Function to close modal
            function closeModal(modalId) {
                document.getElementById(modalId).style.display = 'none';
            }

            // Close modal if clicking outside the modal content
            window.onclick = function (event) {
                const modals = document.getElementsByClassName('custom-modal');
                for (let i = 0; i < modals.length; i++) {
                    if (event.target == modals[i]) {
                        modals[i].style.display = 'none';
                    }
                }
            };
        </script>
        <%@ include file="../../commonStudent/footer.jsp" %>
    </body>
</html>
