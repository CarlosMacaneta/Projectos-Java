package models.beans;

import java.io.Serializable;

/**
 *
 * @author Carlos Macaneta
 */
public class Doador extends Pessoa implements Serializable {
    
    private int id;
    private String categoria;
    private String nomeProd;
    private String valorDoado;
    private int qtd;
    private String data;
    private String foreignkey;
    private int fk;

    public Doador() { 
    }

    public Doador(String nome, String apelido, String tipoDocumento, String nDocumento, String genero, String telefone, String data) {
        super(nome, apelido, tipoDocumento, nDocumento, genero, telefone);
        this.data = data;
    }

    public Doador(int id, String nomeProd, int qtd, String valorDoado, String data, int fk) {
        this.id = id;
        this.nomeProd = nomeProd;
        this.qtd = qtd;
        this.valorDoado = valorDoado;
        this.data = data;
        this.fk = fk;
    }
    
    public Doador(int id, String nomeProd,  String categoria, int qtd, String valorDoado, String data, String foreignkey) {
        this.id = id;
        this.categoria = categoria;
        this.nomeProd = nomeProd;
        this.qtd = qtd;
        this.valorDoado = valorDoado;
        this.data = data;
        this.foreignkey = foreignkey;
    }
    
    public Doador(int id, String nomeProd,  String categoria, int qtd, String valorDoado, String data, String nome, String apelido, String foreignkey) {
        super(nome, apelido);
        this.id = id;
        this.categoria = categoria;
        this.nomeProd = nomeProd;
        this.qtd = qtd;
        this.valorDoado = valorDoado;
        this.data = data;
        this.foreignkey = foreignkey;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public String getValorDoado() {
        return valorDoado;
    }

    public void setValorDoado(String valorDoado) {
        this.valorDoado = valorDoado;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }    

    public String getForeignkey() {
        return foreignkey;
    }

    public void setForeignkey(String foreignkey) {
        this.foreignkey = foreignkey;
    }

    public int getFk() {
        return fk;
    }

    public void setFk(int fk) {
        this.fk = fk;
    }
}