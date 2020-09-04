package models.dao;

import controllers.DoacaoOrfController;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.beans.Doador;

/**
 *
 * @author CarlosMacaneta
 */
public class DoacaoOrfanato extends Doador implements Serializable {
    
    private int qtd;
    private double dinheiro;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public DoacaoOrfanato() {
    }

    public DoacaoOrfanato(int id, String nomeProd, int qtd, String valorDoado, String data, int foreignkey) {
        super(id, nomeProd, qtd, valorDoado, data, foreignkey);
    }

    public int qtdTotalProd_(int fk){
        ArrayList<DoacaoOrfanato> dorf = DoacaoOrfController.lista();
        qtd = 0;
        dorf.forEach((DoacaoOrfanato d)->{
            if(fk == d.getFk()){
                qtd += d.getQtd();
            }
        });
        return qtd;
    }
    public int qtdTotalProd(int fk, String data){
        ArrayList<DoacaoOrfanato> dorf = DoacaoOrfController.lista();
        qtd = 0;
        dorf.forEach((DoacaoOrfanato d)->{
            if(fk == d.getFk()&&d.getData().equals(data)){
                qtd += d.getQtd();
            }
        });
        return qtd;
    }
    
    public int qtdTotalProd(){
        ArrayList<DoacaoOrfanato> dorf = DoacaoOrfController.lista();
        qtd = 0;
        dorf.forEach((DoacaoOrfanato d)-> qtd += d.getQtd());
        return qtd;
    }
    
    public int qtdTotalProd(ArrayList<Orfanato> orf){
        ArrayList<DoacaoOrfanato> dorf = DoacaoOrfController.lista();
        qtd = 0;
        orf.forEach((o)-> dorf.forEach((DoacaoOrfanato d)->{
            if(o.getId() == d.getFk()){
                qtd += d.getQtd();
            }
        }));
        return qtd;
    }
    
    public int qtdTotalProd(String dataInicial, String dataFinal){
        ArrayList<DoacaoOrfanato> dorf = DoacaoOrfController.lista();
        
        try {
            
            Date data1 = sdf.parse(dataInicial);
            Date data2 = sdf.parse(dataFinal);
            
            dorf.forEach((DoacaoOrfanato d)->{
                try {
                    if((data1.before((Date) sdf.parse(d.getData()))||data1.equals(sdf.parse(d.getData()))) &&
                        (data2.after((Date) sdf.parse(d.getData()))||data2.equals((Date) sdf.parse(d.getData())))){
                        qtd += d.getQtd();
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(DoacaoOrfanato.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (ParseException ex) {
            Logger.getLogger(DoacaoOrfanato.class.getName()).log(Level.SEVERE, null, ex);
        }
        return qtd;
    }
    
    public String valorTotal(int fk){
        ArrayList<DoacaoOrfanato> dorf = DoacaoOrfController.lista();
        
        dorf.forEach((d)->{
            if(fk == d.getFk()){
                if (d.getValorDoado().contains("MZN")) {
                    String[] valor = d.getValorDoado().split(" ");
                    dinheiro += Double.parseDouble(valor[1].replace(".", "").replace(",", "."));
                } 
            }
        });
        return "MZN "+dinheiro;
    }
    
    public String valorTotal(){
        ArrayList<DoacaoOrfanato> dorf = DoacaoOrfController.lista();
        
        dorf.forEach((d)->{
            if (d.getValorDoado().contains("MZN")) {
                String[] valor = d.getValorDoado().split(" ");
                dinheiro += Double.parseDouble(valor[1].replace(".", "").replace(",", "."));
            } 
        });
        return "MZN "+dinheiro;
    }
    
    public String valorTotal(ArrayList<Orfanato> orf){
        ArrayList<DoacaoOrfanato> dorf = DoacaoOrfController.lista();

        orf.forEach((o)-> dorf.forEach((d)->{
            if(o.getId() == d.getFk()){
                if (d.getValorDoado().contains("MZN")) {
                    String[] valor = d.getValorDoado().split(" ");
                    dinheiro += Double.parseDouble(valor[1].replace(".", "").replace(",", "."));
                } 
            }
        }));
        return "MZN "+dinheiro;
    }
}