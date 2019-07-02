package com.example.demo.entity;

/**
 * table name:  tb_class
 * author name: bai
 * create time: 2019-06-24 15:44:01
 */ 
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class TbClass{

	private int classid;
	private String classname;
	private int classsize;
	private int gradeid;
	private String remark;
    private String gradename;

}

