package com.ohgiraffers.blog.hwayeon.controller;

import com.ohgiraffers.blog.hwayeon.service.HwayeonService;
import com.ohgiraffers.blog.hwayeon.model.dto.hwayeonBlogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hwayeon")
public class HwayeonController {

    private final HwayeonService hwayeonService;
    private hwayeonBlogDTO currentBlog;

    @Autowired
    public HwayeonController(HwayeonService hwayeonService) {
        this.hwayeonService = hwayeonService;
    }

    @GetMapping("/main")
    public String mainPage(Model model) {
        // 데이터베이스에서 블로그 정보 가져오기
        String blogTitle = "제목입니다";
        String blogContent = "내용 요약입니다.";

        // 모델에 데이터 추가
        model.addAttribute("blogTitle", blogTitle);
        model.addAttribute("blogContent", blogContent);

        return "hwayeon/main";
    }

    @GetMapping("/editpage")
    public String editpagePage() {
        return "hwayeon/editpage";
    }

    @GetMapping("/postpage")
    public String postPage(Model model) {
        if (currentBlog != null) {
            model.addAttribute("blogTitle", currentBlog.getBlogTitle());
            model.addAttribute("blogContent", currentBlog.getBlogContent());
        }
        return "hwayeon/postpage";
    }

    @PostMapping
    public ModelAndView postBlog(hwayeonBlogDTO hyblogDTO, ModelAndView mv) {
        if (hyblogDTO.getBlogTitle() == null || hyblogDTO.getBlogTitle().equals("")) {
            mv.setViewName("redirect:/hwayeon/editpage");
            return mv;
        }
        if (hyblogDTO.getBlogContent() == null || hyblogDTO.getBlogContent().equals("")) {
            mv.setViewName("redirect:/hwayeon/editpage");
            return mv;
        }

        int result = hwayeonService.post(hyblogDTO);

        if (result <= 0) {
            mv.setViewName("error/page");
        } else {
            currentBlog = hyblogDTO;
            mv.setViewName("redirect:/hwayeon/postpage");
        }

        return mv;
    }
}
