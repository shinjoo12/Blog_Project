package com.ohgiraffers.blog.hwayeon.model.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "hwayeon_blog")
public class HwayeonBlog {

    @Id
    @Column(name = "blog_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int blogNo;

    @Column(name = "blog_title", unique = true, nullable = false)
    private String blogTitle;

    @Column(name = "blog_content", nullable = false, length = 3000)
    private String blogContent;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public HwayeonBlog() {
    }

    public HwayeonBlog(int blogNo, String blogTitle, String blogContent, Date createDate) {
        this.blogNo = blogNo;
        this.blogTitle = blogTitle;
        this.blogContent = blogContent;
        this.createDate = createDate;
    }

    public int getBlogNo() {
        return blogNo;
    }

    public void setBlogNo(int blogNo) {
        this.blogNo = blogNo;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "HwayeonBlog{" +
                "blogNo=" + blogNo +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogContent='" + blogContent + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
