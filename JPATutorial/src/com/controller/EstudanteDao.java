package com.controller;

import com.model.Estudante;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author CarlosMacaneta
 */
public class EstudanteDao {
    
    public void create(Estudante estudante) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aluno");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {        
            em.persist(estudante);
            em.getTransaction().commit();
        }catch(Exception ex){
            em.getTransaction().rollback();
        } finally {
            System.out.println("ok");
            em.close();
        }
        
    }
}
