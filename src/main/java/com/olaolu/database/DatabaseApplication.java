package com.olaolu.database;

import com.nimbusds.jwt.JWTClaimsSet;
import com.olaolu.database.model.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;

import java.security.AuthProvider;

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableCaching
@SpringBootApplication(scanBasePackages = {"com.olaolu.database.controllers","com.olaolu.database.model",
		"com.olaolu.database.serviceAndDao","com.olaolu.database.exceptions","com.olaolu.database.config"})
@DatabaseApplication.ClassPreamble(author = "John Doe",
		date = "3/17/2002",
		currentRevision = 6,
		lastModified = "4/12/2007",
		lastModifiedBy = "Jane Doe",
		// Note array notation
		reviewers = {"Alice", "Bob", "Cindy"})
@EnableConfigurationProperties(SecurityProperties.class)
public class DatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
	}

	@interface ClassPreamble {
		String author();
		String date();
		int currentRevision() default 1;
		String lastModified() default "N/A";
		String lastModifiedBy() default "N/A";
		// Note use of array
		String[] reviewers();
	}
}
