package models.beans;

import java.io.Serializable;

/**
 *
 * @author CarlosMacaneta
 */
public abstract class Estoque implements Serializable {
    
    private int id;
    private String nome_prod;
    private String categoria;
    private int qtdActual;
    private int qtdGasta;
    private String valorActual;
    private String valorGasto;

    public Estoque() {
    }

    public Estoque(int id, String nome_prod, String categoria, int qtdActual, int qtdGasta) {
        this.id = id;
        this.nome_prod = nome_prod;
        this.categoria = categoria;
        this.qtdActual = qtdActual;
        this.qtdGasta = qtdGasta;
    }

    public Estoque(int id, String valorActual, String valorGasto) {
        this.id = id;
        this.valorActual = valorActual;
        this.valorGasto = valorGasto;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_prod() {
        return nome_prod;
    }

    public void setNome_prod(String nome_prod) {
        this.nome_prod = nome_prod;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQtdActual() {
        return qtdActual;
    }

    public void setQtdActual(int qtdActual) {
        this.qtdActual = qtdActual;
    }

    public int getQtdGasta() {
        return qtdGasta;
    }

    public void setQtdGasta(int qtdGasta) {
        this.qtdGasta = qtdGasta;
    }

    public String getValorActual() {
        return valorActual;
    }

    public void setValorActual(String valorActual) {
        this.valorActual = valorActual;
    }

    public String getValorGasto() {
        return valorGasto;
    }

    public void setValorGasto(String valorGasto) {
        this.valorGasto = valorGasto;
    }
}