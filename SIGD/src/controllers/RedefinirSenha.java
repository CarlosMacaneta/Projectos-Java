package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.JPasswordField;
import models.dao.Criptografia;
import models.dao.Funcionario;
import models.dao.Gestor;
import models.dao.ValidarDadosUser;

/**
 *
 * @author CarlosMacaneta
 */
public class RedefinirSenha {
    
    public boolean reset(int id, JPasswordField jpfSenha){
        ArrayList<Gestor> dadosGestor = new GestorController().lista();
        ArrayList<Funcionario> dadosFunc = new FuncController().lista();
        String senha = Criptografia.encriptografar(ValidarDadosUser.returnSenha(jpfSenha));
        
        if(!dadosGestor.isEmpty()){
            for(int i = 0; i < dadosGestor.size(); i++){           
                if(dadosGestor.get(i).getId() == id){

                    dadosGestor.get(i).setSenha(senha);
                    new GestorController().update(id, dadosGestor.get(i));
                }
            }
        }
        if(!dadosFunc.isEmpty()){
            for(int i = 0; i < dadosFunc.size(); i++){
                if(dadosFunc.get(i).getId() == id){

                    dadosFunc.get(i).setSenha(senha);
                    new FuncController().update(id, dadosFunc.get(i));
                    return true;
                }
            }
        }
        return false;
    }
}