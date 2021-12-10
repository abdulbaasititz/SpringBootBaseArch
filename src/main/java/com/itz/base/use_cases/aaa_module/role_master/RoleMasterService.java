package com.itz.base.use_cases.aaa_module.role_master;

import com.itz.base.helpers.configs.LoggerConfig;
import com.itz.base.helpers.utils.OffsetBasedPageRequest;
import com.itz.base.persistence.models.aaa_module.RoleMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMasterService {
	@Autowired
	RoleMasterRepository rep;

	public Boolean checkRoleMasterPk1(Integer pk0) throws Exception {
		try {
			RoleMaster getVal = rep.findById(pk0);
			if (getVal == null)
				return false;
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return true;
	}

	public RoleMaster getRoleMasterPk2(Integer pk0) throws Exception {
		RoleMaster getVal;
		try {
			getVal = rep.findById(pk0);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return getVal;
	}


	public RoleMaster getRoleMasterPk1(String pk0) throws Exception {
		RoleMaster getVal;
		try {
			getVal = rep.findByName(pk0);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return getVal;
	}

	public Integer setRoleMasterDetails(RoleMaster val) throws Exception {
		try {
			return rep.save(val).getId();
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public void delRoleMaster(RoleMaster val) throws Exception {
		try {
			rep.delete(val);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public List<RoleMaster> getAllRoleMasterExceptSuperAdmin(String sub) throws Exception {
		try {
			return rep.findByNameNotLike(sub);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public List<RoleMaster> getAllRoleMaster() throws Exception {
		try {
			return rep.findAll();
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public Page<RoleMaster> getAllRoleMasterByPg(int start, int limit, String searchKey) throws Exception {
		try {
			if (!searchKey.equals("-1"))
				return rep.findByIsActiveAndNameContainingIgnoreCase(true, searchKey, new OffsetBasedPageRequest(start, limit));
			return rep.findByIsActive(true, new OffsetBasedPageRequest(start, limit));
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
}
