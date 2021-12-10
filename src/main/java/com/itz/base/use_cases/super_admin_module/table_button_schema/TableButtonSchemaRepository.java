package com.itz.base.use_cases.super_admin_module.table_button_schema;

import com.itz.base.persistence.models.super_admin_module.TableButtonSchema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableButtonSchemaRepository extends JpaRepository<TableButtonSchema, Long> {
	TableButtonSchema findById(Integer pk0);

	List<TableButtonSchema> findByPageMasterId(Integer pk0);

	List<TableButtonSchema> findByIsActive(boolean b);

	Page<TableButtonSchema> findByIsActive(boolean b, Pageable pg);
}
