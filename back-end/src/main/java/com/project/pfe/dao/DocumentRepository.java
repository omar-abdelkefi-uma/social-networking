package com.project.pfe.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.pfe.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

}
