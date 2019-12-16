package com.pyy.manage_cms;

import com.pyy.framework.domain.cms.CmsPage;
import com.pyy.manage_cms.dao.CmsPageRepository;
import com.pyy.manage_cms.service.CmsPageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/12/10 15:45
 * Version: v1.0
 * ========================
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageTest {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Autowired
    private CmsPageService cmsPageService;

    @Test
    public void testFindPage() {
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);

        Page<CmsPage> all = cmsPageRepository.findAll(pageable);
    }

    @Test
    public void testPageHtml() {
        String pageHtml = cmsPageService.getPageHtml("5df6f74fe77860184811b609");
        System.out.println(pageHtml);
    }
}
