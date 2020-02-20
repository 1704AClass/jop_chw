package com.ningmeng.manage_course.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ningmeng.framework.domain.course.CourseBase;
import com.ningmeng.framework.domain.course.CourseMarket;
import com.ningmeng.framework.domain.course.Teachplan;
import com.ningmeng.framework.domain.course.ext.CategoryNode;
import com.ningmeng.framework.domain.course.ext.CourseInfo;
import com.ningmeng.framework.domain.course.ext.TeachplanNode;
import com.ningmeng.framework.domain.course.request.CourseListRequest;
import com.ningmeng.framework.domain.system.SysDictionary;
import com.ningmeng.framework.exception.CustomExceptionCast;
import com.ningmeng.framework.model.response.CommonCode;
import com.ningmeng.framework.model.response.QueryResponseResult;
import com.ningmeng.framework.model.response.QueryResult;
import com.ningmeng.framework.model.response.ResponseResult;
import com.ningmeng.manage_course.dao.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private TeachplanMapper teachplanMapper;
    @Autowired
    private TeachplanRepository repository;
    @Autowired
    private CourseBaseRepository courseBaseRepository;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private DictionaryRepository dictionaryRepository;

    @Autowired
    private CourseMarketRepository courseMarketRepository;


    public TeachplanNode findTeachplanList(String id){

        return  teachplanMapper.findTeachplanList(id);
    };
    public ResponseResult addTeachplan(Teachplan teachplan) {

        //先去判断课程id和课程计划名称是否为空  如果为空直接报错
        if(teachplan==null || StringUtils.isEmpty(teachplan.getCourseid()) || StringUtils.isEmpty(teachplan.getPname())){
            CustomExceptionCast.cast(CommonCode.FAIL);
        }
        //不为空的话 获取两者
        String courseid = teachplan.getCourseid();
        String parentid = teachplan.getParentid();
        //判断是否选择父节点，如果没有选，代表根节点 创建节点
        if(StringUtils.isEmpty(parentid)){
            parentid=getTeachplanRoot(courseid);
        }
        //如果有，取出父节点，对象的信息
        Optional<Teachplan> teachplanOptiona = repository.findById(parentid);
        if(!teachplanOptiona.isPresent()){
            CustomExceptionCast.cast(CommonCode.FAIL);
        }
        Teachplan teachplan1 = teachplanOptiona.get();
        //取出级别 来决定我是第几级别
        String grade = teachplan1.getGrade();
        System.err.println("-------------------------"+grade);
        teachplan.setParentid(parentid);
        teachplan.setStatus("0");
        if(grade.equals("1")){
            teachplan.setGrade("2");
        }else if(grade.equals("2")){
            teachplan.setGrade("3");
        }
        teachplan.setCourseid(teachplan1.getCourseid());
        repository.save(teachplan);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    private String getTeachplanRoot(String courseid) {
        //判断课程id
        Optional<CourseBase> optional = courseBaseRepository.findById(courseid);
        if(!optional.isPresent()){
            return null;
        }
        CourseBase courseBase = optional.get();
        //判断是否当前的这个课程 是否有相同的父节点信息，如果没有，就添加一个根节点（最大的一级），如果有父级，直接返回父级的id
        List<Teachplan> teachplanList = repository.findAllByAndCourseidAndAndParentid(courseid, "0");
        if(teachplanList==null || teachplanList.size()==0){
            Teachplan teachplanRoot = new Teachplan();
            teachplanRoot.setCourseid(courseid);
            teachplanRoot.setPname(courseBase.getName());
            teachplanRoot.setParentid("0");
            teachplanRoot.setGrade("1");
            teachplanRoot.setStatus("0");
            repository.save(teachplanRoot);
            return teachplanRoot.getId();
        }
        Teachplan teachplan = teachplanList.get(0);
        return teachplan.getId();
    }

    @Transactional
    public QueryResponseResult findCourseList(int page, int size, String id ){
        PageHelper.startPage(page,size);
       Page<CourseInfo> pageAll= courseMapper.findCourseListPage(id);

        QueryResult queryResult =new QueryResult();
        queryResult.setList(pageAll.getResult());
        queryResult.setTotal(pageAll.getTotal());

    return  new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }

    //查询分类
    public CategoryNode findList(){
    return courseMapper.findList();
    };

    //查询字典
    public SysDictionary getByType(String type){
        //如果没有类型传过来就直接报错
        if(type==null || StringUtils.isEmpty(type) || StringUtils.isEmpty(type)){
            CustomExceptionCast.cast(CommonCode.FAIL);
        }
    return  dictionaryRepository.findByDType(type);
    }

    //查询基本信息
    public CourseBase getCourseBaseById(String id){
        //如果没有类型传过来就直接报错
        if(id==null || StringUtils.isEmpty(id) || StringUtils.isEmpty(id)){
            CustomExceptionCast.cast(CommonCode.FAIL);
        }
        return  courseBaseRepository.getOne(id);
    }
    //简单修改课程信息
    @Transactional
    public ResponseResult updateCourseBase(String id,CourseBase courseBase){
        CourseBase courseBase1 = courseBaseRepository.getOne(id);
        if (courseBase1 == null){
            CustomExceptionCast.cast(CommonCode.FAIL);
        }
        courseBase.setId(id);
        courseBaseRepository.saveAndFlush(courseBase);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public CourseMarket getCourseMarketById(String id){
        if (id == null || "".equals(id)){
            CustomExceptionCast.cast(CommonCode.FAIL);
        }
         CourseMarket courseMarket = courseMarketRepository.getOne(id);
        return courseMarket;
    }
    @Transactional
    public ResponseResult updateCourseMarket(String id , CourseMarket courseMarket){
        if (id == null || "".equals(id)){
            CustomExceptionCast.cast(CommonCode.FAIL);
        }
        CourseMarket courseMarket1 = courseMarketRepository.getOne(id);
        if (courseMarket1 ==null){
            courseMarket.setId(id);
        }

        courseMarketRepository.saveAndFlush(courseMarket);
        return new ResponseResult(CommonCode.SUCCESS);
    }

}
