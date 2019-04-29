<%
String title = "Change Password Page";
%>
<%@ include file="./header.jsp" %>
<% if (student == null) { 
    session.setAttribute("errors","Please Log in to change your password");
    response.sendRedirect("./login.jsp");
}%>

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
                    <form class="form-signin" method="post" action="./changePassword">
                        <div class="form-label-group">
                            <input type="password" id="inputPassword" class="form-control" name="CurrentPassword" placeholder="Current Password"
                                required autofocus>
                            <label for="inputPassword">Current Password</label>
                        </div>
                        <hr/>
                        <!-- <div class="form-label-group">
                            <input type="password" name="NewPassword" placeholder="New Password" required>
                            <label>New Password</label>
                        </div> -->
                        <div class="form-label-group">
                            <input type="password" id="NewPass" class="form-control" name="NewPassword" placeholder="New Password"
                                required>
                            <label for="NewPass">New Password</label>
                        </div>

                        <div class="form-label-group">
                            <input type="password" id="NewPassConfirm" class="form-control" name="NewPasswordConfirm" placeholder="Re-enter New Password"
                                required>
                            <label for="NewPassConfirm">Confirm New Password</label>
                        </div>

                        <button id="login-btn" type="submit">Update Password</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="./footer.jsp" %>