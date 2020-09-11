import java.util.Stack;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;

public class FluxoDados{

    public boolean registarTarefas(Stack<String> pilha, String tarefa){
        if(!tarefa.isEmpty()){
            pilha.push(tarefa);
            return true;
        }
        return false;
    }

    public boolean removerTarefas(Stack<String> pilha){
        if(!pilha.isEmpty()){
            Object[] options = {"Sim","Não"};
            int yesNo = JOptionPane.showOptionDialog(null, "NB: Apenas sera removida a ultima tarefa registada. \nContinuar?","", YES_NO_OPTION, QUESTION_MESSAGE, null,options, options[0]);
            if(yesNo == 0){
                pilha.pop();
                return true;
            }
        } else {
            JOptionPane.showMessageDialog(null,"A sua lista de tarefas encontra-se vazia");
        }
        return false;
    }

    public boolean removerTodasTarefas(Stack<String> pilha){
        if(!pilha.isEmpty()) {
            Object[] options = {"Sim","Não"};
            int yesNo = JOptionPane.showOptionDialog(null, "NB: Todas as tarefas serao removidas. \nContinuar?","", YES_NO_OPTION, QUESTION_MESSAGE, null,options, options[0]);
            if(yesNo == 0){
                pilha.clear();
                return true;
            }
        } else {
            JOptionPane.showMessageDialog(null,"A sua lista de tarefas encontra-se vazia");
        }
        return false;
    }

    public int numeroTarefas(Stack<String> pilha){
        return pilha.size();
    }

    public String listaTarefas(Stack<String> pilha){
        String lista = "";
        for(String tarefa: pilha){
            lista += tarefa;
            lista += "\n";
        }
        return lista;
    }
}