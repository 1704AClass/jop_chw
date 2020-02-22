package com.ningmeng.api.couresapi;


import com.ningmeng.framework.domain.course.CourseBase;
import com.ningmeng.framework.domain.course.CourseMarket;
import com.ningmeng.framework.domain.course.CoursePic;
import com.ningmeng.framework.domain.course.Teachplan;
import com.ningmeng.framework.domain.course.ext.CategoryNode;
import com.ningmeng.framework.domain.course.ext.CourseInfo;
import com.ningmeng.framework.domain.course.ext.TeachplanNode;
import com.ningmeng.framework.domain.course.request.CourseListRequest;
import com.ningmeng.framework.domain.system.SysDictionary;
import com.ningmeng.framework.model.response.QueryResponseResult;
import com.ningmeng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "课程管理接口",description = "课程管理接口，提供课程的增、删、改、查")
public interface CouresControllerApi {
    //根据couresId查询数据
    @ApiOperation("课程计划查询")
    public TeachplanNode findTeachplanList(String couresId);

    @ApiOperation("添加课程计划")
    public ResponseResult AddTeachplan(Teachplan teachplan);

    @ApiOperation("查询分页列表")
    public QueryResponseResult findCourseList(int page, int size, String id);

    @ApiOperation("课程分类查询")
    public CategoryNode findList();

    @ApiOperation("数据字典查询接口")
    public SysDictionary getByType(String type);

    @ApiOperation("获取基础课程信息")
    public CourseBase getCourseBaseById(String courseBaseid) throws RuntimeException;

    @ApiOperation("修改基础课程信息")
    public ResponseResult updateCourseBase(String id,CourseBase courseBase );
    @ApiOperation("修改基础课程信息")
    public CourseMarket getCourseMarketById(String courseId);

    public ResponseResult updateCourseMarket(String id,CourseMarket courseMarket);
    @ApiOperation("添加课程图片")
    public ResponseResult addCoursePic(String courseId,String pic);
    @ApiOperation("获取课程基础信息")
    public CoursePic findCoursePic(String courseId);
    @ApiOperation("删除课程图片")
    public ResponseResult deleteCoursePic(String courseId);
}
