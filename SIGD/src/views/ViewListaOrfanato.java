package views;

import controllers.DoacaoOrfController;
import controllers.OrfanatoController;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import models.dao.DoacaoOrfanato;
import models.dao.Orfanato;
import models.dao.Propriedades;

/**
 *
 * @author Carlos Macaneta
 */
public class ViewListaOrfanato extends JPanel implements ActionListener {

    private final boolean[] EDIT = { false, false, false, false, false, false, false, false};
    private final boolean[] isEditable = {false, false, false, false, false};
    private ArrayList<String> dadosOrfanato = new ArrayList<>();
    private ArrayList<String> dadosDonativo = new ArrayList<>();
    
    private DefaultTableModel model = new DefaultTableModel();
    private DefaultTableModel dtm = new DefaultTableModel();
    
    private JButton btnAdd = new JButton("Adicionar", new ImageIcon("src/images/add_row.png"));
    private JButton btnPesquisar = new JButton(new ImageIcon("src/images/search.png"));
    private JButton btnEdit = new JButton("Editar", new ImageIcon("src/images/edit_row.png"));
    private JButton btnRemover = new JButton("Remover", new ImageIcon("src/images/delete_row.png"));
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
    
    private EditarOrfanato editOrf = new EditarOrfanato();
    
    public ViewListaOrfanato() {
        this.setBackground(new Color(238, 232, 170));
        this.setLayout(new BorderLayout(400, 30));
    }
    
