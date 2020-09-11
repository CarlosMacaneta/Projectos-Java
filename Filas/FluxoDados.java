import java.util.Queue;

public class FluxoDados {

    public void registarFila(Queue<Pessoa> fila, Pessoa pessoa){
        fila.add(pessoa);
    }

    public boolean removerFila(Queue<Pessoa> fila){
        if(!fila.isEmpty()){
            fila.remove();
            return true;
        }
        return false;
    }

    public boolean removerTodaFila(Queue<Pessoa> fila){
        if(!fila.isEmpty()){
            fila.clear();
            return true;
        }
        return false;
    }

    public String listarFila(Queue<Pessoa> fila){
        String lista = "";
        Pessoa[] pessoa = new Pessoa[fila.size()];
        fila.toArray(pessoa);

        if(!fila.isEmpty()){
            for(int i = 0; i < pessoa.length; i++){
                lista += "Nome: "+pessoa[i].getNome()+"\nContacto: "+pessoa[i].getContacto();
                lista += "\n\n"; 
            }
        }
        return lista;
    }

    public String exibirFila(Queue<Pessoa> fila){
        String exibe = "";
        if(!fila.isEmpty()){
            exibe += "Nome: "+fila.element().getNome()+"\nContacto: "+fila.element().getContacto();
            exibe += "\n\n";
        }
        return exibe;
    }
}