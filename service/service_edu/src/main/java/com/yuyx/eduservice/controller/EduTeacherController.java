package com.yuyx.eduservice.controller;


import com.yuyx.commonutils.R;
import com.yuyx.eduservice.entity.EduTeacher;
import com.yuyx.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-18
 */
@RestController
@RequestMapping("/eduservice/edu-teache")
public class EduTeacherController {
    //访问地址：http://localhost:8001/eduservice/edu-teacher/findAll

    //把service注入
    @Autowired
    private EduTeacherService eduTeacherService;
    //1 查询讲师表所有得信息
    //
    @GetMapping("findAll")
    public R findAllteacher (){
        //调用services;
        List<EduTeacher> list = eduTeacherService.list(null);
        return list;
    }

    @ApiOperation("逻辑删除老师")
    //2.逻辑删除讲师表
    @DeleteMapping("{id}")
    private  boolean removeTeacher(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        boolean flag = eduTeacherService.removeById(id);
        return  flag;
        ///
    }
}

