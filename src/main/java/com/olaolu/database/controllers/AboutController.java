package com.olaolu.database.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AboutController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 
     * this method maps the following URL & http method
     * URL: http://hostname:port/appName/about
     * HTTP method: GET
     * 
     */
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public ResponseEntity<?> home() {
    	return new ResponseEntity<>("This is a demo application to show how to secure REST API using Spring Security and OAuth2", HttpStatus.OK);
    }
    @RequestMapping(value = "/about/web-hook", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postWebHook(@RequestBody Object object) {
        logger.error("body object",object);

        return new ResponseEntity<Object>(object, HttpStatus.OK);

    }
//    @RequestMapping(value = "/about", method = RequestMethod.GET)
//    public ResponseEntity<?> handleClientRequest(@RequestParam String code) {
//        return new ResponseEntity<>("This is a demo application to show how to secure REST API using Spring Security and OAuth2", HttpStatus.OK);
//    }
}
