package com.itz.base.persistence.models.super_admin_module;

import com.itz.base.helpers.utils.JwtUtil;
import com.itz.base.persistence.models.common.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TableButtonSchema")
@Getter
@Setter
public class TableButtonSchema extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="Id")
    private Integer id = 0;
    //	@Column(name="Icon")
    private String icon;
    //	@Column(name="ToolTip")
    private String toolTip;
    //	@Column(name="Action")
    private String action;
    //	@Column(name="IsActive")
    private Boolean isActive;
    //	@Column(name="CrBy")
    private String crBy = JwtUtil.usr;
    //	@Column(name="UpBy")
    private String upBy;
    //	@Column(name="PageMasterId")
    private Integer pageMasterId;
    private String options;
}