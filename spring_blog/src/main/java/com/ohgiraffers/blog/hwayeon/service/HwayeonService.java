package com.ohgiraffers.blog.hwayeon.service;

import com.ohgiraffers.blog.hwayeon.model.dto.hwayeonBlogDTO;
import com.ohgiraffers.blog.hwayeon.model.entity.HwayeonBlog;
import com.ohgiraffers.blog.hwayeon.repository.HwayeonRepository;
import com.ohgiraffers.blog.jaesuk.model.dto.BlogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class HwayeonService {

    private final HwayeonRepository hwayeonRepository;

    @Autowired
    public HwayeonService(HwayeonRepository hwayeonRepository) {
        this.hwayeonRepository = hwayeonRepository;
    }

    @Transactional
    public int post(BlogDTO blogDTO) {
        List<HwayeonBlog> hwayeonBlogs = hwayeonRepository.findAll();
        // 도메인 로직
        for (HwayeonBlog blog: hwayeonBlogs) {
            if(blog.getBlogTitle().equals(blogDTO.getBlogTitle())){
                return 0;
            }
        }

        HwayeonBlog saveBlog = new HwayeonBlog();
        saveBlog.setBlogContent(blogDTO.getBlogContent());
        saveBlog.setBlogTitle(blogDTO.getBlogTitle());
        saveBlog.setCreateDate(new Date());
        HwayeonBlog result  = hwayeonRepository.save(saveBlog);

        int resultValue = 0;

        if(result != null){
            resultValue = 1;
        }

        return resultValue;
    }

    public int post(hwayeonBlogDTO hyblogDTO) {
        return 0;
    }
}
