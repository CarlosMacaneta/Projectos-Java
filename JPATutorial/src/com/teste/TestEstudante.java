package com.teste;

import com.controller.EstudanteDao;
import com.model.Estudante;
import javax.persistence.Persistence;

/**
 *
 * @author CarlosMacaneta
 */
public class TestEstudante {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Estudante est1 = new Estudante();
        EstudanteDao ed = new EstudanteDao();
        
        
        est1.setNome("Carlos Macaneta");
        est1.setMorada("Laulane");
       
        ed.create(est1);
        
    }
    
}
