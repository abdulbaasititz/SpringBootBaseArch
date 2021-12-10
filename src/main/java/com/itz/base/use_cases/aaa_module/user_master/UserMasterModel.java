package com.itz.base.use_cases.aaa_module.user_master;

import com.itz.base.persistence.models.aaa_module.UserMaster;
import com.itz.base.persistence.models.aaa_module.UserRole;
import com.itz.base.use_cases.aaa_module.user_master.dao.UserMasterDao;
import org.modelmapper.ModelMapper;

public class UserMasterModel {

    public UserMaster setUserMaster(UserMasterDao val) {
        UserMaster userMaster = new UserMaster();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        userMaster.setUserId(val.getUserId());
        userMaster.setPassword(val.getPassword());
        userMaster.setDesignation(val.getDesignation());
        userMaster.setPhoneNumber(val.getPhoneNumber());
        userMaster.setUserName(val.getUserName());
        userMaster.setEmail(val.getEmail());
        userMaster.setIsActive(val.getIsActive());
        userMaster.setCrBy("anonymous");

        UserRole userRole = new UserRole();
        userRole.setRoleMasterId(val.getRoleMasterId());
        userRole.setCrBy("anonymous");
        userRole.setUserMaster(userMaster);
        userMaster.setUserRole(userRole);
        return userMaster;
    }
}
