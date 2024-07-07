package com.ohgiraffers.blog.jaesuk.controller;

import com.ohgiraffers.blog.jaesuk.model.dto.BlogDTO;
import com.ohgiraffers.blog.jaesuk.service.JeasukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/jaesuck")
public class JeasuckController {

    private final JeasukService jeasukService;

    @Autowired
    public JeasuckController(JeasukService jeasukService) {
        this.jeasukService = jeasukService;
    }

    @GetMapping
    public String indexJaesuck(){
        return "jaesuck/page";
    }

    @PostMapping
    public ModelAndView postBlog(BlogDTO blogDTO, ModelAndView mv){

        if(blogDTO.getBlogTitle() == null || blogDTO.getBlogTitle().equals("")){
            mv.setViewName("redirect:jaesuck");
        }
        if(blogDTO.getBlogContent() == null || blogDTO.getBlogContent().equals("")){
            mv.setViewName("redirect:jaesuck");
        }

        int result = jeasukService.post(blogDTO);

        if(result <= 0){
            mv.setViewName("error/page");
        }else{
            mv.setViewName("jaesuck/page");
        }

        return mv;
    }

}
