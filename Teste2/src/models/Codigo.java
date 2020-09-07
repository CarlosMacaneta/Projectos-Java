package models;

import controllers.EstController;
import controllers.ProfController;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author CarlosMacaneta
 */
public class Codigo {
    
    private static final String URI1 = "src/arquivos/prof.dat";
    private static final String URI2 = "src/arquivos/est.dat";
    
    public static int idProf(){
        
        if(Files.notExists(Paths.get(URI1))){
            return 1;
        }else{
            ArrayList<Professor> list = ProfController.lista();
            
            if(!list.isEmpty()){
                int maior = list.get(0).getId();

                for(Professor p: list){
                    if(p.getId() > maior){
                        maior = p.getId();
                    }
                }
                return maior+1;
            }
        }
        return 1;
    }
    
    public static int idEst(){
        
        if(Files.notExists(Paths.get(URI2))){
            return 1;
        }else{
            ArrayList<Estudante> list = EstController.lista();
            
            if(!list.isEmpty()){
                int maior = list.get(0).getId();

                for(Estudante p: list){
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