    public JPanel init(JTabbedPane tab, String title, JButton btnCloseTab){
        
        jpGroup.setBackground(new Color(238, 232, 170));
        new Propriedades().setBackground(new Color(45, 82, 124), btnAdd, btnEdit, btnPesquisar, btnRemover, btnRefresh, btnCloseTab);
        
        new Propriedades().searchLayout(jpGroup, jtfPesquisar,btnPesquisar, lista);
        
        jpSearch.setLayout(new GridLayout(2, 1, 20, 20));
        jpPainel.setLayout(new FlowLayout(2, 2, 2));
        jpNorth.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 20));
        jpSouth.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        
        this.setBorder(createLineBorder(new Color(238, 232, 170), 10));
        jpNorth.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 2), "Pesquisar orfanatos", 1, 0, new Font("Calibri", Font.BOLD, 16), Color.BLACK));
        scroll.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 1), "Donativos ofericidos ao orfanato", 1, 0, new Font("Calibri", Font.BOLD, 16), Color.BLACK));
        scrollTable.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 1), "Lista de Orfanatos", 1, 0, new Font("Calibri", Font.BOLD, 16), Color.BLACK));
        new Propriedades().setLineBorder(new Color(45, 82, 124),1, jtfPesquisar, jpCenter);
        new Propriedades().setSoftBevelBorder(BevelBorder.RAISED, btnAdd, btnEdit, btnPesquisar, btnRemover, btnRefresh, btnCloseTab);

        lista.setVisible(false);
        new Propriedades().setOpaque(false, jpNorth, jpCenter, jpSouth, scrollTable, scroll);
        
        new Propriedades().addColumn(model,"Id", "Nome", "Endereco", "Telefone", "Email", "Nº. de pessoas acolhidas", 
            "Nº. de crianças", "Nº. de jovens", "Nº. de adultos", "Data de regisito");
        new Propriedades().addColumn(dtm, "Id", "Nome do produto", "Quantidade", "Valor recebido", "Data da doação recebida");
        
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
            coluna.setPreferredWidth(175);
            coluna.setMaxWidth(500);
        }
        jTable.setAutoResizeMode(AUTO_RESIZE_OFF);
        scroll.setViewportView(jTable);
        
        new Propriedades().setFont("Calibri", Font.PLAIN, 14, jtfPesquisar, tabela, btnAdd, btnEdit, btnPesquisar, btnRemover, btnRefresh, btnCloseTab);
        
        new Propriedades().setForeground(Color.BLACK, jtfPesquisar, tabela);
        new Propriedades().setForeground(Color.WHITE, btnAdd, btnEdit, btnPesquisar, btnRemover, btnRefresh, btnCloseTab);
        
        new Propriedades().setPreferredSize(new Dimension(120, 30), btnAdd, btnEdit, btnRemover, btnRefresh, btnCloseTab);
        scrollTable.setPreferredSize(new Dimension(525, 370)); 
        scroll.setPreferredSize(new Dimension(525, 370)); 
        
        addRow();
        
        new Propriedades().addContainer(jpNorth, jpGroup);
        new Propriedades().addContainer(jpCenter, scrollTable, scroll, jpSouth);
        new Propriedades().addContainer(jpSouth,btnRefresh, btnAdd, btnEdit, btnRemover, btnCloseTab);
        
        btnCloseTab.addActionListener((ActionEvent ae) ->{
            if(ae.getSource() == btnCloseTab){
                tab.remove(tab.indexOfTab(title));
            }
        });
        
        new Propriedades().addActioListener(this, btnPesquisar, btnRefresh, btnRemover, btnAdd, btnEdit);
        
        this.add(jpNorth, BorderLayout.NORTH);
        this.add(jpCenter, BorderLayout.CENTER);
        
        tabela.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
            if(tabela.getSelectedRow() >= 0){
                ArrayList<String> dados_ = new ArrayList<>();
                for(int i = 0; i < tabela.getColumnCount(); i++){
                    dados_.add((String) model.getValueAt(tabela.getSelectedRow(), i));
                }
                addRowTable2(Integer.parseInt(dados_.get(0)));
                dadosOrfanato = dados_;
            }
            }
        });
        
        jTable.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
            if(jTable.getSelectedRow() >= 0){
                ArrayList<String> dados_ = new ArrayList<>();
                for(int i = 0; i < jTable.getColumnCount(); i++){
                    dados_.add((String) model.getValueAt(tabela.getSelectedRow(), i));
                }
                dadosDonativo = dados_;
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
        model_.setNumRows(0);
        ArrayList<Orfanato> dados = OrfanatoController.lista();
        if(!dados.isEmpty()){
            dados.forEach((oft) -> model_.addRow(new String[]{
                String.valueOf(oft.getId()),
                oft.getNome(),
                oft.getEndereco(),
                oft.getTel(),
                oft.getEmail(),
                String.valueOf(oft.getnPessoas()),
                String.valueOf(oft.getnCriancas()),
                String.valueOf(oft.getnJovens()),
                String.valueOf(oft.getnAdultos()),
                oft.getDataReg()
            }));
        }else  JOptionPane.showMessageDialog(this, "Sem orfanatos registados.");
        dadosOrfanato.clear();
    }

    private void search(String str){
        DefaultTableModel model_ = (DefaultTableModel) tabela.getModel();
        model_.setNumRows(0);
        ArrayList<Orfanato> dados = OrfanatoController.search(str);
        if(!dados.isEmpty()){
            dados.forEach((oft) -> model_.addRow(new String[]{
                String.valueOf(oft.getId()),
                oft.getNome(),
                oft.getEndereco(),
                oft.getTel(),
                oft.getEmail(),
                String.valueOf(oft.getnPessoas()),
                String.valueOf(oft.getnCriancas()),
                String.valueOf(oft.getnJovens()),
                String.valueOf(oft.getnAdultos()),
                oft.getDataReg()
            }));
        }else  JOptionPane.showMessageDialog(this, "Orfanto não encontrado.");
        dadosOrfanato.clear();
    }

    private void addRowTable2(int id){
        DefaultTableModel model_ = (DefaultTableModel) jTable.getModel();
        model_.setNumRows(0);
        ArrayList<DoacaoOrfanato> doft = DoacaoOrfController.readById(id);
        if(!doft.isEmpty()){
            doft.forEach((dados) -> model_.addRow(new String[]{
                String.valueOf(dados.getId()),
                dados.getNomeProd(),
                String.valueOf(dados.getQtd()),
                dados.getValorDoado(),
                dados.getData()
            }));
        }else JOptionPane.showMessageDialog(this, "Nenhuma doação foi feita para este orfanato.");
    }

    private void deleteRow(){
        if(tabela.getSelectedRow() != -1){
            OrfanatoController.delete(Integer.parseInt(dadosOrfanato.get(0)));
        }else JOptionPane.showMessageDialog(this, "Selecione um orfanato para remover");
        dadosOrfanato.clear();
    }

    private void cleanTable(){
        DefaultTableModel model_ = (DefaultTableModel) jTable.getModel();
        model_.setNumRows(0);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btnAdd){
            new ViewRegOrfanato().view();
        }else if(ae.getSource() == btnEdit){
            editOrf.setVisible(true);
        }else if(ae.getSource() == btnPesquisar){
            cleanTable();
            search(jtfPesquisar.getText());
        }else if(ae.getSource() == btnRefresh){
            addRow();
            cleanTable();
        }else if(ae.getSource() == btnRemover){
            deleteRow();
        }
    }
}