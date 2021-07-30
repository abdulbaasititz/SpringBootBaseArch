package com.itz.giaiomics.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "UserMaster")
public class UserMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "UserId")
    private String userId;
    @Column(name = "UserEmail")
    private String userEmail;
    @Column(name = "Password")
    private String password;
    @Column(name = "UserName")
    private String userName;
    @Column(name = "Designation")
    private String designation;
    @Column(name = "BaseCurrency")
    private String baseCurrency;
    @Column(name = "IsActive")
    private int isActive;
    //    @Column(name = "UpAt")
//    private String upAt;
//    @Column(name = "UpBy")
//    private String upBy;
    @Column(name = "CrAt")
    private String crAt;
    @Column(name = "CrBy")
    private String crBy;
}
