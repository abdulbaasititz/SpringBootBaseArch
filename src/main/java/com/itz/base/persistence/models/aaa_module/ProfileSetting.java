package com.itz.base.persistence.models.aaa_module;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ProfileSetting")
@Getter
@Setter
public class ProfileSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 0;
    private String companyName;
    private String logoSmallUrl;
    private String logoLargeUrl;
    private String addressOne;
    private String addressTwo;
    private String email;
    private String phoneNumber;
}