package org.example.mockexam;

import org.example.mockexam.repositories.impl.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@EnableJpaAuditing
@EnableScheduling
public class MockExamApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MockExamApplication.class, args);
        
    }
}
