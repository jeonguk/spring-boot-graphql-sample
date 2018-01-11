package com.jeonguk.graphql.service.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeonguk.graphql.entity.User;
import com.jeonguk.graphql.repository.UserRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class UserDataFetcher implements DataFetcher<User> {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public User get(DataFetchingEnvironment arg0) {
        Integer id = arg0.getArgument("id");
        return userRepository.findOne(id);
    }

}