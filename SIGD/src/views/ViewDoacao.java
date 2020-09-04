package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author CarlosMacaneta
 */
public class ViewDoacao extends JFrame implements ActionListener {
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    JLabel lblId = new JLabel("Código");
    JLabel lblName = new JLabel("Nome");
    JLabel lblTipoDoc = new JLabel("Tipo de documento");
    JLabel lblNDoc = new JLabel("Nº. documento");
    JLabel lblTel = new JLabel("Telefone");
    JLabel lblSearch = new JLabel("Pesquisar");
    
    JList list = new JList();
    
    
    JPanel barraPesq = new JPanel();
    
    JTextField jtfSearch = new JTextField();
    
    public ViewDoacao(){
        setSize(1080, 720);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(!true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void view(){
        setLayout(null);
        
        barraPesq.setBounds(50, 30, 980, 100);
        jtfSearch.setBounds(100, 50, 300, 30);
        list.setBounds(100, 80, 300, 150);
        
        barraPesq.setBorder(BorderFactory.createLineBorder(Color.yellow));
        
        add(jtfSearch);
        add(list);
        add(barraPesq);
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
}
