package views;

import com.itextpdf.text.Document;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import static javax.swing.JFileChooser.APPROVE_OPTION;
import static javax.swing.JFileChooser.FILES_ONLY;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;
import models.dao.Propriedades;
import models.dao.RelatorioPdf;

/**
 *
 * @author CarlosMacaneta
 */
public class ViewRelatorio extends JPanel implements ActionListener {
    
    private int index;
    
    private JButton btnPesq = new JButton("Pesquisar", new ImageIcon("src/images/search.png"));
    private JButton btnSave = new JButton("Gerar pdf", new ImageIcon("src/images/pdf.png"));
    private JButton grafico = new JButton("Gráfico", new ImageIcon("src/images/graph.png"));
    
    private JComboBox<String> jcbDatas = new JComboBox<>();
    private JComboBox<String> jcbRelatorio = new JComboBox<>(new String[]{null, "Benefiários",
        "Doadores","Donativos"});

    private JFileChooser jfc = new JFileChooser();
    
    private JFormattedTextField dataInicial = new JFormattedTextField();
    private JFormattedTextField dataFinal = new JFormattedTextField();

    private JLabel lblIcon = new JLabel(new ImageIcon("src/images/donation-icon.png"));
    private JLabel lblRelatorio = new JLabel("Escolha o relatório");
    
    private JPanel jpPesq = new JPanel();
    private JPanel jpRela = new JPanel();
    private JPanel jpRep = new JPanel();
    private JPanel jpNorth = new JPanel();
    private JPanel jpCenter = new JPanel();
    private JPanel jpBtn = new JPanel();
    
    private Grafico graph = new Grafico();
    private GraficoProd graphProd = new GraficoProd();
    
    private void formateDateField(){
        try {
            MaskFormatter  mf = new MaskFormatter("####-##-##");
            MaskFormatter  mf_ = new MaskFormatter("####-##-##");
            mf.install(dataInicial);
            mf_.install(dataFinal);
        } catch (ParseException e) {
        }
    }
    
