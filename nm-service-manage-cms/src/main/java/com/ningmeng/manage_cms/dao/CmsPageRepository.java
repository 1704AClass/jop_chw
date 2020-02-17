package com.ningmeng.manage_cms.dao;

import com.ningmeng.framework.domain.cms.CmsPage;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.print.Pageable;

public interface CmsPageRepository extends MongoRepository<CmsPage,String> {
    CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName,String siteId,String pageWebPath);
}
