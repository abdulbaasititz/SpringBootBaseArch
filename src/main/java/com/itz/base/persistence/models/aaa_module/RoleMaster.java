package com.itz.base.persistence.models.aaa_module;

import com.itz.base.persistence.models.common.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "RoleMaster")
@Getter
@Setter
@NoArgsConstructor
public class RoleMaster extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id = 0;
    @Column(name = "Name")
    private String name;
    @Column(name = "Note")
    private String note;
    @Column(name = "IsActive")
    private Boolean isActive;
    @Column(name = "CrBy")
    private String crBy;
    @Column(name = "UpBy")
    private String upBy;


//	@ManyToMany(cascade = {CascadeType.ALL})
//	@JoinTable(name = "RoleMasterPermission",
//			joinColumns = {
//				@JoinColumn(name="RoleMasterId")
//			},
//			inverseJoinColumns = {
//				@JoinColumn(name = "PageMasterId")
//			}
//	)
//	Set<PageMaster> pageMasterSet = new HashSet<>();

}