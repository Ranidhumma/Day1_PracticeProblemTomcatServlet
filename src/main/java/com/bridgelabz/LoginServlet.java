package com.bridgelabz;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = {"/LoginServlet"},
        initParams = {
                @WebInitParam(name = "user", value = "Rani"),
                @WebInitParam(name = "password", value = "rani@12345")
        }
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get request parameters for userID and password
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        //get servlet config init params
        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");
        //regex for username and password
        String userRegex = "^[A-Z]{1}.{2,}$";//username regex
        /*
         *Checking regex pattern for UserName atleast one upper case min 3 characters
         */
        if (!user.matches(userRegex)) {
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Kindly Enter Correct user name</font>");
        }

        String passwordRegex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        /*
        Checking regex pattern for password
         */
        if (!pwd.matches(passwordRegex)) {
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Kindly Enter Correct password</font>");
        }


        if (userID.equals(user) && password.equals(pwd)) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            if (!userID.equals(user))
                out.println("<font color=red>  user name is wrong.</font>");
            if (!password.equals(pwd))
                out.println("<font color=red>  password is wrong.</font>");
            rd.include(request, response);
        }

    }}