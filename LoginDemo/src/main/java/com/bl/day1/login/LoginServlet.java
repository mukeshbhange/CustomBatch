package com.bl.day1.login;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(
        description = "log in function",
        urlPatterns = {"lServlet"},
        initParams ={
                @WebInitParam(name="user",value="Mukesh"),
                @WebInitParam(name="password",value="Brooklyn@99")
        }
)
public class lServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String user =request.getParameter("username");
        String pwd =request.getParameter("pwd");

        String userId = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");


        if(validateUser(user)){
            if(validatePassword(pwd)){
                if(userId.equals(user) && password.equals(pwd)) {
                    request.setAttribute("user",user);
                    RequestDispatcher rd = request.getRequestDispatcher("LoginSuccess.jsp");
                    rd.forward(request, response);
                }else{
                    out.print("User not present");
                    RequestDispatcher rd=request.getRequestDispatcher("/loginform.html");
                    rd.include(request,response);

                }
            }else{
                out.print("password is Incorrect");
                RequestDispatcher rd=request.getRequestDispatcher("/loginform.html");
                rd.include(request,response);
            }
        }
        else{
            out.print("userName is Incorrect form");
            RequestDispatcher rd=request.getRequestDispatcher("/loginform.html");
            rd.include(request,response);
        }
        out.close();
    }

    public  boolean validateUser(String name){
        Pattern p = Pattern.compile("(?!^.*[A-Z]{1,}.*$)^[A-Za-z].{2,}$");
        Matcher m = p.matcher(name);
        return m.matches();
    }

    public static boolean validatePassword(String pass){
        Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        Matcher m = p.matcher(pass);
        return m.matches();
    }
}

