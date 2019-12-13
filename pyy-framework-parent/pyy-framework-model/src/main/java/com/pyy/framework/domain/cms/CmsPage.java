package com.pyy.framework.domain.cms;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * 页面
 */
@Data
@ToString
@Document(collection = "cms_page")
public class CmsPage {
    /**
     * 页面名称、别名、访问地址、类型（静态/动态）、页面模版、状态
     */
    //页面ID
    @Id
    private String pageId;
    //站点ID
    @NotBlank(message = "站点不能为空")
    private String siteId;
    //页面名称
    @NotBlank(message = "页面不能为空")
    private String pageName;
    //别名
    private String pageAliase;
    //访问地址
    @NotBlank(message = "访问地址不能为空")
    private String pageWebPath;
    //参数
    private String pageParameter;
    //物理路径
    @NotBlank(message = "物理路径不能为空")
    private String pagePhysicalPath;
    //类型（静态/动态）
    private String pageType;
    //页面模版
    private String pageTemplate;
    //页面静态化内容
    private String pageHtml;
    //状态
    private String pageStatus;
    //创建时间
    private Date pageCreateTime;
    //模版id
    @NotBlank(message = "模版不能为空")
    private String templateId;
    //参数列表
    private List<CmsPageParam> pageParams;
    //模版文件Id
//    private String templateFileId;
    //静态文件Id
    private String htmlFileId;
    //数据Url
    private String dataUrl;

}
