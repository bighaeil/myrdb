package com.simple.myrdb.respository;

import com.jayway.jsonpath.Criteria;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    @Test
    void JPQL_TEST() {
        String jpql = "select m from Member as m where m.username = 'kim'";

        List<Member> resultList = em.createQuery(jpql, Member.class).getResultList();
    }

    @Test
    void CRITERIA_TEST() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);

        Root<Member> m = query.from(Member.class);

        CriteriaQuery<Member> cq = query.select(m)
                .where(cb.equal(m.get("username"), "kim"));

        List<Member> resultList = em.createQuery(cq).getResultList();
    }

    @Test
    @Disabled
    void NATIVE_TEST() {
        String sql = "select id, username, nickname from member where username = 'kim'";

        List<Member> resultList = em.createNativeQuery(sql, Member.class).getResultList();
    }

    @Test
    void TYPEQUERY_TEST() {
        TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);

        List<Member> resultList = query.getResultList();
    }

    @Test
    void QUERY_TEST() {
        Query query = em.createQuery("select m.id, m.username from Member m");

        List resultList = query.getResultList();

        for (Object row : resultList) {
            Object[] col = (Object[]) row;

            System.out.println(col[0]);
            System.out.println(col[1]);
        }
    }
}
