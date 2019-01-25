package com.dj.cm.biz.service.filestorage;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

/**
 * File storage service.
 */
public interface FileStorageService {
	/**
	 * Store multipart file.
	 *
	 * @param file {@link MultipartFile} to store
	 * @return result file name
	 */
	String store(MultipartFile file);

	/**
	 * Store file from data url.
	 *
	 * @param dataUrl data url in format data:image/png;base64,iVBORw0KGgoAAAANSUh....
	 * @return file name
	 */
	String storeBase64Url(String dataUrl);

	/**
	 * Load file as resource.
	 *
	 * @param filename filename
	 * @return {@link Resource}
	 */
	Resource loadAsResource(String filename);

	/**
	 * Delete file from storage.
	 *
	 * @param filename filename
	 */
	void delete(String filename);
}
