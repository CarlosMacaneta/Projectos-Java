package models.dao;

import controllers.ControleCodigoId;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 * 
 * @author Carlos Macaneta
 */
public class GerarCodigoId implements Serializable {
   
    private String cod = "";
    private final Calendar date =  Calendar.getInstance();

    private final String c = date.get(Calendar.YEAR) + "0000";
    private final Path path = Paths.get("log/codigoId.txt");
    
    private final ArrayList<String> code = new  ArrayList<>();
    
    public void grava(String id){
        try{
            if(ControleCodigoId.gravarCodigoId(id, path)){   
                System.out.println("Id gravado");
            }else{
                System.err.println("Erro ao gravar");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ocorrou um ao gravar id.\nERRO: "+ex);
        }
    }
    
    private String resetId(){        
        try {
            if(ControleCodigoId.lerCodigoId(code, path)){
                String strAno = Integer.toString(ControleCodigoId.verificaId(code)).substring(0, 4);
                if(Integer.parseInt(strAno) != date.get(Calendar.YEAR)) {
                    return date.get(Calendar.YEAR) + "0000";
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ocorrou um ao actualizar id.\nERRO: "+ex.getMessage());
        }
        return c;
    }
    
    public int codigoFinal(boolean save){
        try{  
            if(ControleCodigoId.lerCodigoId(code, path)){                
                if(ControleCodigoId.verificaId(code) == 0){
                    cod += Integer.parseInt(c) + 1;
                    if (save) grava(cod.trim()); 
                }else if(ControleCodigoId.verificaId(code) < ControleCodigoId.verificaId(code)+1){
                    String strAno = Integer.toString(ControleCodigoId.verificaId(code)).substring(0, 4);
                    if(Integer.parseInt(strAno) == date.get(Calendar.YEAR)){
                        cod +=  ControleCodigoId.verificaId(code)+1;
                        if (save) grava(cod.trim()); 
                    }else if(Integer.parseInt(strAno) != date.get(Calendar.YEAR)){
                        cod += Integer.parseInt(resetId()) + 1;
                        if (save) grava(cod.trim()); 
                    }
                }
            }
        }catch(IOException ex){ 
            JOptionPane.showMessageDialog(null, "Ocorrou um ao gerar id.\nERRO: "+ex.getMessage());
        }
        return Integer.parseInt(cod);
    }   
}