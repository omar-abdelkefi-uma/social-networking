package com.project.pfe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.pfe.entities.Company;
import com.project.pfe.services.CompanyService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CompanyController {
	// Application Api
	@Autowired
	private CompanyService companyservice;

	// ************************************Api
	// Company********************************************************//
	// add new Company
	@PostMapping("/Company/add")
	public Company createCompany(@RequestBody Company Company) {
		return companyservice.save(Company);
	}

	// Get All Companys
	@GetMapping("/Company/liste")
	public List<Company> getCompanys() {
		return companyservice.getCompanys();
	}

	// find by id
	@GetMapping("/Company/{id}")
	public Optional<Company> getCompany(@PathVariable Long id) {
		return companyservice.find(id);

	}

	// Delete Company
	@DeleteMapping("/Company/delete/{id}")
	public boolean deleteCompany(@PathVariable long id) {
		companyservice.delete(id);

		return true;
	}

	// update Company
	@PutMapping("/Company/update")
	public Company updateCompany(@RequestBody Company Company) {
		return companyservice.save(Company);

	}

}