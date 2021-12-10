package com.itz.base.use_cases.aaa_module.user_master;

import com.itz.base.persistence.models.aaa_module.UserMaster;
import com.itz.base.persistence.models.aaa_module.UserRole;
import com.itz.base.use_cases.aaa_module.user_master.dao.UserMasterPojo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserMasterRepository extends JpaRepository<UserMaster, Long> {
    String getAllByQuery = "SELECT t2.Id,t2.UserId,t2.UserName,t2.Designation,t2.PhoneNumber,t2.Email,t2.Password" +
            ",t2.IsActive,t3.Name as RoleName,t3.Id as RoleMasterId FROM UserRole t1 join UserMaster t2 join RoleMaster t3 " +
            "where t1.UserMasterId = t2.Id " +
            "and t1.RoleMasterId = t3.Id";
    String getAllCount = "select count(t1.Id) FROM UserRole t1 join UserMaster " +
            "t2 join RoleMaster t3 where t1.UserMasterId = t2.Id and t1.RoleMasterId = t3.Id";

    UserMaster findById(Integer pk0);

    UserMaster findByUserId(String pk0);

    @Query(value = getAllByQuery, nativeQuery = true)
    List<UserMasterPojo> findAllByQry();

    @Query(value = getAllByQuery, nativeQuery = true, countQuery = getAllCount)
    Page<UserMasterPojo> findAllByQryWtPg(Pageable pg);
}

@Repository
interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findById(Integer pk0);

    @Transactional
    @Modifying
    void deleteByUserMasterId(Integer id);
}