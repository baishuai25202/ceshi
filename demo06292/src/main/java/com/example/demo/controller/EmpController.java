package com.example.demo.controller;

import com.example.demo.entity.Dept;
import com.example.demo.entity.Emp;
import com.example.demo.service.EmpService;
import com.example.demo.util.PageUtil;
import com.example.demo.util.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ProjectName: demo06292
 * @Package: com.example.demo.controller
 * @Author: ${白帅}
 * @Description: ${description}
 * @Date: 2019/6/29 19:59
 * @Version: 1.0
 */
@Controller
@RequestMapping("/login")
public class EmpController {

    @Autowired
    private EmpService service;

    @RequestMapping("/home")
    public String addHome(){
        System.out.println("进入到home中");
        return "home";
    }

    /**
     * 分页查询+模糊搜索
     * @param pageUtil
     * @return
     */
    @RequestMapping("/getEmp")
    @ResponseBody
    public PageInfo<Emp> getEmp(@RequestBody PageUtil pageUtil){
        return service.getEmp(pageUtil);
    }
    @RequestMapping("/getMenu")
    @ResponseBody
    public List<Dept> getMenu(){
        return  service.getMenu();
    }
    /**
     * 添加操作
     */
    @RequestMapping("/addEmp")
    @ResponseBody
    public Result addEmp(@RequestBody Emp emp){
        return service.addEmp(emp);
    }
    /**
     * 根据id查询个人
     */
    @RequestMapping("/updateEmp")
    @ResponseBody
    public Emp updateEmp(Integer empno){
        return service.updateEmp(empno);
    }
    /**
     * 更改信息
     */
    @RequestMapping("/endUpdateEmp")
    @ResponseBody
    public Result endUpdateEmp(@RequestBody Emp emp){
        return service.endUpdateEmp(emp);
    }
    /**
     * 单个删除
     */
    @RequestMapping("/deleteEmp")
    @ResponseBody
    public Result deleteEmp(Integer empno){
        return service.deleteEmp(empno);
    }
    /**
     * 删除全部
     */
    @RequestMapping("/deleteAllEmp")
    @ResponseBody
    public Result deleteAllEmp(@RequestBody Integer[] len){
        System.out.println(len);
        return service.deleteAllEmp(len);
    }
    @RequestMapping("/excel")
    public Result excel(){
            return null;
    }
    /**
     * gitHub提交测试
     */
    @RequestMapping("/gitHub")
    public String gitHub(){
        return null;
    }

}
