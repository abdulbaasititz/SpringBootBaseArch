package com.itz.base.use_cases.aaa_module.profile_setting.dao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;

@Getter
@Setter
public class ProfileSettingCstDao {
    private Integer id;
    private String companyName;
    private MultipartFile logoSmallFile;
    private String logoSmallUrl;

    private MultipartFile logoLargeFile;
    private String logoLargeUrl;
    @Size(max = 60)
    private String addressOne;
    @Size(max = 120)
    private String addressTwo;
    @Size(max = 45)
    private String email;
    @Size(max = 20)
    private String phoneNumber;
}