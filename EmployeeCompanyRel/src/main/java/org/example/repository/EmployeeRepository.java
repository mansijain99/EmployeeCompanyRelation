package org.example.repository;

import java.util.List;

import org.example.pojo.Department;
import org.example.pojo.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("delete from Employee where emp_id = :empId")
    public List<Employee> deleteEmployeeById(@Param("empId") Long employeeId);

}
