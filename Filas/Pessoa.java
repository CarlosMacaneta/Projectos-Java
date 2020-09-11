public class Pessoa {
    
    private String nome;
    private int contacto;

    public Pessoa(){}

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    public void setContacto(int contacto){
        this.contacto = contacto;
    }

    public int getContacto(){
        return contacto;
    }
}