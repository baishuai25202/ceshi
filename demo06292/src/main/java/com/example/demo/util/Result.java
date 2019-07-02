package com.example.demo.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ProjectName: demo06292
 * @Package: com.example.demo.util
 * @Author: ${白帅}
 * @Description: ${description}
 * @Date: 2019/6/30 14:45
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Result {
    private int code;
    private String msg;
}
