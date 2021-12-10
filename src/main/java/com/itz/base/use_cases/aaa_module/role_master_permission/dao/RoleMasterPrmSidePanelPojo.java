package com.itz.base.use_cases.aaa_module.role_master_permission.dao;


public interface RoleMasterPrmSidePanelPojo {
    Integer getId();

    String getName();

    String getNote();

    String getUrl();

    String getIcon();

    Integer getSortId();

    Boolean getParent();

    Integer getParentId();

    String getServiceName();

    Boolean getDoCreate();

    Boolean getDoUpdate();

    Boolean getDoDelete();

    Boolean getDoRead();
}
