package com.example.demo.service;

import com.example.demo.entity.Dept;
import com.example.demo.entity.Emp;
import com.example.demo.util.PageUtil;
import com.example.demo.util.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: demo06292
 * @Package: com.example.demo.service
 * @Author: ${白帅}
 * @Description: ${description}
 * @Date: 2019/6/29 20:00
 * @Version: 1.0
 */
public interface EmpService {
    PageInfo<Emp> getEmp(PageUtil pageUtil);

    List<Dept> getMenu();

    Result addEmp(Emp emp);

    Emp updateEmp(Integer empno);

    Result endUpdateEmp(Emp emp);

    Result deleteEmp(Integer empno);

    Result deleteAllEmp(Integer[] multipleSelection);
}
