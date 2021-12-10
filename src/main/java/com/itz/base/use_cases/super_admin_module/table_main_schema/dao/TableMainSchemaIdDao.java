package com.itz.base.use_cases.super_admin_module.table_main_schema.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableMainSchemaIdDao {
    private Integer id;
    private String field;
    private String header;
    private Boolean isActive;
    private Integer sortBy;
    private Integer pageMasterId;
    private String type;
    private String width;
    private String options;
}
