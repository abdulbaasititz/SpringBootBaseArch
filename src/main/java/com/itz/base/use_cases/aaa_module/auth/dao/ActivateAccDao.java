package com.itz.base.use_cases.aaa_module.auth.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivateAccDao {
    private String userId;
    private String otp;
}