package com.itz.base.use_cases.super_admin_module.table_main_schema;

import com.itz.base.helpers.common.results.ReportDao;
import com.itz.base.helpers.common.results.ResultDao;
import com.itz.base.helpers.common.results.ResultsDao;
import com.itz.base.helpers.common.token.ClaimsDao;
import com.itz.base.helpers.common.token.ClaimsSet;
import com.itz.base.helpers.configs.ModelMapperConfig;
import com.itz.base.persistence.models.super_admin_module.TableMainSchema;
import com.itz.base.use_cases.super_admin_module.table_main_schema.dao.TableMainSchemaDao;
import com.itz.base.use_cases.super_admin_module.table_main_schema.dao.TableMainSchemaIdDao;
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
public class TableMainSchemaController {
	@Autowired
	TableMainSchemaService tableMainSchemaService;
	@Autowired
	ClaimsSet claimsSet;
//	@RequestMapping(value = "/set-tableMainSchema/new", method = RequestMethod.POST)
//	public ResponseEntity<?> newtableMainSchema(HttpServletRequest request,@RequestBody TableMainSchema val) throws Exception {
//		DateTimeCalc dateTimeCalc = new DateTimeCalc();
//		String createdAt = dateTimeCalc.getUcloTodayDateTime();
//		String createdBy = "Abdul Baasit";
//		Boolean status1 = tableMainSchemaService.checkTableMainSchemaPk1(val.getId());
//		if(!status1){
//			tableMainSchemaService.setTableMainSchemaDetails(val);
//		}else{
//			throw new Exception("value already set");
//		}
//	return new ResponseEntity<>("Success", HttpStatus.OK);
//	}

	@PostMapping(value = "/table-main-schema")
	public ResponseEntity<?> masterSet(HttpServletRequest request, @RequestBody TableMainSchemaDao getVal) throws Exception {

		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		tableMainSchemaService.setTableMainSchemaDetails(
				new ModelMapperConfig().modelMapper().map(getVal, TableMainSchema.class));
		return new ResponseEntity<>(new ReportDao("Success", true), HttpStatus.OK);
	}

	@PutMapping(value = "/table-main-schema")
	public ResponseEntity<?> masterUpdate(HttpServletRequest request, @RequestBody TableMainSchemaIdDao getVal) throws Exception {

		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		TableMainSchema setVal = tableMainSchemaService.getTableMainSchemaPk1(getVal.getId());
		if (setVal != null) {
			setVal.setUpBy(claimsDao.getUsr());
			setVal.setField(getVal.getField());
			setVal.setIsActive(getVal.getIsActive());
			setVal.setHeader(getVal.getHeader());
			setVal.setPageMasterId(getVal.getPageMasterId());
			setVal.setSortBy(getVal.getSortBy());
			setVal.setType(getVal.getType());
			setVal.setWidth(getVal.getWidth());
			setVal.setOptions(getVal.getOptions());
			tableMainSchemaService.setTableMainSchemaDetails(setVal);
		} else {
			throw new Exception("No value");
		}
		return new ResponseEntity<>(new ReportDao("Success", true), HttpStatus.OK);
	}

	@DeleteMapping(path = "/table-main-schema/{tableMainSchemaId}")
	public ResponseEntity<?> masterDelete(HttpServletRequest request
			, @PathVariable(name = "tableMainSchemaId") Integer tableMainSchemaId) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));

		TableMainSchema setVal = tableMainSchemaService.getTableMainSchemaPk1(tableMainSchemaId);
		if (setVal == null) {
			throw new Exception("No User Set");
		} else {
			tableMainSchemaService.delData(setVal);
		}
		return new ResponseEntity<>(new ReportDao("Success", true), HttpStatus.OK);
	}

	@GetMapping(path = "/table-main-schema/{pageMasterId}")
	public ResponseEntity<?> masterGetAllByUserId(HttpServletRequest request
			, @PathVariable(name = "pageMasterId") Integer pageMasterId) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		List<TableMainSchema> getVal = tableMainSchemaService.getTableMainSchemaPk2(pageMasterId);

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		List<TableMainSchemaIdDao> getValAll = modelMapper.map(getVal,
				new TypeToken<List<TableMainSchemaIdDao>>() {
				}.getType());

		return new ResponseEntity<>(new ResultDao(getValAll, "Success", true), HttpStatus.OK);
	}

	@GetMapping(value = "/table-main-schema")
	public ResponseEntity<?> masterGetAll(HttpServletRequest request
			, @RequestParam(required = false, name = "start", defaultValue = "1") int pageNumber
			, @RequestParam(required = false, name = "limit", defaultValue = "25") int pageSize
			, @RequestParam(required = false, name = "searchKey", defaultValue = "-1") String searchKey
			, @RequestParam(required = false, name = "orderBy", defaultValue = "-1") String orderBy
			, @RequestParam(required = false, name = "sortOrder", defaultValue = "-1") int sortOrder
			, @RequestParam(required = false, name = "isPagination", defaultValue = "true") Boolean isPagination) throws Exception {

		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		List<TableMainSchema> getVal;

		long totalCount;
		if (isPagination) {
			Page<TableMainSchema> getAllWtPg = tableMainSchemaService.getAllDataByPg(pageNumber - 1, pageSize);
			getVal = getAllWtPg.getContent();
			totalCount = getAllWtPg.getTotalElements();
		} else {
			getVal = tableMainSchemaService.getAllData();
			totalCount = getVal.size();
			pageNumber = 1;
			pageSize = Math.toIntExact(totalCount);
		}
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);

		List<TableMainSchemaIdDao> getValAll = modelMapper.map(getVal,
				new TypeToken<List<TableMainSchemaIdDao>>() {
				}.getType());

		return new ResponseEntity<>(new ResultsDao(getValAll, pageNumber, pageSize, totalCount), HttpStatus.OK);
	}
}
