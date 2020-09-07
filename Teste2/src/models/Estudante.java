package models;

import controllers.EstController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author CarlosMacaneta
 */
public class Estudante extends Pessoa {
    
    private double nota1;
    private double nota2;
    private double media;
    private String situacao;

    public Estudante(){}

    public Estudante(int id, String nome, String genero, double nota1, double nota2, double media, String situacao) {
        super(id, nome, genero);
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.media = media;
        this.situacao = situacao;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }
    
    public double getMedia(){
        return media;
    }

    public void setMedia(double nota1, double nota2){
        this.media = (nota1 + nota2)/2;
    }

    public String getSitucao(){
        return situacao;
    }

    public void setSituacao(double media){
        if(media < 10)
            situacao = "Excluído";
        else if(media >= 10 && media < 14)
            situacao = "Aprovado";
        else if(media >= 14 && media <= 20)
            situacao = "Dispensado";
    }
    
    public double media(){
        ArrayList<Estudante> list = EstController.lista();
        double media_ = 0;
        if(!list.isEmpty()){
            for(Estudante e: list){
                media_ += e.getMedia();
            }
        }
        return media_ / list.size();
    }
    
    public ArrayList<Estudante> acimaMedia(){
        ArrayList<Estudante> list = EstController.lista();
        ArrayList<Estudante> est = new ArrayList<>();
        
        if(!list.isEmpty()){
            list.forEach((l)->{
                if(l.getMedia() > media()){
                    est.add(l);
                }
            });
        }
        return est;
    }
    
    public ArrayList<Estudante> pequisa(String str){
        ArrayList<Estudante> est = new ArrayList<>();
        
        if(!EstController.isNumber(str)){
            if(!acimaMedia().isEmpty()){
                acimaMedia().forEach((am)->{
                    if(str.equalsIgnoreCase(am.getNome())){
                        est.add(am);
                    }
                });
            }
        }else{
            if(!acimaMedia().isEmpty()){
                acimaMedia().forEach((am)->{
                    if(Integer.parseInt(str) == am.getId()){
                        est.add(am);
                    }
                });
            }
        }
        return est;
    }
    
    public ArrayList<Double> moda(ArrayList<Estudante> est){
        Map<Double, Integer> freqMedia = new HashMap<>();

        int maiorFreq = 0;

        for (int i = 0; i < est.size(); i++) {
            if(est.get(i) != null){
                //verificar se o numero esta no est
                Integer quantidade = freqMedia.get(est.get(i).getMedia());

                //lista de numeros
                if(quantidade == null) quantidade = 0;
                else quantidade++;

                freqMedia.put(est.get(i).getMedia(), quantidade);

                if(maiorFreq < quantidade) maiorFreq = quantidade;
            }else break;
        }
        ArrayList<Double> moda = new ArrayList<>();
        for (double numeroChave: freqMedia.keySet()) {
            int qtd = freqMedia.get(numeroChave);

            if (maiorFreq == qtd) moda.add(numeroChave);
        }
        return moda;
    }
}