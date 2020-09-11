import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JOptionPane;

public class Fila {

    public static void main(String[] args) {
        
        FluxoDados fd = new FluxoDados();
        Pessoa pessoa = new Pessoa();
        Queue<Pessoa> fila = new LinkedList<>();

        boolean callCenter = true;

        while(callCenter){
            int opcao = Integer.parseInt(JOptionPane.showInputDialog("=======MENU=======\n[1] Inserir contacto\n[2] Remover contacto\n[3] Listar contacto\n[4] Efectuar contacto\n[5] Sair"));
            switch(opcao){
                case 1: 
                    pessoa.setNome(JOptionPane.showInputDialog("Nome"));
                    pessoa.setContacto(Integer.parseInt(JOptionPane.showInputDialog("Contacto")));
                    fd.registarFila(fila, pessoa);
                    break;
                case 2:
                    int option = Integer.parseInt(JOptionPane.showInputDialog("======MENU======\n[1] Remover um contacto\n[2] Remover todos"));
                    switch(option){
                        case 1:
                            if(fd.removerFila(fila)){
                                JOptionPane.showMessageDialog(null, "Contacto removido com sucesso");
                            }
                            break;
                        case 2:
                            if(fd.removerTodaFila(fila)){
                                JOptionPane.showMessageDialog(null, "Contactos removidos com sucesso");
                            }
                        break;
                    }
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null,"======LISTA======\n"+fd.listarFila(fila));
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "======PRIMEIRO DA FILA======\n"+fd.exibirFila(fila));
                    break;
                case 5:
                    callCenter = false;
                    break;
                default: JOptionPane.showMessageDialog(null, "Opcao invalida");
            }
        }
    }
}