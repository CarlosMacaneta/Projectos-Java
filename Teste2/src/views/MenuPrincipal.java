package views;

import controllers.ProfController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author CarlosMacaneta
 */
public class MenuPrincipal extends JFrame implements ActionListener {
    
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuProf = new JMenu("Professor");
    private JMenuItem profReg= new JMenuItem("Registar professor");
    private JMenuItem profLista = new JMenuItem("Lista dos Professores");
    private JMenu menuEst = new JMenu("Estudante");
    private JMenuItem estReg = new JMenuItem("Registar estudante");
    private JMenuItem estLista = new JMenuItem("Lista dos estudantes");
    private JMenuItem estSituacao = new JMenuItem("Situação dos estudantes");
    private JMenuItem estModaMedia = new JMenuItem("Visualizar(média e moda) das médias");

    public MenuPrincipal() {
        setSize(900, 700);
        setLocationRelativeTo(null);
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void init(){
        
        menuBar.setBackground(new Color(199, 199, 201));
        menuProf.setFont(new Font("Calibri", Font.PLAIN, 16));
        menuEst.setFont(new Font("Calibri", Font.PLAIN, 16));
        
        menuProf.add(profReg);
        menuProf.add(profLista);
        menuEst.add(estReg);
        menuEst.add(estLista);
        menuEst.add(estSituacao);
        menuEst.add(estModaMedia);
        
        menuBar.add(menuProf);
        menuBar.add(menuEst);
        
        add(menuBar, BorderLayout.NORTH);
        
        profReg.addActionListener(this);
        profLista.addActionListener(this);
        estReg.addActionListener(this);
        estLista.addActionListener(this);
        estSituacao.addActionListener(this);
        estModaMedia.addActionListener(this);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == profReg){
            new RegProfessor().init();
        }else if(ae.getSource() == profLista){
            new ListaProf().init();
        }else if(ae.getSource() == estReg){
            if(!ProfController.lista().isEmpty()){
                new RegEstudante().init();
            }else JOptionPane.showMessageDialog(this, "Para registar um estudante deve\n registar um professor antes.");
        }else if(ae.getSource() == estLista){
            new ListaEst().init();
        }else if(ae.getSource() == estSituacao){
            new Situacao().init();
        }else if(ae.getSource() == estModaMedia){
            new VisualizarMM().init();
        }
    }
    
    public static void main(String[] args) {
        new MenuPrincipal().init();
    }   
}