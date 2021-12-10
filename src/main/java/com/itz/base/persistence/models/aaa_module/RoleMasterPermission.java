package com.itz.base.persistence.models.aaa_module;


import com.itz.base.helpers.utils.JwtUtil;
import com.itz.base.persistence.models.common.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "RoleMasterPermission")
@Getter
@Setter
public class RoleMasterPermission extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id = 0;
    @Column(name = "DoCreate")
    private Boolean create;
    @Column(name = "DoUpdate")
    private Boolean update;
    @Column(name = "DoDelete")
    private Boolean delete;
    @Column(name = "DoRead")
    private Boolean read;
    @Column(name = "CrBy")
    private String crBy = JwtUtil.usr;
    @Column(name = "UpBy")
    private String upBy;
    @Column(name = "PageMasterId")
    private Integer pageMasterId;

    @Column(name = "RoleMasterId")
    private Integer roleMasterId;

//	@JsonBackReference
//	@OneToMany(fetch = FetchType.LAZY)
//	@JoinColumn(name="RoleMasterId",nullable = false)
//	private RoleMaster roleMaster;
}