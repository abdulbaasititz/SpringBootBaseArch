package com.itz.base.persistence.models.super_admin_module;

import com.itz.base.helpers.utils.JwtUtil;
import com.itz.base.persistence.models.common.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TableMainSchema")
@Getter
@Setter
public class TableMainSchema extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="Id")
    private Integer id = 0;
    //	@Column(name="Field")
    private String field;
    //	@Column(name="Header")
    private String header;
    //	@Column(name="Type")
    private String type;
    //	@Column(name="Width")
    private String width;
    //	@Column(name="IsActive")
    private Boolean isActive;
    //	@Column(name="SortBy")
    private Integer sortBy;
    //	@Column(name="CrBy")
    private String crBy = JwtUtil.usr;
    //	@Column(name="UpBy")
    private String upBy;
    //	@Column(name="PageMasterId")
    private Integer pageMasterId;
    private String options;
}