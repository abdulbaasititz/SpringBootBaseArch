package com.itz.base.use_cases.aaa_module.profile_setting;

import com.itz.base.helpers.common.results.ReportDao;
import com.itz.base.helpers.common.results.ResultDao;
import com.itz.base.helpers.common.results.ResultsDao;
import com.itz.base.helpers.common.token.ClaimsDao;
import com.itz.base.helpers.common.token.ClaimsSet;
import com.itz.base.helpers.utils.AwsS3Service;
import com.itz.base.persistence.models.aaa_module.ProfileSetting;
import com.itz.base.use_cases.aaa_module.profile_setting.dao.ProfileSettingCstDao;
import com.itz.base.use_cases.aaa_module.profile_setting.dao.ProfileSettingDaos;
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
public class ProfileSettingController {

	@Autowired
	ProfileSettingService ser;
	@Autowired
	ClaimsSet claimsSet;
	@Autowired
	AwsS3Service awsS3Service;

	@PostMapping(value = "/profile-setting")
	public ResponseEntity<?> masterSet(HttpServletRequest request, @ModelAttribute ProfileSettingCstDao getVal) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		String fileName;
		if (getVal.getLogoSmallFile() != null) {
			fileName = awsS3Service.generateFileName(getVal.getLogoSmallFile().getOriginalFilename());
			awsS3Service.uploadFile(getVal.getLogoSmallFile(), fileName);
			getVal.setLogoSmallUrl(awsS3Service.getFileUrl(fileName));
		}
		if (getVal.getLogoLargeFile() != null) {
			fileName = awsS3Service.generateFileName(getVal.getLogoLargeFile().getOriginalFilename());
			awsS3Service.uploadFile(getVal.getLogoLargeFile(), fileName);
			getVal.setLogoLargeUrl(awsS3Service.getFileUrl(fileName));
		}

		ser.setData(new ModelMapper().map(getVal, ProfileSetting.class));
		return new ResponseEntity<>(new ReportDao("Added Successfully", true), HttpStatus.OK);
	}

	@PutMapping(value = "/profile-setting")
	public ResponseEntity<?> masterUpdate(HttpServletRequest request, @ModelAttribute ProfileSettingCstDao getVal) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		ProfileSetting setVal = ser.getPk1(getVal.getId());
		String fileName;
		if (setVal != null) {
			if (getVal.getLogoSmallFile() != null) {
				if (setVal.getLogoSmallUrl() != null && !setVal.getLogoSmallUrl().equals(""))
					awsS3Service.deleteFileFromS3Bucket(setVal.getLogoSmallUrl());
				fileName = awsS3Service.generateFileName(getVal.getLogoSmallFile().getOriginalFilename());
				awsS3Service.uploadFile(getVal.getLogoSmallFile(), fileName);
				setVal.setLogoSmallUrl(awsS3Service.getFileUrl(fileName));
			}
			if (getVal.getLogoLargeFile() != null) {
				if (setVal.getLogoLargeUrl() != null && !setVal.getLogoLargeUrl().equals(""))
					awsS3Service.deleteFileFromS3Bucket(setVal.getLogoLargeUrl());
				fileName = awsS3Service.generateFileName(getVal.getLogoLargeFile().getOriginalFilename());
				awsS3Service.uploadFile(getVal.getLogoLargeFile(), fileName);
				setVal.setLogoLargeUrl(awsS3Service.getFileUrl(fileName));
			}
			setVal.setAddressOne(getVal.getAddressOne());
			setVal.setAddressTwo(getVal.getAddressTwo());
			setVal.setCompanyName(getVal.getCompanyName());
			setVal.setEmail(getVal.getEmail());
			setVal.setPhoneNumber(getVal.getPhoneNumber());
			ser.setData(setVal);
		} else {
			throw new Exception("Value Not Found To Update");
		}
		return new ResponseEntity<>(new ReportDao("Updated Successfully", true), HttpStatus.OK);
	}

	@DeleteMapping(value = "/profile-setting/{id}")
	public ResponseEntity<?> masterDelete(HttpServletRequest request
			, @PathVariable(name = "id") Integer id) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		ProfileSetting getVal = ser.getPk1(id);
		if (getVal == null) {
			throw new Exception(id + " Not Found To Delete");
		} else {
			ser.delData(getVal);
		}
		return new ResponseEntity<>(new ReportDao("Deleted Successfully", true), HttpStatus.OK);
	}

	@GetMapping(value = "/profile-setting/{id}")
	public ResponseEntity<?> masterGet(HttpServletRequest request
			, @PathVariable(name = "id") Integer id) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		ProfileSetting getVal = ser.getPk1(id);
		if (getVal == null)
			throw new Exception(id + " Not Found To Get");
		return new ResponseEntity<>(new ResultDao(new ModelMapper().map(getVal, ProfileSettingDaos.class), "Fetched Successfully", true), HttpStatus.OK);
	}

	@GetMapping(value = "/auth/profile-setting")
	public ResponseEntity<?> masterGetAll(HttpServletRequest request) throws Exception {
		//ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		List<ProfileSetting> getVal = ser.getAllData();
		long totalCount = getVal.size();
		return new ResponseEntity<>(new ResultsDao(new ModelMapper().map(getVal,
				new TypeToken<List<ProfileSettingDaos>>() {
				}.getType()), 1, Math.toIntExact(totalCount), totalCount), HttpStatus.OK);
	}
}
