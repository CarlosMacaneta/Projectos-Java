package models.dao;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JPasswordField;

import controllers.FuncController;
import controllers.GestorController;

/**
 *
 * @author CarlosMacaneta
 */
public class RedefinirSenha implements Serializable {
    
    public boolean reset(int id, JPasswordField jpfSenha){
        ArrayList<Gestor> dadosGestor = new GestorController().lista();
        ArrayList<Funcionario> dadosFunc = new FuncController().lista();
        String senha = Criptografia.encriptografar(ValidarDadosUser.returnSenha(jpfSenha));

        if(!dadosGestor.isEmpty()){
            for (Gestor gestor : dadosGestor) {
                if (gestor.getId() == id) {

                    gestor.setSenha(senha);
                    new GestorController().update(id, gestor);
                    return true;
                }
            }
        }
        if(!dadosFunc.isEmpty()){
            for (Funcionario funcionario : dadosFunc) {
                if (funcionario.getId() == id) {

                    funcionario.setSenha(senha);
                    new FuncController().update(id, funcionario);
                    return true;
                }
            }
        }
        return false;
    }
}