package com.ningmeng.manage_course.pojo;

import com.ningmeng.framework.domain.course.Teachplan;
import lombok.Data;
import lombok.ToString;

import java.util.List;
/**
 * 自定义课程计划类
 * */
@Data
@ToString
public class TeachplanNode extends Teachplan {
    List<TeachplanNode> children;

}
