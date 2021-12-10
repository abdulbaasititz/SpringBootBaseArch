package com.itz.base.use_cases.aaa_module.profile_setting;

import com.itz.base.persistence.models.aaa_module.ProfileSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileSettingService {

	@Autowired
	ProfileSettingRepository rep;

	public ProfileSetting getPk1(Integer pk0) throws Exception {
		try {
			return rep.findById(pk0);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Integer setData(ProfileSetting val) throws Exception {
		try {
			return rep.save(val).getId();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void delData(ProfileSetting val) throws Exception {
		try {
			rep.delete(val);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<ProfileSetting> getAllData() throws Exception {
		try {
			return rep.findAll();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
