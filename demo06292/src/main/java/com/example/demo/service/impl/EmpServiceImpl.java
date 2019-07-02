package com.example.demo.service.impl;

import com.example.demo.entity.Dept;
import com.example.demo.entity.Emp;
import com.example.demo.mapper.EmpMapper;
import com.example.demo.service.EmpService;
import com.example.demo.util.PageUtil;
import com.example.demo.util.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


/**
 * @ProjectName: demo06292
 * @Package: com.example.demo.service.impl
 * @Author: ${白帅}
 * @Description: ${description}
 * @Date: 2019/6/29 20:01
 * @Version: 1.0
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper mapper;

    @Override
    public PageInfo<Emp> getEmp(PageUtil pageUtil) {
        System.out.println(pageUtil.toString());
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getCount());
        List<Emp> emp = mapper.getEmp(pageUtil);
        PageInfo<Emp> info = new PageInfo(emp);
       return info;
    }

    /**
     * 获取部门列表信息
     * @return
     */
    @Override
    public List<Dept> getMenu() {
        return mapper.getMenu();
    }

    @Override
    public Result addEmp(Emp emp) {
        int id=mapper.addEmp(emp);
        if (id==0){
            return new Result(400,"添加失败");
        }
        return new Result(200,"添加成功");
    }

    /**
     * 根据id查询信息
     * @param empno
     * @return
     */
    @Override
    public Emp updateEmp(Integer empno) {
        return mapper.updateEmp(empno);
    }

    @Override
    public Result endUpdateEmp(Emp emp) {
        int id=mapper.endUpdateEmp(emp);
        if (id==0){
          return   new Result(400,"修改失败");
        }
        return new Result(200,"修改成功");

    }

    @Override
    public Result deleteEmp(Integer empno) {
        int id=mapper.deleteEmp(empno);
        if (id==0){
            return   new Result(400,"删除失败");
        }
        return new Result(200,"删除成功");

    }

    @Override
    public Result deleteAllEmp(Integer[] multipleSelection) {
        List<Integer> list = Arrays.asList(multipleSelection);
        int id=mapper.deleteAllEmp(list);
        if (id==0){
            return   new Result(400,"删除失败");
        }
        return new Result(200,"删除成功");
    }
}
