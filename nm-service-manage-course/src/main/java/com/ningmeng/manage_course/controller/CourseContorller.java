package com.ningmeng.manage_course.controller;

import com.ningmeng.api.couresapi.CouresControllerApi;

import com.ningmeng.framework.domain.course.CourseBase;
import com.ningmeng.framework.domain.course.CourseMarket;
import com.ningmeng.framework.domain.course.CoursePic;
import com.ningmeng.framework.domain.course.Teachplan;
import com.ningmeng.framework.domain.course.ext.CategoryNode;
import com.ningmeng.framework.domain.course.ext.TeachplanNode;
import com.ningmeng.framework.domain.course.request.CourseListRequest;
import com.ningmeng.framework.domain.system.SysDictionary;
import com.ningmeng.framework.model.response.QueryResponseResult;
import com.ningmeng.framework.model.response.ResponseResult;
import com.ningmeng.manage_course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Override
    public QueryResponseResult findCourseList(int page, int size, String id) {
        return courseService.findCourseList(page,size,id);
    }

    @Override
    public CategoryNode findList() {
        return courseService.findList();
    }

    @Override
    public SysDictionary getByType(String type) {
        return courseService.getByType(type);
    }

    @Override
    public CourseBase getCourseBaseById(String courseBaseid) throws RuntimeException {
        return courseService.getCourseBaseById(courseBaseid);
    }

    @Override
    public ResponseResult updateCourseBase(String id, CourseBase courseBase) {
        return courseService.updateCourseBase(id,courseBase);
    }

    @Override
    public CourseMarket getCourseMarketById(String courseId) {
        return courseService.getCourseMarketById(courseId);
    }

    @Override
    public ResponseResult updateCourseMarket(String id, CourseMarket courseMarket) {

        return courseService.updateCourseMarket(id,courseMarket);
    }

    @Override
    public ResponseResult addCoursePic(String courseId,String pic){
        return courseService.addCoursePic(courseId,pic);
    }
    @GetMapping("/coursepic/findCoursePic/{courseId}")
    @Override
    public CoursePic findCoursePic(@PathVariable("courseId") String courseId) {
        return courseService.findCoursePic(courseId);
    }
    @Override
    @DeleteMapping("/coursepic/delete")
    public ResponseResult deleteCoursePic(@RequestParam("courseId") String courseId) {
        return courseService.deleteCoursePic(courseId);
    }

}
