package com.itz.base.persistence.models.aaa_module;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.itz.base.persistence.models.common.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "UserRole")
@Getter
@Setter
@NoArgsConstructor
public class UserRole extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id = 0;


    @Column(name = "CrBy")
    private String crBy;
    @Column(name = "UpBy")
    private String upBy;

    @Column(name = "RoleMasterId")
    private Integer roleMasterId;
//	@Column(name="UserMasterId")
//	private Integer userMasterId;


    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserMasterId", nullable = false)
    private UserMaster userMaster;
}