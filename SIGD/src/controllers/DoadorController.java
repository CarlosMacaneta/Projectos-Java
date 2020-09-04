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
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import models.dao.DoadorDAO;

public class DoadorController {

    private static final String URI = "src/arquivos/doadores.dat";

    private void create(ArrayList<DoadorDAO> fnc){
        try(FileOutputStream fos = new FileOutputStream(URI)){
            try(ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(fnc);
                oos.close();
            }catch(FileNotFoundException ex){}
        }catch(IOException ex){}
    }

    public boolean create(DoadorDAO func){
        if(Files.notExists(Paths.get(URI))){
            ArrayList<DoadorDAO> func_ = new ArrayList();
            func_.add(func);
            create(func_);
            return true;
        }else{
            ArrayList<DoadorDAO> func_ = lista();
            func_.add(func);
            create(func_);
            return true;
        }
    }

    public ArrayList<DoadorDAO> lista(){
        ArrayList<DoadorDAO> list = new ArrayList<>();

        try(FileInputStream fis = new FileInputStream(URI)){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                list = (ArrayList<DoadorDAO>) ois.readObject();
                ois.close();
            }catch(FileNotFoundException|ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, "ERRO: " + e, "ERROR", ERROR_MESSAGE);
            }
        }catch(IOException ex){}
        return list;
    }

    public ArrayList<DoadorDAO> search(String str){
        ArrayList<DoadorDAO> don = new ArrayList<>();

        if(!lista().isEmpty()){
            lista().forEach((l) ->{
                if((l.getNome()+" "+l.getApelido()).toLowerCase().startsWith(str.toLowerCase())){
                    don.add(l);
                }else if(l.getnDocumento().equalsIgnoreCase(str)) don.add(l);
            });
        }
        return don;
    }
    
    public ArrayList<DoadorDAO> donater(String str){
        ArrayList<DoadorDAO> don = new ArrayList<>();

        if(!lista().isEmpty()){
            lista().forEach((l) ->{
                if(l.getnDocumento().equalsIgnoreCase(str)){
                    don.add(l);
                }else if(l.getnDocumento().equalsIgnoreCase(str)) don.add(l);
            });
        }
        return don;
    }

    public void update(String id, DoadorDAO func){
        ArrayList<DoadorDAO> don = lista();
        if(!don.isEmpty()){
            for (int i = 0; i < don.size(); i++) {
                if(don.get(i).getnDocumento().equalsIgnoreCase(id)){
                    don.remove(i);
                    don.add(i, func);
                    create(don);
                    break;
                }
            }
        }
    }

    public boolean delete(String id){
        ArrayList<DoadorDAO> don = lista();
        if(!don.isEmpty()){
            for (DoadorDAO don_ : don) {
                if(don_.getnDocumento().equalsIgnoreCase(id)){
                    don.remove(don_);
                    create(don);
                    return true;
                }
            }
        }
        return false;
    }
}
