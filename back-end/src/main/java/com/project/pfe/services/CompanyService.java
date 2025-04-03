package com.project.pfe.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.pfe.dao.CompanyRepository;
import com.project.pfe.entities.Company;

@Service
public class CompanyService {
	@Autowired
	CompanyRepository companydao;

	public Company save(Company company) {
		return companydao.save(company);
	}

	public Company update(Company company) {
		return companydao.save(company);
	}

	public Optional<Company> find(Long id) {
		return companydao.findById(id);
	}

	public List<Company> getCompanys() {
		return companydao.findAll();
	}

	public boolean delete(Long id) {
		companydao.deleteById(id);
		return true;
	}
}