    public ViewRelatorio() {
        new Propriedades().setBackground(new Color(238, 232, 170), this, jpNorth, jpPesq, jpRela, jpRep, 
            jcbDatas, jcbRelatorio, jpCenter, jpBtn);
        this.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(238, 232, 170)));
        this.setLayout(new BorderLayout(400, 30));
        formateDateField();
    }
    
    public JPanel init(JTabbedPane tab, String title, JButton btnCloseTab){
        
        jfc.setBackground(new Color(25, 25, 25));
            
        jfc.setFileSelectionMode(FILES_ONLY);
        
        new Propriedades().setBackground(new Color(45, 82, 124), btnPesq, btnCloseTab, btnSave, grafico);
        
        new Propriedades().setForeground(Color.WHITE, btnPesq, btnCloseTab, btnSave, grafico);
        new Propriedades().setForeground(Color.BLACK, dataFinal, dataInicial, jcbDatas, jcbRelatorio);
        
        jpNorth.setLayout(new BorderLayout(10, 10));
        jpPesq.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jpRep.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jpBtn.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        jpPesq.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 2), "Pesquisar por data", 1, 0, new Font("Calibri", Font.BOLD, 16), Color.BLACK));
        dataInicial.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 1), "Data inicial", 1, 0, new Font("Calibri", Font.ITALIC, 16), Color.BLACK));
        dataFinal.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 1), "Data final", 1, 0, new Font("Calibri", Font.ITALIC, 16), Color.BLACK));
        jpCenter.setBorder(new LineBorder(new Color(45, 82, 124), 1));
        new Propriedades().setSoftBevelBorder(BevelBorder.RAISED, btnPesq, btnSave, grafico, btnCloseTab);
        
        new Propriedades().setPreferredSize(new Dimension(200, 40), dataInicial, dataFinal);
        new Propriedades().setPreferredSize(new Dimension(200, 30), jcbDatas, btnPesq, jcbRelatorio);
        new Propriedades().setPreferredSize(new Dimension(120, 30), btnSave, grafico, btnCloseTab);
        new Propriedades().setPreferredSize(new Dimension(400, 160), jpRela);
        
        new Propriedades().setFont("Calibri", Font.BOLD, 14, dataInicial, dataFinal, lblRelatorio);
        new Propriedades().setFont("Calibri", Font.PLAIN, 14, jcbDatas, jcbRelatorio, grafico, btnCloseTab, btnPesq, btnSave);
        
        new Propriedades().setOpaque(false, dataFinal, dataInicial);
        
        new Propriedades().addContainer(jpPesq, dataInicial, dataFinal, jcbDatas, btnPesq);
        new Propriedades().addContainer(jpRep, lblRelatorio, jcbRelatorio);
        new Propriedades().addContainer(jpBtn, btnSave, grafico, btnCloseTab);
        new Propriedades().addContainer(jpRela, jpRep, jpBtn);
        jpNorth.add(lblIcon, BorderLayout.WEST);
        //jpNorth.add(jpPesq, BorderLayout.CENTER);
        jpNorth.add(jpRela, BorderLayout.CENTER);
        
        this.add(jpNorth, BorderLayout.NORTH);
        this.add(jpCenter, BorderLayout.CENTER);
        
        btnCloseTab.addActionListener((ActionEvent ae) ->{
            if(ae.getSource() == btnCloseTab){
                tab.remove(tab.indexOfTab(title));
            }
        });
        
        new Propriedades().addActioListener(this, btnSave, btnPesq, grafico);
        jcbRelatorio.addActionListener(this);
        
        return this;
    }
    
    private void setFieldsEmpty(){
        dataInicial.setText(null);
        dataFinal.setText(null);
    }
    
    private void clean(){
        if(jpCenter.getComponentCount() > 0){
            jpCenter.removeAll();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(null != (String) jcbRelatorio.getSelectedItem())switch ((String) jcbRelatorio.getSelectedItem()) {
            case "Benefiários":
                clean();
                jpCenter.add(Relatorios.relatorioBen());
                jpCenter.add(Relatorios.infoBen());
                break;
            case "Doadores":
                clean();
                jpCenter.add(Relatorios.relatorioDoador());
                jpCenter.add(Relatorios.infoDoador());
                break;
            case "Donativos":
                clean();
                jpCenter.add(Relatorios.relatorioDonativo());
                jpCenter.add(Relatorios.infoDonativo());
                break;
            default: clean(); break;
        }else clean();
        
        if(ae.getSource() == btnSave && null != (String) jcbRelatorio.getSelectedItem()){ 
            String path ;
            
            int save = jfc.showSaveDialog(this);
            
            if(save == APPROVE_OPTION){
                path = jfc.getSelectedFile().getPath();
            
                Document doc = new Document();
                RelatorioPdf.pfdReport(doc, (String) jcbRelatorio.getSelectedItem(), path);
            }else System.out.println("Aborted...");
        }else if(ae.getSource() == btnPesq && null != (String) jcbRelatorio.getSelectedItem() && !dataInicial.getText().isEmpty() 
            && !dataFinal.getText().isEmpty()){
            switch((String) jcbRelatorio.getSelectedItem()){
                case "Benefiários":
                    clean();
                    jpCenter.add(Relatorios.filtroBen(dataInicial.getText(), dataFinal.getText()));
                    jpCenter.add(Relatorios.infoFiltroBen(dataInicial.getText(), dataFinal.getText()));
                    break;
                case "Doadores":
                    clean();
                    jpCenter.add(Relatorios.filtroDoador(dataInicial.getText(), dataFinal.getText()));
                    jpCenter.add(Relatorios.infoFiltroDoador(dataInicial.getText(), dataFinal.getText()));
                    break;
                case "Donativos":
                    clean();
                    jpCenter.add(Relatorios.filtroDonativo(dataInicial.getText(), dataFinal.getText()));
                    jpCenter.add(Relatorios.infoFiltroDonativo(dataInicial.getText(), dataFinal.getText()));
                    break;
                default: clean(); break;
            }
        }else if(dataInicial.getText().isEmpty() && dataFinal.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Informe a data incial e a data final\nseguindo o formato: yyyy-mm-dd");
        }else if(ae.getSource() == grafico && null != (String) jcbRelatorio.getSelectedItem()){
            switch((String) jcbRelatorio.getSelectedItem()){
                case "Benefiários":
                    clean();
                    graph.init("Benefiários");
                    break;
                case "Doadores":
                    graph.init("Doadores");
                    graphProd.init("Doadores");
                    break;
                case "Donativos":
                    graph.init("Donativos");
                    graphProd.init("Donativos");
                    break;
                default: clean(); break;
            }
        }
    }
}