package com.simple.myrdb.respository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SimpleTest {

    @Autowired
    EntityManagerFactory emf;

    @BeforeEach
    void init() {
        em = emf.createEntityManager();
    }

    EntityManager em;

    @Test
    void TEST() {
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Member member = em.find(Member.class, 1L);
            System.out.println(member);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            em.close();
        }

    }

}
