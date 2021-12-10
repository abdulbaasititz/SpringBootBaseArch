package com.itz.base.use_cases.aaa_module.role_master;

import com.itz.base.persistence.models.aaa_module.RoleMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMasterRepository extends JpaRepository<RoleMaster, Long> {
    RoleMaster findById(Integer pk0);

    RoleMaster findByName(String pk0);

    List<RoleMaster> findByIsActive(boolean b);

    Page<RoleMaster> findByIsActive(boolean b, Pageable pg);

    Page<RoleMaster> findByIsActiveAndNameContainingIgnoreCase(boolean b, String searchKey, Pageable of);

    List<RoleMaster> findByNameNotLike(String sub);
}
