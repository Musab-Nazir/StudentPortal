package webd4201.nazirm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;

/**
 * Servlet that handles new account registrations
 */
public class RegisterServlet extends HttpServlet {
    /**
     * Performs this method when registering new user
     * @param request page data being sent from the form
     * @param response what is sent back
     * @throws IOException if the correct files cannot be accessed
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // connect to database
            Connection c = DatabaseConnect.initialize();
            Student.initialize(c);
            HttpSession session = request.getSession(true);
            boolean anyErrors = false;
            StringBuilder errors = new StringBuilder();
            String userID = "";
            String firstName = "";
            String lastName = "";
            String emailAddress = "";
            String password = "";
            String progCode = "";
            String progDesc = "";
            String year = "";
            long id = 0l;

                    // retrieve data from DB
                userID = request.getParameter("userID");
                firstName = request.getParameter("firstName");
                lastName = request.getParameter("lastName");
                emailAddress = request.getParameter("email");
                password = request.getParameter("password");
                progCode = request.getParameter("PRCode");
                progDesc = request.getParameter("PRdesc");
                year = request.getParameter("year");

                // check for empty fields
                if (password.equals("") || emailAddress.equals("") || lastName.equals("") || firstName.equals("") ||
                        userID.equals("") || progCode.equals("")||progDesc.equals("")||year.equals("")) {
                    errors.append("\nNo empty fields allowed. Please try again.");

                }
                else{
                    // set session attributes
                    session.setAttribute("firstName",firstName);
                    session.setAttribute("lastName",lastName);
                    session.setAttribute("progCode", progCode);
                    session.setAttribute("progDesc", progDesc);
                }
                try{
                    int yearInt = Integer.parseInt(year);
                    if(userID.length() == 0){
                        errors.append("<br/>You must enter a User ID.");

                    }
                    else{
                        try{
                            id = Long.parseLong(userID);
                            if(!User.verifyId(id)){     // invalid range for userID
                                errors.append("<br/>User ID is not in valid range ");
                                anyErrors = true;

                            }
                            else{
                                session.setAttribute("userID",userID);
                            }
                        }catch(NumberFormatException nfe){
                            errors.append("Your id is your student number, therefore " + userID + " is not valid.");
                            anyErrors = true;
                        }
                    }

                    if(yearInt <= 0 || yearInt >= 10){
                        errors.append("<br/>Year can only be 1-9");
                        anyErrors = true;
                    }
                    if(!User.isValidEmailAddress(emailAddress)){   // email is invalid
                        anyErrors = true;
                        errors.append("<br/>Email is not valid");

                    }
                    else{
                        session.setAttribute("email",emailAddress);
                    }

                    if(anyErrors){
                        session.setAttribute("errors",errors.toString());
                        response.sendRedirect("./register.jsp");
                    }
                    else {
                        // make a student
                        Student newRegister = new Student(id,password,firstName,lastName,emailAddress,
                                's',true, new Date(), new Date(),progCode,
                                progDesc,yearInt);

                        // try to insert the object
                        newRegister.create();
                        session.setAttribute("message","User Account Created!");
                        response.sendRedirect("./login.jsp");
                    }
                }
                catch (NumberFormatException e) {
                    session.setAttribute("errors", "Year is not numeric");
                    response.sendRedirect("./register.jsp");
                }
                catch (DuplicateException de){
                    session.setAttribute("errors", "A user with that ID already exists");
                    response.sendRedirect("./register.jsp");
                }
            }
        catch (Exception e) {
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
