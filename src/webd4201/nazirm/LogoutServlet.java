package webd4201.nazirm;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Logout Servlet handles logout requests
 */
public class LogoutServlet extends HttpServlet {
    /**
     * Performs this method when the user want to logout
     * @param request what is sent from the page
     * @param response what is sent back by th servlet
     * @throws IOException if the correct files cannot be accessed
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(true); //retrieve the session (or start)
        session.removeAttribute("student"); //remove the object stored at login
        session.setAttribute("message", "You have successfully logged out");
        //give an informational message
        response.sendRedirect("./login.jsp"); // redirect to login.jsp
    }
}
