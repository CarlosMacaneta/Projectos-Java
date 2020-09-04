package views;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author CarlosMacaneta
 */
public class GraficoProd extends JFrame {
    
    private JPanel panel = new JPanel();
    
    public GraficoProd() {
        super("Grafico de barras");
        //setLocation(CENTER_ALIGNMENT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getRootPane().setBackground(Color.WHITE);
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
                break;
            case "Doadores":
                clean();
                panel.add(RelatorioGrafico.graficoDoadoresDoacoes());
                break;
            case "Donativos":
                clean();
                panel.add(RelatorioGrafico.graficoDonativosProd());
                break;
            default: break;
        }
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
        pack();
    }
}