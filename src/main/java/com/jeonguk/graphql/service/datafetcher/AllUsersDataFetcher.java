package com.jeonguk.graphql.service.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeonguk.graphql.entity.User;
import com.jeonguk.graphql.repository.UserRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllUsersDataFetcher implements DataFetcher<List<User>> {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public List<User> get(DataFetchingEnvironment arg0) {
        return userRepository.findAll();
    }

}
