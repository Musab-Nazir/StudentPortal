<%
String title = "Register Page";
%>
<%@ include file="./header.jsp" %>
<% if (student != null) { 
    session.setAttribute("errors","Please Log out to access the registration form");
    response.sendRedirect("./logout.jsp");
}
String email = (String)session.getAttribute("email");
String userID = (String)session.getAttribute("userID");
String firstName = (String)session.getAttribute("firstName");
String lastName = (String)session.getAttribute("lastName");
String progCode = (String)session.getAttribute("progCode");
String progDesc = (String)session.getAttribute("progDesc");
String year = (String)session.getAttribute("year");
if(email == null){email = "";}
if(userID == null){userID = "";}
if(firstName == null){firstName = "";}
if(lastName == null){lastName = "";}
if(progCode == null){progCode = "";}
if(progDesc == null){progDesc = "";}
if(year == null){year = "";}

%>

<div class="container" style="height:100vh;">
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
                    <h5 class="card-title text-center">Register</h5>
                    <form class="form-signin" method="post" action="./Register">
                        <div class="form-label-group">
                            <input type="text" id="userID" class="form-control" name="userID" placeholder="User ID"
                                value="<%= userID %>" required autofocus>
                            <label for="userID">User ID</label>

                        </div>
                        <div class="form-label-group">
                            <input type="password" id="inputPassword" class="form-control" name="password"
                                placeholder="Password" required>
                            <label for="inputPassword">Password</label>
                            <br />
                        </div>
                        <div class="form-label-group">
                            <input type="text" id="firstName" class="form-control" name="firstName"
                                value="<%= firstName %>" placeholder="First Name" required>
                            <label for="firstName">First Name</label>
                        </div>
                        <div class="form-label-group">
                            <input type="text" id="lastName" class="form-control" name="lastName"
                                value="<%= lastName %>" placeholder="Last Name" required>
                            <label for="lastName">Last Name</label>
                        </div>
                        <div class="form-label-group">
                            <input type="text" id="inputEmail" class="form-control" name="email" placeholder="Email"
                                value="<%= email %>" required>
                            <label for="inputEmail">Email Address</label>
                            <br />
                        </div>
                        <div class="form-label-group">
                            <input type="text" id="inputCode" class="form-control" name="PRCode"
                                placeholder="Program Code" value="<%= progCode %>" required>
                            <label for="inputCode">Program Code</label>
                        </div>
                        <div class="form-label-group">
                            <input type="text" id="inputDescription" class="form-control" name="PRdesc"
                                placeholder="Program Description" value="<%= progDesc %>" required>
                            <label for="inputDescription">Program Description</label>
                        </div>
                        <div class="form-label-group">
                            <input type="text" id="inputYear" class="form-control" name="year" placeholder="Year"
                                value="<%= year %>" required>
                            <label for="inputYear">Year</label>
                        </div>

                        <button id="login-btn" type="submit">Sign Up!</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
<%@ include file="./footer.jsp" %>