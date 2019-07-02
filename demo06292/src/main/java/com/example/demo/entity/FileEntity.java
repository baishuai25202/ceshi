package com.example.demo.entity;/**
 * @title: FileEntity
 * @projectName boot062403
 * @description: TODO
 * @author ${白帅}
 * @date 2019/6/2613:40
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;


/**
 *@ClassName FileEntity
 *@Author 白帅
 *@Date 2019/6/26 13:40
 *@Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileEntity {
    private long fileId;

    /*****
     * 原文件名
     ***/

    private String titleOrig;

    /*****
     * 修改后文件名
     ***/

    private String titleAlter;

    /*****
     * 文件大小
     ***/

    private String size;

    /*****
     * 文件类型
     ***/

    private String type;

    /*****
     * 文件保存路径
     ***/

    private String path;

    /*****
     * 文件上传时间
     ***/

    private Timestamp uploadTime;
}
