package views;

import controllers.ActividadeController;
import controllers.DoacaoOrfController;
import controllers.FinancaController;
import controllers.StockController;
import controllers.UserLogged;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import models.dao.Actividades;
import models.dao.CampoMoney;
import models.dao.DoacaoOrfanato;
import models.dao.Financa;
import models.dao.Log;
import models.dao.Orfanato;
import models.dao.Propriedades;
import models.dao.Stock;

/**
 *
 * @author CarlosMacaneta
 */
public class ViewDoar extends JPanel implements ActionListener {
    
    private Calendar c = Calendar.getInstance();
    private String data = String.format("%d-%02d-%02d", c.get(Calendar.YEAR), (c.get(Calendar.MONTH)+1), c.get(Calendar.DATE));
    
    private final boolean[] EDIT = { false, false, false, false, false, false};
    private final boolean[] isEditable = {false, false, false, false};
    private int rowtable1;
    private int rowtable2;
    private ArrayList<String> dadosMoney = new ArrayList<>();
    private ArrayList<String> dadosStock = new ArrayList<>();
    
    private DefaultTableModel model = new DefaultTableModel();
    private DefaultTableModel dtm = new DefaultTableModel();
    
    private JButton btnDoar = new JButton("Doar", new ImageIcon("src/images/send_donation.png"));
    private JButton btnPesquisar = new JButton(new ImageIcon("src/images/search.png"));
    private JButton btnRefresh = new JButton("Actualizar", new ImageIcon("src/images/refresh.png"));
    
    private JComboBox<String> jcbMoeda = new JComboBox<>(new String[]{"MZN"});
    
    private JLabel lblIcon = new JLabel(new ImageIcon("src/images/donation-icon.png"));
    
    private JList lista = new JList();
    
    private JPanel jpFormulario = new JPanel();
    private JPanel jpGroup = new JPanel();
    private JPanel jpCenter = new JPanel();
    private JPanel jpDonativos = new JPanel();
    private JPanel jpValor = new JPanel();
    private JPanel jpNorth = new JPanel();
    private JPanel jpSouth = new JPanel();
    
    private JTextField jtfNomeProd = new JTextField();
    private JTextField jtfQtd = new JTextField();
    private JTextField jtfValor = new JTextField();
    private JTextField jtfId = new JTextField();
    private JTextField jtfNomeOrf = new JTextField();
    private JTextField jtfPesquisar = new JTextField(1);
    
    private JScrollPane scrollTable = new JScrollPane();
    private JScrollPane scroll = new JScrollPane();
    
    private JTable tabela;
    private JTable jTable;
    
