package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos Macaneta
 */
public class ControleCodigoId {
    
    public static boolean gravarCodigoId(String id,Path caminho) throws IOException{
        try (BufferedWriter user = Files.newBufferedWriter(caminho)) {
            user.write(id);
            user.flush();
            user.close();
            return true;
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage()+ "\nErro ao gravar o ficheiro","ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
       
    public static boolean lerCodigoId(ArrayList<String> id,Path caminho) throws IOException{
        try(BufferedReader readUser = Files.newBufferedReader(caminho)){
            String lineId;
            while((lineId = readUser.readLine())!= null){
                id.add(lineId);
            } 
            return true;
        }catch(IOException e2){
            JOptionPane.showMessageDialog(null, e2.getMessage()+ "\nErro na leitura do ficheiro","ERRO", JOptionPane.ERROR_MESSAGE);
        } 
        return false;
    }
    
    public static int verificaId(ArrayList<String> id){
        if(!id.isEmpty()){
            return Integer.parseInt(id.get(0));
        }
        return 0;
    }
}
