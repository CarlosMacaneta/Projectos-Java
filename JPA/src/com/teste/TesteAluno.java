package com.teste;

import com.controller.AlunoController;
import com.entities.Aluno;

/**
 *
 * @author CarlosMacaneta
 */
public class TesteAluno {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //criando o aluno
        Aluno al = new Aluno("Carlos Macaneta", "123456-CM", 25);
        
        //salvando/gravando o aluno
        AlunoController ac = new AlunoController();
        ac.create(al);
    }
    
}
