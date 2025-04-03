package com.project.pfe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.pfe.entities.Company;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
