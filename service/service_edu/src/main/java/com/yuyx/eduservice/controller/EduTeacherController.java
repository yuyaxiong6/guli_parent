package com.yuyx.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuyx.commonutils.R;
import com.yuyx.eduservice.entity.EduTeacher;
import com.yuyx.eduservice.entity.vo.TeacherQuery;
import com.yuyx.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    @ApiOperation(value="所有讲师列表")@GetMapping
    public R list(){
        List<EduTeacher> list= eduTeacherService.list(null);
        return R.ok().data("items",list);
    }

    @ApiOperation("逻辑删除老师")
    //2.逻辑删除讲师表
    @DeleteMapping("{id}")
    private  R removeTeacher(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        boolean flag = eduTeacherService.removeById(id);
        if(flag){
            return R.ok();
            /////////
        }
        else {
            return  R.error();
            ///结束4/////hahha
        }
    }


    ////3.分页功能
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        //调用方法进行分页
        //分页的时候会把，底层封装，把分页中的数据都封装到pageparam中
     int i=10/0;
        eduTeacherService.page(pageParam,null);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();    //总记录数
        return R.ok().data("total", total).data("rows", records); // 数据List集合
    }

    //4.条件查询带分页的方法
    @PostMapping("PageTeacherCondition/{current}/{limit}")
    public  R pageTeacherCondition(@PathVariable long current ,@PathVariable long limit,
                                   @RequestBody (required = false) TeacherQuery teacherQuery )
    {
        //创建Page对象
        Page <EduTeacher> pageTeacher=new Page<>(current,limit);
        //构造条件
        QueryWrapper<EduTeacher>wrapper=new QueryWrapper<>();
        //动态sql
        //判断条件值是否为空，如果不为空拼接条件
        String name = teacherQuery.getName();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        Integer level = teacherQuery.getLevel();
        //StringUtils 工具类，判断是否为空
        if (!StringUtils.isEmpty(name)){
            //构建条件
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level) ) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }
        //调用方法实现分页查询
        eduTeacherService.page(pageTeacher,wrapper);
        List<EduTeacher> records = pageTeacher.getRecords();
        long total = pageTeacher.getTotal();
        return R.ok().data("total", total).data("rows", records);

    }
    //5.根据讲师id进行查询
    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public R getById(  @ApiParam(name = "id", value = "讲师ID", required = true)
                           @PathVariable String id){
         EduTeacher teacher = eduTeacherService.getById(id);
         return R.ok().data("item", teacher);
         }
     //6.讲师修改功能
    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("{id}")
    public R updateById( @ApiParam(name = "id", value = "讲师ID", required = true)
                             @PathVariable String id, @ApiParam(name = "teacher", value = "讲师对象", required = true)
    @RequestBody EduTeacher teacher){
         teacher.setId(id);
        eduTeacherService.updateById(teacher);
         return R.ok();
         }


}

