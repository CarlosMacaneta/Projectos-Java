package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JToolBar;

/**
 *
 * @author CarlosMacaneta
 */
public class TelaPrincipal extends JFrame implements ActionListener {
    
    private JButton btnFunc = new JButton("Registar funcionarios");
    private JButton btnListF = new JButton("Listar funcionarios");
    private JButton btnRegProd = new JButton("Registar produtos");
    private JButton btnListProd = new JButton("Listar produtos");
    private JButton btnVender = new JButton("Vender");
    private JButton btnListVend = new JButton("Listar vendas");
    
    private JToolBar menu = new JToolBar();
    
    public TelaPrincipal(){
        setSize(900, 500);
        setLocationRelativeTo(null);
        setUndecorated(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
    }
    
    public void GUI(){
        this.setBackground(Color.white);
        menu.setBackground(new Color(18, 30, 49));
        btnFunc.setBackground(new Color(38, 178, 243));
        btnListF.setBackground(new Color(38, 178, 243));
        btnRegProd.setBackground(new Color(38, 178, 243));
        btnListProd.setBackground(new Color(38, 178, 243));
        btnVender.setBackground(new Color(38, 178, 243));
        btnListVend.setBackground(new Color(38, 178, 243));
        
        menu.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 1));
        
        menu.add(btnFunc);
        menu.add(btnListF);
        menu.add(btnRegProd);
        menu.add(btnListProd);
        menu.add(btnVender);
        menu.add(btnListVend);        
        
        this.add(menu, BorderLayout.NORTH);
        
        btnFunc.addActionListener(this);
        btnListF.addActionListener(this);
        btnListProd.addActionListener(this);
        btnListVend.addActionListener(this);
        btnRegProd.addActionListener(this);
        btnVender.addActionListener(this);
        
        setVisible(true);
    }
    
    @Override 
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == btnFunc){
            
        }else if(ae.getSource() == btnListF){
            
        }else if(ae.getSource() == btnListProd){
            
        }else if(ae.getSource() == btnListVend){
            
        }else if(ae.getSource() == btnRegProd){
            
        }else if(ae.getSource() == btnVender){
            
        }
    }
}
