package com.ningmeng.manage_cms.service;

import com.ningmeng.framework.domain.cms.CmsPage;
import com.ningmeng.framework.domain.cms.request.QueryPageRequest;
import com.ningmeng.framework.domain.cms.response.CmsCode;
import com.ningmeng.framework.domain.cms.response.CmsPageResult;
import com.ningmeng.framework.exception.CustomException;
import com.ningmeng.framework.exception.CustomExceptionCast;
import com.ningmeng.framework.model.response.CommonCode;
import com.ningmeng.framework.model.response.QueryResponseResult;
import com.ningmeng.framework.model.response.QueryResult;
import com.ningmeng.framework.model.response.ResponseResult;
import com.ningmeng.manage_cms.dao.CmsPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PageService {
    @Autowired
    CmsPageRepository cmsPageRepository;

    public QueryResponseResult findList(int page,int size,QueryPageRequest queryPageRequest){
         if (queryPageRequest == null){
            queryPageRequest = new QueryPageRequest();
        }
        if(page<=0){
            page=1;
        }
         page=page-1;
         if(size<=0){
              size=20;
         }
        Pageable pageable=  new PageRequest(page,size);
        Page<CmsPage> all=cmsPageRepository.findAll(pageable);
        QueryResult<CmsPage> cmsPageQueryResult = new QueryResult<CmsPage>();
        cmsPageQueryResult.setList(all.getContent());
        cmsPageQueryResult.setTotal(all.getTotalElements());
        return new QueryResponseResult(CommonCode.SUCCESS,cmsPageQueryResult);
    }

    public CmsPageResult add(CmsPage cmsPage){
        if (cmsPage == null){
            CustomExceptionCast.cast(CommonCode.FAIL);
        }
            //添加页面
        CmsPage cmsPage1 = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(),cmsPage.getSiteId(),cmsPage.getPageWebPath());
        if (cmsPage1 == null){//判断有没有这跳数据
            CustomExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }
        cmsPage.setPageId(cmsPage1.getPageId());
        cmsPage.setPageCreateTime(new Date());
        cmsPageRepository.save(cmsPage);
        return new CmsPageResult(CommonCode.FAIL,null);//失败返回的参数
    }



    public CmsPage getById(String id){
        //根据id查询
            Optional<CmsPage> optional = cmsPageRepository.findById(id);
            if (optional .isPresent()){//判断是不是空
                return optional.get();//返回
            }
        return null;
    }

    public CmsPageResult update(String id,CmsPage cmsPage){
           CmsPage one= this.getById(id);
           if (one!=null){
               //模板ID
               one.setTemplateId(cmsPage.getTemplateId());
               //所属站点
               one.setSiteId(cmsPage.getSiteId());
               //页面别名
               one.setPageAliase(cmsPage.getPageAliase());
               //页面名字
               one.setPageName(cmsPage.getPageName());
               //访问路径
               one.setPageWebPath(cmsPage.getPageWebPath());
                //物理路径
               one.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
                CmsPage save = cmsPageRepository.save(one);//更新操作
                if (save!=null){
                    CmsPageResult cmsPageResult =new CmsPageResult(CommonCode.SUCCESS,save);
                    return  cmsPageResult;
                }
           }

        return new CmsPageResult(CommonCode.FAIL,null);
    }

    public ResponseResult delete(String id){
        CmsPage one= this.getById(id);
        if (one!=null){
            cmsPageRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

}
