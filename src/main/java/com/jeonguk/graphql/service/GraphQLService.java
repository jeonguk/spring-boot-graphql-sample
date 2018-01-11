package com.jeonguk.graphql.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.jeonguk.graphql.entity.User;
import com.jeonguk.graphql.repository.UserRepository;
import com.jeonguk.graphql.service.datafetcher.AllUsersDataFetcher;
import com.jeonguk.graphql.service.datafetcher.UserDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {

    @Value("classpath:users.graphql")
    private Resource resource;

    private GraphQL graphQL;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AllUsersDataFetcher allUsersDataFetcher;
    
    @Autowired
    private UserDataFetcher userDataFetcher;
    
    @PostConstruct
    public void loadSchema() throws IOException {
        
        loadDataHSQL();
        
        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring =  buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }
  
    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> 
                        typeWiring.dataFetcher("allUsers", allUsersDataFetcher)
                        .dataFetcher("user", userDataFetcher))
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }

    private void loadDataHSQL() {
        Stream.of(
                new User("Lee","lee@mail.com"),
                new User("Kim","kim@mail.com"),
                new User("Park","park@mail.com")
        ).forEach(user -> 
            userRepository.save(user)
        );
    }
    
}