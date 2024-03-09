package com.tablenine.application.exam.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring")
@Getter
@Setter
public class Configuration {
	DataSourceConfig datasource;

	@Getter
	@Setter
	public static class DataSourceConfig {
		private String url;
		private String username;
		private String password;
	}
}
