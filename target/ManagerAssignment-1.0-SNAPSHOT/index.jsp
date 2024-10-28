<%-- 
    Document   : index
    Created on : Jun 12, 2024, 12:03:04 AM
    Author     : HP
--%>
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
                            <h5 class="card-title text-center">Login admin</h5>
                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger" role="alert">
                                    ${param.error}
                                </div>
                            </c:if>
                            <form action="LoginController" method="post">
                                <div class="form-group">
                                    <label for="inputEmail">Email</label>
                                    <input required type="email" class="form-control" id="inputEmail" name="email" placeholder="Enter email">
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword">Password</label>
                                    <input required type="password" class="form-control" id="inputPassword" name="password" placeholder="Password">
                                </div>
                                <a href="AdminForgetPasswordController" class="text-right mb-3" style="display: block; color: #333">Forget password</a>
                                <button type="submit" class="btn btn-primary btn-block">Login</button>
                                <p class="text-center mt-3">Login with google by student and teacher</p>
                                <a class="btn btn-danger btn-block"
                                   href="https://accounts.google.com/o/oauth2/auth?scope=email profile&redirect_uri=http://localhost:8080/Edunext/LoginWithGoogleController&response_type=code
                                   &client_id=855024682610-7me9k0ap16evk6iero8s8293i4sp7o7o.apps.googleusercontent.com&approval_prompt=force"
                                   >
                                    Login with Google
                                </a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
