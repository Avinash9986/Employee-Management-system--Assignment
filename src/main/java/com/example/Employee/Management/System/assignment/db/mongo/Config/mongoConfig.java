package com.example.Employee.Management.System.assignment.db.mongo.Config;


import com.example.Employee.Management.System.assignment.db.mongo.Repository.DepartmentRepository;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static java.util.Collections.singletonList;

@Configuration
@EnableMongoRepositories(basePackageClasses = DepartmentRepository.class,mongoTemplateRef = "mongoMongoTemplate")
@EnableConfigurationProperties
public class mongoConfig {

    @Bean(name = "mongoProperties")
    @ConfigurationProperties(prefix = "mongodb.mongoConfig")
    public MongoProperties mongoProperties(){
        return new MongoProperties() ;
    }

    @Bean(name = "mongoMongoClient")
    public MongoClient mongoClient(@Qualifier("mongoProperties") MongoProperties mongoProperties){

        MongoCredential credential = MongoCredential.createCredential(mongoProperties.getUsername(),
                mongoProperties.getAuthenticationDatabase(),mongoProperties.getPassword()) ;

        return MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder
                        .hosts(singletonList(new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort()))))
                .credential(credential)
                .build());
    }

    @Bean(name = "mongoConfigMongoDbFactory")
    public MongoDatabaseFactory mongoDatabaseFactory(@Qualifier("mongoMongoClient") MongoClient mongoClient,
                                                     @Qualifier("mongoProperties") MongoProperties mongoProperties){
        return new SimpleMongoClientDatabaseFactory(mongoClient,mongoProperties().getDatabase()) ;
    }

    @Bean(name = "mongoMongoTemplate")
    public MongoTemplate mongoTemplate(@Qualifier("mongoConfigMongoDbFactory") MongoDatabaseFactory mongoDatabaseFactory){
        return new MongoTemplate(mongoDatabaseFactory) ;
    }



}
