package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @title: Emp
 * @projectName boot062403
 * @description: TODO
 * @author ${白帅}
 * @date 2019/6/2518:18
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    private int empno;
    private String ename;
    private String job;
    private String sex;
    private int deptno;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hiredate;
    private int roleid;
    private String dname;
}
