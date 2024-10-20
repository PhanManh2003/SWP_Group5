<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../commonAdmin/head.jsp"></jsp:include>
    <!--Main Content-->
    <div class="col-lg-9">
        <div class="dashboard__content-wrap">
            <div class="dashboard__content-title">
                <h4 class="title">My Profile</h4>
            </div>
            <div class="row">
                <div class="col-lg-12">
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
                <div class="profile__content-wrap">
                    <form action="AdminController?action=edit" method="post">
                        <input type="hidden" name="type" value="profile" required>
                        <div class="form-group">
                            <label for="name">Name:</label>
                            <input type="text" class="form-control" id="name" name="name" value="${currentAdmin.name}" required>
                            <input type="hidden" name="idAdmin" value="${currentAdmin.id}" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" id="email" name="email" value="${currentAdmin.email}" required>
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone:</label>
                            <input type="text" class="form-control" id="phone" name="phone" value="${currentAdmin.phone}" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control" id="password" name="password">
                            <input type="hidden" value="${currentAdmin.password}" name="oldPassword"/>
                        </div>
                        <div class="form-group">
                            <label for="status">Status:</label>
                            <select class="form-control" id="status" name="status">
                                <option value="1" ${currentAdmin.status == 1 ? "selected" : ""}>Active</option>
                                <option value="0" ${currentAdmin.status == 0 ? "selected" : ""}>Inactive</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Update Admin</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
<%@include file="../../commonAdmin/footer.jsp" %>