package com.itz.base.use_cases.super_admin_module.table_main_schema;

import com.itz.base.persistence.models.super_admin_module.TableMainSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableMainSchemaRepository extends JpaRepository<TableMainSchema, Long> {
	TableMainSchema findById(Integer pk0);

	List<TableMainSchema> findByPageMasterId(Integer pk0);
}
