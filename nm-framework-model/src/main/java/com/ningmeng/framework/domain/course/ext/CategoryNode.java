package com.ningmeng.framework.domain.course.ext;

import com.ningmeng.framework.domain.course.Category;
import lombok.Data;
import lombok.ToString;

import java.util.List;


@Data
@ToString
public class CategoryNode extends Category {

    List<CategoryNode> children;

}