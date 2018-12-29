package com.dj.cm.biz.service.filestorage.impl;

import com.dj.cm.biz.service.exception.BizException;
import com.dj.cm.biz.service.exception.NotFoundBizException;
import com.dj.cm.biz.service.filestorage.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

	@Autowired
	private FileStorageConfig config;

	@Override
	public String store(MultipartFile file) {
		if (file == null) {
			throw new IllegalArgumentException("File is null");
		}

		if (file.isEmpty()) {
			throw new BizException("Failed to store empty file with name: " + file.getOriginalFilename());
		}

		if (file.getOriginalFilename().isEmpty()) {
			throw new BizException("Failed to store file with empty filename: " + file);
		}

		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			File uploadDir = new File(config.getFileStoragePath());

			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			String uuidFile = UUID.randomUUID().toString();
			String resultFilename = uuidFile + "." + filename;

			InputStream inputStream = file.getInputStream();
			Files.copy(inputStream, getPath(resultFilename), StandardCopyOption.REPLACE_EXISTING);
			return resultFilename;
		} catch (IOException e) {
			throw new BizException("Failed to store file " + filename, e);
		}
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path path = getPath(filename);
			Resource resource = new UrlResource(path.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new NotFoundBizException(String.format("Could not read file: %s, path: %s", filename, path.toUri()));
			}
		} catch (MalformedURLException e) {
			throw new NotFoundBizException(String.format("Could not read file: %s", filename), e);
		}
	}

	@Override
	public void delete(String filename) {
		Path path = getPath(filename);
		File file = new File(path.toUri());

		if (file.exists()) {
			file.delete();
		}
	}

	private Path getPath(String filename) {
		return Paths.get(config.getFileStoragePath() + File.separator + filename);
	}
}
