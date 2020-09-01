package com.upgrad.ublog.servlets;


import com.upgrad.ublog.dto.PostDTO;
import com.upgrad.ublog.exceptions.EmailNotValidException;
import com.upgrad.ublog.exceptions.PostNotFoundException;
import com.upgrad.ublog.services.PostService;
import com.upgrad.ublog.services.ServiceFactory;
import com.upgrad.ublog.utils.EmailValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PostUtilServlet extends HttpServlet {
    PostService postService;

    @Override
    public void init() {
        ServiceFactory serviceFactory = new ServiceFactory();
        postService = serviceFactory.getPostService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email;
        try {
            email = (String) req.getSession().getAttribute("email");
            if (email == null || email.equals("")) {
                throw new Exception();
            }
        } catch (Exception e) {
            resp.sendRedirect("/index.jsp");
            return;
        }

        String requestFrom = req.getParameter("requestFrom");


        switch (requestFrom) {
            case "Search":
                String sEmail = req.getParameter("sEmail");
                try {
                    EmailValidator.isValidEmail(sEmail);
                } catch (EmailNotValidException e) {
                    req.setAttribute("error", "Please provide valid email address");
                    req.getRequestDispatcher("/ublog/Search.jsp").forward(req, resp);
                    return;
                }
                try {
                    List<PostDTO> posts = postService.getPostsByEmail(sEmail);
                    if (posts == null || posts.size() == 0) {
                        throw new PostNotFoundException("Sorry no posts exists for this email id");
                    }
                    req.setAttribute("posts", posts);
                    req.getRequestDispatcher("/ublog/Search.jsp").forward(req, resp);
                } catch (PostNotFoundException e) {
                    req.setAttribute("error", e.getMessage());
                    req.getRequestDispatcher("/ublog/Search.jsp").forward(req, resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            case "Delete":
                int postId = Integer.parseInt(req.getParameter("postId"));
                try {
                    if (postService.deletePost(postId, email)) {
                        req.setAttribute("DeleteSuccess", "Post deleted successfully!");
                    } else {
                        req.setAttribute("DeleteSuccess", "You are not authorised to delete this post");
                    }
                    req.getRequestDispatcher("/ublog/Delete.jsp").forward(req, resp);
                } catch (PostNotFoundException e) {
                    req.setAttribute("DeleteSuccess", "No Post exist with the given Post Id");
                    req.getRequestDispatcher("/ublog/Delete.jsp").forward(req, resp);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            case "Filter":
                String tag = req.getParameter("selectTag");
                try {
                    List<PostDTO> posts = postService.getPostsByTag(tag);
                    if (posts == null || posts.size() == 0) {
                        throw new PostNotFoundException("Sorry no posts exists for this tag");
                    } else {
                        req.setAttribute("posts", posts);
                        req.getRequestDispatcher("/ublog/Filter.jsp").forward(req, resp);
                    }
                } catch (PostNotFoundException e) {
                    req.setAttribute("filterSuccess", e.getMessage());
                    req.getRequestDispatcher("/ublog/Filter.jsp").forward(req, resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }

        }
    }
}
