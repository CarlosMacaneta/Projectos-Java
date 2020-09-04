package models.dao;

import controllers.OrfanatoController;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import models.beans.Beneficiario;

/**
 *
 * @author CarlosMacaneta
 */
public class Orfanato extends Beneficiario implements Serializable {

    private static String nome;

    public Orfanato() {
    }

    public Orfanato(int id, String nome, String endereco, String tel, String email, int nPessoas, int nCriancas, int nJovens, int nAdultos, String dataReg) {
        super(id, nome, endereco, tel, email, nPessoas, nCriancas, nJovens, nAdultos, dataReg);
    }
    
    public ArrayList<Orfanato> pesqData(String dataInicial, String DataFinal){
        ArrayList<Orfanato> orf = OrfanatoController.lista();
        ArrayList<Orfanato> result = new ArrayList<>();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            Date data1 = sdf.parse(dataInicial);
            Date data2 = sdf.parse(DataFinal);
            
            if(data1.before(data2)){
                if(!orf.isEmpty()){
                    orf.forEach((d)->{
                        try {
                            if((data1.before((Date) sdf.parse(d.getDataReg()))||data1.equals((Date) sdf.parse(d.getDataReg()))) && 
                                (data2.after(sdf.parse(d.getDataReg()))||data2.equals((Date) sdf.parse(d.getDataReg())))){
                                
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

    public static String findById(int id){
        ArrayList<Orfanato> lista = OrfanatoController.lista();

        if (!lista.isEmpty()){
            lista.forEach((orf)->{
                if(id == orf.getId())
                    nome = orf.getNome();
            });
        }
        return nome;
    }
}