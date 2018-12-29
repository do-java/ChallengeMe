package com.dj.cm.ui.controller.filestorage;


import com.dj.cm.biz.service.exception.NotFoundBizException;
import com.dj.cm.biz.service.filestorage.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileStorageController {

	@Autowired
	private FileStorageService fileStorageService;

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource resource = fileStorageService.loadAsResource(filename);
		return ResponseEntity.ok()
//				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + resource.getFilename() + "\"") // For instance download
				.header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
				.body(resource);
	}

	@PostMapping("/files/upload")
	@ResponseBody
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		String resultFilename = fileStorageService.store(file);
		return resultFilename;
	}

	@ExceptionHandler(NotFoundBizException.class)
	public ResponseEntity<?> handleFileNotFound(NotFoundBizException e) {
		return ResponseEntity.notFound().build();
	}
}
