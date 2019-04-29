<%
String title = "Dashboard";
%>
<%@ include file="./header.jsp" %>
<% if (student == null) { 
    session.setAttribute("errors","Please Log in to access the Dashboard");
    response.sendRedirect("./login.jsp");
}%>
<div class="container-fluid" id="dash-container">

    <div class="card card-signin my-5 col-md-3" style="margin-right: 2rem;">
        <div class="card-body">
            <h3 class="text-center">Profile</h3>
            <img src="./images/placeholder 200x200.jpg" alt="ID_placeholder" width="100%">
            <hr />
            <table style="width:100%;">
                <tr>
                    <td><label for="">User ID:</label></td>
                    <td class="right-align"><label for=""> <%= student.getId() %></label></td>
                </tr>
                <tr>
                    <td><label for="">First Name: </label></td>
                    <td class="right-align"><label for=""><%= student.getFirstName() %></label></td>
                </tr>
                <tr>
                    <td><label for="">Last Name: </label></td>
                    <td class="right-align"><label for=""><%= student.getLastName() %></label></td>
                </tr>
                <tr>
                    <td><label for="">Email Address: </label></td>
                    <td class="right-align"><label for=""><%= student.getEmailAddress() %></label></td>
                </tr>
                <tr>
                    <td><label for="">Enrol Date: </label></td>
                    <td class="right-align"><label for=""><%= student.getEnrolDate() %></label></td>
                </tr>
                <tr>
                    <td><label for="">Last Access: </label></td>
                    <td class="right-align"><label for=""><%= student.getLastAccess() %></label></td>
                </tr>
            </table>
        </div>
    </div>
    <div class="card card-signin my-5 col-md-8">
        <div class="card-body">
            <h3 class="text-center">Dashboard</h3>
            <% if(errorMessage != "" || message != "") { %>
            <h3>
                <%= errorMessage %>
                <%= message %>
            </h3>
            <% } %>
            <%= student %>
            <hr />
            <h3 class="text-center">Marks</h3>
            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>Course Code</th>
                        <th>Course Title</th>
                        <th>GPA Weighting</th>
                        <th>Grade</th>
                    </tr>
                </thead>

                <% Vector<Mark> marks = student.getMarks();
                    if(marks.size() >= 1){
                for(int i = 0; i < marks.size(); i++){
                    out.print("<tr>");
                    out.print("<td>");
                        %><%=marks.elementAt(i).getCourseCode()%><%
                    out.print("</td>");
                    out.print("<td>");
                            %><%=marks.elementAt(i).getCourseName()%><%
                        out.print("</td>");
                        out.print("<td>");
                                %><%=marks.elementAt(i).getGpaWeighting()%><%
                            out.print("</td>");
                    out.print("<td>");
                            %><%=marks.elementAt(i).getResult()%><%
                        out.print("</td>");
                    out.print("</tr>");

                } }%>
            </table>
            <h2>GPA = <%if(marks.size() >= 1){out.print(student.CalculateGPA());}
                else{
                    out.print("No marks on record");}%></h2>

        </div>
    </div>
</div>

<%@ include file="./footer.jsp" %>