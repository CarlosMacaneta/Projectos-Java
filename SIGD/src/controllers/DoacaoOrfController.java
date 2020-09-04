package controllers;

import models.dao.DoacaoOrfanato;
import models.dao.Orfanato;

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

public class DoacaoOrfController {
    private static final String URI = "src/arquivos/DoacaoOrfanato.dat";

    private static void create(ArrayList<DoacaoOrfanato> stk){
        try(FileOutputStream fos = new FileOutputStream(URI)){
            try(ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(stk);
                oos.close();
            }catch(FileNotFoundException ex){}
        }catch(IOException ex){}
    }

    public static boolean create(DoacaoOrfanato DoacaoOrfanato){
        if(Files.notExists(Paths.get(URI))){
            ArrayList<DoacaoOrfanato> DoacaoOrfanato_ = new ArrayList<>();
            DoacaoOrfanato_.add(DoacaoOrfanato);
            create(DoacaoOrfanato_);
            return true;
        }else{
            ArrayList<DoacaoOrfanato> DoacaoOrfanato_ = lista();
            DoacaoOrfanato_.add(DoacaoOrfanato);
            create(DoacaoOrfanato_);
            return true;
        }
    }

    public static ArrayList<DoacaoOrfanato> lista(){
        ArrayList<DoacaoOrfanato> list = new ArrayList<>();

        try(FileInputStream fis = new FileInputStream(URI)){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                list = (ArrayList<DoacaoOrfanato>) ois.readObject();
            }catch(FileNotFoundException|ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, "ERRO: " + e, "ERROR", ERROR_MESSAGE);
            }
        }catch(IOException ex){}
        return list;
    }

    public static void update(int id, DoacaoOrfanato DoacaoOrfanato){
        ArrayList<DoacaoOrfanato> DoacaoOrfanatos = lista();
        if(!DoacaoOrfanatos.isEmpty()){
            for (int i = 0; i < DoacaoOrfanatos.size(); i++) {
                if(DoacaoOrfanatos.get(i).getId() == id){
                    DoacaoOrfanatos.remove(i);
                    DoacaoOrfanatos.add(i, DoacaoOrfanato);
                    create(DoacaoOrfanatos);
                    break;
                }
            }
        }
    }

    public static boolean delete(int id){
        ArrayList<DoacaoOrfanato> DoacaoOrfanatos = lista();
        if(!DoacaoOrfanatos.isEmpty()){
            for (DoacaoOrfanato DoacaoOrfanato_ : DoacaoOrfanatos) {
                if(DoacaoOrfanato_.getId() == id){
                    DoacaoOrfanatos.remove(DoacaoOrfanato_);
                    create(DoacaoOrfanatos);
                    return true;
                }
            }
        }
        return false;
    }

    public static ArrayList<DoacaoOrfanato> readById(int id){
        ArrayList<DoacaoOrfanato> dorf = new ArrayList<>();

        if(!lista().isEmpty()){
            lista().forEach((l)->{
                if(id == l.getFk())
                    dorf.add(l);
            });
        }
        return dorf;
    }

    public static int id(){
        if(Files.notExists(Paths.get(URI))){
            return 1;
        }else{
            ArrayList<DoacaoOrfanato> list = lista();

            if(!list.isEmpty()){
                int maior = list.get(0).getId();

                for(DoacaoOrfanato p: list){
                    if(p.getId() > maior){
                        maior = p.getId();
                    }
                }
                return maior+1;
            }
        }
        return 1;
    }
}