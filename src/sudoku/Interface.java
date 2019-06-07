/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
/**
 *
 * @author POSITIVITY
 */
public class Interface extends JFrame {
    //private Integer p =23;
    private String level="Easy";
    private ButtonMap buttons[][] ;
    private int comp=3;
    /**
     * Creates new form Interface
     */
    public Interface() {
        setLocation(0,0);
        setSize(1366, 768);
        initComponents();
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        hard = new javax.swing.JRadioButton();
        easy = new javax.swing.JRadioButton();
        medium = new javax.swing.JRadioButton();
        start = new javax.swing.JButton();
        view = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        hard.setBackground(new java.awt.Color(102, 0, 102));
        buttonGroup1.add(hard);
        hard.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        hard.setForeground(new java.awt.Color(153, 153, 0));
        hard.setText("Hard");
        hard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hardActionPerformed(evt);
            }
        });

        easy.setBackground(new java.awt.Color(102, 0, 102));
        buttonGroup1.add(easy);
        easy.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        easy.setForeground(new java.awt.Color(153, 153, 0));
        easy.setText("Easy");
        easy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                easyActionPerformed(evt);
            }
        });

        medium.setBackground(new java.awt.Color(102, 0, 102));
        buttonGroup1.add(medium);
        medium.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        medium.setForeground(new java.awt.Color(153, 153, 0));
        medium.setText("Medium");
        medium.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mediumActionPerformed(evt);
            }
        });

        start.setText("Start");
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });

        view.setText("View");
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(easy, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(medium, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(hard, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(257, 257, 257)
                        .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(view)))
                .addContainerGap(215, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(350, Short.MAX_VALUE)
                .addComponent(view)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(easy)
                    .addComponent(medium)
                    .addComponent(hard))
                .addGap(34, 34, 34)
                .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void easyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_easyActionPerformed
        getLevel((JRadioButton)evt.getSource());
    }//GEN-LAST:event_easyActionPerformed

    private void mediumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mediumActionPerformed
        getLevel((JRadioButton)evt.getSource());
    }//GEN-LAST:event_mediumActionPerformed

    private void hardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hardActionPerformed
        getLevel((JRadioButton)evt.getSource());
    }//GEN-LAST:event_hardActionPerformed

    private void removeAll(ButtonMap[][]buttons){
        for(JButton [] b : buttons){
            for(JButton c: b){
                remove(c);
            }
        }
    }
    
    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
        set=false;
        if(buttons!=null){
            removeAll(buttons);
            buttons=null;
        }
        String a= JOptionPane.showInputDialog(this,"Enter the Sudoku Details", "Prompt" ,JOptionPane.PLAIN_MESSAGE);
        comp=(int) Math.sqrt(a.length());
       Sudoku s=null;
        try {
            s = new Sudoku(Solver.setValues(a));
        } catch (Exception ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        setValues(s.getValues());
        Solver2 s1= new Solver2(s);
        s1.solve();
        try {
            sol=s1.solvedSudoku();
        } catch (NotASudokuFormatException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_startActionPerformed
    private Sudoku sol;
    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
        setValues(sol.getValues());
    }//GEN-LAST:event_viewActionPerformed
    private boolean set =false;
    private void setButtons(Integer values[][]){
        comp=values.length;
        if(buttons==null){
            buttons= new ButtonMap[comp][comp];
        }
        int  i=0;
        while(i<comp){
            int j=0;
            while(j<comp){
                if(!set){
                    buttons[i][j]= new ButtonMap(i+1, j+1, values[i][j], this);
                    add(buttons[i][j]);
                } else {
                    buttons[i][j].setText(Integer.toString(values[i][j]));
                }
                j++;    
            }
            i++;
        }
        set=true;
    }
   
    private void getLevel(JRadioButton  j){
        level= j.getText();
    } 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Interface().setVisible(true);
            }
        });
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton easy;
    private javax.swing.JRadioButton hard;
    private javax.swing.JRadioButton medium;
    private javax.swing.JButton start;
    private javax.swing.JButton view;
    // End of variables declaration//GEN-END:variables

    //@todo
    
    private void setValues(Integer[][] values) {
       setButtons(values);
       repaint();
    }
    
    public Integer[][] getValues(){
        Integer [][] values = new Integer[buttons.length][buttons.length];
        for(int i=0; i<buttons.length;i++){
            for(int j=0; j<buttons.length;j++){
                Integer value ;
                try{
                    value =Integer.parseInt(buttons[i][j].getText());
                }catch(NumberFormatException ex){
                    value=0;
                }
                values[i][j]=value;
            }
        }
        return values;
    }
   
    public int getComp(){
        return this.comp;
    }

}
