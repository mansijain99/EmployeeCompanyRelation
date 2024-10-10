package org.example.services;

import java.util.List;
import java.util.Optional;

import org.example.pojo.Department;
import org.example.pojo.Employee;
import org.example.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
	
	@Autowired
	DepartmentRepository deptRepository;
	
	public List<Department> getAllDepartments(){
		List<Department> deptList = deptRepository.findAll();
		return deptList;
	}
	
	public void saveDepartment(Department dept) throws Exception {
		// TODO Auto-generated method stub
		this.deptRepository.save(dept);
	}
	
	public Department updateDepartment(Long deptId, Department dept) throws Exception{
		Optional<Department> existingDeptOpt = deptRepository.findById(deptId);
		if (existingDeptOpt.isPresent()) {
			Department existingDept = existingDeptOpt.get();
			existingDept.setName(dept.getName());
			return deptRepository.save(existingDept);
		} else {
			throw new Exception("Employee with ID " + deptId + " not found.");
		}
	}
	
	public void deleteDepartment(long deptId){
		deptRepository.deleteById(deptId);
	}
	
	public List<Department> getDeptByCompanies(Long companyId){
		List<Department> deptListByCompny = deptRepository.findDeptByCompanies(companyId);
		System.out.println("Department List By Companies: " + deptListByCompny);
		return deptListByCompny;
	}

}
