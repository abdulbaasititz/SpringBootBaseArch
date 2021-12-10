package com.itz.base.use_cases.super_admin_module.form_data;

import com.itz.base.helpers.configs.LoggerConfig;
import com.itz.base.persistence.models.super_admin_module.FormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormDataService {
	@Autowired
	FormDataRepository formDataRepository;

	public Boolean checkFormDataPk1(Integer pk0) throws Exception {
		try {
			FormData getVal = formDataRepository.findById(pk0);
			if (getVal == null)
				return false;
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return true;
	}

	public FormData getFormDataPk1(Integer pk0) throws Exception {
		FormData getVal;
		try {
			getVal = formDataRepository.findById(pk0);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return getVal;
	}

	public List<FormData> getFormDataPk2(Integer pk0) throws Exception {
		try {
			return formDataRepository.findByPageMasterId(pk0);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public String setAllFormDataDetails(List<FormData> val) throws Exception {
		try {
			formDataRepository.saveAll(val);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return "1";
	}

	public String setFormDataDetails(FormData val) throws Exception {
		try {
			formDataRepository.save(val);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return "1";
	}

	public void delData(FormData val) throws Exception {
		try {
			formDataRepository.delete(val);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public List<FormData> getAllData() throws Exception {
		try {
			return formDataRepository.findAll();
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public Page<FormData> getAllDataByPg(int pn, int ps) throws Exception {
		Pageable pg = PageRequest.of(pn, ps);
		try {
			return formDataRepository.findAll(pg);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public List<String> getFormDataPk2WtJson(Integer pageMasterId) throws Exception {
		try {
			return formDataRepository.findByPageMasterIdWtJson(pageMasterId);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public void delByParentId(Integer pageMasterId) throws Exception {
		try {
			formDataRepository.deleteAllByPageMasterId(pageMasterId);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
}
