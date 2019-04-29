package webd4201.nazirm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

/**
 * Servlet handles changing of passwords
 */
public class PasswordServlet extends HttpServlet {
    /**
     * Takes information from a form and changes password in the database
     * @param request page data being sent from the form
     * @param response what is sent back
     * @throws IOException if the correct files cannot be accessed
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            // connect to database
            Connection c = DatabaseConnect.initialize();
            Student.initialize(c);
            HttpSession session = request.getSession(true);
            String login = "";
            String password = "";
            String NewPassword = "";
            String NewPasswordConfrim = "";
            try {   // retrieve data from DB
                login = (String) session.getAttribute("login");
                password = request.getParameter("CurrentPassword");
                NewPassword = request.getParameter("NewPassword");
                NewPasswordConfrim = request.getParameter("NewPasswordConfirm");
                //attempt to find the student, this could cause a NotFoundException
                Student aStudent = Student.Authenticate(Long.parseLong(login), password);

                // if the Student was found and retrieved from the db
                if(password.equals("") || NewPassword.equals("") || NewPasswordConfrim.equals(""))
                {
                    session.setAttribute("errors","No empty fields allowed. Please try again.");
                    response.sendRedirect("./changePassword.jsp");
                }
                else if(!NewPassword.equals(NewPasswordConfrim)){
                    session.setAttribute("errors","New passwords do NOT match. Please try again.");
                    response.sendRedirect("./changePassword.jsp");
                }
                else if(NewPassword.length() < User.MINIMUM_PASSWORD_LENGTH || NewPassword.length() > User.MAXIMUM_PASSWORD_LENGTH){
                    session.setAttribute("errors","New passwords can only be between 8 and 50 characters. Please try again.");
                    response.sendRedirect("./changePassword.jsp");
                }
                else if(NewPassword.equals(password) || NewPasswordConfrim.equals(password)){
                    session.setAttribute("errors","New password cannot be the same as existing password.");
                    response.sendRedirect("./changePassword.jsp");
                }
                else{
                    // set the new password
                    aStudent.setPassword(NewPassword);
                    // update the database
                    aStudent.update();

                    //redirect and set message
                    session.setAttribute("message", "Password Successfully changed");
                    response.sendRedirect("./dashboard.jsp");
                }
                // put the found Student onto the session
                session.setAttribute("student", aStudent);


                // redirect the user to a JSP
                response.sendRedirect("./dashboard.jsp");
            } catch (NotFoundException e) {
                session.setAttribute("errors","Password is incorrect");
                response.sendRedirect("./changePassword.jsp");
            }
        }
        catch (Exception e) //not connected
        {
            System.out.println(e);
            String line1 = "<h2>A network error has occurred!</h2>";
            String line2 = "<p>Please notify your system " +
                    "administrator and check log. " + e.toString() + "</p>";
            formatErrorPage(line1, line2, response);
        }
    }

    /**
     * Performs this method on a GET request when the user lands on the page
     * @param request what is sent from the page
     * @param response what is sent back by th servlet
     * @throws ServletException if something goes wrong with the servlet
     * @throws IOException if the correct files cannot be accessed
     */
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Helper method to format text
     * @param first first string object
     * @param second second string object that is appended
     * @param response final string sent back
     * @throws IOException if the correct files cannot be accessed
     */
    public void formatErrorPage(String first, String second,
                                HttpServletResponse response) throws IOException {
        PrintWriter output = response.getWriter();
        response.setContentType("text/html");
        output.println(first);
        output.println(second);
        output.close();
    }
}
