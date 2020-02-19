package com.ningmeng.manage_course.controller;

import com.ningmeng.api.couresapi.CouresControllerApi;

import com.ningmeng.framework.domain.course.Teachplan;
import com.ningmeng.framework.domain.course.ext.TeachplanNode;
import com.ningmeng.framework.model.response.ResponseResult;
import com.ningmeng.manage_course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseContorller implements CouresControllerApi {
    @Autowired
    private CourseService courseService;

    @GetMapping("/teachplan/findTeachplanList/{couresId}")
    @Override
    public TeachplanNode findTeachplanList(@PathVariable("couresId") String couresId) {
        return courseService.findTeachplanList(couresId);
    }

    @Override
    public ResponseResult AddTeachplan(Teachplan teachplan) {
        return courseService.addTeachplan(teachplan);
    }
}
