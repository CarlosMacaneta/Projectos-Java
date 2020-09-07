package views;

import controllers.EstController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import models.Estudante;

/**
 *
 * @author CarlosMacaneta
 */
public class VisualizarMM extends JFrame implements ActionListener{
    
    private ArrayList<String> est = new ArrayList<>();
    
    private boolean[] cellEditable = {false, false, false}; 
    
    private DefaultTableModel model = new DefaultTableModel();
    
    private JButton btnRefresh = new JButton("Refresh");
    private JButton btnRemover = new JButton("Remover");
    private JButton btnEditar = new JButton("Editar");
    private JButton pesquisar = new JButton("Pesquisar");
    
    private int abaixo  = EstController.lista().size() - new Estudante().acimaMedia().size();
    private JTextArea info = new JTextArea();
    
    private JTable tabela;
    
    private JTextField pes = new JTextField("Nome ou id");
    
    private JScrollPane scroll;

    private JPanel centro = new JPanel();
    private JPanel pesquisa = new JPanel();
    private JPanel buttons = new JPanel();

    public VisualizarMM() {
        setSize(800, 550);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(238, 232, 170)));
    }
    
    public void init(){
        Container main = this.getContentPane();
        main.setLayout(new BorderLayout(10, 50));
        
        main.setBackground(new Color(238, 232, 170));
        centro.setBackground(new Color(238, 232, 170));
        pesquisa.setBackground(new Color(238, 232, 170));
        buttons.setBackground(new Color(238, 232, 170));
        
        model.addColumn("Código do estudante");
        model.addColumn("Nome do estudante");
        model.addColumn("Média");
        
        tabela = new JTable(model) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return cellEditable[columnIndex];
            }
        };
        
        tabela.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
                if(tabela.getSelectedRow() >= 0){
                    ArrayList<String> dados_ = new ArrayList<>();
                    for(int i = 0; i < tabela.getColumnCount(); i++){
                        dados_.add((String) model.getValueAt(tabela.getSelectedRow(), i));
                    }
                    est = dados_;
                }
            }
        });
        
        for(int i = 0; i < tabela.getColumnCount(); i++){
            TableColumn coluna = tabela.getColumnModel().getColumn(i);
            coluna.setPreferredWidth(150);
            coluna.setMaxWidth(200);
        }
        
        tabela.setAutoResizeMode(AUTO_RESIZE_OFF);
        
        scroll = new JScrollPane(tabela);
        
        scroll.setPreferredSize(new Dimension(450, 300));
        pes.setPreferredSize(new Dimension(200, 30));
        pesquisar.setPreferredSize(new Dimension(120, 30));
        btnEditar.setPreferredSize(new Dimension(120, 30));
        btnRefresh.setPreferredSize(new Dimension(120, 30));
        btnRemover.setPreferredSize(new Dimension(120, 30));
        info.setPreferredSize(new Dimension(300, 300));
        
        info.setOpaque(false);
        scroll.setOpaque(false);
        
        info.setText("Total de estudantes: "+EstController.lista().size()+
            "\nMédia das médias: "+new Estudante().media()+ "\nModa das médias: "+new Estudante().moda(EstController.lista())+
            "\nTotal de est. acima da média: "+new Estudante().acimaMedia().size()+
            "\nTotal de est. abaixo da média: "+abaixo);
        
        addRow();
        
        pesquisa.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        pesquisa.add(pes);
        pesquisa.add(pesquisar);
        
        buttons.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        buttons.add(btnRefresh);
        
        pesquisa.setBorder(new TitledBorder(new LineBorder(new Color(18, 30, 49), 2), "Pesquisar", 1, 0, new Font("Calibri",Font.BOLD, 14), Color.BLACK));
        scroll.setBorder(new TitledBorder(new LineBorder(new Color(18, 30, 49), 1), "Estudantes acima da média", 1, 0, new Font("Calibri",Font.BOLD, 14), Color.BLACK));
       
        centro.add(info);
        centro.add(scroll);
        centro.add(buttons);
        
        main.add(pesquisa, BorderLayout.NORTH);
        main.add(centro, BorderLayout.CENTER);
        
        pesquisar.addActionListener(this);
        btnRefresh.addActionListener(this);
        
        setVisible(true);
    }
    
    private void addRow(){
        DefaultTableModel model_ = (DefaultTableModel) tabela.getModel();
        model_.setNumRows(0);
        ArrayList<Estudante> est_ = new Estudante().acimaMedia();
        
        if(!est_.isEmpty()){
            est_.forEach((aln)-> model_.addRow(new String[]{ 
                String.valueOf(aln.getId()),
                aln.getNome(),
                String.valueOf(aln.getMedia())
            }));
        }else JOptionPane.showMessageDialog(this, "A lista de estudantes acima\n da media encontra-se vazia.");
    }
    
    private void find(String str){
        DefaultTableModel model_ = (DefaultTableModel) tabela.getModel();
        model_.setNumRows(0);
        ArrayList<Estudante> est_ = new Estudante().pequisa(str);
        
        if(!est_.isEmpty()){
            est_.forEach((aln)-> model_.addRow(new String[]{ 
                String.valueOf(aln.getId()),
                aln.getNome(),
                String.valueOf(aln.getMedia())
            }));
        }else JOptionPane.showMessageDialog(this, "A lista de estudantes acima\n da media encontra-se vazia.");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btnRefresh){
            addRow();
        }else if(ae.getSource() == pesquisar){
             if(!pes.getText().isEmpty()&&!pes.getText().equals("Nome ou id")){
                find(pes.getText());
            }else if(pes.getText().equals("Nome ou id") || pes.getText().isEmpty()) addRow();
        }
    }   
}