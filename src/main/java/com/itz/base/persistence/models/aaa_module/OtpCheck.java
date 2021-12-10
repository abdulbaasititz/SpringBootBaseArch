package com.itz.base.persistence.models.aaa_module;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "OtpCheck")
@Getter
@Setter
@NoArgsConstructor
public class OtpCheck {
    private static final int EXPIRATION = 60 * 5 * 1000;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "UserName")
    private String userName;
    @Column(name = "UserMasterUserId")
    private String userId;
    @Column(name = "Token")
    private String otp;
    @Column(name = "ExpiryDate")
    private Long expiryDate;

    public OtpCheck(UserMaster userMaster, String otp, Integer id) {
        this.userId = userMaster.getUserId();
        this.userName = userMaster.getUserName();
        this.otp = otp;
        this.expiryDate = System.currentTimeMillis() + EXPIRATION;
        this.id = id;
    }
}