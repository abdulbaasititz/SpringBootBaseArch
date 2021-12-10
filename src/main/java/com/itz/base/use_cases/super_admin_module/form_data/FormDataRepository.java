package com.itz.base.use_cases.super_admin_module.form_data;

import com.itz.base.persistence.models.super_admin_module.FormData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FormDataRepository extends JpaRepository<FormData, Long> {
	String formDataConcat = "SELECT CONCAT('{',GROUP_CONCAT(CONCAT('\"',Header,'\":',value)),'}') as json FROM FormData where PageMasterId = ?1 GROUP BY LiNo";

	FormData findById(Integer pk0);

	List<FormData> findByPageMasterId(Integer pk0);

	@Query(value = formDataConcat, nativeQuery = true)
	List<String> findByPageMasterIdWtJson(Integer pageMasterId);

	@Transactional
	void deleteAllByPageMasterId(Integer pageMasterId);
}
