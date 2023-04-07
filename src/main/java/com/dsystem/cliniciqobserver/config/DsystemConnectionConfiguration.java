package com.dsystem.cliniciqobserver.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component

public class DsystemConnectionConfiguration {
    @Autowired
    private Environment environment;

    @Bean
    @Qualifier("dsystemDataSource")
    public DataSource dataSource(@Qualifier("dsystem") HikariConfig config) {
        return new HikariDataSource(config);
    }

    @Bean
    @Qualifier("dsystem")
    public HikariConfig hikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(environment.getProperty("db.url"));
        config.setUsername(environment.getProperty("db.user"));
        config.setPassword(environment.getProperty("db.password"));
        config.setDriverClassName(environment.getProperty("db.driver-class-name"));
        config.setMaximumPoolSize(Integer.parseInt(environment.getProperty("db.hikari.max-pool-size")));
        return config;
    }


    @Bean
    @Qualifier("dsystemNamedParameterJdbcTemplate")
    NamedParameterJdbcTemplate dsystemParameterJdbcTemplate(@Qualifier("dsystemDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }


}
