package com.itz.base.use_cases.aaa_module.profile_setting;

import com.itz.base.persistence.models.aaa_module.ProfileSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileSettingRepository extends JpaRepository<ProfileSetting, Long> {
	ProfileSetting findById(Integer pk0);
}
