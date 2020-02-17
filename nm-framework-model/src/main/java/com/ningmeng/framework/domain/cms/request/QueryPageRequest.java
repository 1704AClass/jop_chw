package com.ningmeng.framework.domain.cms.request;

import com.ningmeng.framework.model.request.RequestData;
/***
 *页面查询自己使用的类
 *
 *
 */

public class QueryPageRequest extends RequestData {
    //站点ID
    private String siteId;
    //页面ID
    private String pageId;
    //页面名称
    private String pageName;
    //别名
    private String pageAliase;
    //模板ID
    private String templateId;

}
