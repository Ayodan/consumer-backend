package com.olaolu.database.controllers;

import com.olaolu.database.model.Article;
import com.olaolu.database.model.BusinessType;
import com.olaolu.database.serviceAndDao.AbstractDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.sql.DataSource;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(value = {"/api/v1/"})
@Validated
public class BusinessTypeController <T> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AbstractDao abstractDao;

    @RequestMapping(value = "businesstype",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BusinessType> createBusinessType(@Valid @RequestBody BusinessType businessType, UriComponentsBuilder builder) {
        abstractDao.createBusinessType(businessType);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/business/{id}").buildAndExpand(businessType.getId()).toUri());
        return new ResponseEntity<BusinessType>(businessType,headers, HttpStatus.OK);
    }
    @RequestMapping(value = "businesstypes",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<BusinessType>> fetchAllBusinessTypes() {
        List<BusinessType> businessTypes = abstractDao.fetchAllBusinessTypes();
        return new ResponseEntity<List<BusinessType>>(businessTypes,HttpStatus.FOUND);
    }
    @RequestMapping(value = "businesstypes/{id}",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BusinessType> getBusinessTypeById(@PathVariable @Min(1) int businessTypeId) {
        BusinessType businessTypeById = abstractDao.getBusinessTypeById(businessTypeId);
        if (businessTypeById==null){
            return new ResponseEntity<BusinessType>(new BusinessType(),HttpStatus.FOUND);

        }
        return new ResponseEntity<>(businessTypeById,HttpStatus.FOUND);
    }

    @RequestMapping(value = "businesstype",method = RequestMethod.PUT,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> updateBusinessType(@RequestBody BusinessType businessType) {
        abstractDao.updateBusinessType(businessType);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


}
