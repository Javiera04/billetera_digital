package com.bootcamp.billetera.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class BeanConfig {
	@Bean
	JdbcTemplate dbcTemplate(DataSource datasource) {
		return new JdbcTemplate(datasource);
	}
}
