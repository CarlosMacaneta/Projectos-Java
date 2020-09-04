package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos Macaneta
 */
public class FluxoDados {
    
    public boolean readUser(ArrayList<String> users,Path caminho) throws IOException{
        try(BufferedReader readUser = Files.newBufferedReader(caminho, StandardCharsets.UTF_8)){
            String lineUser;
            while((lineUser = readUser.readLine())!= null){
                users.add(lineUser);
            } 
            return true;
        }catch(IOException e2){
            JOptionPane.showMessageDialog(null, e2.getMessage()+ "\nErro na leitura do ficheiro","ERRO", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    public boolean readPassword(ArrayList<String> pwd, Path caminho) throws IOException{
        try( BufferedReader readPass = Files.newBufferedReader(caminho, StandardCharsets.UTF_8)){
            String linePass;
            while((linePass = readPass.readLine())!= null){
                pwd.add(linePass);
            }
            return true;
        }catch(IOException e2){
            JOptionPane.showMessageDialog(null, e2.getMessage()+ "\nErro na leitura do ficheiro","ERRO", JOptionPane.ERROR_MESSAGE);           
        }
        return false;
    }
    
    public boolean verificaUser(ArrayList<String> users, String username){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).equalsIgnoreCase(username)){
                return true;
            }
        }
        return false;
    }
    
     public boolean verificaPassword(ArrayList<String> pwd, String password){
        for(int i = 0; i < pwd.size(); i++){
            if(pwd.get(i).equals(password)){
                return true;
            }
        }
        return false;
    }
}
