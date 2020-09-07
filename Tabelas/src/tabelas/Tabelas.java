package tabelas;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
/**
 *
 * @author Carlos Macaneta
 */
public class Tabelas extends JFrame {
    
    boolean[] cellEditable = {false, false, false, false, false, false, false, false}; 
    
    DefaultTableModel model = new DefaultTableModel();
    
    JTextField jtfNome = new JTextField();
    JTextField jtfCategoria = new JTextField();
    
    JTable tabela;
    
    JScrollPane scroll;
    
    JButton btnMete = new JButton("Meter");
    
    public Tabelas(){
        setSize(500, 450);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    
    public void criarTabela(){
         //definito modelo(adicionando colunas na tabela)
        model.addColumn("Nome");
        model.addColumn("Categoria");
        
        //adicionando o modelo a tabela e torna-la nao editavel
        tabela = new JTable(model) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return cellEditable[columnIndex];
            }
        };
        
        //accao do mouse quando o mouse clicar na tabela
        tabela.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
                tabelaMouseClicked(me);
            }
        });
        
        //definindo tamanho minimo e maximo das colunas
        for(int i = 0; i < tabela.getColumnCount(); i++){
            TableColumn coluna = tabela.getColumnModel().getColumn(i);
            coluna.setPreferredWidth(150);
            coluna.setMaxWidth(200);
        }
        
        //desabilitando o auto ajuste da tabela
        tabela.setAutoResizeMode(AUTO_RESIZE_OFF);
        
        //adicionando a tabela numa scroll pane
        scroll = new JScrollPane(tabela);
        
        //definindo uma borda a tabela(nao e imperioso)
        tabela.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        
        //definindo layout da tel
        setLayout(null);
        
        jtfNome.setBounds(50, 30, 200, 30);
        jtfCategoria.setBounds(50, 70, 200, 30);
        btnMete.setBounds(50, 100, 120, 30);
        
        //definindo tamanho e posicao da tabela
        scroll.setBounds(50, 150, 300, 250);
        
        add(jtfNome);
        add(btnMete);
        add(jtfCategoria);
        add(scroll);
        
        btnMete.addActionListener((ActionEvent ae) ->{
            if(ae.getSource() == btnMete){
                dadosTabela();
                clean();
            }
        });
        
        setVisible(true);
    }
    
    public void clean(){
        jtfCategoria.setText(null);
        jtfNome.setText(null);
    }
    
    public void dadosTabela(){
        //recuperando o modelo da tabela para sua manipulacao
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        
        //inserindo o numero de linhas
        modelo.setNumRows(0);
        
        //adicionado linha na tabela
        modelo.addRow(new String[]{
            jtfNome.getText(),
            jtfCategoria.getText()
        });
    }
    
    private void tabelaMouseClicked(MouseEvent me){
        //buscando os dados da tabela quando o mouse for cliclado nela
        jtfNome.setText((String) model.getValueAt(tabela.getSelectedRow(), 0));
        jtfCategoria.setText((String) model.getValueAt(tabela.getSelectedRow(), 1));
    }
   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Tabelas().criarTabela();
    }
    
}