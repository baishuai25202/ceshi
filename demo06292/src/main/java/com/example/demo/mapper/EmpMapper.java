package com.example.demo.mapper;

import com.example.demo.entity.Dept;
import com.example.demo.entity.Emp;
import com.example.demo.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ProjectName: demo06292
 * @Package: com.example.demo.mapper
 * @Author: ${白帅}
 * @Description: ${description}
 * @Date: 2019/6/29 20:04
 * @Version: 1.0
 */
@Mapper
public interface EmpMapper {
    List<Emp> getEmp(PageUtil pageUtil);

    Integer getCount();

    List<Dept> getMenu();

    int addEmp(Emp emp);

    Emp updateEmp(Integer empno);

    int endUpdateEmp(Emp emp);

    int deleteEmp(Integer empno);

    int deleteAllEmp(List<Integer> list);
}
