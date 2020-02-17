package com.ningmeng.api.cmsapi;

import com.ningmeng.framework.domain.cms.CmsPage;
import com.ningmeng.framework.domain.cms.request.QueryPageRequest;
import com.ningmeng.framework.domain.cms.response.CmsPageResult;
import com.ningmeng.framework.model.response.QueryResponseResult;
import com.ningmeng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@Api(value = "cms页面管理接口",description = "cms页面管理接口，提供页面的增、删、改、查")
public interface CmsPageContorllerApi {
    @ApiOperation("分页查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value="每页记录数",required=true,paramType="path",dataType="int")
    })
    //分页
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);
    //添加
    @ApiOperation("添加方法")
    public CmsPageResult add(CmsPage cmsPage);

    @ApiOperation("按照ID查询")
    public  CmsPage findById(String id);

    @ApiOperation("更新")
    public  CmsPageResult update(String id,CmsPage cmsPage);

    @ApiOperation("删除")
    public ResponseResult delete(String id);

    @ApiOperation("发布页面")
    public ResponseResult post(String pageId);
}
