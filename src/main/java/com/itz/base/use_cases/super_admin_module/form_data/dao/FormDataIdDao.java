package com.itz.base.use_cases.super_admin_module.form_data.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormDataIdDao {
    private Integer id;
    private String header;
    private Integer liNo;
    private String value;
    private Integer pageMasterId;
    private Boolean isActive;
}
