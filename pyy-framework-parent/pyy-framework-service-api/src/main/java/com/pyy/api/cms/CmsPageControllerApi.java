package com.pyy.api.cms;

import com.pyy.framework.domain.cms.CmsPage;
import com.pyy.framework.domain.cms.request.QueryPageRequest;
import com.pyy.framework.domain.cms.response.CmsPageResult;
import com.pyy.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * ========================
 * Cms页面查询接口
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/12/10 14:57
 * Version: v1.0
 * ========================
 */
@Api(value="cms页面管理接口",description = "cms页面管理接口，提供页面的增、删、改、查")
public interface CmsPageControllerApi {

    /**
     * 页面列表分页查询
     * @param page
     * @param size
     * @param queryPageRequest
     * @return
     */
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码", required=true, paramType="path", dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数", required=true, paramType="path", dataType="int")
    })
    QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) ;

    @ApiOperation("添加页面")
    CmsPageResult add(CmsPage cmsPage);
}
