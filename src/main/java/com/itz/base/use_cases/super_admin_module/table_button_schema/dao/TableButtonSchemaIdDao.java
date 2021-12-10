package com.itz.base.use_cases.super_admin_module.table_button_schema.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableButtonSchemaIdDao {
    private Integer id;
    private String action;
    private String icon;
    private Boolean isActive;
    private Integer pageMasterId;
    private String toolTip;
    private String options;
}
