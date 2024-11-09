<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Course Lessons</title>
    <!-- Bootstrap and FontAwesome for styling -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            background-color: #f4f6f9;
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 800px;
            margin-top: 60px;
        }
        h2 {
            font-weight: bold;
            color: #333;
        }
        .lesson-card {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-bottom: 15px;
            padding: 15px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            transition: transform 0.2s ease;
            position: relative;
        }
        .lesson-card:hover {
            transform: scale(1.02);
        }
        .lesson-title {
            font-size: 1.2rem;
            font-weight: bold;
            color: #007bff;
            margin-bottom: 8px;
        }
        .lesson-description {
            color: #555;
            margin-bottom: 15px;
        }
        .btn-primary {
            background-color: #ff4757;
            border: none;
            transition: background-color 0.3s ease;
        }
        .btn-primary:hover {
            background-color: #ff6b81;
        }
        .empty-state {
            text-align: center;
            padding: 50px;
            font-size: 1.2rem;
            color: #6c757d;
        }
        .empty-state i {
            font-size: 3rem;
            color: #ff4757;
            margin-bottom: 20px;
        }
        .status-badge {
            position: absolute;
            top: 15px;
            right: 15px;
            padding: 5px 10px;
            font-size: 0.9rem;
            color: #fff;
            background-color: #28a745;
            border-radius: 5px;
        }
        /* Style for Active Lesson Card */
        .active-card {
            background-color: #e8f5e9; /* Light green background for active lessons */
            border: 1px solid #4caf50; /* Green border for active lessons */
        }
        .active-card .lesson-title {
            color: #2e7d32; /* Darker green color for title */
        }
    </style>
</head>
<body>
    <%@ include file="../../commonStudent/head.jsp" %>
    <div class="container">
        <h2 class="text-center mb-4">Course Lessons</h2>

        <!-- Empty state message if there are no lessons -->
        <c:choose>
            <c:when test="${empty lessions}">
                <div class="empty-state">
                    <i class="fas fa-exclamation-circle"></i>
                    <p>No lessons available at the moment. Please check back later!</p>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach var="lession" items="${lessions}">
                    <!-- Add active-card class if studentLession exists -->
                    <div class="lesson-card <c:if test='${lession.studentLession != null}'>active-card</c:if>">
                        <!-- Display Active Badge if studentLession exists -->
                        <c:if test="${lession.studentLession != null}">
                            <div class="status-badge">Active</div>
                        </c:if>

                        <div class="lesson-title">
                            <i class="fas fa-book mr-2"></i> ${lession.title}
                        </div>
                        <p class="lesson-description">
                            ${lession.description}
                        </p>
                        <a href="LessionStudentController?action=learn&lessionId=${lession.lessionId}" class="btn btn-primary">
                            <i class="fas fa-play-circle"></i> Start Learning
                        </a>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
    <%@ include file="../../commonStudent/footer.jsp" %>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
