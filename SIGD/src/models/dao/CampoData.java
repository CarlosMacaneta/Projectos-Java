package models.dao;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
/**
 *
 * @author Carlos Macaneta
 */
public class CampoData extends JFormattedTextField implements Serializable {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

    public CampoData() {
        setColumns(50);       
        
        try {
            MaskFormatter  mf = new MaskFormatter("##/##/####");
            mf.install(this);
        } catch (ParseException e) {
        }
    } 
    
    public static boolean validarData(String[] data){
        int[] intData = new int[3];
        
        for(int i = 0; i < data.length; i++){
            intData[i] = Integer.parseInt(data[i]);
        }
        
        return ((intData[0] > 0)&&(intData[0] <= 31))&&((intData[1] > 0)&&(intData[1] <= 12))&&((intData[2] >= 1930)&&(intData[2] <= 2002));
    }
}