//package com.ohgiraffers.blog.jooyeon.jooyeon.service;
//
//import com.ohgiraffers.blog.jooyeon.dto.BlogDTO;
//import com.ohgiraffers.blog.jooyeon.entity.JooyeonBlog;
//import com.ohgiraffers.blog.jooyeon.repository.JooyeonRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class JooyeonService {
//
//    private final JooyeonRepository jooyeonRepository;
//
//
//    @Autowired
//    public JooyeonService(JooyeonRepository jooyeonRepository) {
//        this.jooyeonRepository = jooyeonRepository;
//    }
//
//    @Transactional
//    public int post(BlogDTO blogDTO) {
//        List<JooyeonBlog> jooyeonBlogs = jooyeonRepository.findAll();
//        // 도메인 로직
//        for (JooyeonBlog blog : jooyeonBlogs) {
//            if (blog.getBlogTitle().equals(blogDTO.getBlogTitle())) {
//                return 0;
//            }
//        }
//
//        JooyeonBlog saveBlog = new JooyeonBlog();
//        saveBlog.setBlogContent(blogDTO.getBlogContent());
//        saveBlog.setBlogTitle(blogDTO.getBlogTitle());
//        saveBlog.setCreateDate(new Date());
//        JooyeonBlog result = jooyeonRepository.save(saveBlog);
//
//        return result != null ? 1 : 0;
//    }
//
//    public List<BlogDTO> listBlogs() {
//        List<JooyeonBlog> jooyeonBlogs = jooyeonRepository.findAll();
//        List<BlogDTO> blogDTOs = new ArrayList<>();
//
//        for (JooyeonBlog blog : jooyeonBlogs) {
//            BlogDTO blogDTO = new BlogDTO();
//            blogDTO.setBlogTitle(blog.getId());
//            blogDTO.setBlogTitle(blog.getBlogTitle());
//            blogDTO.setBlogContent(blog.getBlogContent());
//            blogDTO.setCreateDate(blog.getCreateDate());
//            blogDTOs.add(blogDTO);
//        }
//
//        return blogDTOs;
//    }
//
//    public BlogDTO getBlogById(Integer id) {
//        JooyeonBlog blog = jooyeonRepository.findById(id).orElse(null);
//        if (blog != null) {
//            BlogDTO blogDTO = new BlogDTO();
//            blogDTO.setId(blog.getId());
//            blogDTO.setBlogTitle(blog.getBlogTitle());
//            blogDTO.setBlogContent(blog.getBlogContent());
//            blogDTO.setCreateDate(blog.getCreateDate());
//            return blogDTO;
//        }
//        return null;
//    }
//
//    @Transactional
//    public void deleteBlogPost(Integer id) {
//        jooyeonRepository.deleteById(id);
//    }
//
//    @org.springframework.transaction.annotation.Transactional
//    public void savePost(BlogDTO blogDTO) {
//        JooyeonBlog blog = new JooyeonBlog();
//        blog.setBlogTitle(blogDTO.getBlogTitle());
//        blog.setBlogContent(blogDTO.getBlogContent());
//        blog.setCreateDate(new Date());
//        jooyeonRepository.save(blog);
//    }
//
//
//    @org.springframework.transaction.annotation.Transactional
//    public void updateBlog(BlogDTO blogDTO) {
//        JooyeonBlog blog = jooyeonRepository.findById(blogDTO.getId()).orElse(null);
//        if (blog != null) {
//            blog.setBlogTitle(blogDTO.getBlogTitle());
//            blog.setBlogContent(blogDTO.getBlogContent());
//            jooyeonRepository.save(blog);
//        }
//    }
//
//
//
//
//
//}
