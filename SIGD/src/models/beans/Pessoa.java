package models.beans;

import java.io.Serializable;

/**
 *
 * @author Carlos Macaneta
 */
public abstract class Pessoa implements Serializable {
    
    private String nome;
    private String apelido;
    private String dataNascimento;
    private String tipoDocumento;
    private String nDocumento;
    private String nacionalidade;
    private int nuit;
    private String bairro;
    private String provincia;
    private String estadoCivil;
    private String genero;
    private String telefone;
    private String email;
    
    public Pessoa(){
    }
    
    public Pessoa(String nome){
        this.nome = nome;
    }
    
    public Pessoa(String nome, String apelido){
        this.nome = nome;
        this.apelido = apelido;
    }
    
    public Pessoa(String nome, String apelido, String tipoDocumento, String nDocumento, String genero, String telefone){
        this.nome = nome;
        this.apelido = apelido;
        this.tipoDocumento = tipoDocumento;
        this.nDocumento = nDocumento;
        this.genero = genero;
        this.telefone = telefone;
    }

    public Pessoa(String nome, String apelido, String dataNascimento, String tipoDocumento, String nDocumento, int nuit, String genero, String estadoCivil, String nacionalidade, String provincia, String bairro, String telefone, String email) {
        this.nome = nome;
        this.apelido = apelido;
        this.dataNascimento = dataNascimento;
        this.tipoDocumento = tipoDocumento;
        this.nDocumento = nDocumento;
        this.nacionalidade = nacionalidade;
        this.nuit = nuit;
        this.bairro = bairro;
        this.provincia = provincia;
        this.estadoCivil = estadoCivil;
        this.genero = genero;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getnDocumento() {
        return nDocumento;
    }

    public void setnDocumento(String nDocumento) {
        this.nDocumento = nDocumento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public int getNuit() {
        return nuit;
    }

    public void setNuit(int nuit) {
        this.nuit = nuit;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }   
}