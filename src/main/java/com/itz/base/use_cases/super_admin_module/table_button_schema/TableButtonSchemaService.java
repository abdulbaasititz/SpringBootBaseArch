package com.itz.base.use_cases.super_admin_module.table_button_schema;

import com.itz.base.helpers.configs.LoggerConfig;
import com.itz.base.persistence.models.super_admin_module.TableButtonSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableButtonSchemaService {
	@Autowired
	TableButtonSchemaRepository tableButtonSchemaRepository;

	public Boolean checkTableButtonSchemaPk1(Integer pk0) throws Exception {
		try {
			TableButtonSchema getVal = tableButtonSchemaRepository.findById(pk0);
			if (getVal == null)
				return false;
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return true;
	}

	public TableButtonSchema getTableButtonSchemaPk1(Integer pk0) throws Exception {
		TableButtonSchema getVal;
		try {
			getVal = tableButtonSchemaRepository.findById(pk0);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return getVal;
	}

	public List<TableButtonSchema> getTableButtonSchemaPk2(Integer pk0) throws Exception {
		try {
			return tableButtonSchemaRepository.findByPageMasterId(pk0);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public String setData(TableButtonSchema val) throws Exception {
		try {
			tableButtonSchemaRepository.save(val);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return "1";
	}

	public void delAllData(List<TableButtonSchema> val) throws Exception {
		try {
			tableButtonSchemaRepository.deleteAll(val);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public void delData(TableButtonSchema val) throws Exception {
		try {
			tableButtonSchemaRepository.delete(val);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public List<TableButtonSchema> getAllData() throws Exception {
		try {
			return tableButtonSchemaRepository.findByIsActive(true);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public Page<TableButtonSchema> getAllDataByPg(int pn, int ps) throws Exception {
		Pageable pg = PageRequest.of(pn, ps);
		try {
			return tableButtonSchemaRepository.findByIsActive(true, pg);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

}
