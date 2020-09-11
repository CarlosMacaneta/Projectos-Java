package exemplo;

import javax.swing.JOptionPane;

/**
 *
 * @author Carlos Macaneta
 */

public class ArrayClasse {
    
    private String[] nome;
    private int posicao;
    
    public ArrayClasse(){
    }
    
    public ArrayClasse(int size){
        this.nome = new String[size];
    }
    
    public ArrayClasse(String nome, int posicao){
        this.nome[posicao] = nome;
        this.posicao = posicao;
    }
    
    public void setPosicao(int posicao){
        this.posicao = posicao;
    }
    
    public int getPosicao(){
        return posicao;
    }
    
    public void setNome(String nome){
        this.nome[this.getPosicao()] = nome;
    }
    
    public String[] getNome(){
        return nome;
    }
    
    public String listar(){
        String nomes = "";
        for (String nome1 : this.getNome()) {
            if(nome1 != null){
                nomes += nome1+"\n";
            }
        }
        return nomes;
    }
    
    public boolean remover(String nome){
        for(int i = 0; i < this.getNome().length; i++){
            if(nome.equals(this.getNome()[i])){
                this.nome[i] = "Removido";
                return true;
            }
        }
        return false;
    }
    
    public int pesquisa(String nome){
        for(int i = 0; i < this.getNome().length; i++){
            if(nome.equals(this.getNome()[i])){
                return i;
            }
        }
        return -1;
    }
    
    public boolean actualizarNome(String nome){
       for(int i = 0; i < this.getNome().length; i++){
            if(nome.equalsIgnoreCase(this.getNome()[i])){
               this.nome[i] = JOptionPane.showInputDialog("Nome");
               return true;
            }
       }
       return false;
    }
}
