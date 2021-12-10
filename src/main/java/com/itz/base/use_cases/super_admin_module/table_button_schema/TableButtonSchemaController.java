package com.itz.base.use_cases.super_admin_module.table_button_schema;

import com.itz.base.helpers.common.results.ReportDao;
import com.itz.base.helpers.common.results.ResultDao;
import com.itz.base.helpers.common.results.ResultsDao;
import com.itz.base.helpers.common.token.ClaimsDao;
import com.itz.base.helpers.common.token.ClaimsSet;
import com.itz.base.helpers.configs.ModelMapperConfig;
import com.itz.base.persistence.models.super_admin_module.TableButtonSchema;
import com.itz.base.use_cases.super_admin_module.table_button_schema.dao.TableButtonSchemaDao;
import com.itz.base.use_cases.super_admin_module.table_button_schema.dao.TableButtonSchemaIdDao;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("${spring.base.path}")
public class TableButtonSchemaController {
	@Autowired
	TableButtonSchemaService tableButtonSchemaService;
	@Autowired
	ClaimsSet claimsSet;

	@PostMapping(value = "/table-button-schema")
	public ResponseEntity<?> masterSet(HttpServletRequest request, @RequestBody TableButtonSchemaDao getVal) throws Exception {

		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));

		tableButtonSchemaService.setData(
				new ModelMapperConfig().modelMapper().map(getVal, TableButtonSchema.class));

		return new ResponseEntity<>(new ReportDao("Success", true), HttpStatus.OK);
	}

	@PutMapping(value = "/table-button-schema")
	public ResponseEntity<?> masterUpdate(HttpServletRequest request, @RequestBody TableButtonSchemaIdDao getVal) throws Exception {

		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		TableButtonSchema setVal = tableButtonSchemaService.getTableButtonSchemaPk1(getVal.getId());
		if (setVal != null) {
			setVal.setUpBy(claimsDao.getUsr());
			setVal.setAction(getVal.getAction());
			setVal.setIsActive(getVal.getIsActive());
			setVal.setIcon(getVal.getIcon());
			setVal.setPageMasterId(getVal.getPageMasterId());
			setVal.setToolTip(getVal.getToolTip());
			setVal.setOptions(getVal.getOptions());
			tableButtonSchemaService.setData(setVal);
		} else {
			throw new Exception("No value");
		}
		return new ResponseEntity<>(new ReportDao("Success", true), HttpStatus.OK);
	}

	@GetMapping(path = "/table-button-schema/{pageMasterId}")
	public ResponseEntity<?> masterGetAllByUserId(HttpServletRequest request
			, @PathVariable(name = "pageMasterId") Integer pageMasterId) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		List<TableButtonSchema> getVal = tableButtonSchemaService.getTableButtonSchemaPk2(pageMasterId);

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		List<TableButtonSchemaIdDao> getValAll = modelMapper.map(getVal,
				new TypeToken<List<TableButtonSchemaIdDao>>() {
				}.getType());

		return new ResponseEntity<>(new ResultDao(getValAll, "Success", true), HttpStatus.OK);
	}

	@DeleteMapping(path = "/table-button-schema/{tableButtonSchemaId}")
	public ResponseEntity<?> masterDelete(HttpServletRequest request
			, @PathVariable(name = "tableButtonSchemaId") Integer tableButtonSchemaId) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));

		TableButtonSchema setVal = tableButtonSchemaService.getTableButtonSchemaPk1(tableButtonSchemaId);
		if (setVal == null) {
			throw new Exception("No User Set");
		} else {
			tableButtonSchemaService.delData(setVal);
		}
		return new ResponseEntity<>(new ReportDao("Success", true), HttpStatus.OK);
	}

	@GetMapping(value = "/table-button-schema")
	public ResponseEntity<?> masterGetAll(HttpServletRequest request
			, @RequestParam(required = false, name = "start", defaultValue = "1") int pageNumber
			, @RequestParam(required = false, name = "limit", defaultValue = "25") int pageSize
			, @RequestParam(required = false, name = "searchKey", defaultValue = "-1") String searchKey
			, @RequestParam(required = false, name = "orderBy", defaultValue = "-1") String orderBy
			, @RequestParam(required = false, name = "sortOrder", defaultValue = "-1") int sortOrder
			, @RequestParam(required = false, name = "isPagination", defaultValue = "true") Boolean isPagination) throws Exception {

		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		List<TableButtonSchema> getVal;

		long totalCount;
		if (isPagination) {
			Page<TableButtonSchema> getAllWtPg = tableButtonSchemaService.getAllDataByPg(pageNumber - 1, pageSize);
			getVal = getAllWtPg.getContent();
			totalCount = getAllWtPg.getTotalElements();
		} else {
			getVal = tableButtonSchemaService.getAllData();
			totalCount = getVal.size();
			pageNumber = 1;
			pageSize = Math.toIntExact(totalCount);
		}
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);

		List<TableButtonSchemaIdDao> getValAll = modelMapper.map(getVal,
				new TypeToken<List<TableButtonSchemaIdDao>>() {
				}.getType());

		return new ResponseEntity<>(new ResultsDao(getValAll, pageNumber, pageSize, totalCount), HttpStatus.OK);
	}
//
//	@RequestMapping(value = "/table-button-schema/get-pg")
//	public ResponseEntity<?> masterGetAllByPagination(HttpServletRequest request
//			, @RequestParam int pageNumber , @RequestParam  int pageSize
//	) throws Exception {
//		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
//		List<TableButtonSchema> getVal = tableButtonSchemaService.getAllDataByPg(pageNumber,pageSize);
//
//		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.getConfiguration().setAmbiguityIgnored(true);
//
//		List<TableButtonSchemaIdDao> getValAll = modelMapper.map(getVal,
//				new TypeToken<List<TableButtonSchemaIdDao>>() {
//				}.getType());
//
//		return new ResponseEntity<>(new ResultDao(getValAll,"Success",true), HttpStatus.OK);
//	}

}
