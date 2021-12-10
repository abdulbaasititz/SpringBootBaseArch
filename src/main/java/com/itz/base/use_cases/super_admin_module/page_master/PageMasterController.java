package com.itz.base.use_cases.super_admin_module.page_master;

import com.itz.base.helpers.common.results.ReportDao;
import com.itz.base.helpers.common.results.ResultDao;
import com.itz.base.helpers.common.results.ResultsDao;
import com.itz.base.helpers.common.token.ClaimsDao;
import com.itz.base.helpers.common.token.ClaimsSet;
import com.itz.base.persistence.models.aaa_module.RoleMaster;
import com.itz.base.persistence.models.aaa_module.RoleMasterPermission;
import com.itz.base.persistence.models.super_admin_module.PageMaster;
import com.itz.base.persistence.models.super_admin_module.TableButtonSchema;
import com.itz.base.persistence.models.super_admin_module.TableMainSchema;
import com.itz.base.use_cases.aaa_module.role_master.RoleMasterService;
import com.itz.base.use_cases.aaa_module.role_master_permission.RoleMasterPermissionService;
import com.itz.base.use_cases.super_admin_module.form_data.FormDataService;
import com.itz.base.use_cases.super_admin_module.page_master.dao.PageMasterDao;
import com.itz.base.use_cases.super_admin_module.page_master.dao.PageMasterIdDao;
import com.itz.base.use_cases.super_admin_module.page_master.dao.PageMasterPojo;
import com.itz.base.use_cases.super_admin_module.table_button_schema.TableButtonSchemaService;
import com.itz.base.use_cases.super_admin_module.table_button_schema.dao.TableButtonSchemaIdDao;
import com.itz.base.use_cases.super_admin_module.table_main_schema.TableMainSchemaService;
import com.itz.base.use_cases.super_admin_module.table_main_schema.dao.TableMainSchemaIdDao;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${spring.base.path}")
public class PageMasterController {
	@Autowired
	ClaimsSet claimsSet;

	@Autowired
	PageMasterService pageMasterService;
	@Autowired
	RoleMasterService roleMasterService;
	@Autowired
	RoleMasterPermissionService roleMasterPrmService;
	@Autowired
	TableButtonSchemaService tableButtonSchemaService;
	@Autowired
	TableMainSchemaService tableMainSchemaService;
	@Autowired
	FormDataService formDataService;

	@PostMapping(value = "/page-master")
	@Transactional
	public ResponseEntity<?> masterSet(HttpServletRequest request, @RequestBody PageMasterDao getVal) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		PageMaster setVal = modelMapper.map(getVal, PageMaster.class);
		if (getVal.getParent() == null)
			setVal.setParent(false);
		if (getVal.getParentId() == null)
			setVal.setParentId(0);
		Integer pageMasterId = pageMasterService.setPageMasterDetails(setVal);

		List<RoleMaster> roleMasters = roleMasterService.getAllRoleMaster();
		modelMapper.typeMap(RoleMaster.class, RoleMasterPermission.class).addMappings(mapper -> {
			mapper.map(RoleMaster::getId,
					RoleMasterPermission::setRoleMasterId);
			mapper.map(RoleMaster -> pageMasterId,
					RoleMasterPermission::setPageMasterId);
			mapper.map(RoleMaster -> true,
					RoleMasterPermission::setRead);
			mapper.map(RoleMaster -> false,
					RoleMasterPermission::setUpdate);
			mapper.map(RoleMaster -> false,
					RoleMasterPermission::setDelete);
			mapper.map(RoleMaster -> false,
					RoleMasterPermission::setCreate);
//			mapper.map(RoleMaster -> 0,
//					RoleMasterPermission::setId);
//			mapper.map(RoleMaster -> claimsDao.getUsr(),
//					RoleMasterPermission::setCrBy);
		});
		List<RoleMasterPermission> getValAll = modelMapper.map(roleMasters,
				new TypeToken<List<RoleMasterPermission>>() {
				}.getType());
		roleMasterPrmService.setData(getValAll);

