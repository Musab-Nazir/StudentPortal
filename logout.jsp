<%
String title = "Logout Page";
%>
<%@ include file="./header.jsp" %>
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
            <div class="card card-signin my-5">
                <div class="card-body">
                    <h5 class="card-title text-center">See you soon</h5>
                    <form class="login100-form validate-form" method="post" action="./Logout">
                        <button id="login-btn" type="submit">Log Out</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="./footer.jsp" %>