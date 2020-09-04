package models.dao;

import controllers.UserLogged;
import models.beans.Usuario;

public class Log extends Usuario {
    public Log() {
    }

    public Log(int id, String nomeUsuario, String senha, int nivelAcesso, String nome, String apelido, String dataNascimento, String tipoDocumento, String nDocumento, int nuit, String genero, String estadoCivil, String nacionalidade, String provincia, String bairro, String telefone, String email) {
        super(id, nomeUsuario, senha, nivelAcesso, nome, apelido, dataNascimento, tipoDocumento, nDocumento, nuit, genero, estadoCivil, nacionalidade, provincia, bairro, telefone, email);
    }

    public static void gestorLogged(Gestor g){
        UserLogged.create(new Log(g.getId(), g.getNomeUsuario(), g.getSenha(), g.getNivelAcesso(), g.getNome(),
            g.getApelido(), g.getDataNascimento(), g.getTipoDocumento(), g.getnDocumento(), g.getNuit(), g.getGenero(),
            g.getEstadoCivil(), g.getNacionalidade(), g.getProvincia(), g.getBairro(), g.getTelefone(), g.getEmail()));
    }

    public static void funcLogged(Funcionario f){
        UserLogged.create(new Log(f.getId(),f.getNomeUsuario(), f.getSenha(), f.getNivelAcesso(), f.getNome(),
            f.getApelido(), f.getDataNascimento(), f.getTipoDocumento(), f.getnDocumento(), f.getNuit(), f.getGenero(),
            f.getEstadoCivil(), f.getNacionalidade(), f.getProvincia(), f.getBairro(), f.getTelefone(), f.getEmail()));
    }
}