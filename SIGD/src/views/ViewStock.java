package views;

import controllers.FinancaController;
import controllers.StockController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import models.dao.Financa;
import models.dao.Propriedades;
import models.dao.Stock;

/**
 *
 * @author CarlosMacaneta
 */
public class ViewStock extends JPanel implements ActionListener {
    
    private final boolean[] EDIT = { false, false, false, false, false, false, false};
    private final boolean[] isEditable = {false, false, false, false, false};
    private int rowtable1;
    private int rowtable2;
    private ArrayList<String> dadosMoney = new ArrayList<>();
    private ArrayList<String> dadosStock = new ArrayList<>();
    
    private DefaultTableModel model = new DefaultTableModel();
    private DefaultTableModel dtm = new DefaultTableModel();
    
    private JButton btnPesquisar = new JButton(new ImageIcon("src/images/search.png"));
    private JButton btnRemover = new JButton("Remover", new ImageIcon("src/images/delete_prod.png"));
    private JButton btnRefresh = new JButton("Actualizar", new ImageIcon("src/images/refresh.png"));
    
    private JList lista = new JList();
    
    private JPanel jpGroup = new JPanel();
    private JPanel jpSearch = new JPanel();
    private JPanel jpPainel = new JPanel();
    private JPanel jpNorth = new JPanel();
    private JPanel jpCenter = new JPanel();
    private JPanel jpSouth = new JPanel();
    
    private JScrollPane scrollTable = new JScrollPane();
    private JScrollPane scroll = new JScrollPane();
    
    private JTable tabela;
    private JTable jTable;
    
    private JTextField jtfPesquisar = new JTextField(1);
    
    public ViewStock() {
        this.setBackground(new Color(238, 232, 170));
        this.setLayout(new BorderLayout(400, 30));
    }
    
    public JPanel init(JTabbedPane tab, String title, JButton btnCloseTab){
        
        jpGroup.setBackground(new Color(238, 232, 170));
        new Propriedades().setBackground(new Color(45, 82, 124), btnPesquisar, btnRemover, btnRefresh, btnCloseTab);
        
        new Propriedades().searchLayout(jpGroup, jtfPesquisar,btnPesquisar, lista);
        
        jpSearch.setLayout(new GridLayout(2, 1, 20, 20));
        jpPainel.setLayout(new FlowLayout(2, 2, 2));
        jpNorth.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 20));
        jpSouth.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        
        this.setBorder(createLineBorder(new Color(238, 232, 170), 10));
        jpNorth.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 2), "Pesquisar produto no stock", 1, 0, new Font("Calibri", Font.BOLD, 16), Color.BLACK));
        scroll.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 1), "Valor em caixa", 1, 0, new Font("Calibri", Font.BOLD, 16), Color.BLACK));
        scrollTable.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 1), "Lista de produtos", 1, 0, new Font("Calibri", Font.BOLD, 16), Color.BLACK));
        new Propriedades().setLineBorder(new Color(45, 82, 124),1, jtfPesquisar, jpCenter);
        new Propriedades().setSoftBevelBorder(BevelBorder.RAISED, btnPesquisar, btnRemover, btnRefresh, btnCloseTab);
        
        new Propriedades().setOpaque(false, jpNorth, jpCenter, jpSouth, scrollTable, scroll);
        
        new Propriedades().addColumn(model,"Id", "Nome do produto", "Categoria", "Quantidade existente", "Quantidade gasta");
        new Propriedades().addColumn(dtm, "Id", "Valor existente", "Valor gasto");
        
        tabela = new JTable(model){
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return EDIT[columnIndex];
            }
        };
        
        for(int i = 0; i < tabela.getColumnCount(); i++){
            TableColumn coluna = tabela.getColumnModel().getColumn(i);
            coluna.setPreferredWidth(200);
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
            coluna.setPreferredWidth(130);
            coluna.setMaxWidth(500);
        }
        jTable.setAutoResizeMode(AUTO_RESIZE_OFF);
        scroll.setViewportView(jTable);
        
        new Propriedades().setFont("Calibri", Font.PLAIN, 14, jtfPesquisar, tabela,btnPesquisar, btnRemover, btnRefresh, btnCloseTab);
        
        new Propriedades().setForeground(Color.BLACK, jtfPesquisar, tabela);
        new Propriedades().setForeground(Color.WHITE, btnPesquisar, btnRemover, btnRefresh, btnCloseTab);
        
        lista.setVisible(false);
        
        new Propriedades().setPreferredSize(new Dimension(120, 30),btnRemover, btnRefresh, btnCloseTab);
        scrollTable.setPreferredSize(new Dimension(600, 370)); 
        scroll.setPreferredSize(new Dimension(400, 370)); 
        
        addRow();
        
        new Propriedades().addContainer(jpNorth, jpGroup);
        new Propriedades().addContainer(jpCenter, scrollTable, scroll, jpSouth);
        new Propriedades().addContainer(jpSouth,btnRefresh,btnRemover, btnCloseTab);
        
        btnCloseTab.addActionListener((ActionEvent ae) ->{
            if(ae.getSource() == btnCloseTab){
                tab.remove(tab.indexOfTab(title));
            }
        });
        
        new Propriedades().addActioListener(this, btnPesquisar, btnRefresh, btnRemover);
        
        this.add(jpNorth, BorderLayout.NORTH);
        this.add(jpCenter, BorderLayout.CENTER);
        
        tabela.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
            if(tabela.getSelectedRow() >= 0){
                rowtable1 = tabela.getSelectedRow();
                ArrayList<String> dados_ = new ArrayList<>();
                for(int i = 0; i < tabela.getColumnCount(); i++){
                    dados_.add((String) model.getValueAt(tabela.getSelectedRow(), i));
                }
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
            if(ke.getKeyChar() != KeyEvent.VK_BACK_SPACE)
                search(jtfPesquisar.getText());
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
                String.valueOf(stk.getQtdActual()),
                String.valueOf(stk.getQtdGasta())
            }));
            financa.forEach((fin) -> modelo.addRow(new String[]{
                String.valueOf(fin.getId()),
                fin.getValorActual(),
                fin.getValorGasto()
            }));
        }else JOptionPane.showMessageDialog(this, "O stock encontra-se vazio.", "Stock", INFORMATION_MESSAGE, new ImageIcon("src/images/out_of_stock.png"));
    }
    
    private void search(String str){
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
    
    private void deleteRow(){
        Object[] options = {"Sim","Não"};
        int yesNo = JOptionPane.showOptionDialog(null, "Tem certeza de que deseja remover ?",null, YES_NO_OPTION, QUESTION_MESSAGE, null,options, options[0]);
        if(yesNo == 0){
            if(tabela.getSelectedRow() != -1){
                StockController.delete(Integer.parseInt(dadosStock.get(0)));
            }else if(jTable.getSelectedRow() != -1){
                FinancaController.delete(Integer.parseInt(dadosMoney.get(0)));
            }else JOptionPane.showMessageDialog(this, "Selecione um produto/valor para\n remover do seu stock.");
        }else JOptionPane.showMessageDialog(this, "Cancelado.");
        addRow();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btnRefresh){
            addRow();
        }else if(ae.getSource() == btnPesquisar){
            search(jtfPesquisar.getText());
        }else if(ae.getSource() == btnRemover){
            deleteRow();
        }
    }
}