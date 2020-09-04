package models.dao;

import controllers.DoadorController;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import models.beans.Doador;

/**
 *
 * @author Carlos Macaneta
 */
public class DoadorDAO extends Doador implements Serializable {
   
    public DoadorDAO(){
    }

    public DoadorDAO(String nome, String apelido, String tipoDocumento, String nDocumento, String genero, String telefone, String data) {
        super(nome, apelido, tipoDocumento, nDocumento, genero, telefone, data);
    }
    
    public ArrayList<DoadorDAO> pesqData(String dataInicial, String DataFinal){
        ArrayList<DoadorDAO> lista = new DoadorController().lista();
        ArrayList<DoadorDAO> result = new ArrayList<>();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            Date data1 = sdf.parse(dataInicial);
            Date data2 = sdf.parse(DataFinal);
            
            if(data1.before(data2)){
                if(!lista.isEmpty()){
                    lista.forEach((d)->{
                        try {
                            if((data1.before((Date) sdf.parse(d.getData()))||data1.equals((Date) sdf.parse(d.getData()))) && 
                                (data2.after((Date) sdf.parse(d.getData()))||data2.equals((Date) sdf.parse(d.getData())))){
                                
                                result.add(d);
                            }
                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(null, "Para a data inicial e a data final\nuse o seguinte formato: yyyy-mm-dd\n"
                            + "Exemplo: 2020-01-31");
                        }
                    });
                }
            }        
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Para a data inicial e a data final\nuse o seguinte formato: yyyy-mm-dd\n"
            + "Exemplo: 2020-01-31");
        }
        return result;        
    }
    
    public ArrayList<DoadorDAO> pesqData(ArrayList<Donativo> don){
        ArrayList<DoadorDAO> lista = new DoadorController().lista();
        ArrayList<DoadorDAO> result = new ArrayList<>();
            
            don.forEach((dtv)->{
                lista.forEach((d)->{
                    if(dtv.getForeignkey().equals(d.getnDocumento())){
                        result.add(d);
                    }
                });
            });
        return result;        
    }
}