package com.itz.base.use_cases.super_admin_module.page_master;


import com.itz.base.persistence.models.super_admin_module.PageMaster;
import com.itz.base.use_cases.super_admin_module.page_master.dao.PageMasterPojo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageMasterRepository extends JpaRepository<PageMaster, Long> {
	String pageMasterWtParentName = "SELECT t1.*,(select name from PageMaster where id = t1.ParentId) " +
			"as parentName FROM PageMaster as t1 Where IsActive = 1 and Id =?1";
	String pageMasterAll = "SELECT t1.*,(select name from PageMaster where id = t1.ParentId) " +
			"as parentName FROM PageMaster as t1 Where IsActive = 1";

	String pageMasterGetByParent = "SELECT t1.*,(select name from PageMaster where id = t1.ParentId) " +
			"as parentName FROM PageMaster as t1 Where IsActive = 1 and parent =?1";

	PageMaster findById(Integer pk0);

	PageMaster findByParentId(Integer pk0);

	PageMaster findByServiceName(String pk0);

	List<PageMaster> findAllByIsActive(boolean b);

	List<PageMaster> findAllByIsActive(boolean b, Pageable pg);

	@Query(value = pageMasterWtParentName, nativeQuery = true)
	PageMasterPojo findByIdWtNm(Integer pageMasterId);

	@Query(value = pageMasterAll, nativeQuery = true)
	List<PageMasterPojo> findAllWtNm();

	@Query(value = pageMasterAll, nativeQuery = true)
	List<PageMasterPojo> findAllWtNmPg(Pageable pg);

	@Query(value = pageMasterGetByParent, nativeQuery = true)
	List<PageMasterPojo> findByParent(Boolean b);

	long countByIsActive(boolean b);
}
