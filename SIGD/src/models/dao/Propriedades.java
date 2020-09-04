package models.dao;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author CarlosMacaneta
 */
public class Propriedades implements Serializable {
 
    private Point p = new Point();
    
    public void add(JFrame frame, Component...component){
        for(Component c: component){
            frame.add(c);
        }
    }
    
    public void addColumn(DefaultTableModel dtm, String...string){
        for(String str: string){
            dtm.addColumn(str);
        }
    }
    
    public void addContainer(Container cont, Component...component){
        for(Component c: component){
            cont.add(c);
        }
    }
    
    public void addActioListener(JFrame frame, AbstractButton...abstractbutton){
        for(AbstractButton ab: abstractbutton){
            ab.addActionListener((ActionListener) frame);
        }
    }
    
    public void addActioListener(JPanel jp, AbstractButton...abstractbutton){
        for(AbstractButton ab: abstractbutton){
            ab.addActionListener((ActionListener) jp);
        }
    }
    
    public static void closeTab(JButton btn, JTabbedPane jtp, int index){
        btn.addActionListener((ActionEvent ae) ->{
            if(ae.getSource() == btn){
                jtp.remove(index);
            }
        });
    }

    public static void setEnabled(boolean bln, Component...component){
        for (Component c: component){
            c.setEnabled(bln);
        }
    }

    public void setFocusable(boolean bln, Component...component){
        for(Component c: component){
            c.setFocusable(bln);
        }
    }
    
    public void searchLayout(JPanel group, JTextField jtf, JButton btn, JList list){
        btn.setPreferredSize(new Dimension(45, 32));
        
        java.awt.GridBagConstraints gbc;
        
        group.setLayout(new GridBagLayout());
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.ipadx = 320;
        gbc.ipady = 15;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        group.add(jtf, gbc);
        
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 7;
        gbc.ipady = 5;
        gbc.anchor = java.awt.GridBagConstraints.NORTHWEST;
        group.add(btn, gbc);
        
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.ipadx = 250;
        gbc.ipady = 100;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        group.add(list, gbc);
    }
    
    public void setBackground(Color color, Component...component){
        for(Component c: component){
            c.setBackground(color);
        }
    }
    
    public void setBorder(Border border, JComponent...jcomponent){
        for(JComponent jc: jcomponent){
            jc.setBorder(border);
        }
    }
    
    public void setSoftBevelBorder(int i, JComponent...jcomponent){
        for(JComponent jc: jcomponent){
            jc.setBorder(new SoftBevelBorder(i));
        }
    }
    
    public void setFont(String fontName, int fontType, int size, Component...component){        
        for(Component c: component){
            c.setFont(new Font(fontName, fontType, size));
        }
    }
    
    public void setEditable(boolean bln, JTextComponent...jtcomponent){
        for(JTextComponent jtc: jtcomponent){
            jtc.setEditable(bln);
        }
    }
    
    public void setForeground(Color color, Component...component){        
        for(Component c: component){
            c.setForeground(color);
        }
    }
    
    public void setLineBorder(Color color, int i, JComponent...jcomponent){
        for(JComponent jc: jcomponent){
            jc.setBorder(createLineBorder(color, i));
        }
    }
    
    public void setOpaque(boolean bln, JComponent...jcomponent){        
        for(JComponent jc: jcomponent){
            jc.setOpaque(bln); 
        }
    }
    
    public void setPreferredSize(Dimension dmnsn, Component...components){
        for(Component c: components){
            c.setPreferredSize(dmnsn);
        }
    }
    
    public void setTitledBorder(Color color1, int i1, String title, int i2, int i3, Font font, Color color2, JComponent...jcomponent){
        for(JComponent jc: jcomponent){
            jc.setBorder(BorderFactory.createTitledBorder(createLineBorder(color1, i1), title, i2, i3, font, color2));
        }
    }
    
    public void setVisible(boolean bln, Component...component){
        for(Component c: component){
            c.setVisible(bln);
        }
    }
    
    public String valorDoado(String valor){
        String[] dinheiro = valor.split(" ");
        return dinheiro[1];
    }
    
    public static boolean isNumber(String string){ 
        final String REGEX = "\\d+";
  
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(string.trim());
        
        return m.matches(); 
    }
    
    public static void addCell(PdfPTable tbl, String...string){
        com.itextpdf.text.Font timeRom = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, 
            com.itextpdf.text.Font.BOLD, new BaseColor(0,0,0));
        
        for(String str: string){
            PdfPCell cell = new PdfPCell(new Phrase(str, timeRom));
            
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            cell.setBackgroundColor(new BaseColor(188, 192, 195));
            
            tbl.addCell(cell);
        }
    }
    
    public static void isTypingNumber(JTextField...field){    
        for(JTextField jtf: field){
            jtf.addKeyListener(new KeyAdapter(){
                @Override
                public void keyReleased(KeyEvent ke){
                    if(!isNumber(jtf.getText())&&!jtf.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Só pode inserir números.");
                        jtf.setText(null);
                    }
                }
            });
        }
    }
    
    public void moveWindow(JFrame frame){
        frame.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){            
                p.x = me.getX();
                p.y = me.getY();
            }
            @Override
            public void mouseDragged(MouseEvent me){
                Point point = frame.getLocation();
                frame.setLocation(point. x + me.getX() - p.x, point.y + me.getY() - p.y);
            }
        });
    }
    
    public void closeWindow(JLabel lbl1, JLabel lbl2){
        lbl1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                Object[] options = {"Sim","Não"};
                int yesNo = JOptionPane.showOptionDialog(null, "Tem certeza de que deseja sair?",null, YES_NO_OPTION, QUESTION_MESSAGE, null,options, options[0]);
                if(yesNo == 0){
                    System.exit(0);
                }            
            }
            @Override
            public void mouseEntered(MouseEvent me){
                lbl1.setVisible(false);
                lbl2.setVisible(true);
            }    
            @Override
            public void mouseExited(MouseEvent me){
                lbl2.setVisible(false);
                lbl1.setVisible(true);
            }
        });
    }
    
    public void disposeWindow(JFrame frame, JLabel lbl1, JLabel lbl2){
        lbl1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                Object[] options = {"Sim","Não"};
                int yesNo = JOptionPane.showOptionDialog(frame, "Tem certeza de que deseja sair?",null, YES_NO_OPTION, QUESTION_MESSAGE, null,options, options[0]);
                if(yesNo == 0){
                    frame.dispose();
                }            
            }
            @Override
            public void mouseEntered(MouseEvent me){
                lbl1.setVisible(false);
                lbl2.setVisible(true);
            }    
            @Override
            public void mouseExited(MouseEvent me){
                lbl2.setVisible(false);
                lbl1.setVisible(true);
            }
        });
    }
}