package com.ningmeng.api.couresapi;


import com.ningmeng.framework.domain.course.Teachplan;
import com.ningmeng.framework.domain.course.ext.TeachplanNode;
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

}
