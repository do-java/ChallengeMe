package com.dj.cm.biz.service.filestorage.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file-storage.properties")
@Getter
public class FileStorageConfig {

	@Value("${file.storage.path}")
	private String fileStoragePath;
}
