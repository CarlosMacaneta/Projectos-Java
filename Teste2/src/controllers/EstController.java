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
import models.Estudante;

/**
 *
 * @author CarlosMacaneta
 */
public class EstController {
   
    private static final String URI = "src/arquivos/est.dat";
    
    private static void create(ArrayList<Estudante> est){
        try(FileOutputStream fos = new FileOutputStream(URI)){
            try(ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(est);
                oos.close();
            }catch(FileNotFoundException ex){}
        }catch(IOException ex){}
    }
    
    public static void create(Estudante est){        
        if(Files.notExists(Paths.get(URI))){
            ArrayList<Estudante> est_ = new ArrayList();
            est_.add(est);
            create(est_);
        }else{
            ArrayList<Estudante> est_ = lista();      
            est_.add(est);
            create(est_);
        }
    }
    
    public static ArrayList<Estudante> lista(){        
        ArrayList<Estudante> list = new ArrayList<>();
        
        try(FileInputStream fis = new FileInputStream(URI)){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                list = (ArrayList<Estudante>) ois.readObject();
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
    
    public static ArrayList<Estudante> pesquisaNome(String nome){
        ArrayList<Estudante> est = new ArrayList<>();
        
        if(!isNumber(nome)){
            if(!lista().isEmpty()){
                lista().forEach((nome_) ->{
                    if(nome_.getNome().equalsIgnoreCase(nome)){
                        est.add(nome_);
                    }
                });
            }
        }else est.add(pesquisaId(Integer.parseInt(nome)));
        return est;
    }
    
    public static Estudante pesquisaId(int id){
        
        if(!lista().isEmpty()){
            for(Estudante e: lista()){
                if(id == e.getId()){
                    System.out.println(id);
                    System.out.println(e.getId());
                    return e;
                }
            }
        }
        return null;
    }
    
    public static ArrayList<Estudante> filtro(String situacao){
        ArrayList<Estudante> ests = new ArrayList<>();
        
        if(!lista().isEmpty()){
            lista().forEach((l) ->{
                if(l.getSitucao().equalsIgnoreCase(situacao)){
                    ests.add(l);
                }
            });
        }
        return ests;
    }
 
    public static void update(int id, Estudante est){
        ArrayList<Estudante> ests = lista();
        if(!ests.isEmpty()){
            for (int i = 0; i < ests.size(); i++) {
                if(ests.get(i).getId() == id){
                    ests.remove(i);
                    ests.add(i, est);
                    create(ests);
                    break;
                }
            }
        }
    }
    
    public static void delete(int id){
        ArrayList<Estudante> ests = lista();       
        if(!ests.isEmpty()){
            for (Estudante est_ : ests) {
                if(est_.getId() == id){
                    ests.remove(est_);
                    create(ests);
                    break;
                }
            }
        }
    } 
}
