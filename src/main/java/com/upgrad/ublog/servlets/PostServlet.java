package com.upgrad.ublog.servlets;

import com.upgrad.ublog.dto.PostDTO;
import com.upgrad.ublog.services.PostService;
import com.upgrad.ublog.services.ServiceFactory;
import com.upgrad.ublog.utils.DateTimeFormatter;
import com.upgrad.ublog.utils.LogWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


@WebServlet("/ublog/post")
public class PostServlet extends HttpServlet {

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

        PostDTO postDTO = new PostDTO();
        postDTO.setPostId(1);
        postDTO.setDescription(req.getParameter("blog-desc"));
        postDTO.setEmailId(email);
        postDTO.setTag(req.getParameter("blog-tag"));
        postDTO.setTitle(req.getParameter("blog-title"));
        postDTO.setTimestamp(LocalDateTime.now());

        try {
            postService.save(postDTO);
            String log = DateTimeFormatter.format(postDTO.getTimestamp())
                    + "\t New post with title " + postDTO.getTitle() +
                    " created by " + postDTO.getEmailId();

            Thread logThread = new LogWriter(log, System.getProperty("user.dir"));
            logThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("postDTO", postDTO);
        req.getRequestDispatcher("/ublog/View.jsp").forward(req, resp);
    }
}