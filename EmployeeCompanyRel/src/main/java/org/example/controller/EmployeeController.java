package org.example.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.example.pojo.Employee;
import org.example.repository.EmployeeRepository;
import org.example.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/getAllEmployees")
	public ResponseEntity<List<Employee>> getAllEmpDetails(){
		try {
			List<Employee> empList = empService.getAllEmployees();
			if(empList != null || !empList.isEmpty()) {
				System.out.println("Employee Details: " + empList);
				return ResponseEntity.ok(empList);
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/addEmployee")
	public ResponseEntity<String> addEmployee(@RequestBody Employee emp){
		try {
			empService.saveEmployee(emp);
			return ResponseEntity.status(HttpStatus.CREATED).body("Employee with id " + emp.getEmpId() + " is added successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the employee.");
		
	}
	
	@PatchMapping("/update/{id}")
	public ResponseEntity<String> updateEmpDetails(@PathVariable("id") long id, @RequestBody Employee emp) {
		try {
			Employee updatedEmployee = empService.updateEmployee(id, emp);
			return ResponseEntity.ok("Employee with ID " + updatedEmployee.getEmpId() + " updated successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
		}
	}

	@DeleteMapping("/deleteEmployee/name/{name}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("name") String name) {
		try {
			empService.deleteEmployee(name);
			return ResponseEntity.ok("Employee(s) with the name '" + name + "' deleted successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting employees.");
		}
	}
	
	@GetMapping("/getEmpByCompany/{cmp_id}")
	public ResponseEntity<List<Employee>> getEmpByCompany(@PathVariable("cmp_id") Long cmp_id){
		List<Employee> employeeByComp = null;
		try {
			employeeByComp = empService.getEmployeesByCompanies(cmp_id);
			System.out.println("Employees by Companies fetched successfully");
			return ResponseEntity.ok(employeeByComp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/getEmpByDept/{dept_id}")
	public ResponseEntity<List<Employee>> getEmpByDept(@PathVariable("dept_id") Long dept_id){
		List<Employee> employeeByDepart = null;
		try {
			employeeByDepart = empService.getEmployeesByDepartment(dept_id);
			System.out.println("Employees by Department fetched successfully");
			return ResponseEntity.ok(employeeByDepart);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
