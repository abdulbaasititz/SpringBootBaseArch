package com.itz.base.use_cases.aaa_module.profile_setting.dao;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class ProfileSettingDaos {
    @Size(max = 11)
    private Integer id;
    @Size(max = 30)
    private String companyName;
    @Size(max = 45)
    private String logoSmallUrl;
    @Size(max = 45)
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