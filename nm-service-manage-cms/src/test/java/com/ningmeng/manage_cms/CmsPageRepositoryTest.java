package com.ningmeng.manage_cms;

import com.ningmeng.framework.domain.cms.CmsPage;
import com.ningmeng.manage_cms.dao.CmsPageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {
    @Autowired
    CmsPageRepository cmsPageRepository;
    @Test
    public void findAll(){
       List<CmsPage> list= cmsPageRepository.findAll();
        for (CmsPage cmsPage :list) {
            System.out.println(cmsPage.getPageName());
        }
    }
}
