package webd4201.nazirm;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;

/**
 * Servlet that handles update requests
 */
public class UpdateServlet extends HttpServlet {

    /**
     * This method validate form data and updates the student attributes in the database
     * @param request form data entered by the user
     * @param response what gets sent to the user
     * @throws IOException if any files cannot be located
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // connect to database
            Connection c = DatabaseConnect.initialize();
            Student.initialize(c);

            //get the session info
            HttpSession session = request.getSession(true);

            boolean anyErrors = false;
            StringBuilder errors = new StringBuilder();
            // get existing info
            Student aStudent = (Student)session.getAttribute("student");
            long userID = aStudent.getId();
            String password = aStudent.getPassword();
            Date enrolDate = aStudent.getEnrolDate();

            String firstName = "";
            String lastName = "";
            String emailAddress = "";
            String progCode = "";
            String progDesc = "";
            String year = "";

            // retrieve data from DB
            firstName = request.getParameter("firstName").trim();
            lastName = request.getParameter("lastName").trim();
            emailAddress = request.getParameter("email").trim();
            progCode = request.getParameter("PRCode").trim();
            progDesc = request.getParameter("PRdesc").trim();
            year = request.getParameter("year").trim();

            // check for empty fields
            if (password.equals("") || emailAddress.equals("") || lastName.equals("") || firstName.equals("") ||
                    progCode.equals("") || progDesc.equals("") || year.equals("")) {
                errors.append("\nNo empty fields allowed. Please try again.");

            } else {
                // set session attributes
                session.setAttribute("firstName", firstName);
                session.setAttribute("lastName", lastName);
                session.setAttribute("progCode", progCode);
                session.setAttribute("progDesc", progDesc);
            }
            try {
                int yearInt = Integer.parseInt(year);

                if (yearInt <= 0 || yearInt >= 10) {
                    errors.append("<br/>Year can only be 1-9");
                    anyErrors = true;
                }
                if (!User.isValidEmailAddress(emailAddress)) {   // email is invalid
                    anyErrors = true;
                    errors.append("<br/>Email is not valid");

                } else {
                    session.setAttribute("email", emailAddress);
                }

                if (anyErrors) {
                    session.setAttribute("errors", errors.toString());
                    response.sendRedirect("./update.jsp");
                } else {
                    // make a student
                    Student updatedStudent = new Student(userID, password, firstName, lastName, emailAddress,
                            's', true, new Date(), enrolDate, progCode,
                            progDesc, yearInt);

                    // try to update the object
                    int records = updatedStudent.update();
                    if(records > 0){
                        session.setAttribute("message", "User Information updated. Please Log back in to see " +
                                "updated information ");
                        response.sendRedirect("./dashboard.jsp");
                    }
                    else{
                        session.setAttribute("message", "Update Failed");
                        response.sendRedirect("./update.jsp");
                    }

                }
            } catch (NumberFormatException e) {
                session.setAttribute("errors", "Year is not numeric");
                response.sendRedirect("./update.jsp");
            }
        } catch (Exception e) {
            System.out.println(e);
            String line1 = "<h2>A network error has occurred!</h2>";
            String line2 = "<p>Please notify your system " +
                    "administrator and check log. " + e.toString() + "</p>";
            formatErrorPage(line1, line2, response);
        }

    }

    /**
     * Performs this method on a GET request when the user lands on the page
     *
     * @param request  what is sent from the page
     * @param response what is sent back by th servlet
     * @throws IOException if the correct files cannot be accessed
     */
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }

    /**
     * Helper method to format text
     *
     * @param first    first string object
     * @param second   second string object that is appended
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
