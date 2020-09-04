package models.beans;

import java.io.Serializable;

/**
 *
 * @author Carlos Macaneta
 */
public class Usuario extends Pessoa implements Serializable {
    
    private int id;
    private String nomeUsuario;
    private String senha;
    private int nivelAcesso;
    
    public Usuario() {
    }
    
    public Usuario(int id, String nome) {
        super(nome);
        this.id = id;
    }
    
    public Usuario(int id, String nomeUsuario, String senha, int nivelAcesso, String nome, String apelido, String dataNascimento, String tipoDocumento, String nDocumento, int nuit, String genero, String estadoCivil, String nacionalidade, String provincia,String bairro, String telefone, String email){
        super(nome, apelido, dataNascimento, tipoDocumento, nDocumento, nuit, genero,estadoCivil, nacionalidade, provincia, bairro, telefone, email);
        this.id= id;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }   
}