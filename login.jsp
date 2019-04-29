<%
String title = "Login Page";
%>
<%@ include file="./header.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">

            <% if(errorMessage != "" || message != "") { %>
            <div class="card card-signin my-5" id="myCard">
                <div class="card-body">
                    <h3>
                        <%= errorMessage %>
                        <%= message %>
                    </h3>
                </div>
            </div>
            <% } %>

            <div class="card card-signin my-5" id="myCard">
                <div class="card-body">
                    <h5 class="card-title text-center">Sign In</h5>
                    <form class="form-signin" method="post" action="./Login">
                        <div class="form-label-group">
                            <input type="text" id="inputEmail" class="form-control" name="Login" placeholder="ID number"
                                required autofocus>
                            <label for="inputEmail">User ID</label>
                        </div>

                        <div class="form-label-group">
                            <input type="password" id="inputPassword" class="form-control" name="Password" placeholder="Password"
                                required>
                            <label for="inputPassword">Password</label>
                        </div>

                        <div class="custom-control custom-checkbox mb-3">
                            <input type="checkbox" class="custom-control-input" id="customCheck1">
                            <label class="custom-control-label" for="customCheck1">Remember password</label>
                        </div>
                        <button id="login-btn" type="submit">Sign in</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="./footer.jsp" %>