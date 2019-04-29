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
 * Servlet that handles log in requests
 */
public class LoginServlet extends HttpServlet {

    /**
     * Performs this method when logging in a user
     * @param request page data being sent from the form
     * @param response what is sent back
     * @throws IOException if the correct files cannot be accessed
     */
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException {

        //CREATE A TEXT FILE
	   	/*String logFile = "./test_log.log";
	    File f = new File(logFile);
	    PrintStream printStream = new PrintStream(new BufferedOutputStream(new FileOutputStream(f)), true);
	    System.setErr(printStream);
	    System.setOut(printStream);
	    System.out.println("Log started: " + new java.util.Date());
	    */
        try {
            // connect to database
            Connection c = DatabaseConnect.initialize();
            Student.initialize(c);
            HttpSession session = request.getSession(true);
            String login = "";
            String password = "";
            try {   // retrieve data from DB
                login = request.getParameter("Login"); //this is the name of the text input box on the form
                password = request.getParameter("Password");
                //attempt to find the student, this could cause a NotFoundException
                Student aStudent = Student.Authenticate(Long.parseLong(login), password);

                // if the Student was found and retrieved from the db
                // put the found Student onto the session
                session.setAttribute("student", aStudent);
                session.setAttribute("login", login);


                // redirect the user to a JSP
                response.sendRedirect("./dashboard.jsp");
            } catch (NumberFormatException e) {
                StringBuffer errorBuffer = new StringBuffer();
                errorBuffer.append("<strong>Your sign in information is not valid.<br/>");
                errorBuffer.append("Please try again.</strong>");
                errorBuffer.append(" Please ensure User ID is a numeric value");

                session.setAttribute("errors", errorBuffer.toString());
                response.sendRedirect("./login.jsp");
            } catch (NotFoundException nfe) {
                login = request.getParameter("Login");
                //new code == way better, if I do say so myself
                //sending errors to the page thru the session
                StringBuffer errorBuffer = new StringBuffer();
                errorBuffer.append("<strong>Your sign in information is not valid.<br/>");
                errorBuffer.append("Please try again.</strong>");
                if (Student.StudentExists(Long.parseLong(login))) {
                    session.setAttribute("login", login);
                    errorBuffer.append(" Invalid Password.");
                } else {
                    errorBuffer.append(" Invalid login id.");
                    session.setAttribute("login", "");
                }
                session.setAttribute("errors", errorBuffer.toString());
                response.sendRedirect("./login.jsp");

                //for the first deliverable you will have to check if there was a problem
                //with just the password, or login id and password
                //this will require a special method e.g. public static boolean isExistingLogin(String arg);
            }
        } catch (Exception e) //not connected
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