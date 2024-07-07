package com.ohgiraffers.blog.jaesuk.repository;

import com.ohgiraffers.blog.jaesuk.model.entity.JaesukBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JeasuckRepository extends JpaRepository<JaesukBlog, Integer> {


}
