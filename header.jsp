<%@ page import = "java.util.*" %>
<%@ page import="webd4201.nazirm.*" %>
<%   String errorMessage = (String)session.getAttribute("errors"); 
    Student student = (Student)session.getAttribute("student"); 
    String login = (String)session.getAttribute("login");
    String message = (String)session.getAttribute("message");
    if(message == null) //there was not message
    {
    message = "";
    }
    else{
    session.removeAttribute("message");
    }
	if(errorMessage == null)
        errorMessage="";
    else{
        session.removeAttribute("errors");
    }
	if(login == null)
		login = "";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>
            <%= title %>
        </title>
        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    </head>

    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light" style="box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);">
            <a href="./index.jsp"><img src="./images/durham.png" alt="logo" id="brand-logo"></a>
            <a class="navbar-brand" href="./index.jsp">Durham College</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav"
                aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="./index.jsp">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <% if (student == null) { %>
                    <li class="nav-item">
                        <a class="nav-link" href="./login.jsp">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="./register.jsp">Register</a>
                    </li>
                    <% } else { %>
                    <li class="nav-item">
                        <a class="nav-link" href="./update.jsp">Update</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="./changePassword.jsp">Change Password</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="./dashboard.jsp">Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="./logout.jsp">Logout</a>
                    </li>
                    <% } 
                        %>
                </ul>
            </div>
        </nav>