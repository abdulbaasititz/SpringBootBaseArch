package com.itz.base.use_cases.super_admin_module.page_master.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageMasterIdDao {
    private Integer id;
    private String name;
    private String icon;
    private Boolean isActive;
    private String note;
    private Boolean parent;
    private Integer parentId;
    private String serviceName;
    private Float sortId;
    private String url;
    private String options;
}
