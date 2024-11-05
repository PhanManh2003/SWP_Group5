
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../commonAdmin/head.jsp"></jsp:include>
<div class="container mt-5">
    <h2 class="mb-4">List of Admins</h2>
    <a href="AdminController?action=add" class="btn btn-primary mb-4">Add admin</a>
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
    <table class="table" id="data-table">
        <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${admins}" var="admin">
                <c:if test="${sessionScope.adminLogin.id != admin.id}">
                    <tr>
                        <td>${admin.id}</td>
                        <td>${admin.name}</td>
                        <td>${admin.email}</td>
                        <td>${admin.phone}</td>
                        <td>
                            <c:if test="${admin.status == 1}">
                                Active
                            </c:if>
                            <c:if test="${admin.status == 0}">
                                Hidden
                            </c:if>
                        </td>
                        <td>
                            <a href="AdminController?action=edit&adminID=${admin.id}" class="btn btn-warning">Edit</a>
                            <a onClick="return confirm('Are your sure to delete it?')" href="AdminController??action=delete&adminID=${admin.id}" class="btn btn-danger">Delete</a>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
        </tbody>
    </table>
</div>
<%@include file="../../commonAdmin/footer.jsp" %>
