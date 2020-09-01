package com.upgrad.ublog.services;

import com.upgrad.ublog.dao.DAOFactory;
import com.upgrad.ublog.dto.PostDTO;
import com.upgrad.ublog.exceptions.PostNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class PostServiceImpl implements PostService {

    private static DAOFactory daoFactory;
    private static PostServiceImpl postService;

    private PostServiceImpl() {
    }

    public static PostServiceImpl getInstance() {

        if (postService == null) {
            daoFactory = new DAOFactory();
            postService = new PostServiceImpl();
        }

        return postService;
    }

    @Override
    public PostDTO save(PostDTO postDTO) throws Exception {
        return daoFactory.getPostDAO().create(postDTO);
    }

    @Override
    public List<PostDTO> getPostsByEmail(String emailId) throws Exception {
        return daoFactory.getPostDAO().findByEmail(emailId);
    }

    @Override
    public List<PostDTO> getPostsByTag(String tag) throws Exception {
        return daoFactory.getPostDAO().findByTag(tag);
    }

    @Override
    public Set<String> getAllTags() throws Exception {
        List<String> tags = daoFactory.getPostDAO().findAllTags();
        return new HashSet<>(tags);
    }

    @Override
    public boolean deletePost(int id, String emailId) throws Exception {
        PostDTO post = daoFactory.getPostDAO().findById(id);
        if (post == null) {
            throw new PostNotFoundException("No Post exist with the given Post Id");
        } else if (post.getEmailId().equals(emailId)) {
            return daoFactory.getPostDAO().deleteById(id);
        } else {
            return false;
        }
    }
}
