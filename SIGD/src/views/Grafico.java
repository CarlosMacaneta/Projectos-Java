package views;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author CarlosMacaneta
 */
public class Grafico extends JFrame {
    
    private JPanel panel = new JPanel();

    public Grafico() {
        super("Grafico de barras");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getRootPane().setBackground(Color.white);
        this.setLayout( new BorderLayout());
    }
    
    private void clean(){
        if(panel.getComponentCount() > 0){
            panel.removeAll();
        }
    }
    
    public void init(String report){  
        switch (report) {
            case "Benefiários":
                clean();
                panel.add(RelatorioGrafico.graficoBeneficiario());
                break;
            case "Doadores":
                clean();
                panel.add(RelatorioGrafico.graficoDoadores());
                break;
            case "Donativos":
                clean();
                panel.add(RelatorioGrafico.graficoDonativos());
                break;
            default: break;
        }
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
        pack();
    }  
}