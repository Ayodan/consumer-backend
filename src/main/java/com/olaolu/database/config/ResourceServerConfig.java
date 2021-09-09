package com.olaolu.database.config;

import com.olaolu.database.exceptions.InvalidClientException;
import com.olaolu.database.filters.ClaimsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${oauth2.resourceId}")
    private String oauth2ResourceId;

    @Value("${oauth2.verifierKey}")
    private String oauth2VerifierKey;
    @Autowired
    private CustomAccessTokenConverter customAccessTokenConverter;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(oauth2ResourceId).tokenStore(tokenStore());
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .antMatchers("/health", "/info", "/trace", "/metrics", "/monitoring", "/webjars/**","/swagger.html").permitAll();
        http.authorizeRequests().antMatchers("/api/v1/**")
                .authenticated().and().addFilterBefore(getInvalidClientFilter(), UsernamePasswordAuthenticationFilter.class);

    }


    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setAccessTokenConverter(customAccessTokenConverter);
        converter.setVerifierKey(oauth2VerifierKey);
        return converter;
    }
    @Bean
    public ClaimsFilter getInvalidClientFilter(){
        return new ClaimsFilter();
    }
}