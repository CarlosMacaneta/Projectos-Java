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

import models.dao.Orfanato;
import models.dao.Orfanato;
import models.dao.Propriedades;

public class OrfanatoController {

    private static final String URI = "src/arquivos/orf.dat";

    private static void create(ArrayList<Orfanato> fnc){
        try(FileOutputStream fos = new FileOutputStream(URI)){
            try(ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(fnc);
                oos.close();
            }catch(FileNotFoundException ex){}
        }catch(IOException ex){}
    }

    public static boolean create(Orfanato orf){
        if(Files.notExists(Paths.get(URI))){
            ArrayList<Orfanato> orf_ = new ArrayList();
            orf_.add(orf);
            create(orf_);
            return true;
        }else{
            ArrayList<Orfanato> orf_ = lista();
            orf_.add(orf);
            create(orf_);
            return true;
        }
    }

    public static ArrayList<Orfanato> lista(){
        ArrayList<Orfanato> list = new ArrayList<>();

        try(FileInputStream fis = new FileInputStream(URI)){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                list = (ArrayList<Orfanato>) ois.readObject();
                ois.close();
            }catch(FileNotFoundException|ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, "ERRO: " + e, "ERROR", ERROR_MESSAGE);
            }
        }catch(IOException ex){}
        return list;
    }

    public static ArrayList<Orfanato> pesquisaNome(String nome){
        ArrayList<Orfanato> orfs = new ArrayList<>();

        if(!lista().isEmpty()){
            lista().forEach((nome_) ->{
                if(nome_.getNome().equalsIgnoreCase(nome)){
                    orfs.add(nome_);
                }
            });
        }
        return orfs;
    }

    public static void update(int id, Orfanato orf){
        ArrayList<Orfanato> orfs = lista();
        if(!orfs.isEmpty()){
            for (int i = 0; i < orfs.size(); i++) {
                if(orfs.get(i).getId() == id){
                    orfs.remove(i);
                    orfs.add(i, orf);
                    create(orfs);
                    break;
                }
            }
        }
    }

    public static void delete(int id){
        ArrayList<Orfanato> orfs = lista();
        if(!orfs.isEmpty()){
            for (Orfanato orf_ : orfs) {
                if(orf_.getId() == id){
                    orfs.remove(orf_);
                    create(orfs);
                    break;
                }
            }
        }
    }

    public static ArrayList<Orfanato> search(String str){
        ArrayList<Orfanato> orf = new ArrayList<>();

        if(!Propriedades.isNumber(str)) {
            if (!lista().isEmpty()) {
                lista().forEach((l) -> {
                    if (l.getNome().toLowerCase().startsWith(str.toLowerCase()))
                        orf.add(l);
                });
            }
        }else orf.add(findById(Integer.parseInt(str))) ;
        return orf;
    }

    public static Orfanato findById(int id){
        if (!lista().isEmpty()){
            for(Orfanato o: lista()){
                if (id == o.getId())
                    return o;
            }
        }
        return null;
    }

    public static int id(){
        if(Files.notExists(Paths.get(URI))){
            return 1;
        }else{
            ArrayList<Orfanato> list = lista();

            if(!list.isEmpty()){
                int maior = list.get(0).getId();

                for(Orfanato p: list){
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