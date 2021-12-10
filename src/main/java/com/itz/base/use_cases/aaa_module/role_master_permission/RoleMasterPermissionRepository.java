package com.itz.base.use_cases.aaa_module.role_master_permission;

import com.itz.base.persistence.models.aaa_module.RoleMasterPermission;
import com.itz.base.use_cases.aaa_module.role_master_permission.dao.RoleMasterPrmIdPojo;
import com.itz.base.use_cases.aaa_module.role_master_permission.dao.RoleMasterPrmSidePanelPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RoleMasterPermissionRepository extends JpaRepository<RoleMasterPermission, Long> {
    String modulePrmAccessWtPrt = "SELECT t1.*, t2.name, t2.Parent, t2.ParentId,t2.SortId " +
            "FROM RoleMasterPermission as t1 " +
            "JOIN PageMaster as t2 on t1.PageMasterId =  t2.Id where " +
            "t1.RoleMasterId =?1 ORDER BY t2.SortId ";

    String modulePrmAccessWtPrtExceptSuperAdmin = "SELECT t1.*, t2.name, t2.Parent, t2.ParentId,t2.SortId " +
            "FROM RoleMasterPermission as t1 " +
            "JOIN PageMaster as t2 on t1.PageMasterId =  t2.Id where t1.RoleMasterId =?1  " +
            "and t2.ParentId != 1 and t2.Id != 1 ORDER BY t2.SortId";

    String modulePrmAccess = "SELECT t2.*, t1.doCreate, t1.doUpdate, t1.doDelete, t1.doRead " +
            "FROM RoleMasterPermission as t1 join PageMaster as t2 " +
            "on t1.PageMasterId = t2.Id where t1.RoleMasterId = ?1 " +
            "and t1.doRead = 1 ORDER BY t2.SortId";

    String modulePrmAccessExceptSuperAdmin = "SELECT t2.*, t1.doCreate, t1.doUpdate, t1.doDelete, t1.doRead " +
            "FROM RoleMasterPermission as t1 join PageMaster as t2 " +
            "on t1.PageMasterId = t2.Id where t1.RoleMasterId = ?1 " +
            "and t1.doRead = 1 and t2.ParentId != 1 and t2.Id != 1 ORDER BY t2.SortId";

    RoleMasterPermission findById(Integer pk0);

    RoleMasterPermission findByRoleMasterIdAndPageMasterId(Integer pk0, Integer pk1);

    List<RoleMasterPermission> findByRoleMasterId(int roleMasterId);

    @Transactional
    void deleteByPageMasterIdAndRoleMasterId(Integer pageMasterId, Integer roleMasterId);

    @Query(value = modulePrmAccessWtPrt, nativeQuery = true)
    List<RoleMasterPrmIdPojo> findByRoleMasterIdWtPrt(int roleMasterId);

    @Query(value = modulePrmAccessWtPrtExceptSuperAdmin, nativeQuery = true)
    List<RoleMasterPrmIdPojo> findByRoleMasterIdWtPrtExceptSuperAdmin(int roleMasterId);

    @Transactional
    void deleteByPageMasterId(Integer pageMasterId);

    void deleteByRoleMasterId(Integer roleMasterId);

    @Query(value = modulePrmAccess, nativeQuery = true)
    List<RoleMasterPrmSidePanelPojo> findByReadOnly(Integer roleMasterId);

    @Query(value = modulePrmAccessExceptSuperAdmin, nativeQuery = true)
    List<RoleMasterPrmSidePanelPojo> findByReadOnlyExceptSuperAdmin(Integer roleMasterId);
}
