package com.ohgiraffers.blog.jooyeon.service;

import com.ohgiraffers.blog.jooyeon.dto.BlogDTO;
import com.ohgiraffers.blog.jooyeon.entity.JooyeonBlog;
import com.ohgiraffers.blog.jooyeon.repository.JooyeonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JooyeonService {

    private final JooyeonRepository jooyeonRepository;


    @Autowired
    public JooyeonService(JooyeonRepository jooyeonRepository) {
        this.jooyeonRepository = jooyeonRepository;
    }

    @Transactional
    public int post(BlogDTO blogDTO) {
        List<JooyeonBlog> jooyeonBlogs = jooyeonRepository.findAll();
        // 도메인 로직
        for (JooyeonBlog blog : jooyeonBlogs) {
            if (blog.getBlogTitle().equals(blogDTO.getBlogTitle())) {
                return 0;
            }
        }

        JooyeonBlog saveBlog = new JooyeonBlog();
        saveBlog.setBlogContent(blogDTO.getBlogContent());
        saveBlog.setBlogTitle(blogDTO.getBlogTitle());
        saveBlog.setCreateDate(new Date());
        JooyeonBlog result = jooyeonRepository.save(saveBlog);

        return result != null ? 1 : 0;
    }

    public List<BlogDTO> listBlogs() {
        List<JooyeonBlog> jooyeonBlogs = jooyeonRepository.findAll();
        List<BlogDTO> blogDTOs = new ArrayList<>();

        for (JooyeonBlog blog : jooyeonBlogs) {
            BlogDTO blogDTO = new BlogDTO();
            blogDTO.setId(String.valueOf(blog.getBlogNo()));
            blogDTO.setBlogTitle(blog.getBlogTitle());
            blogDTO.setBlogContent(blog.getBlogContent());
            blogDTO.setCreateDate(blog.getCreateDate());
            blogDTOs.add(blogDTO); // 조회된 블로그 포스트를 DTO 리스트에 추가
        }

        return blogDTOs;
    }
    // 추가된 블로그 포스트 ID로 조회 메서드
    public BlogDTO getBlogById(Integer id) {
        JooyeonBlog blog = jooyeonRepository.findById(id).orElse(null); // ID로 블로그 포스트 조회
        if (blog != null) {
            BlogDTO blogDTO = new BlogDTO();
            blogDTO.setId(String.valueOf(blog.getBlogNo())); // int 값을 String으로 변환
            blogDTO.setBlogTitle(blog.getBlogTitle());
            blogDTO.setBlogContent(blog.getBlogContent());
            blogDTO.setCreateDate(blog.getCreateDate());
            return blogDTO; // 조회된 블로그 포스트를 DTO로 변환하여 반환
        }
        return null; // 블로그 포스트가 없으면 null 반환
    }

    @Transactional
    public void deleteBlogPost(Integer id) {

        jooyeonRepository.deleteById(id);
    }

    @Transactional
    public void savePost(BlogDTO blogDTO) {
        JooyeonBlog blog = new JooyeonBlog();
        blog.setBlogTitle(blogDTO.getBlogTitle());
        blog.setBlogContent(blogDTO.getBlogContent());
        blog.setCreateDate(new Date());
        jooyeonRepository.save(blog); // 블로그 포스트 저장
    }


    @Transactional
    public void updateBlog(BlogDTO blogDTO) {
        JooyeonBlog blog = jooyeonRepository.findById(blogDTO.getId()).orElse(null);
        if (blog != null) {
            blog.setBlogTitle(blogDTO.getBlogTitle());
            blog.setBlogContent(blogDTO.getBlogContent());
            jooyeonRepository.save(blog); // 블로그 포스트 업데이트
        }
    }





}
