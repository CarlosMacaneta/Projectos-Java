import java.util.Stack;
import javax.swing.JOptionPane;

public class Pilha {

    public static void main(String[] args) {
        
        FluxoDados fd = new FluxoDados();
        Stack<String> pilha = new Stack<>();

        boolean tarefa = true;

        while(tarefa){
            int opcao = Integer.parseInt(JOptionPane.showInputDialog("======MENU======\n[1] Inserir tarefa\n[2] Remover tarefa"+
                "\n[3] Numero de tarefas registadas\n[4] Listar tarefas"+"\n[5] Sair\n"));
            switch(opcao){
                case 1:
                    if(fd.registarTarefas(pilha, JOptionPane.showInputDialog("Nome da tarefa"))){
                        JOptionPane.showMessageDialog(null, "Tarefa registada com sucesso");
                    }else{
                        JOptionPane.showMessageDialog(null, "Ocorreu um erro ao registar tarefa.");
                    }
                    break;
                case 2:
                    int option = Integer.parseInt(JOptionPane.showInputDialog("======MENU======\n[1] Remover uma tarefa"+
                        "\n[2] Remover todas as tarefas"));
                    switch(option){
                        case 1:
                            String tarefaRemovida = pilha.get(0);
                            if(fd.removerTarefas(pilha)){
                                JOptionPane.showMessageDialog(null, "A tarefa "+tarefaRemovida+" foi removida com sucesso");
                            }
                            break;
                        case 2:
                            if(fd.removerTodasTarefas(pilha)){
                                JOptionPane.showMessageDialog(null, "Suas tarefas foram removidas com sucesso");
                            }
                        break;
                    }
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "voce possuir "+fd.numeroTarefas(pilha)+" tarefas");
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "======LISTA DE TAREFAS======\n"+fd.listaTarefas(pilha));
                    break;       
                case 5:
                    tarefa = false;
                    break;
                default: JOptionPane.showMessageDialog(null, "Opcao invalida");
            }
            
        }
    }
}