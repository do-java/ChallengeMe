package com.dj.cm.biz.service.filestorage;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * File storage service.
 */
public interface FileStorageService {
	String store(MultipartFile file);

	Resource loadAsResource(String filename);

	String getPath(String filename);

	void delete(String filename);
}
