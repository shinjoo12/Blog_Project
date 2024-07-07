package com.ohgiraffers.blog.jooyeon.dto;

import java.util.Date;

public class BlogDTO {



    private String blogTitle;
    private String blogContent;

    public BlogDTO() {
    }

    public BlogDTO(String blogContent, String blogTitle) {
        this.blogContent = blogContent;
        this.blogTitle = blogTitle;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    @Override
    public String toString() {
        return "BlogDTO{" +
                "blogTitle='" + blogTitle + '\'' +
                ", blogContent='" + blogContent + '\'' +
                '}';
    }

    public static Integer getId() {
        return getId();
    }

    public void setCreateDate(Date createDate) {
    }

    public void setId(String id) {
    }
}
