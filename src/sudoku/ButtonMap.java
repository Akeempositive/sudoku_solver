package sudoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ButtonMap extends JButton{
    private final int i; 
    private final int j;
    private final Interface frame;
    
    public ButtonMap(int i , int j, int value, Interface frame){
        this.frame=frame;
        this.i=i;
        this.j =j;  
        setFeatures(value);
    }

    public int getI(){
        return i;
    }
    
    public int getJ(){
        return j;
    }
    
    private void setActionListener() {
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                Integer a = Integer.parseInt(JOptionPane.showInputDialog(frame,"Enter the value in row " + i + " column " + j, "Prompt", JOptionPane.PLAIN_MESSAGE));
                setText(Integer.toString(a));
                checkSolved();
            }        
        }); 
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                char value= evt.getKeyChar();
                if(Character.isDigit(value)){
                    int res = Integer.parseInt(""+value);
                    if(res!=0&& frame.getComp()>=res){
                        setText(""+res);
                        checkSolved();
                    }
                }
            }
        });
    }
    
    void checkSolved(){
        try {
            if(new Sudoku(frame.getValues()).isSolved()){
                JOptionPane.showMessageDialog(frame, "completed");
            }
        } catch (NotASudokuFormatException ex) {
            Logger.getLogger(ButtonMap.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    private void setFeatures(int value) {
        setFont(new Font("Segoe Print", 0, 20));
        if(value!=0){
            setEnabled(false);
            setText(Integer.toString(value));
        } else {
            setForeground(Color.BLUE);
        }
        setVisible(true);
        setFocusable(true);
        setLocation(i * 50, j * 50);
        setSize(50,50);
        
        setActionListener();
    }
}