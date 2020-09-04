package views;

import controllers.DoadorController;
import controllers.OrfanatoController;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.dao.DoacaoOrfanato;
import models.dao.DoadorDAO;
import models.dao.Donativo;
import models.dao.Orfanato;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author CarlosMacaneta
 */
public class RelatorioGrafico {
    
    private static int qtd;
    private static DefaultCategoryDataset barra = new DefaultCategoryDataset();
    private static DefaultCategoryDataset barra_ = new DefaultCategoryDataset();
    private static DefaultCategoryDataset barra1 = new DefaultCategoryDataset();
    private static DefaultCategoryDataset barra2 = new DefaultCategoryDataset();
    private static Donativo d_ = new Donativo();
    private static DoacaoOrfanato do_ = new DoacaoOrfanato();
    private static ArrayList<Donativo> don = d_.findDonater();
    private static ArrayList<DoadorDAO> donater = new DoadorController().lista();
    private static ArrayList<Orfanato> orf = OrfanatoController.lista();
    
    public static ChartPanel graficoBeneficiario(){
        
        if(!orf.isEmpty()){
            orf.forEach((o)->{
                barra.setValue(do_.qtdTotalProd_(o.getId()), o.getNome(), o.getDataReg());
            });
        }else JOptionPane.showMessageDialog(null, "");
        
        JFreeChart grafico = ChartFactory.createBarChart3D("Beneficiários(orfanatos)", "Data de registo dos orfanatos", "Quantidade de produtos recebidos",
            barra, PlotOrientation.VERTICAL, true, true, true);
        
        ChartPanel painel = new ChartPanel(grafico);
        saveReportImg("relatorio-beneficiarios", grafico);
        return painel;
    }
    
    public static ChartPanel graficoDoadores(){
        
        if(!donater.isEmpty()){
            donater.forEach((d)->{
                qtd = d_.qtdTotalProd(d.getnDocumento());
                barra1.setValue(qtd, d.getNome()+" "+d.getApelido(), d.getData());
            });
        }
        
        JFreeChart grafico = ChartFactory.createBarChart3D("Doadores", "Data de registo dos doadores", "Quantidade de prod. doados", barra1,
            PlotOrientation.VERTICAL, true, true, true);
        
        ChartPanel painel = new ChartPanel(grafico);
        saveReportImg("relatorio-doadores1", grafico);
        return painel;
    }
    
    public static ChartPanel graficoDoadoresDoacoes(){
        
        if(!donater.isEmpty()){
            donater.forEach((d)->{
                qtd = d_.numDoacoes(d.getnDocumento());
                barra2.setValue(qtd, d.getNome()+" "+d.getApelido(), d.getData());
            });
        }
        
        JFreeChart grafico = ChartFactory.createBarChart3D("Doadores", "Data de registo dos doadores", "Nº. de doações", barra2,
            PlotOrientation.VERTICAL, true, true, true);
        
        ChartPanel painel = new ChartPanel(grafico);
        saveReportImg("relatorio-doadores2", grafico);
        return painel;
    }
    
    public static ChartPanel graficoDonativos(){
        
        if(!don.isEmpty()){
            don.forEach((d)->{
                barra.setValue(d_.qtdCategoria(d.getCategoria(), d.getData()), d.getCategoria(), d.getData());
            });
        }
        
        JFreeChart grafico = ChartFactory.createBarChart3D("Donativos", "Data de registo dos donativos", "Quantidade", barra,
            PlotOrientation.VERTICAL, true, true, true);
        
        ChartPanel painel = new ChartPanel(grafico);
        saveReportImg("relatorio-donativo1", grafico);
        return painel;
    }
    
    public static ChartPanel graficoDonativosProd(){
        
        if(!don.isEmpty()){
            don.forEach((d)->{
                barra_.setValue(d_.qtdProd_(d.getNomeProd()), d.getNomeProd(), d.getCategoria());
            });
        }
        
        JFreeChart grafico = ChartFactory.createBarChart3D("Donativos", "Produtos doados", "Quantidade", barra_,
            PlotOrientation.VERTICAL, true, true, true);
        
        ChartPanel painel = new ChartPanel(grafico);
        saveReportImg("relatorio-donativo2", grafico);
        return painel;
    }
    
    private static void saveReportImg(String url, JFreeChart chart){
        final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
        final File file  = new File("src/images/graficos/"+url+".png");
        try {
            ChartUtilities.saveChartAsPNG(file, chart, 784, 361, info);
        } catch (IOException ex) {
            Logger.getLogger(RelatorioGrafico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}