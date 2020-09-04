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
import models.dao.Funcionario;
import models.dao.Propriedades;

public class FuncController {

    private static final String URI = "src/arquivos/func.dat";

    private void create(ArrayList<Funcionario> fnc){
        try(FileOutputStream fos = new FileOutputStream(URI)){
            try(ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(fnc);
                oos.close();
            }catch(FileNotFoundException ex){}
        }catch(IOException ex){}
    }

    public boolean create(Funcionario func){
        if(Files.notExists(Paths.get(URI))){
            ArrayList<Funcionario> func_ = new ArrayList();
            func_.add(func);
            create(func_);
            return true;
        }else{
            ArrayList<Funcionario> func_ = lista();
            func_.add(func);
            create(func_);
            return true;
        }
    }

    public ArrayList<Funcionario> lista(){
        ArrayList<Funcionario> list = new ArrayList<>();

        try(FileInputStream fis = new FileInputStream(URI)){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                list = (ArrayList<Funcionario>) ois.readObject();
                ois.close();
            }catch(FileNotFoundException|ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, "ERRO: " + e, "ERROR", ERROR_MESSAGE);
            }
        }catch(IOException ex){}
        return list;
    }

    public ArrayList<Funcionario> search(String str){
        ArrayList<Funcionario> funcs = new ArrayList<>();

        if (!Propriedades.isNumber(str)) {
            if (!lista().isEmpty()) {
                lista().forEach((l) -> {
                    if ((l.getNome()+" "+l.getApelido()).toLowerCase().startsWith(str.toLowerCase())) {
                        funcs.add(l);
                    }
                });
            }
        }else funcs.add(findById(Integer.parseInt(str)));
        return funcs;
    }

    public Funcionario findById(int id){
        if(!lista().isEmpty()&& String.valueOf(id).length() >= 8){
            for(Funcionario f: lista()){
                if (f.getId() == id)
                    return f;
            }
        }
        return new Funcionario();
    }

    public void update(int id, Funcionario func){
        ArrayList<Funcionario> funcs = lista();
        if(!funcs.isEmpty()){
            for (int i = 0; i < funcs.size(); i++) {
                if(funcs.get(i).getId() == id){
                    funcs.remove(i);
                    funcs.add(i, func);
                    create(funcs);
                    break;
                }
            }
        }
    }

    public boolean delete(int id){
        ArrayList<Funcionario> funcs = lista();
        if(!funcs.isEmpty()){
            for (Funcionario func_ : funcs) {
                if(func_.getId() == id){
                    funcs.remove(func_);
                    create(funcs);
                    return true;
                }
            }
        }
        return false;
    }
}