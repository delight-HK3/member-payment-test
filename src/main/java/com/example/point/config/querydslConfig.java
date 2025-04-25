package com.example.point.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Configuration
public class querydslConfig {
    
    @PersistenceContext
    private EntityManager em;

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager em){ 
        // 쿼리를 작성하는 JPAQueryFactory에 EntityManager를 넘겨서 사용
        return new JPAQueryFactory(em);
    }

}
