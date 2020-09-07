package views;

import controllers.ProfController;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.LineBorder;
import models.Codigo;
import models.Professor;

/**
 *
 * @author CarlosMacaneta
 */
public class RegProfessor extends JFrame implements ActionListener {
    
    private int id =  Codigo.idProf();
    
    private JButton btnReg = new JButton("Registar");
    private JComboBox<String> genero = new JComboBox<>(new String[]{"Masculino", "Feminino"});
    
    private JTextField nome = new JTextField();
    private JTextField grauAcademico = new JTextField();
    
    private JLabel lblId = new JLabel("Id: "+id);
    private JLabel lblNome = new JLabel("Nome:");
    private JLabel lblGenero = new JLabel("Género:");
    private JLabel lblGrauAcad = new JLabel("Grau académico");
    
    private JPanel panel = new JPanel();

    public RegProfessor() {
        super("Registo do professor");
        setSize(600, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(238, 232, 170)));
    }
    
    public void init(){
        
        Container main = this.getContentPane();
        main.setLayout(null);
        
        main.setBackground(new Color(238, 232, 170));
        panel.setOpaque(false);
        
        panel.setBorder(new LineBorder(new Color(20, 63, 114), 1));
        
        lblId.setBounds(50, 50, 120, 30);
        lblNome.setBounds(50, 95, 120, 30);
        nome.setBounds(200-80, 95, 200, 30);
        lblGenero.setBounds(50, 135, 120, 30);
        genero.setBounds(200-80, 135, 200, 30);
        lblGrauAcad.setBounds(410-50, 75, 120, 30);
        grauAcademico.setBounds(420-60, 95, 180, 30);
        panel.setBounds(10, 10, 565, 245);
        btnReg.setBounds(420-60, 200, 180, 30);
        
        main.add(lblId);
        main.add(lblNome);
        main.add(nome);
        main.add(lblGenero);
        main.add(genero);
        main.add(lblGrauAcad);
        main.add(grauAcademico);
        main.add(btnReg);
        main.add(panel);
        
        btnReg.addActionListener(this);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btnReg){
            if(!nome.getText().isEmpty() &&! grauAcademico.getText().isEmpty()){
                ProfController.create(new Professor(id, nome.getText(), (String) genero.getSelectedItem(), grauAcademico.getText()));
                JOptionPane.showMessageDialog(this, "Professor registado com sucesso.");
                dispose();
            }else JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
        }
    }   
}