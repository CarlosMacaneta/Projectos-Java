package views;

import controllers.DoadorController;
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
import models.dao.DoadorDAO;
import models.dao.Donativo;
import models.dao.Propriedades;

/**
 *
 * @author CarlosMacaneta
 */
public class ViewDoadores extends JPanel implements ActionListener {
    
    private final boolean[] EDIT = { false, false, false, false, false, false, false};
    private final boolean[] isEditable = {false, false, false, false, false};
    private int rowtable1;
    private int rowtable2;
    private ArrayList<String> dadosDoador = new ArrayList<>();
    private ArrayList<String> dadosDonativo = new ArrayList<>();
    
    private DefaultTableModel model = new DefaultTableModel();
    private DefaultTableModel dtm = new DefaultTableModel();
    
    private JButton btnAdd = new JButton("Adicionar", new ImageIcon("src/images/add_row.png"));
    private JButton btnPesquisar = new JButton(new ImageIcon("src/images/search.png"));
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
    private ViewRegistarDoador regDoador = new ViewRegistarDoador();
    private ViewRegDoacao regDoacao = new ViewRegDoacao();
    
    public ViewDoadores(){
        this.setBackground(new Color(238, 232, 170));
        this.setLayout(new BorderLayout(400, 30));
    }
    
    public JPanel init(JTabbedPane tab, String title, JButton btnCloseTab){
        
        jpGroup.setBackground(new Color(238, 232, 170));
        new Propriedades().setBackground(new Color(45, 82, 124), btnAdd, btnPesquisar,btnRefresh, btnRemover, btnCloseTab);
        
        new Propriedades().searchLayout(jpGroup, jtfPesquisar,btnPesquisar, lista);
        
        jpSearch.setLayout(new GridLayout(2, 1, 20, 20));
        jpPainel.setLayout(new FlowLayout(2, 2, 2));
        jpNorth.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 20));
        jpSouth.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        
        this.setBorder(createLineBorder(new Color(238, 232, 170), 10));
        jpNorth.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 2), "Pesquisar doadores", 1, 0, new Font("Calibri", Font.BOLD, 16), Color.BLACK));
        scroll.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 1), "Lista de donativos do doador", 1, 0, new Font("Calibri", Font.BOLD, 16), Color.BLACK));
        scrollTable.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 1), "Lista de doadores", 1, 0, new Font("Calibri", Font.BOLD, 16), Color.BLACK));
        new Propriedades().setLineBorder(new Color(45, 82, 124),1, jtfPesquisar, jpCenter);
        new Propriedades().setSoftBevelBorder(BevelBorder.RAISED, btnAdd, btnPesquisar,btnRefresh, btnRemover, btnCloseTab);
        
        new Propriedades().setOpaque(false, jpNorth, jpCenter, jpSouth, scrollTable, scroll);
        
        new Propriedades().addColumn(model, "Nome", "Apelido", "Tipo de documento", "Nº. documento", "Genero", "Telefone", "Data de registo");
        new Propriedades().addColumn(dtm,"Id", "Nome do produto", "Categoria", "Quantidade", "Valor Doado", "Data de doação");
        
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
        
        new Propriedades().setFont("Calibri", Font.PLAIN, 14, jtfPesquisar, tabela, btnRefresh, btnAdd, btnPesquisar, btnRemover, btnCloseTab);
        
        new Propriedades().setForeground(Color.BLACK, jtfPesquisar, tabela);
        new Propriedades().setForeground(Color.WHITE, btnRefresh, btnAdd, btnPesquisar, btnRemover, btnCloseTab);
        
        lista.setVisible(false);
        
        new Propriedades().setPreferredSize(new Dimension(120, 30), btnRefresh, btnAdd, btnRemover, btnCloseTab);
        scrollTable.setPreferredSize(new Dimension(525, 370)); 
        scroll.setPreferredSize(new Dimension(525, 370)); 
        
        addRowTable1();
        
        new Propriedades().addContainer(jpNorth, jpGroup);
        new Propriedades().addContainer(jpCenter, scrollTable, scroll, jpSouth);
        new Propriedades().addContainer(jpSouth, btnRefresh, btnAdd, btnRemover, btnCloseTab);
        
        btnCloseTab.addActionListener((ActionEvent ae) ->{
            if(ae.getSource() == btnCloseTab){
                tab.remove(tab.indexOfTab(title));
            }
        });
        
        new Propriedades().addActioListener(this, btnPesquisar, btnRefresh, btnRemover, btnAdd);
        
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
                addRowTable2(dados_.get(3));
                dadosDoador = dados_;
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
    
    private void addRowTable1(){
        DefaultTableModel model_ = (DefaultTableModel) tabela.getModel();
        model_.setNumRows(0);
        ArrayList<DoadorDAO> doador = new DoadorController().lista();
        
        if(!doador.isEmpty()){
            doador.forEach((get) -> model_.addRow(new String[]{
                get.getNome(),
                get.getApelido(),
                get.getTipoDocumento(),
                get.getnDocumento(),
                get.getGenero(),
                get.getTelefone(),
                get.getData()
            }));
        }else JOptionPane.showMessageDialog(this, "Sem doadores registados");
    }

    private void search(String str){
        DefaultTableModel model_ = (DefaultTableModel) tabela.getModel();
        model_.setNumRows(0);
        ArrayList<DoadorDAO> doador = new DoadorController().search(str);

        if(!doador.isEmpty()){
            doador.forEach((get) -> model_.addRow(new String[]{
                get.getNome(),
                get.getApelido(),
                get.getTipoDocumento(),
                get.getnDocumento(),
                get.getGenero(),
                get.getTelefone(),
                get.getData()
            }));
        }else JOptionPane.showMessageDialog(this, "Doador não encontrado.");
    }

    private void addRowTable2(String id){
        DefaultTableModel model_ = (DefaultTableModel) jTable.getModel();
        model_.setNumRows(0);
        Donativo d = new Donativo();
        ArrayList<Donativo> donativo = d.findDonativo(id);
        
        if(!donativo.isEmpty()){
            donativo.forEach((donativo_) -> model_.addRow(new String[]{
                String.valueOf(donativo_.getId()),
                donativo_.getNomeProd(),
                donativo_.getCategoria(),
                String.valueOf(donativo_.getQtd()),
                donativo_.getValorDoado(),
                donativo_.getData()
            }));
        }
    }
    
    private void deleteRowTable1(){
        if(rowtable1 != -1){
            if(new DoadorController().delete(dadosDoador.get(3))){
                JOptionPane.showMessageDialog(this, "Doador removido com sucesso.");
            }else JOptionPane.showMessageDialog(this, "Ocorreu um erro ao remover.\nTente novamente.");
        }else JOptionPane.showMessageDialog(this, "Selecione um doador para remover.");
    }

    private void cleanTable2(){
        DefaultTableModel model_ = (DefaultTableModel) jTable.getModel();
        model_.setNumRows(0);
    }
    
    private void add(){
        boolean adding = true;
        while(adding){
            switch(JOptionPane.showInputDialog("[1] Primeira via\n[2] Segunda via")){
                case "1":
                    regDoador.View();
                    adding = false;
                    break;
                case "2":
                    regDoacao.View();
                    adding = false;
                    break;
                case "":
                    adding = false;
                    break;
                default: JOptionPane.showMessageDialog(this, "Opção inválida.");
                    
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == btnAdd){
            add();
        }else if(ae.getSource() == btnPesquisar){
            if(jtfPesquisar.getText().isEmpty()){
                addRowTable1();
            }else search(jtfPesquisar.getText());
            cleanTable2();
        }else if(ae.getSource() == btnRefresh){
            addRowTable1();
            cleanTable2();
        }else if(ae.getSource() == btnRemover){
            Object[] options = {"Sim","Não"};
            int yesNo = JOptionPane.showOptionDialog(this, "Tem certeza de que deseja remover ?",null, YES_NO_OPTION, QUESTION_MESSAGE, null,options, options[0]);
            if(yesNo == 0){
                deleteRowTable1();
            }else JOptionPane.showMessageDialog(this, "Cancelado.");
        }
    }
}