

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Questions List</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
         <%@ include file="../component/header.jsp" %>
        <div class="container">
            <h2>Questions List</h2>
            <table class="table table-striped mt-3">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Question</th>
                        <th>Slot</th>
                        <th>Class</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="question" items="${listQuestions}">
                        <tr>
                            <td>${question.id}</td>
                            <td>${question.question}</td>
                            <td>${question.slot}</td>
                            <td>${question.classInfo.className}</td>
                            <td>
                                <a href="student-question?action=answer&id=${question.id}&classID=${classId}" class="btn btn-success">View</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${listQuestions.size() == 0}">
                        <tr>
                            <td colspan="10">No have question</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </body>
</html>
