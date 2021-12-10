package com.itz.base.persistence.models.super_admin_module;

import com.itz.base.helpers.utils.JwtUtil;
import com.itz.base.persistence.models.common.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PageMaster")
@Getter
@Setter
@NoArgsConstructor
public class PageMaster extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 0;
    private Integer parentId = 0;
    private String name;
    private String note;
    private String url;
    private String icon;
    private String serviceName;
    private Boolean isActive;
    private Float sortId;
    private Boolean parent;
    private String crBy = JwtUtil.usr;
    private String upBy;
    private String options;
//
//	@ManyToMany(mappedBy = "RoleMaster",cascade = {CascadeType.ALL})
//	private Set<RoleMaster> roleMasters = new HashSet<RoleMaster>();


}