		return new ResponseEntity<>(new ReportDao("Success", true), HttpStatus.OK);
	}

	@PutMapping(value = "/page-master")
	public ResponseEntity<?> masterUpdate(HttpServletRequest request, @RequestBody PageMasterIdDao getVal) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		PageMaster setVal = pageMasterService.getPageMasterPk1(getVal.getId());
		if (setVal != null) {
			setVal.setUpBy(claimsDao.getUsr());
			setVal.setName(getVal.getName());
			setVal.setIsActive(getVal.getIsActive());
			setVal.setNote(getVal.getNote());
			setVal.setIcon(getVal.getIcon());
			setVal.setParent(getVal.getParent());
			setVal.setParentId(getVal.getParentId());
			setVal.setSortId(getVal.getSortId());
			setVal.setServiceName(getVal.getServiceName());
			setVal.setOptions(getVal.getOptions());
			setVal.setUrl(getVal.getUrl());
			pageMasterService.setPageMasterDetails(setVal);
		} else {
			throw new Exception("No value");
		}
		return new ResponseEntity<>(new ReportDao("Success", true), HttpStatus.OK);
	}

	@DeleteMapping(path = "/page-master/{pageMasterId}")
	@Transactional
	public ResponseEntity<?> masterDelete(HttpServletRequest request
			, @PathVariable(name = "pageMasterId") Integer pageMasterId) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));

		roleMasterPrmService.delByPageMstId(pageMasterId);

		PageMaster setVal = pageMasterService.getPageMasterPk1(pageMasterId);
		if (setVal == null) {
			throw new Exception("No User Set");
		} else {
			pageMasterService.delPageMaster(setVal);
		}
		//delete table button schema
		List<TableButtonSchema> tableButtonSchemas = tableButtonSchemaService.getTableButtonSchemaPk2(pageMasterId);
		if (tableButtonSchemas != null)
			tableButtonSchemaService.delAllData(tableButtonSchemas);
		//delete table main schema
		List<TableMainSchema> tableMainSchemas = tableMainSchemaService.getTableMainSchemaPk2(pageMasterId);
		if (tableMainSchemas != null)
			tableMainSchemaService.delAllData(tableMainSchemas);

		return new ResponseEntity<>(new ReportDao("Success", true), HttpStatus.OK);
	}

	@GetMapping(path = "/page-master/{pageMasterId}")
	public ResponseEntity<?> masterGetAllByUserId(HttpServletRequest request
			, @PathVariable(name = "pageMasterId") Integer pageMasterId) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		return new ResponseEntity<>(new ResultDao(pageMasterService.getPageMasterPk1WtParentName(pageMasterId), "Success", true), HttpStatus.OK);
	}

	@GetMapping(value = "/page-master")
	public ResponseEntity<?> masterGetAll(HttpServletRequest request
			, @RequestParam(required = false, name = "start", defaultValue = "1") int pageNumber
			, @RequestParam(required = false, name = "limit", defaultValue = "25") int pageSize
			, @RequestParam(required = false, name = "searchKey", defaultValue = "-1") String searchKey
			, @RequestParam(required = false, name = "orderBy", defaultValue = "-1") String orderBy
			, @RequestParam(required = false, name = "sortOrder", defaultValue = "-1") int sortOrder
			, @RequestParam(required = false, name = "isPagination", defaultValue = "true") Boolean isPagination) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		long totalCount = 0;
		List<PageMasterPojo> getVal;
		if (isPagination) {
			getVal = pageMasterService.getAllQryPageMasterWtPg(pageNumber - 1, pageSize);
			totalCount = pageMasterService.getTotalElements();
		} else {
			getVal = pageMasterService.getAllQryPageMaster();
		}
		return new ResponseEntity<>(new ResultsDao(getVal, pageNumber, pageSize, totalCount), HttpStatus.OK);
	}

	//Each page module call 1
	@GetMapping(path = "/all-module/{pageMasterId}")
	public ResponseEntity<?> masterGetAllPageTableButtonById(HttpServletRequest request
			, @PathVariable(name = "pageMasterId") Integer pageMasterId) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		//PageMasterDao pageMaster = modelMapper.map(pageMasterService.getPageMasterPk1WtParentName(pageMasterId), PageMasterDao.class);

		List<String> formData = formDataService.getFormDataPk2WtJson(pageMasterId);

		List<TableButtonSchemaIdDao> tableButtonSchema = modelMapper.map(tableButtonSchemaService.getTableButtonSchemaPk2(pageMasterId),
				new TypeToken<List<TableButtonSchemaIdDao>>() {
				}.getType());

		List<TableMainSchemaIdDao> tableMainSchema = modelMapper.map(tableMainSchemaService.getTableMainSchemaPk2(pageMasterId),
				new TypeToken<List<TableMainSchemaIdDao>>() {
				}.getType());

		RoleMasterPermission rolPrm = roleMasterPrmService.getUserRolePermissionPk2(Integer.valueOf(claimsDao.getRol()), pageMasterId);

		Map<String, Object> result = new HashMap<>();
		result.put("pageMaster", pageMasterService.getPageMasterPk1WtParentName(pageMasterId));
		result.put("formData", formData);
		result.put("tableButtonSchema", tableButtonSchema);
		result.put("tableMainSchema", tableMainSchema);
		result.put("roleMasterPrm", rolPrm);
		return new ResponseEntity<>(new ResultDao(result, "Success", true), HttpStatus.OK);
	}

	@GetMapping(value = "/page-master/parent")
	public ResponseEntity<?> masterGetAllByParent(HttpServletRequest request) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		return new ResponseEntity<>(new ResultDao(pageMasterService.getPageMasterByParent(true), "Success", true), HttpStatus.OK);
	}
}
