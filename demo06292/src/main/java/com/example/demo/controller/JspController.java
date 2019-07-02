package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @ProjectName: demo06292
 * @Package: com.example.demo.controller
 * @Author: ${白帅}
 * @Description: ${description}
 * @Date: 2019/7/1 15:52
 * @Version: 1.0
 */
@Controller
@RequestMapping("/jsp")
public class JspController {

    @RequestMapping("/test")
    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setAttribute("result","成功登录jsp界面");
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
    @RequestMapping("/put")
    public void put(){

    }

}
