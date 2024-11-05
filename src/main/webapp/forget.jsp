
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="text-center mt-5">
                        <img src="https://upload.wikimedia.org/wikipedia/commons/6/68/Logo_FPT_Education.png" width="200px" alt="Logo" class="logo">
                    </div>
                    <div class="card mt-5">
                        <div class="card-body">
                            <h5 class="card-title text-center">Forget password for admin</h5>
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
                            <form action="AdminForgetPasswordController" method="post">
                                <div class="form-group">
                                    <label for="inputEmail">Email</label>
                                    <input required type="email" class="form-control" id="inputEmail" name="email" placeholder="Enter email">
                                </div>
                                <a href="LoginController" class="text-right mb-3" style="display: block; color: #333">Login</a>
                                <button onclick="return confirm('Are you sure to reset password')" type="submit" class="btn btn-primary btn-block">Reset password</button>
                                <a class="btn btn-danger btn-block" href="LoginController">Login with google by student and teacher</a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
