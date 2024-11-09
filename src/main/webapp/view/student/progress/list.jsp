<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Progress</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .card {
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .progress-bar {
            background-color: #28a745;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
            transition: background-color 0.3s ease;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .alert-warning {
            background-color: #ffeeba;
            color: #856404;
            font-weight: bold;
            display: flex;
            align-items: center;
        }
        .alert-warning i {
            margin-right: 10px;
        }
    </style>
</head>
<body>
    <%@ include file="../../commonStudent/head.jsp" %>

    <div class="container mt-5">
        <h2 class="text-center mb-4">Your Progress</h2>
        <c:forEach var="course" items="${completedLessions}">
            <div class="card mb-4">
                <div class="card-header bg-info text-white">
                    <h4>${course.courseName}</h4>
                    <span class="badge badge-warning">Course Code: ${course.courseCode}</span>
                </div>
                <div class="card-body">
                    <c:choose>

                        <c:when test="${course.lession.size() == 0}">
                            <div class="alert alert-warning">
                                <i class="fas fa-exclamation-circle"></i>
                                This course has no lessons available at the moment.
                            </div>
                        </c:when>
                        <c:otherwise>
                            <p><strong>Lessons completed:</strong> ${course.studentLession.size()} / ${course.lession.size()}</p>

                            <div class="progress mb-3">
                                <div class="progress-bar" role="progressbar" 
                                     style="width: ${(course.studentLession.size() * 100) / course.lession.size()}%;" 
                                     aria-valuenow="${course.studentLession.size()}" aria-valuemin="0" 
                                     aria-valuemax="${course.lession.size()}">
                                    ${course.studentLession.size()} / ${course.lession.size()}
                                </div>
                            </div>

                            <c:choose>
                                <c:when test="${course.studentLession.size() == course.lession.size()}">
                                    <button class="btn btn-success mt-3" disabled>
                                        <i class="fas fa-check-circle"></i> Course Completed!
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <a href="LessionStudentController?action=learn&lessionId=${course.lession.size() > 0 ? course.lession.get(0).lessionId : 0}" class="btn btn-primary mt-3">
                                        <i class="fas fa-play-circle"></i> Continue Learning
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </c:forEach>
    </div>

    <%@ include file="../../commonStudent/footer.jsp" %>

    <!-- Bootstrap JS và FontAwesome -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
