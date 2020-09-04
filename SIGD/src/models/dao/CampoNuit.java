package models.dao;

import java.io.Serializable;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
/**
 *
 * @author Carlos Macaneta
 */
public class CampoNuit extends JFormattedTextField implements Serializable {
    
    public CampoNuit(){
        setColumns(120);
        try{
            MaskFormatter mf = new MaskFormatter("#########");
            mf.install(this);
        }catch(ParseException e){
        }
    }
    
}
