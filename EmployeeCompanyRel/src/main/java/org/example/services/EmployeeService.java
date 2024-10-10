package org.example.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.example.pojo.Employee;
import org.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository emplRepository;
	
	public List<Employee> getAllEmployees() throws Exception{
		List<Employee> empList = emplRepository.findAll();
		return empList;
	}
	
	public void saveEmployee(Employee emp) throws Exception {
		// TODO Auto-generated method stub
		this.emplRepository.save(emp);
	}
	
	public Employee updateEmployee(Long empId, Employee emp) throws Exception{
		Optional<Employee> existingEmpOpt = emplRepository.findById(empId);
		if (existingEmpOpt.isPresent()) {
			Employee existingEmp = existingEmpOpt.get();
			existingEmp.setName(emp.getName());
			existingEmp.setAddress(emp.getAddress());
			existingEmp.setCity(emp.getCity());
			existingEmp.setstate(emp.getstate());
			existingEmp.setCountry(emp.getCountry());
			existingEmp.setZipCode(emp.getZipCode());
			return emplRepository.save(existingEmp);
		} else {
			throw new Exception("Employee with ID " + empId + " not found.");
		}
	}

	public void deleteEmployee(String name) throws Exception {
		List<Employee> employees = emplRepository.findAll();
		// Iterate through the list to find employees with the given name
		for (Employee employee : employees) {
			if (employee.getName().equalsIgnoreCase(name)) {
				emplRepository.delete(employee);
			}
		}
	}
	
	public List<Employee> getEmployeesByCompanies(Long cmpanyId){
		List<Employee> employees = emplRepository.findAll();
		List<Employee> empByCompany = employees.stream().filter(e -> e.getDepartment()!=null 
				&& e.getDepartment().getCompany().getCmpId().equals(cmpanyId)).collect(Collectors.toList());
		System.out.println("Employee List By Companies: " + empByCompany);
		return empByCompany;
		
	}
	
	public List<Employee> getEmployeesByDepartment(Long departmentId){
		List<Employee> emp = emplRepository.findAll();
		List<Employee> empByDept = emp.stream().filter(e -> e.getDepartment() != null 
				&& e.getDepartment().getDeptId().equals(departmentId)).collect(Collectors.toList());
		System.out.println("Employee List By Departments: " + empByDept);
		return empByDept;
		
	}

}
