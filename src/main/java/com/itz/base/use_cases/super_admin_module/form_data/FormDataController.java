package com.itz.base.use_cases.super_admin_module.form_data;

import com.itz.base.helpers.common.results.ReportDao;
import com.itz.base.helpers.common.results.ResultDao;
import com.itz.base.helpers.common.results.ResultsDao;
import com.itz.base.helpers.common.token.ClaimsDao;
import com.itz.base.helpers.common.token.ClaimsSet;
import com.itz.base.helpers.configs.ModelMapperConfig;
import com.itz.base.persistence.models.super_admin_module.FormData;
import com.itz.base.use_cases.super_admin_module.form_data.dao.FormDataDao;
import com.itz.base.use_cases.super_admin_module.form_data.dao.FormDataIdDao;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("${spring.base.path}")
public class FormDataController {
	@Autowired
	FormDataService formDataService;
	@Autowired
	ClaimsSet claimsSet;

	@PostMapping(value = "/form-data")
	@Transactional
	public ResponseEntity<?> formDataSet(HttpServletRequest request, @RequestBody List<FormDataDao> getVal) throws Exception {

		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));

		formDataService.delByParentId(getVal.get(0).getPageMasterId());

//		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.getConfiguration().setAmbiguityIgnored(true);

//		modelMapper.typeMap(FormDataDao.class, FormData.class).addMappings(mapper -> {
//			mapper.map(FormDataDao -> claimsDao.getUsr(),
//					FormData::setCrBy);
//			mapper.map(FormDataDao -> 0,
//					FormData::setId);
//		});

		List<FormData> setValAll = new ModelMapperConfig().modelMapper().map(getVal,
				new TypeToken<List<FormData>>() {
				}.getType());

		formDataService.setAllFormDataDetails(setValAll);

		return new ResponseEntity<>(new ReportDao("Success", true), HttpStatus.OK);
	}

	@PutMapping(value = "/form-data")
	public ResponseEntity<?> formDataUpdate(HttpServletRequest request, @RequestBody FormDataIdDao getVal) throws Exception {

		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		FormData setVal = formDataService.getFormDataPk1(getVal.getId());
		if (setVal != null) {
			setVal.setUpBy(claimsDao.getUsr());
			setVal.setHeader(getVal.getHeader());
			setVal.setLiNo(getVal.getLiNo());
			setVal.setPageMasterId(getVal.getPageMasterId());
			setVal.setValue(getVal.getValue());
			formDataService.setFormDataDetails(setVal);
		} else {
			throw new Exception("No value");
		}
		return new ResponseEntity<>(new ReportDao("Success", true), HttpStatus.OK);
	}

	@DeleteMapping(path = "/form-data/{formDataId}")
	public ResponseEntity<?> formDataDelete(HttpServletRequest request
			, @PathVariable(name = "formDataId") Integer formDataId) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));

		FormData setVal = formDataService.getFormDataPk1(formDataId);
		if (setVal == null) {
			throw new Exception("No User Set");
		} else {
			formDataService.delData(setVal);
		}
		return new ResponseEntity<>(new ReportDao("Success", true), HttpStatus.OK);
	}


	@GetMapping(path = "/form-data/{pageMasterId}")
	public ResponseEntity<?> formDataGetAllByUserId(HttpServletRequest request
			, @PathVariable(name = "pageMasterId") Integer pageMasterId) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		List<String> formData = formDataService.getFormDataPk2WtJson(pageMasterId);
		return new ResponseEntity<>(new ResultDao(formData, "Success", true), HttpStatus.OK);
	}

	@GetMapping(value = "/form-data")
	public ResponseEntity<?> masterGetAll(HttpServletRequest request
			, @RequestParam(required = false, name = "start", defaultValue = "1") int pageNumber
			, @RequestParam(required = false, name = "limit", defaultValue = "25") int pageSize
			, @RequestParam(required = false, name = "searchKey", defaultValue = "-1") String searchKey
			, @RequestParam(required = false, name = "orderBy", defaultValue = "-1") String orderBy
			, @RequestParam(required = false, name = "sortOrder", defaultValue = "-1") int sortOrder
			, @RequestParam(required = false, name = "isPagination", defaultValue = "true") Boolean isPagination) throws Exception {

		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));

		long totalCount;
		List<FormData> getVal;
		if (isPagination) {
			Page<FormData> getAllWtPg = formDataService.getAllDataByPg(pageNumber - 1, pageSize);
			getVal = getAllWtPg.getContent();
			totalCount = getAllWtPg.getTotalElements();
		} else {
			getVal = formDataService.getAllData();
			totalCount = getVal.size();
			pageNumber = 1;
			pageSize = Math.toIntExact(totalCount);
		}

//		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		List<FormDataIdDao> getValAll = new ModelMapperConfig().modelMapper().map(getVal,
				new TypeToken<List<FormDataIdDao>>() {
				}.getType());

		return new ResponseEntity<>(new ResultsDao(getValAll, pageNumber, pageSize, totalCount), HttpStatus.OK);
	}
}
