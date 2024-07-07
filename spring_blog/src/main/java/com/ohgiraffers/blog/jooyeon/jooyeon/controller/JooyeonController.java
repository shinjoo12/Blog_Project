package com.ohgiraffers.blog.jooyeon.jooyeon.controller;

import com.ohgiraffers.blog.jooyeon.dto.BlogDTO;

import com.ohgiraffers.blog.jooyeon.service.JooyeonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/jooyeon")
public class JooyeonController {

    private JooyeonService jooyeonService;
    private BlogDTO currentBlog;

    @Autowired
    public JooyeonController(JooyeonService jooyeonService) {

        this.jooyeonService = jooyeonService;
    }

    @GetMapping("/regist")
    public String jyregist() {
        return "/jooyeon/regist";

    }

    @GetMapping("jypage")
    public String jypage(Model model) {
        if (currentBlog != null) {
            model.addAttribute("blogTitle", currentBlog.getBlogTitle());
            model.addAttribute("blogContent", currentBlog.getBlogContent());
        }
        return "/jooyeon/jypage";
    }

    @PostMapping
    public ModelAndView regist(BlogDTO blogDTO, ModelAndView mv) {

        if (blogDTO.getBlogTitle() == null || blogDTO.getBlogTitle().equals("")) {
            mv.setViewName("redirect:/jooyeon/regist");
            return mv;
        }
        if (blogDTO.getBlogContent() == null || blogDTO.getBlogContent().equals("")) {
            mv.setViewName("redirect:/jooyeon/regist");
            return mv;
        }

        int result = jooyeonService.post(blogDTO);

        if (result <= 0) {
            mv.setViewName("error/page");
        } else {
            currentBlog = blogDTO;
            mv.setViewName("redirect:/jooyeon/jypage");
        }

        return mv;
    }
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        BlogDTO blogDTO = jooyeonService.getBlogById(id);
        if (blogDTO == null) {
            return "redirect:/jooyeon/jypage";
        }
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
            if (currentBlog != null && currentBlog.getId().equals(id)) {
                currentBlog = null; // 현재 블로그 삭제
            }
            return "redirect:/jooyeon/jypage";
        }
    }

}


