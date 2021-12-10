package com.itz.base.use_cases.aaa_module.role_master_permission.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleMasterPrmDao {
    private Boolean doCreate;
    private Boolean doUpdate;
    private Boolean doDelete;
    private Boolean doRead;
    private Integer pageMasterId;
    private Integer roleMasterId;
}
