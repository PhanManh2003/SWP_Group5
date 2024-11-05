<%-- 
    Document   : question-form
    Created on : Jul 17, 2024, 7:27:46 PM
    Author     : HP
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../../commonTeacher/head.jsp"></jsp:include>
        <div class="container">
            <h2>${question == null ? 'Add New Question' : 'Edit Question'}</h2>
            <form action="teacher-question" method="post">
                <input type="hidden" name="action" value="${question == null ? 'insert' : 'update'}">
                <input type="hidden" name="id" value="${question.id}">
                <div class="form-group">
                    <label>Question</label>
                    <input type="text" name="question" class="form-control" value="${question.question}" required>
                </div>
                <div class="form-group">
                    <label>Status</label>
                    <select name="status" class="form-control" required>
                        <option value="0" ${question != null && question.status == 0 ? 'selected' : ''}>Inactive</option>
                        <option value="1" ${question != null && question.status == 1 ? 'selected' : ''}>Active</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Slot</label>
                    <select name="slot" class="form-control">
                        <c:forEach var="i" begin="1" end="20">
                            <option value="${i}" ${question != null && question.slot == i ? 'selected' : ''}>${i}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <input type="hidden" name="classId" class="form-control" value="${question == null ? classId : question.classId}">
                </div>
                <button type="submit" class="btn btn-primary">${question == null ? 'Add' : 'Update'}</button>
                <a href="teacher-question" class="btn btn-secondary">Cancel</a>
            </form>
        </div>
     <%@include file="../../commonTeacher/footer.jsp" %>
