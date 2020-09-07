package models;

import java.io.Serializable;

/**
 *
 * @author CarlosMacaneta
 */
public abstract class Pessoa implements Serializable {
    
    private int id;
    private String nome;
    private String genero;

    public Pessoa() {
    }

    public Pessoa(int id, String nome, String genero) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}