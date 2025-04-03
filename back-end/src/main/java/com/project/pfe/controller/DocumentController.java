package com.project.pfe.controller;

import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.project.pfe.entities.Document;
import com.project.pfe.services.DocumentService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

@RestController
@RequestMapping("/api/document")
public class DocumentController {

	private static final Logger LOG = LoggerFactory.getLogger(DocumentController.class);

	@Autowired
	private DocumentService documentService;

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "multipart/form-data")
	public void addDocument(@RequestParam(value = "documents") MultipartFile[] multipartFiles)
			throws NoSuchAlgorithmException, IOException {
		LOG.debug("Adding document >>>");
		documentService.addDocuments(multipartFiles);
		LOG.debug("<<< Document added");
	}

	@GetMapping("/download/{filename:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
		try {
			Path filePath = this.documentService.getDocStorageLocation().resolve(filename).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists() || resource.isReadable()) {
				return ResponseEntity.ok().body(resource);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
