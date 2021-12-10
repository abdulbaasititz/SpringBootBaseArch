package com.itz.base.use_cases.super_admin_module.page_master;

import com.itz.base.helpers.configs.LoggerConfig;
import com.itz.base.persistence.models.super_admin_module.PageMaster;
import com.itz.base.use_cases.super_admin_module.page_master.dao.PageMasterPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageMasterService {
	@Autowired
	PageMasterRepository pageMasterRepository;

	public Boolean checkPageMasterPk1(Integer pk0) throws Exception {
		try {
			PageMaster getVal = pageMasterRepository.findById(pk0);
			if (getVal == null)
				return false;
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return true;
	}

	public Boolean checkPageMasterPk2(Integer pk0) throws Exception {
		try {
			PageMaster getVal = pageMasterRepository.findByParentId(pk0);
			if (getVal == null)
				return false;
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return true;
	}

	public PageMaster getPageMasterPk1(Integer pk0) throws Exception {
		PageMaster getVal;
		try {
			getVal = pageMasterRepository.findById(pk0);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return getVal;
	}

	public PageMaster getPageMasterPk2(Integer pk0) throws Exception {
		PageMaster getVal;
		try {
			getVal = pageMasterRepository.findByParentId(pk0);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return getVal;
	}

	public Integer setPageMasterDetails(PageMaster val) throws Exception {
		try {
			return pageMasterRepository.save(val).getId();
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public void delPageMaster(PageMaster val) throws Exception {
		try {
			pageMasterRepository.delete(val);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}


	public List<PageMaster> getAllRoleMasterByPg(int pn, int ps) throws Exception {
		Pageable pg = PageRequest.of(pn, ps);
		try {
			return pageMasterRepository.findAllByIsActive(true, pg);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public Object getPageMasterPk1WtParentName(Integer pageMasterId) throws Exception {
		try {
			return pageMasterRepository.findByIdWtNm(pageMasterId);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public List<PageMaster> getAllPageMaster() throws Exception {
		try {
			return pageMasterRepository.findAllByIsActive(true);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public List<PageMasterPojo> getAllQryPageMaster() throws Exception {
		try {
			return pageMasterRepository.findAllWtNm();
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public List<PageMasterPojo> getAllQryPageMasterWtPg(int pn, int ps) throws Exception {
		Pageable pg = PageRequest.of(pn, ps);
		try {
			return pageMasterRepository.findAllWtNmPg(pg);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public List<PageMasterPojo> getPageMasterByParent(Boolean i) throws Exception {
		try {
			return pageMasterRepository.findByParent(i);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public long getTotalElements() throws Exception {
		try {
			return pageMasterRepository.countByIsActive(true);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
}
