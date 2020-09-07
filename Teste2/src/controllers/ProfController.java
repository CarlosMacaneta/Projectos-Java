package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import models.Professor;

/**
 *
 * @author CarlosMacaneta
 */
public class ProfController {
   
    private static final String URI = "src/arquivos/prof.dat";
    
    private static void create(ArrayList<Professor> prof){
        try(FileOutputStream fos = new FileOutputStream(URI)){
            try(ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(prof);
                oos.close();
            }catch(FileNotFoundException ex){}
        }catch(IOException ex){}
    }
    
    public static void create(Professor prof){        
        if(Files.notExists(Paths.get(URI))){
            ArrayList<Professor> prof_ = new ArrayList();
            prof_.add(prof);
            create(prof_);
        }else{
            ArrayList<Professor> prof_ = lista();      
            prof_.add(prof);
            create(prof_);
        }
    }
    
    public static ArrayList<Professor> lista(){        
        ArrayList<Professor> list = new ArrayList<>();
        
        try(FileInputStream fis = new FileInputStream(URI)){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                list = (ArrayList<Professor>) ois.readObject();
                ois.close();
            }catch(FileNotFoundException|ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, "ERRO: " + e, "ERROR", ERROR_MESSAGE);
            }
        }catch(IOException ex){}
        return list; 
    }
    
    public static boolean isNumber(String string){ 
        final String REGEX = "\\d+";
  
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(string.trim());
        
        return m.matches(); 
    }
    
    public static ArrayList<Professor> pesquisaNome(String nome){
        ArrayList<Professor> profs = new ArrayList<>();
        
        if(!isNumber(nome)){
            if(!lista().isEmpty()){
                lista().forEach((nome_) ->{
                    if(nome_.getNome().equalsIgnoreCase(nome)){
                        profs.add(nome_);
                    }
                });
            }
        }else  profs.add(pesquisaId(Integer.parseInt(nome)));
        return profs;
    }
    
    public static Professor pesquisaId(int id){
        
        if(!lista().isEmpty()){
            for(Professor p: lista()){
                if(id == p.getId()){
                    return p;
                }
            }
        }
        return null;
    }
 
    public static void update(int id, Professor prof){
        ArrayList<Professor> profs = lista();
        if(!profs.isEmpty()){
            for (int i = 0; i < profs.size(); i++) {
                if(profs.get(i).getId() == id){
                    profs.remove(i);
                    profs.add(i, prof);
                    create(profs);
                    break;
                }
            }
        }
    }
    
    public static void delete(int id){
        ArrayList<Professor> profs = lista();       
        if(!profs.isEmpty()){
            for (Professor prof_ : profs) {
                if(prof_.getId() == id){
                    profs.remove(prof_);
                    create(profs);
                    break;
                }
            }
        }
    }     
}
