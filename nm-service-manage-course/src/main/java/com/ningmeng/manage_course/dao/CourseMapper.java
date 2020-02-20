package com.ningmeng.manage_course.dao;

import com.github.pagehelper.Page;
import com.ningmeng.framework.domain.course.Category;
import com.ningmeng.framework.domain.course.CourseBase;
import com.ningmeng.framework.domain.course.ext.CategoryNode;
import com.ningmeng.framework.domain.course.ext.CourseInfo;
import com.ningmeng.framework.domain.course.request.CourseListRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator.
 */
@Mapper
public interface CourseMapper {
   CourseBase findCourseBaseById(String id);

   Page<CourseInfo> findCourseListPage(String courseListRequest);
   CategoryNode findList();

}
