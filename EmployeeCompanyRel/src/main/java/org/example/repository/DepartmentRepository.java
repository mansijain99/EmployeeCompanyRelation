package org.example.repository;

import java.util.List;

import org.example.pojo.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	@Query("SELECT d FROM Department d JOIN d.company c WHERE c.cmpId = :comapny_id")
	public List<Department> findDeptByCompanies(@Param("comapny_id") Long companyId);

}
