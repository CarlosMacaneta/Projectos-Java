package exemplo;

import javax.swing.JOptionPane;

/**
 *
 * @author Carlos Macaneta
 */
public class Exemplo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int size = Integer.parseInt(JOptionPane.showInputDialog("Quantos nomes vai guardar?"));
        if(size <= 0){
            JOptionPane.showMessageDialog(null, "Quantidade invalida");
        }
        
        ArrayClasse ac = new ArrayClasse(size);
        
        int posicao = 0;
        
        boolean activo = true;
        
        while((activo == true)&&(size != 0)){
          
            int opcao = Integer.parseInt(JOptionPane.showInputDialog("[1] Registar\n[2] Listar\n[3] Remove\n[4] Alterar nome\n[5] Pesquisar nome\n[6] Sair"));
            
            switch(opcao){
                case 1:
                    ac.setNome(JOptionPane.showInputDialog("Nome"));
                    posicao++; 
                    ac.setPosicao(posicao);
                    break;
                case 2: 
                    JOptionPane.showMessageDialog(null, ac.listar());
                    break;
                case 3: 
                    if(ac.remover(JOptionPane.showInputDialog("Nome")) == true){
                        JOptionPane.showMessageDialog(null, "Removido com sucesso");
                    }else{
                        JOptionPane.showMessageDialog(null, "Individuo nao encontrado");
                    }
                    break;
                case 4:
                    if(ac.actualizarNome(JOptionPane.showInputDialog("Nome")) == true){
                        JOptionPane.showMessageDialog(null, "Nome actualizado com sucesso.");
                    }else{
                        JOptionPane.showMessageDialog(null, "Ocorreu um erro ao actualizar o nome.");
                    }
                    break;
                case 5:
                    String name = JOptionPane.showInputDialog("Nome");
                    if(ac.pesquisa(name) >= 0){
                        JOptionPane.showMessageDialog(null, "Posicao: "+(ac.pesquisa(name)+1)+"\nNome: "+name);
                    }else{
                        JOptionPane.showMessageDialog(null, "Posicao: "+ac.pesquisa(name)+"\nNome nao encontrado.");
                    }
                    break;
                case 6:
                    activo = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcao invalida");
            }
        }
    }
}