    public ViewDoar(){
        this.setBackground(new Color(238, 232, 170));
        this.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(238, 232, 170)));
    }
    
    public JPanel init(JTabbedPane tab, String title, JButton btnCloseTab){
        
        Container main = this;
        
        new Propriedades().setBackground(new Color(238, 232, 170), jcbMoeda, jpGroup);
        new Propriedades().setBackground(new Color(45, 82, 124), btnPesquisar, btnRefresh, btnCloseTab, btnDoar);
        
        jpNorth.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 1), "Pesquisar produtos", 1, 0, new Font("Calibri", Font.ITALIC, 16), Color.BLACK));
        jpFormulario.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 2), "Formulário", 1, 0, new Font("Calibri", Font.BOLD, 16), Color.BLACK));
        jpDonativos.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 2), "Donativos disponíveis", 1, 0, new Font("Calibri", Font.BOLD, 16), Color.BLACK));
        jtfNomeProd.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(22, 41, 80), 1), "Nome do produto", 1, 0, new Font("Calibri", Font.ITALIC, 16), Color.BLACK));
        jtfQtd.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(22, 41, 80), 1), "Quantidade", 1, 0, new Font("Calibri", Font.ITALIC, 16), Color.BLACK));
        jtfValor.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(22, 41, 80), 1), "Valor a doar", 1, 0, new Font("Calibri", Font.ITALIC, 16), Color.BLACK));
        jtfId.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(22, 41, 80), 1), "Id do orfanato", 1, 0, new Font("Calibri", Font.ITALIC, 16), Color.BLACK));
        jtfNomeOrf.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(22, 41, 80), 1), "Nome do orfanato", 1, 0, new Font("Calibri", Font.ITALIC, 16), Color.BLACK));
        scrollTable.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(22, 41, 80), 1), "Produtos disponíveis", 1, 0, new Font("Calibri", Font.ITALIC, 16), Color.BLACK));
        scroll.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(22, 41, 80), 1), "Valor disponível", 1, 0, new Font("Calibri", Font.ITALIC, 16), Color.BLACK));
        jcbMoeda.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        new Propriedades().setLineBorder(new Color(45, 82, 124),1, jtfPesquisar);
        new Propriedades().setSoftBevelBorder(BevelBorder.RAISED, btnPesquisar, btnRefresh, btnDoar, btnCloseTab);
        
        new Propriedades().setOpaque(false, jpFormulario, jpDonativos, jpValor, jtfNomeProd, jtfQtd, jtfValor, jtfId, jtfNomeOrf, jpNorth, jpCenter, jpSouth, scrollTable, scroll);
        
        jpFormulario.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
        jpValor.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        main.setLayout(new BorderLayout(10, 10));
        jpDonativos.setLayout(new BorderLayout(10, 20));
        new Propriedades().searchLayout(jpGroup, jtfPesquisar,btnPesquisar, lista);
        jpSouth.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        
        jpFormulario.setPreferredSize(new Dimension(250, 300));
        new Propriedades().setPreferredSize(new Dimension(210, 50), jtfNomeProd, jtfQtd, jtfId, jtfNomeOrf);
        btnDoar.setPreferredSize(new Dimension(210, 30));
        jtfValor.setPreferredSize(new Dimension(130, 50));
        jcbMoeda.setPreferredSize(new Dimension(75, 50));
        
        new Propriedades().setForeground(Color.WHITE, btnPesquisar, btnRefresh, btnCloseTab, btnDoar);
        
        new Propriedades().addColumn(model,"Id", "Nome do produto", "Categoria", "Quantidade disponível");
        new Propriedades().addColumn(dtm, "Id", "Valor disponível");
        
        tabela = new JTable(model){
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return EDIT[columnIndex];
            }
        };
        
        for(int i = 0; i < tabela.getColumnCount(); i++){
            TableColumn coluna = tabela.getColumnModel().getColumn(i);
            coluna.setPreferredWidth(175);
            coluna.setMaxWidth(500);
        }
        tabela.setAutoResizeMode(AUTO_RESIZE_OFF);
        scrollTable.setViewportView(tabela);
        
        jTable = new JTable(dtm){
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return isEditable[columnIndex];
            }
        };
        
        
        for(int i = 0; i < jTable.getColumnCount(); i++){
            TableColumn coluna = jTable.getColumnModel().getColumn(i);
            coluna.setPreferredWidth(122);
            coluna.setMaxWidth(500);
        }
        jTable.setAutoResizeMode(AUTO_RESIZE_OFF);
        scroll.setViewportView(jTable);
        
        new Propriedades().setForeground(Color.BLACK, jtfPesquisar, tabela, jTable, jtfId, jtfNomeOrf, jtfNomeProd, jtfQtd, jtfValor);
        
        new Propriedades().setFont("Calibri", Font.PLAIN, 14, jtfPesquisar,jtfId, jtfNomeOrf, jtfNomeProd, jtfQtd, jtfValor, tabela, jTable, btnRefresh, btnDoar, btnCloseTab);
        
        new Propriedades().setPreferredSize(new Dimension(120, 30), btnRefresh, btnCloseTab);
        scrollTable.setPreferredSize(new Dimension(500, 370)); 
        scroll.setPreferredSize(new Dimension(250, 370)); 
        lista.setVisible(false);
        
        addRow();
        
        jpNorth.add(jpGroup);
        new Propriedades().addContainer(jpSouth,btnRefresh, btnCloseTab);
        new Propriedades().addContainer(jpCenter, scrollTable, scroll, jpSouth);
        new Propriedades().addContainer(jpValor,jtfValor, jcbMoeda);
        new Propriedades().addContainer(jpFormulario, lblIcon, jtfNomeProd, jtfQtd, jpValor, jtfId, jtfNomeOrf, btnDoar);
        jpDonativos.add(jpNorth, BorderLayout.NORTH);
        jpDonativos.add(jpCenter, BorderLayout.CENTER);
        main.add(jpFormulario, BorderLayout.WEST);
        main.add(jpDonativos, BorderLayout.CENTER);
        
        btnCloseTab.addActionListener((ActionEvent ae) ->{
            if(ae.getSource() == btnCloseTab){
                tab.remove(tab.indexOfTab(title));
            }
        });
        
        btnDoar.addActionListener(this);
        btnPesquisar.addActionListener(this);
        btnRefresh.addActionListener(this);
        
        Propriedades.isTypingNumber(jtfId);           
        jtfId.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent ke){ 
            String nomeOrf = Orfanato.findById(Integer.parseInt(jtfId.getText()));
            jtfNomeOrf.setText(nomeOrf);
            jtfNomeOrf.setEditable(false);
            }
        });
            
        tabela.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
            if(tabela.getSelectedRow() >= 0){
                rowtable1 = tabela.getSelectedRow();
                ArrayList<String> dados_ = new ArrayList<>();
                for(int i = 0; i < tabela.getColumnCount(); i++){
                    dados_.add((String) model.getValueAt(tabela.getSelectedRow(), i));
                }
                jtfNomeProd.setText(dados_.get(1));
                dadosStock = dados_;
            }
            }
        });
        
        jTable.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
            if(jTable.getSelectedRow() >= 0){
                rowtable2 = jTable.getSelectedRow();
                ArrayList<String> dados_ = new ArrayList<>();
                for(int i = 0; i < jTable.getColumnCount(); i++){
                    dados_.add((String) model.getValueAt(tabela.getSelectedRow(), i));
                }
                dadosMoney = dados_;
            }
            }
        });
        
        jtfPesquisar.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent ke){
            if(ke.getKeyChar() != KeyEvent.VK_BACK_SPACE) addRowSearch(jtfPesquisar.getText());
            }
        });
        
        jtfValor.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent ke){
                if(CampoMoney.validarMoeda(jtfValor)){
                    if(!Financa.valorDisponivel(Double.parseDouble(jtfValor.getText()))){
                        JOptionPane.showMessageDialog(null, "O valor disponível é insuficiente\n"
                            + "para efectuar a doação desejada.");
                        jtfValor.setText(null);
                    }
                }
            }
        });
        Propriedades.isTypingNumber(jtfQtd);
        jtfQtd.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent ke){
                if(Propriedades.isNumber(jtfQtd.getText())){
                    if(!Stock.qtdDisponivel(jtfNomeProd.getText(), Integer.parseInt(jtfQtd.getText()))){
                        JOptionPane.showMessageDialog(null, "Quantidade disponível é insuficiente\npara "
                            + "efectuar a doação desejada.");
                        jtfQtd.setText(null);
                    }
                }
            }
        });
        
        return this;
    }
    
    private void addRow(){
        DefaultTableModel model_ = (DefaultTableModel) tabela.getModel();
        DefaultTableModel modelo  = (DefaultTableModel) jTable.getModel();
        model_.setNumRows(0);
        modelo.setNumRows(0);
        ArrayList<Stock> stock = StockController.lista();
        ArrayList<Financa> financa = FinancaController.lista();
        
        if(!stock.isEmpty()||!financa.isEmpty()){
            stock.forEach((stk) -> model_.addRow(new String[]{
                String.valueOf(stk.getId()),
                stk.getNome_prod(),
                stk.getCategoria(),
                String.valueOf(stk.getQtdActual())
            }));
            financa.forEach((fin) -> modelo.addRow(new String[]{
                String.valueOf(fin.getId()),
                fin.getValorActual()
            }));
        }else JOptionPane.showMessageDialog(this, "O stock encontra-se vazio.\nNão poderá efectuar doações.", "Stock", INFORMATION_MESSAGE, new ImageIcon("src/images/out_of_stock.png"));
    }
    
    private void addRowSearch(String str){
        DefaultTableModel model_ = (DefaultTableModel) tabela.getModel();
        model_.setNumRows(0);
        ArrayList<Stock> stock = StockController.find(str);
        
        if(!stock.isEmpty()){
            stock.forEach((stk) -> model_.addRow(new String[]{
                String.valueOf(stk.getId()),
                stk.getNome_prod(),
                stk.getCategoria(),
                String.valueOf(stk.getQtdActual()),
                String.valueOf(stk.getQtdGasta())
            }));
        }
    }
    
    private void clean(){
        jtfId.setText(null);
        jtfNomeOrf.setText(null);
        jtfNomeProd.setText(null);
        jtfPesquisar.setText(null);
        jtfQtd.setText(null);
        jtfValor.setText(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btnDoar){
            if(jtfValor.getText().isEmpty()) jtfValor.setText("0");
            if(!jtfNomeProd.getText().isEmpty()||!jtfQtd.getText().isEmpty()||!jtfValor.getText().isEmpty()&&
                !jtfId.getText().isEmpty()&&!jtfNomeOrf.getText().isEmpty()){
                if(jtfQtd.getText().isEmpty()) jtfQtd.setText("0");
                int id = DoacaoOrfController.id();
                DoacaoOrfController.create(new DoacaoOrfanato(id, jtfNomeProd.getText(), Integer.parseInt(jtfQtd.getText()),
                    CampoMoney.formattedMoney(Double.parseDouble(jtfValor.getText()), (String) jcbMoeda.getSelectedItem()), data,
                    Integer.parseInt(jtfId.getText())));
                if(!jtfNomeProd.getText().isEmpty()&&!jtfQtd.getText().isEmpty()){
                    new Stock().qtdGasta(jtfNomeProd.getText(), Integer.parseInt(jtfQtd.getText()));
                }
                new Financa().valorGasto(Double.parseDouble(jtfValor.getText()), (String) jcbMoeda.getSelectedItem());
                
                ArrayList<Log> logged = UserLogged.lista();
                int id_ = ActividadeController.id();
                Actividades act = new Actividades(id_, 0, 0, 1, Integer.parseInt(jtfQtd.getText()), "MZN 0", CampoMoney.formattedMoney(
                    Double.parseDouble(jtfValor.getText()), (String) jcbMoeda.getSelectedItem()),data, logged.get(0).getId());
                Actividades.dailyActivity(logged.get(0).getId(), act); 
                
                JOptionPane.showMessageDialog(this, "Doação efectuada com sucesso.");
                clean();
            }else JOptionPane.showMessageDialog(this, "Preencha o formulário.");
        }else if(ae.getSource() == btnPesquisar){
            addRowSearch(jtfPesquisar.getText());
        }else if(ae.getSource() == btnRefresh){
            addRow();
        }
    }
}