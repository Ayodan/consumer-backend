package com.olaolu.database.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
@Configuration
public class FlywayConfig {
    @Autowired
    DataSource dataSource;

    @Value("${flyway.migrations.locations}")
    public String migrationLocation;
    @Bean
    public Flyway flyway(){
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setSqlMigrationPrefix("V");
        flyway.setLocations(migrationLocation);
        flyway.setBaselineOnMigrate(true);
        flyway.repair();
        flyway.migrate();
        return  flyway;

    }
}
