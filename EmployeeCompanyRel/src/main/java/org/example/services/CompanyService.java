package org.example.services;

import java.util.List;
import java.util.Optional;

import org.example.pojo.Company;
import org.example.pojo.Department;
import org.example.pojo.Employee;
import org.example.repository.ComapnyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
	
	@Autowired
	ComapnyRepository cmpRepository;
	
	public List<Company> getAllCompanies(){
		List<Company> cmptList = cmpRepository.findAll();
		return cmptList;
	}
	
	public void saveCompany(Company cmp) throws Exception {
		// TODO Auto-generated method stub
		this.cmpRepository.save(cmp);
	}
	
	public Company updateCompany(Long cmpId,Company cmp) throws Exception{
		Optional<Company> existingECmpOpt = cmpRepository.findById(cmpId);
		if (existingECmpOpt.isPresent()) {
			Company existingCmp= existingECmpOpt.get();
			existingCmp.setName(cmp.getName());
			existingCmp.setAddress(cmp.getAddress());
			existingCmp.setCity(cmp.getCity());
			existingCmp.setState(cmp.getState());
			existingCmp.setCountry(cmp.getCountry());
			existingCmp.setZipCode(cmp.getZipCode());
			return cmpRepository.save(existingCmp);
		} else {
			throw new Exception("Company with ID " + cmpId + " not found.");
		}
	}
	
	public void deleteCompany(long cmpId){
		cmpRepository.deleteById(cmpId);
	}

}
