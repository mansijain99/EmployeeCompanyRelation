package org.example.controller;

import java.util.List;
import java.util.Optional;

import org.example.pojo.Company;
import org.example.pojo.Employee;
import org.example.repository.ComapnyRepository;
import org.example.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyService cmpService;

    @Autowired
    ComapnyRepository cmpRepo;

    @GetMapping("/getAllCompany")
    public ResponseEntity<List<Company>> getAllCompanyDetails() {
        try {
            List<Company> cmpList = cmpService.getAllCompanies();
            if (cmpList != null || !cmpList.isEmpty()) {
                System.out.println("Company Details: " + cmpList);
                return ResponseEntity.ok(cmpList);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/addCompany")
    public ResponseEntity<String> addCompany(@RequestBody Company cmp) {
        try {
            cmpService.saveCompany(cmp);
            return ResponseEntity.status(HttpStatus.CREATED).body("Company with id " + cmp.getCmpId() + " is added successfully");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the company.");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateCmpDetails(@PathVariable("id") long id, @RequestBody Company cmp) {
        try {
            Company updatedCompany = cmpService.updateCompany(id, cmp);
            return ResponseEntity.ok("Company with ID " + updatedCompany.getCmpId() + " updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteCompany/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable("id") Long cmpId) {
        try {
            cmpService.deleteCompany(cmpId);
            return ResponseEntity.ok("Company with ID '" + cmpId + "' deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company with ID '" + cmpId + "' not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the company.");
        }
    }

}
