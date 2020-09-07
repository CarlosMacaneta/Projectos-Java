package view;

import java.awt.Color;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author CarlosMacaneta
 */
public class GraficoLinha extends JFrame {

    public GraficoLinha() {
        super("Grafico de Linha");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getRootPane().setBackground(Color.WHITE);
        init();
    }
    
    private void init(){
        
        DefaultCategoryDataset dataset = createDataset();
        
        JFreeChart grafico = ChartFactory.createLineChart("Salario medio por idade", "Idade", "Salario(MZN)", 
            dataset);
        
        ChartPanel painel = new ChartPanel(grafico);
        this.add(painel);
    }
    
    private DefaultCategoryDataset createDataset(){
        
        String series = "Visitante";
        String series1 = "Visitantes";
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        dataset.addValue(14.5, series, "2020-08-26");
        dataset.addValue(214.5, series, "2020-08-27");
        dataset.addValue(144.5, series, "2020-08-28");
        
        dataset.addValue(174.5, series1, "2020-08-25");
        dataset.addValue(74.5, series1, "2020-08-29");
        dataset.addValue(1.5, series1, "2020-09-30");
        
        return dataset;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new GraficoLinha().setVisible(true);
    }
    
}
