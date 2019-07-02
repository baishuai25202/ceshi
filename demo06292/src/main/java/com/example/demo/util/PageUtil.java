package com.example.demo.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;

/**
 * @ProjectName: demo06292
 * @Package: com.example.demo.util
 * @Author: ${白帅}
 * @Description: ${description}
 * @Date: 2019/6/29 21:46
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageUtil {
    private String name;
    private String startDate;
    private String endDate;
    private Integer page;
    private Integer count;


    public Integer getPage() {
       return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
