<%
String title = "Update Page";
%>
<%@ include file="./header.jsp" %>
<% if (student == null) { 
    session.setAttribute("errors","Please Log in to access the update form");
    response.sendRedirect("./login.jsp");
}

long userID = student.getId();
String firstName = student.getFirstName();
String lastName = student.getLastName();
String email = student.getEmailAddress();
String progCode = student.getProgramCode();
String progDesc = student.getProgramDescription();
int year = student.getYear();


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
                    <h5 class="card-title text-center">Update</h5>
                    <form class="form-signin" method="post" action="./Update">
                        <div class="form-label-group">
                            <h6>User ID: <%= userID %></h6>

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

                        <button id="login-btn" type="submit">Update Info</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
<%@ include file="./footer.jsp" %>