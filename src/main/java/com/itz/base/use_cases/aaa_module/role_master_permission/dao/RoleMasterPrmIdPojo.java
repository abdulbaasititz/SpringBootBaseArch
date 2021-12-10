package com.itz.base.use_cases.aaa_module.role_master_permission.dao;


public interface RoleMasterPrmIdPojo {
    Boolean getDoCreate();

    Boolean getDoUpdate();

    Boolean getDoDelete();

    Boolean getDoRead();

    Integer getPageMasterId();

    Integer getRoleMasterId();

    Integer getId();

    Boolean getParent();

    Integer getParentId();

    String getName();

    Integer getSortId();

}
