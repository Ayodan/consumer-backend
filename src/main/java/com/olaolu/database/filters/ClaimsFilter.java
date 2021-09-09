package com.olaolu.database.filters;

import com.olaolu.database.exceptions.InvalidClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author akano.olanrewaju  @on 24/03/2021
 */
public class ClaimsFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenStore jwtTokenStore;
    @Value("${oauth2.resourceId}")
    private String oauth2ResourceId;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String tokeValue=getJwtFromRequest(httpServletRequest);
        if (tokeValue!=null){
            Map<String, Object> authenticationClaims = getAuthenticationClaims(tokeValue);
            List<String> audiences= (ArrayList<String>) authenticationClaims.get("aud");
            if (audiences!=null){
                List<String> audienceList= (audiences);
                if (!audienceList.contains(oauth2ResourceId)){
                   throw new InvalidClientException("Client is not valid");
                }
            }
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }
    private Map<String,Object> getAuthenticationClaims(String tokenValue){
        OAuth2Authentication oAuth2Authentication = jwtTokenStore.readAuthentication(tokenValue);
        Map<String,Object> claims= (Map<String, Object>) oAuth2Authentication.getDetails();
        return claims;
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

}
