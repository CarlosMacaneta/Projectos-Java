package view;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.OHLCDataset;

/**
 *
 * @author CarlosMacaneta
 */
public class GraficoFina extends JFrame {
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    public GraficoFina() {
        super("Grafico de Linha");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getRootPane().setBackground(Color.WHITE);
        init();
    }
    
    private void init(){
        
        
        
        
    }
    
    private OHLCDataset dataset(){
        OHLCDataItem[] dataItem = null;
        
        try {
            OHLCDataItem data1 = new OHLCDataItem(sdf.parse("2020-08-31"), 288.0, 228.45, 126.4, 100, 210);
            OHLCDataItem data2 = new OHLCDataItem(sdf.parse("2020-08-31"), 288.23, 228.45, 16.4, 105, 201);
            OHLCDataItem data3 = new OHLCDataItem(sdf.parse("2020-08-31"), 28.0, 228.41, 126.4, 160, 20);
            
            dataItem = new OHLCDataItem[]{data1, data2, data3};
        } catch (ParseException ex) {
            Logger.getLogger(GraficoFina.class.getName()).log(Level.SEVERE, null, ex);
        }
        OHLCDataset dataset = new DefaultOHLCDataset("Price", dataItem);
        return dataset;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
