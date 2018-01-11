package com.jeonguk.graphql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeonguk.graphql.service.GraphQLService;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private GraphQLService graphQLservice;
    
    @PostMapping
    public ResponseEntity<Object> getAllUsers(@RequestBody String query) {
        ExecutionResult execute = graphQLservice.getGraphQL().execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
    
}