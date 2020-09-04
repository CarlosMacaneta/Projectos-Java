package models.dao;

import java.io.Serializable;
import java.util.ArrayList;

import controllers.FuncController;
import models.beans.Usuario;

/**
 *
 * @author Carlos Macaneta
 */
public class Funcionario extends Usuario implements Serializable {

    public Funcionario(){
    }
    
    public Funcionario(int id, String nomeUsuario, String senha, int nivelAcesso, String nome, String apelido, String dataNascimento, String tipoDocumento, String nDocumento, int nuit, 
        String genero, String estadoCivil, String nacionalidade, String provincia, String bairro, String telefone, String email){
        super(id, nomeUsuario, senha, nivelAcesso, nome, apelido, dataNascimento, tipoDocumento, nDocumento, nuit, genero, estadoCivil, nacionalidade, provincia, bairro, telefone, email);
    }    
    
    public ArrayList<Funcionario> resultSearch(String nome, String apelido){
        ArrayList<Funcionario> func = new FuncController().lista();
        ArrayList<Funcionario> result = new ArrayList<>();

        if(!func.isEmpty()){
            func.forEach((func_)->{
                if(func_.getNome().equalsIgnoreCase(nome)&&func_.getApelido().equalsIgnoreCase(apelido)){
                    result.add(func_);
                }
            });
        }
        return result;
    }
}