package com.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.entities.Aluno;

/**
 *
 * @author CarlosMacaneta
 */
public class AlunoController {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public AlunoController(){
        emf = Persistence.createEntityManagerFactory("aluno");//abrindo uma sessao sql
        em = emf.createEntityManager(); //realizador de operacoes no banco
    }
    
    public void create(Aluno aluno){
        //iniciando uma conexao com o banco
        em.getTransaction().begin();
        
        //salvando o objecto aluno no banco
        em.merge(aluno);
        
        //fazendo commit no banco para salvar o aluno
        em.getTransaction().commit();
        
        //fechando a conexao com o banco
        emf.close();
    }
    
    public void remove(int id){
        em.getTransaction().begin();
        
        Query q = em.createNamedQuery("DELETE FROM aluno WHERE id = '"+id+"'");
        
        em.getTransaction().commit();
    }
}
