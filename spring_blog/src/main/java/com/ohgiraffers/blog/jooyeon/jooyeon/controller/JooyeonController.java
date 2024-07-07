package com.ohgiraffers.blog.jooyeon.jooyeon.controller;

import com.ohgiraffers.blog.jooyeon.dto.BlogDTO;

import com.ohgiraffers.blog.jooyeon.service.JooyeonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/jooyeon")
public class JooyeonController {

    private JooyeonService jooyeonService;
    // 현재 블로그 게시물 정보를 저장할 객체
    private BlogDTO currentBlog;

    // JooyeonService 의존성을 주입받는 생성자
    @Autowired
    public JooyeonController(JooyeonService jooyeonService) {

        this.jooyeonService = jooyeonService;
    }
    // "/regist" 경로에 대한 GET 요청을 처리
    @GetMapping("/regist")
    public String jyregist() {
        // "jooyeon/regist" 뷰를 반환
        return "/jooyeon/regist";

    }

    @GetMapping("jypage")
    public String jypage(Model model) {
        // currentBlog가 null이 아닌 경우 모델에 블로그 제목과 내용을 추가
        if (currentBlog != null) {
            model.addAttribute("blogTitle", currentBlog.getBlogTitle());
            model.addAttribute("blogContent", currentBlog.getBlogContent());
        }
        return "/jooyeon/jypage";
    }
    // 기본 경로에 대한 POST 요청을 처리
    @PostMapping
    public ModelAndView regist(BlogDTO blogDTO, ModelAndView mv) {
        // 블로그 제목이 null이거나 빈 문자열인 경우 "/jooyeon/regist"로 리다이렉트
        if (blogDTO.getBlogTitle() == null || blogDTO.getBlogTitle().equals("")) {
            mv.setViewName("redirect:/jooyeon/regist");
            return mv;
        }
        // 블로그 내용이 null이거나 빈 문자열인 경우 "/jooyeon/regist"로 리다이렉트
        if (blogDTO.getBlogContent() == null || blogDTO.getBlogContent().equals("")) {
            mv.setViewName("redirect:/jooyeon/regist");
            return mv;
        }
        // 블로그 게시물을 저장하고 결과를 반환
        int result = jooyeonService.post(blogDTO);
        // 결과가 0 이하인 경우 오류 페이지로 이동
        if (result <= 0) {
            mv.setViewName("error/page");
        } else {
            // 성공적으로 저장된 경우 currentBlog를 업데이트하고 "/jooyeon/jypage"로 리다이렉트
            currentBlog = blogDTO;
            mv.setViewName("redirect:/jooyeon/jypage");
        }

        return mv;
    }
    // "/edit/{id}" 경로에 대한 POST 요청을 처리
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        // 특정 ID에 해당하는 블로그 게시물을 가져옴
        BlogDTO blogDTO = jooyeonService.getBlogById(id);
        // 블로그 게시물이 없는 경우 "/jooyeon/registList"로 리다이렉트
        if (blogDTO == null) {
            return "redirect:/jooyeon/registList";
        }
        // 모델에 블로그 게시물을 추가
        model.addAttribute("blogDTO", blogDTO);
        return "jooyeon/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Integer id, @RequestParam(name = "confirm", defaultValue = "false") boolean confirm, Model model) {
        if (!confirm) {
            BlogDTO blogDTO = jooyeonService.getBlogById(id);
            if (blogDTO == null) {
                return "redirect:/jooyeon/jypage";
            }
            model.addAttribute("blogDTO", blogDTO);
            return "jooyeon/delete";
        } else {
            // currentBlog가 존재하고 해당 ID와 일치하는 경우 currentBlog를 null로 설정
            if (currentBlog != null && currentBlog.getId().equals(id)) {
                currentBlog = null; // 현재 블로그 삭제
            }
            jooyeonService.deleteBlogPost(id); // 블로그 포스트 삭제
            return "redirect:/jooyeon/jypage";
        }


    }
    @GetMapping("/registList")
    public String registList(Model model) {
        List<BlogDTO> blogs = jooyeonService.listBlogs();
        model.addAttribute("blogs", blogs);
        return "jooyeon/registList";
    }

}


