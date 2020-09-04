package models.dao;

import java.io.Serializable;

import models.beans.Usuario;
/**
 *
 * @author Carlos Macaneta
 */
public class Gestor extends Usuario implements Serializable {
    
    public Gestor(){
    }

    public Gestor(int id, String nomeUsuario, String senha, int nivelAcesso, String nome, String apelido, String dataNascimento, String tipoDocumento, String nDocumento, 
        int nuit, String genero, String estadoCivil, String nacionalidade, String provincia, String bairro, String telefone, String email){
        super(id, nomeUsuario, senha, nivelAcesso, nome, apelido, dataNascimento, tipoDocumento, nDocumento, nuit, genero, estadoCivil, nacionalidade, provincia, bairro, telefone, email);
    }
}