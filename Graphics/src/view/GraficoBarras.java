package view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author CarlosMacaneta
 */
public class GraficoBarras extends JFrame {
    
    private DefaultCategoryDataset barra = new DefaultCategoryDataset();
    private DefaultCategoryDataset barra_ = new DefaultCategoryDataset();

    public GraficoBarras() {
        super("Grafico de barras");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void view(){
        
        barra.setValue(100, "Arroz", "Alimento");
        barra.setValue(170, "Arroz", "Alimentos");
        barra.setValue(110, "Agua", "Bebida");
        barra.setValue(10, "Amendoim", "Alimento");
        barra.setValue(200, "Caderno", "Material escolar");
        barra.setValue(150, "Lapis", "Material escolar");
        barra.setValue(450, "Camisas", "Vestuario");
        barra.setValue(5, "Bolas", "Brinquedo");
        barra.setValue(190, "Perfumes", "Outros");
        
        barra_.setValue(100, "Arroz", "2020-08-14");
        barra_.setValue(170, "Arroz", "2020-08-20");
        barra_.setValue(110, "Agua", "2020-08-25");
        barra_.setValue(10, "Amendoim", "2020-08-29");
        barra_.setValue(200, "Caderno", "2020-09-05");
        
        JFreeChart grafico = ChartFactory.createBarChart3D("Donativos(Categoria)", "Produtos doados", "Quantidade", barra,
            PlotOrientation.VERTICAL, true, true, true);
        JFreeChart grafico_ = ChartFactory.createBarChart3D("Donativos(Data)", "Produtos doados", "Quantidade", barra_,
            PlotOrientation.VERTICAL, true, true, true);
        
        ChartPanel painel = new ChartPanel(grafico);
        ChartPanel painel_ = new ChartPanel(grafico_);
        
        painel.setBackground(Color.BLACK);
        
        this.add(painel, BorderLayout.WEST);
        this.add(painel_, BorderLayout.CENTER);
        setVisible(true);
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       new GraficoBarras().view();
    }
    
}
