package models;


/**
 *
 * @author CarlosMacaneta
 */
public class Professor extends Pessoa {
    
    private String grauAcademico;

    public Professor(String grauAcademico) {
        this.grauAcademico = grauAcademico;
    }

    public Professor(int id, String nome, String genero, String grauAcademico) {
        super(id, nome, genero);
        this.grauAcademico = grauAcademico;
    }

    public String getGrauAcademico() {
        return grauAcademico;
    }

    public void setGrauAcademico(String grauAcademico) {
        this.grauAcademico = grauAcademico;
    }
}