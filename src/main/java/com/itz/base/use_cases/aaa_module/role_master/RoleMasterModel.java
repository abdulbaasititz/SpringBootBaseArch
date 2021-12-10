package com.itz.base.use_cases.aaa_module.role_master;

import com.itz.base.persistence.models.aaa_module.RoleMasterPermission;
import com.itz.base.use_cases.super_admin_module.page_master.dao.PageMasterPojo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class RoleMasterModel {
    public List<RoleMasterPermission> setPageMasterModel(List<PageMasterPojo> pageMasters, Integer roleMasterId, String usr) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.typeMap(PageMasterPojo.class, RoleMasterPermission.class).addMappings(mapper -> {
            mapper.map(PageMasterPojo -> roleMasterId,
                    RoleMasterPermission::setRoleMasterId);
            mapper.map(PageMasterPojo::getId,
                    RoleMasterPermission::setPageMasterId);
            mapper.map(PageMasterPojo -> true,
                    RoleMasterPermission::setRead);
            mapper.map(PageMasterPojo -> false,
                    RoleMasterPermission::setUpdate);
            mapper.map(PageMasterPojo -> false,
                    RoleMasterPermission::setDelete);
            mapper.map(PageMasterPojo -> false,
                    RoleMasterPermission::setCreate);
            mapper.map(PageMasterPojo -> 0,
                    RoleMasterPermission::setId);
            mapper.map(PageMasterPojo -> usr,
                    RoleMasterPermission::setCrBy);
        });

        return modelMapper.map(pageMasters,
                new TypeToken<List<RoleMasterPermission>>() {
                }.getType());
    }
}
