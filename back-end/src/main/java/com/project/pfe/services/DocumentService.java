package com.project.pfe.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.pfe.dao.DocumentRepository;
import com.project.pfe.entities.Document;
import com.project.pfe.property.DocumentStorageProperty;

import jakarta.transaction.Transactional;

@Service
public class DocumentService {

	@Autowired
	private DocumentRepository documentRepository;

	private final Path docStorageLocation;

	@Autowired
	public DocumentService(DocumentStorageProperty documentStorageProperty) throws IOException {
		this.docStorageLocation = Paths.get(documentStorageProperty.getUploadDirectory()).toAbsolutePath().normalize();
		Files.createDirectories(this.docStorageLocation);
	}

	@Transactional
	public void addDocuments(MultipartFile[] multipartFiles) throws NoSuchAlgorithmException, IOException {
		for (MultipartFile multipartFile : multipartFiles) {
			create(multipartFile);
		}
	}

	private void create(MultipartFile multipartFile) throws NoSuchAlgorithmException, IOException {
		Document document = new Document();
		document.setName(multipartFile.getOriginalFilename());
		document.setMimeType(multipartFile.getContentType());
		document.setSize(multipartFile.getSize());
		document.setHash();
		storeDocument(multipartFile, document.getHash());
		documentRepository.save(document);
	}

	// hash name of file
	private void storeDocument(MultipartFile file, String hash) throws IOException {
		Path targetLocation = this.docStorageLocation.resolve(hash);
		Files.copy(file.getInputStream(), targetLocation);
	}


	public Path getDocStorageLocation() {
		return docStorageLocation;
	}

	public Optional<Document> findById(Long id) {
		// TODO Auto-generated method stub
		return documentRepository.findById(id);
	}
}
