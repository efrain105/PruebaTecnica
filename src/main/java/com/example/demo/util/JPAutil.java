package com.example.demo.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAutil {
    public static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("colegio");

        public static EntityManager getEntityManager(){
            return FACTORY.createEntityManager();
        }
}
