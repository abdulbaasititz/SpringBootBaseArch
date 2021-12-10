package com.itz.base.use_cases.aaa_module.role_master_permission;

import com.itz.base.helpers.common.results.ReportDao;
import com.itz.base.helpers.common.results.ResultDao;
import com.itz.base.helpers.common.token.ClaimsDao;
import com.itz.base.helpers.common.token.ClaimsSet;
import com.itz.base.persistence.models.aaa_module.RoleMasterPermission;
import com.itz.base.use_cases.aaa_module.role_master_permission.dao.RoleMasterPrmIdDao;
import com.itz.base.use_cases.aaa_module.role_master_permission.dao.RoleMasterPrmIdPojo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("${spring.base.path}")
public class RoleMasterPermissionController {
	@Autowired
	RoleMasterPermissionService roleMasterPrmService;
	@Autowired
	ClaimsSet claimsSet;

	// update when prm set
	@PutMapping(value = "/role-master-prm")
	public ResponseEntity<?> masterSet(HttpServletRequest request, @RequestBody List<RoleMasterPrmIdDao> getVal) throws Exception {

		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));

		//roleMasterPrmService.delByPageMstIdAndRolMstId(getVal.get(0).getPageMasterId(),getVal.get(0).getRoleMasterId());

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);

		modelMapper.typeMap(RoleMasterPrmIdDao.class, RoleMasterPermission.class).addMappings(mapper -> {
			mapper.map(RoleMasterPrmIdDao -> claimsDao.getUsr(),
					RoleMasterPermission::setCrBy);
			mapper.map(RoleMasterPrmIdDao -> claimsDao.getUsr(),
					RoleMasterPermission::setUpBy);
		});
		List<RoleMasterPermission> getValAll = modelMapper.map(getVal,
				new TypeToken<List<RoleMasterPermission>>() {
				}.getType());
		roleMasterPrmService.setData(getValAll);
		return new ResponseEntity<>(new ReportDao("Success", true), HttpStatus.OK);
	}


	@GetMapping(value = "/role-master-prm")
	public ResponseEntity<?> masterGetAll(HttpServletRequest request) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		int roleMasterId = Integer.parseInt(claimsDao.getRol());
		List<RoleMasterPrmIdPojo> roleMasterPermission = roleMasterPrmService.getAllByRolWtPrt(roleMasterId);

		return new ResponseEntity<>(new ResultDao(roleMasterPermission, "Success", true), HttpStatus.OK);
	}

	@GetMapping(path = "/role-master-prm/{roleMasterId}")
	public ResponseEntity<?> masterGetAllByRole(HttpServletRequest request
			, @PathVariable(name = "roleMasterId") Integer roleMasterId) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		if (!claimsDao.getSub().equalsIgnoreCase("abdul")) {
			//getVal = roleMasterService.getAllRoleMasterExceptSuperAdmin("SuperAdmin");
			return new ResponseEntity<>(new ResultDao(roleMasterPrmService.getAllByRolWtPrtExceptSuperAdmin(roleMasterId)
					, "Success", true), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResultDao(roleMasterPrmService.getAllByRolWtPrt(roleMasterId)
					, "Success", true), HttpStatus.OK);
		}

	}

	//Each page load a 2
	@GetMapping(value = "/role-master-prm/read-only")
	public ResponseEntity<?> masterGetReadOnly(HttpServletRequest request) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		if (!claimsDao.getSub().equalsIgnoreCase("abdul")) {
			//getVal = roleMasterService.getAllRoleMasterExceptSuperAdmin("SuperAdmin");
			return new ResponseEntity<>(new ResultDao(roleMasterPrmService.getByReadOnlyExceptSuperAdmin(Integer.valueOf(claimsDao.getRol()))
					, "Success", true), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResultDao(roleMasterPrmService.getByReadOnly(Integer.valueOf(claimsDao.getRol()))
					, "Success", true), HttpStatus.OK);
		}


	}

}
