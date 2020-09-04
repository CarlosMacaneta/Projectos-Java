package models.dao;

import controllers.FluxoDados;
import controllers.FuncController;
import controllers.GestorController;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.JTextField;
/**
 *
 * @author Carlos Macaneta
 */
public class ControleUsuario implements Serializable {
    
    private final String URI1 = "log/masterUsername.txt";
    private final String URI2 = "log/masterPassword.txt";

    public int nivelAcesso(){
        ArrayList<Gestor> gestor = new GestorController().lista();
        
        if(gestor.isEmpty()) return 1;
        else return 2;
    }
    
    public int controlUsuario(JTextField nomeUsuario, char[] senha){
        String pass = "";
        for (char c : senha) {
            pass += c;
        }
        
        FluxoDados fd = new FluxoDados();
        ArrayList<String> adminUser = new ArrayList<>();
        ArrayList<String> adminPass = new ArrayList<>();
        ArrayList<Gestor> gestor = new GestorController().lista();
        ArrayList<Funcionario> func = new FuncController().lista();
        String senhaCriptografada = Criptografia.encriptografar(pass);
        
        if(!gestor.isEmpty()){
            for (Gestor value : gestor) {
                if (value.getNomeUsuario().equals(nomeUsuario.getText()) && value.getSenha().equals(senhaCriptografada)) {
                    Log.gestorLogged(value);
                    return value.getNivelAcesso();
                }
            }
        }else try {
            if(fd.readUser(adminUser, Paths.get(URI1))&&fd.readPassword(adminPass, Paths.get(URI2))){
                if(fd.verificaUser(adminUser, nomeUsuario.getText())&&fd.verificaPassword(adminPass, pass)){
                    return 3;
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "ERRO: "+ex, "ERRO DE LEITURA", INFORMATION_MESSAGE);
        } 
        
        if(!func.isEmpty()){
            for (Funcionario funcionario : func) {
                if (funcionario.getNomeUsuario().equals(nomeUsuario.getText()) && funcionario.getSenha().equals(senhaCriptografada)) {
                    Log.funcLogged(funcionario);
                    return funcionario.getNivelAcesso();
                }
            }
        }  
        return -1;
    }
}