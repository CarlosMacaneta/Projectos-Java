package models.beans;

import java.io.Serializable;

/**
 *
 * @author CarlosMacaneta
 */
public abstract class Beneficiario implements Serializable {
   
    private int id;
    private String nome;
    private String endereco;
    private String tel;
    private String email;
    private int nPessoas;
    private int nCriancas;
    private int nJovens;
    private int nAdultos;
    private String dataReg;

    public Beneficiario() {
    }

    public Beneficiario(int id, String nome, String endereco, String tel, String email, int nPessoas, int nCriancas, int nJovens, int nAdultos, String dataReg) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tel = tel;
        this.email = email;
        this.nPessoas = nPessoas;
        this.nCriancas = nCriancas;
        this.nJovens = nJovens;
        this.nAdultos = nAdultos;
        this.dataReg = dataReg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getnPessoas() {
        return nPessoas;
    }

    public void setnPessoas(int nPessoas) {
        this.nPessoas = nPessoas;
    }

    public int getnCriancas() {
        return nCriancas;
    }

    public void setnCriancas(int nCriancas) {
        this.nCriancas = nCriancas;
    }

    public int getnJovens() {
        return nJovens;
    }

    public void setnJovens(int nJovens) {
        this.nJovens = nJovens;
    }

    public int getnAdultos() {
        return nAdultos;
    }

    public void setnAdultos(int nAdultos) {
        this.nAdultos = nAdultos;
    }
    
    public String getDataReg() {
        return dataReg;
    }

    public void setDataReg(String dataReg) {
        this.dataReg = dataReg;
    }
}