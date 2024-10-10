package org.example.controller;

import java.util.List;
import java.util.Optional;

import org.example.pojo.Department;
import org.example.pojo.Employee;
import org.example.repository.DepartmentRepository;
import org.example.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

	@Autowired
	DepartmentService deptService;

	@Autowired
	DepartmentRepository deptRepo;

	@GetMapping("/getAllDepartments")
	public ResponseEntity<List<Department>> getAllDeptDetails() {
		try {
			List<Department> deptList = deptService.getAllDepartments();
			if (deptList != null || !deptList.isEmpty()) {
				System.out.println("Department Details: " + deptList);
				return ResponseEntity.ok(deptList);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/addDepartment")
	public ResponseEntity<String> addDepartment(@RequestBody Department dept) {
		try {
			deptService.saveDepartment(dept);
			return ResponseEntity.status(HttpStatus.CREATED).body("Department with id " + dept.getDeptId() + " is added successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the department.");
	}

	@PatchMapping("/update/{id}")
	public ResponseEntity<String> updateDeptDetails(@PathVariable("id") long id, @RequestBody Department dept) {
		try {
			Department updatedDepartment = deptService.updateDepartment(id, dept);
			return ResponseEntity.ok("Department with ID " + updatedDepartment.getDeptId() + " updated successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
		}
	}
	
	@DeleteMapping("/deleteDepartment/{id}")
	public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long deptId){
		try {
			deptService.deleteDepartment(deptId);
			return ResponseEntity.ok("Department with ID '" + deptId + "' deleted successfully.");
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department with ID '" + deptId + "' not found.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the department.");
		}
	}
	
	@GetMapping("/getDeptByCompany/{cmp_id}")
	public ResponseEntity<List<Department>> getDeptByCompany(@PathVariable("cmp_id") Long cmp_id){
		List<Department> deptByComp = null;
		try {
			deptByComp = deptService.getDeptByCompanies(cmp_id);
			System.out.println("Departments by Companies fetched successfully");
			return ResponseEntity.ok(deptByComp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
