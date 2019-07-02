package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title: Dept
 * @projectName boot062403
 * @description: TODO
 * @author ${白帅}
 * @date 2019/6/2518:20
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    private int deptno;
    private String dname;
}
