package com.itz.base.use_cases.aaa_module.role_master_permission;

import com.itz.base.helpers.configs.LoggerConfig;
import com.itz.base.persistence.models.aaa_module.RoleMasterPermission;
import com.itz.base.use_cases.aaa_module.role_master_permission.dao.RoleMasterPrmIdPojo;
import com.itz.base.use_cases.aaa_module.role_master_permission.dao.RoleMasterPrmSidePanelPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMasterPermissionService {
	@Autowired
	RoleMasterPermissionRepository roleMasterPermissionRepository;

	public Boolean checkUserRolePermissionPk1(Integer pk0) throws Exception {
		try {
			RoleMasterPermission getVal = roleMasterPermissionRepository.findById(pk0);
			if (getVal == null)
				return false;
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return true;
	}


	public RoleMasterPermission getUserRolePermissionPk1(Integer pk0) throws Exception {
		RoleMasterPermission getVal;
		try {
			getVal = roleMasterPermissionRepository.findById(pk0);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return getVal;
	}

	public RoleMasterPermission getUserRolePermissionPk2(Integer pk0, Integer pk1) throws Exception {
		try {
			return roleMasterPermissionRepository.findByRoleMasterIdAndPageMasterId(pk0, pk1);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public String setUserRolePermissionDetails(RoleMasterPermission val) throws Exception {
		try {
			roleMasterPermissionRepository.save(val);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return "1";
	}

	public void setData(List<RoleMasterPermission> val) throws Exception {
		try {
			roleMasterPermissionRepository.saveAll(val);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public List<RoleMasterPrmIdPojo> getAllByRolWtPrtExceptSuperAdmin(int roleMasterId) throws Exception {
		try {
			return roleMasterPermissionRepository.findByRoleMasterIdWtPrtExceptSuperAdmin(roleMasterId);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public List<RoleMasterPrmIdPojo> getAllByRolWtPrt(int roleMasterId) throws Exception {
		try {
			return roleMasterPermissionRepository.findByRoleMasterIdWtPrt(roleMasterId);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public List<RoleMasterPermission> getAllByRol(int roleMasterId) throws Exception {
		try {
			return roleMasterPermissionRepository.findByRoleMasterId(roleMasterId);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public void delByPageMstIdAndRolMstId(Integer pageMasterId, Integer roleMasterId) throws Exception {
		try {
			roleMasterPermissionRepository.deleteByPageMasterIdAndRoleMasterId(pageMasterId, roleMasterId);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public void delByPageMstId(Integer pageMasterId) throws Exception {
		try {
			roleMasterPermissionRepository.deleteByPageMasterId(pageMasterId);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public void delByRoleMstId(Integer roleMasterId) throws Exception {
		try {
			roleMasterPermissionRepository.deleteByRoleMasterId(roleMasterId);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public List<RoleMasterPrmSidePanelPojo> getByReadOnly(Integer roleMasterId) throws Exception {
		try {
			return roleMasterPermissionRepository.findByReadOnly(roleMasterId);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public List<RoleMasterPrmSidePanelPojo> getByReadOnlyExceptSuperAdmin(Integer roleMasterId) throws Exception {
		try {
			return roleMasterPermissionRepository.findByReadOnlyExceptSuperAdmin(roleMasterId);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
}
