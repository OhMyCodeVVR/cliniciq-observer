package com.dsystem.cliniciqobserver.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class BitrixConnectionConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    @Qualifier("dsystemBitrixDataSource")
    public DataSource dataSourceBitrix(@Qualifier("bitrix") HikariConfig config) {
        return new HikariDataSource(config);
    }

    @Bean
    @Qualifier("bitrix")
    public HikariConfig hikariBitrixConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(environment.getProperty("db.bitrix_url"));
        config.setUsername(environment.getProperty("db.bitrix_db_user"));
        config.setPassword(environment.getProperty("db.bitrix_db_password"));
        config.setDriverClassName(environment.getProperty("db.driver-class-name"));
        config.setMaximumPoolSize(Integer.parseInt(environment.getProperty("db.bitrix_hikari.max-pool-size")));
        return config;
    }

    @Bean
    @Qualifier("bitrixNamedParameterJdbcTemplate")
    NamedParameterJdbcTemplate bitrixNamedParameterJdbcTemplate(@Qualifier("dsystemBitrixDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
