package formulario;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import entidades.Notas;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.w3c.dom.Node;

public class FormNotes {
   private JButton add = new JButton("add");
    private JButton del = new JButton("delete");
    private JTextArea textBox = new JTextArea();
    private JTextField title = new JTextField();
    private JTextField filter = new JTextField();
    private JButton save = new JButton("save");
    private JList list = new JList();
    
    private JLabel titleLabel = new JLabel("Texto:");
    private JLabel textLabel = new JLabel("Texto:");
    ///private JLabel label = new JLabel("Add or select existing note to proceed...");
    
    
    public void createGUI() {
        
        ArrayList<Notas> array = new ArrayList<Notas>();//mi arreglo para guardar mis notas
        DefaultListModel  model = new DefaultListModel();//agrega mi array notas a la Jlist
        
        JFrame notes = new JFrame("Notes");
        notes.setSize(960, 600);
        notes.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel left = new JPanel();
        left.setBorder(new LineBorder(Color.BLACK));
        left.setSize(320, 600);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        JPanel filterPanel = new JPanel();
        filterPanel.add(new JLabel("Nota:"));
        filter.setColumns(10);
        filterPanel.add(filter);
        filterPanel.setPreferredSize(new Dimension(280, 40));
        JPanel listPanel = new JPanel();
        list.setFixedCellWidth(260);
        listPanel.setSize(320, 470);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(275, 410));
        listPanel.add(scrollPane);
        JPanel buttonPanel = new JPanel();
        add.setPreferredSize(new Dimension(85, 25));
        buttonPanel.add(add);
        del.setPreferredSize(new Dimension(85, 25));
        buttonPanel.add(del);
        buttonPanel.setLayout(new FlowLayout());
        left.add(filterPanel);
        left.add(listPanel);
        left.add(buttonPanel);
        JPanel right = new JPanel();
        right.setLayout(null);
        right.setSize(640, 600);
        right.setLocation(320, 0);
        right.setBorder(new LineBorder(Color.BLACK));
        titleLabel.setBounds(20, 4, 50, 20);
        title.setBounds(70, 5, 520, 20);
        textLabel.setBounds(20, 4, 50, 130);
        textBox.setBorder(new LineBorder(Color.DARK_GRAY));
        textBox.setBounds(20, 80, 595, 410);
        save.setBounds(270, 535, 80, 25);
        //label.setFont(new Font("Verdana", Font.PLAIN, 22));
        //label.setBounds(100, 240, 500, 100);
       /// right.add(label);
        right.add(titleLabel);
        right.add(title);
        right.add(textLabel);
        right.add(textBox);
        right.add(save);
        notes.setLayout(null);
        notes.getContentPane().add(left);
        notes.getContentPane().add(right);
        notes.setResizable(false);
        notes.setLocationRelativeTo(null);
        notes.setVisible(true);
        
        
        //configuro el evento de seleccion del Jlist
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            if (list.getSelectedValue() != null) {
                filter.setText(list.getSelectedValue().toString()); //al seleccionar lo mando al Jtextfield filtro
                String note = list.getSelectedValue().toString();// tomo el elemento seleccionado en la variable note
                
                //con el valor de "note" busco su "texto" y lo mando al textbox
                for (int i =0; i < array.size();i++){
                    if (array.get(i).getNota().equals(note)) {                    
                        textBox.setText( array.get(i).getCuerpoTexto());
                    }
                }
                
                }
            }
        });
    
        //configuro el evento de agregar notas
        add.addActionListener(new ActionListener() {        
           public void actionPerformed (ActionEvent e){              
                String note = filter.getText(); //tomo el valor de filter
                filter.setText(null); // limpio la casilla filter 
                String cuerpo = null; 
                array.add(new Notas (note, cuerpo)); //creo un objeto tipo Notas
                title.setText(null); // limpio la casilla titulo por si hubiera algo escrito alli
                model.removeAllElements(); //limpio el vector  
                list.setModel(model); // a mi Jlist agrego el vector
                for (int i=0 ; i <array.size(); i++){
                    model.addElement(array.get(i).getNota()); //en Jlist muestro las notas grabadas
                }
           }
        });
        
        //configuro el boton borrar
        del.addActionListener(new ActionListener() {    
           public void actionPerformed (ActionEvent e){               
                String note = filter.getText();
                filter.setText(null);
                 String cuerpo = null ;
                Notas searchNota = new Notas(note, cuerpo); 
                ///int index=array.indexOf(searchNota);
                for (int i =0; i < array.size();i++){
                    if (array.get(i).getNota().equals(note)) {                    
                        array.remove( i);
                        textBox.setText( null);
                    }
                }
                              
           model.removeAllElements();
           list.setModel(model);
                                
           for (int i=0 ; i <array.size(); i++){
               model.addElement(array.get(i).getNota());
            }
          }
       }); 
        
        //configuro el evento salvar 
        save.addActionListener((var e) -> {
            String note = filter.getText();
            int bandera=0;
            if (note.isEmpty()){
                JOptionPane.showMessageDialog(null,"campo nota esta vacio");
            } else {
                
                ///String cuerpo = null ;
               
                if ( list.getModel().getSize() == 0){
                    JOptionPane.showMessageDialog(null,"Debe existir una nota grabada para agregar texto");
                } else {    
                   
                    for(int x=0;x<model.getSize();x++){ 
                        if (model.getElementAt(x).equals(note) ){
                    
                            for (int i =0; i < array.size();i++){
                        
                                if (array.get(i).getNota().equals(note)) {
                                    String texto = title.getText();
                                    title.setText(null);
                                    array.get(i).setCuerpoTexto(texto);
                                    textBox.setText( array.get(i).getCuerpoTexto());
                                    bandera=1;
                                }
                            }     
                        }else{
                            if (bandera==0 & x+1 == model.getSize()){
                                JOptionPane.showMessageDialog(null,"Debe seleccionar una de las notas grabadas");
                                bandera=1;
                            }
                         }        
                     }
                }
            }
        });      
        
        
	          
    }
}

