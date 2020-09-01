package com.upgrad.ublog.servlets;

import com.upgrad.ublog.dto.UserDTO;
import com.upgrad.ublog.exceptions.EmailNotValidException;
import com.upgrad.ublog.services.ServiceFactory;
import com.upgrad.ublog.services.UserService;
import com.upgrad.ublog.utils.EmailValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ublog/user")
public class UserServlet extends HttpServlet {

    UserService userService;

    @Override
    public void init() {
        ServiceFactory serviceFactory = new ServiceFactory();
        userService = serviceFactory.getUserService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String buttonType = req.getParameter("buttonType");

        try {
            EmailValidator.isValidEmail(email);
        } catch (EmailNotValidException e) {
            req.setAttribute("error", "Please provide valid email address");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        if (password == null || password.isEmpty()) {
            req.setAttribute("error", "Password is a required field");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(req, resp);
            return;
        }

        try {
            switch (buttonType) {

                case "Sign Up": {
                    UserDTO user = userService.getUser(email);
                    if (user.getEmailId() != null && user.getEmailId().equals(email)) {
                        req.setAttribute("error", "A user with this email address already exists!");
                        req.getRequestDispatcher("/index.jsp").forward(req, resp);
                        return;
                    } else {
                        System.out.println("User Signed Up");
                        System.out.println(email);
                        UserDTO userDTO = new UserDTO();
                        userDTO.setUserId(1);
                        userDTO.setEmailId(email);
                        userDTO.setPassword(password);
                        userService.saveUser(userDTO);
                        break;
                    }
                }
                case "Sign In": {
                    UserDTO user = userService.getUser(email);
                    if (user.getEmailId() == null) {
                        req.setAttribute("error", "No user registered with the given email address!");
                        req.getRequestDispatcher("/index.jsp").forward(req, resp);
                        return;
                    } else if (user.getEmailId().equals(email)) {
                        if (user.getPassword().equals(password)) {
                            System.out.println("User Signed In");
                            System.out.println(email);
                            break;
                        } else {
                            req.setAttribute("error", "Please enter valid credentials");
                            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
                            requestDispatcher.forward(req, resp);
                            return;
                        }
                    }
                }
            }

            HttpSession session = req.getSession();
            session.setAttribute("email", email);
            resp.sendRedirect("/Home.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
