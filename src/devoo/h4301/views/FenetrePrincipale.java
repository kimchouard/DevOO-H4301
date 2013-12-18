/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.views;

import devoo.h4301.controller.*;
import devoo.h4301.outils.LogOutputStream;
import devoo.h4301.outils.MyException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;

/**
 *
 * @author chouard
 */
public class FenetrePrincipale extends javax.swing.JFrame {

    public ControleurPrincipal controleurPrincipal;
    
    /**
     * Creates new form FenetrePrincipale
     */
    public FenetrePrincipale() {
        initComponents();
        PrintStream printStream = new PrintStream(new LogOutputStream(log));
        System.setOut(printStream);
        System.setErr(printStream);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pGauche = new javax.swing.JScrollPane();
        chargerPlan = new javax.swing.JButton();
        chargerLiv = new javax.swing.JButton();
        redo = new javax.swing.JButton();
        undo = new javax.swing.JButton();
        pDroit = new javax.swing.JScrollPane();
        debug = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        log = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pGauche.setBackground(new java.awt.Color(231, 228, 219));
        pGauche.setAutoscrolls(true);

        chargerPlan.setText("Charger Plan");
        chargerPlan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clickChargerPlan(evt);
            }
        });

        chargerLiv.setText("Charger Livraisons");
        chargerLiv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clickChargerLivraisons(evt);
            }
        });

        redo.setText("Redo");
        redo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoActionPerformed(evt);
            }
        });

        undo.setText("Undo");
        undo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoActionPerformed(evt);
            }
        });

        pDroit.setAutoscrolls(true);

        debug.setText("DEBUG");
        debug.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clickDebug(evt);
            }
        });

        log.setColumns(20);
        log.setRows(5);
        jScrollPane1.setViewportView(log);

        jLabel1.setText("Log");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(chargerPlan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chargerLiv)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(debug)
                .addGap(45, 45, 45)
                .addComponent(undo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(redo)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pGauche, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pDroit, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chargerPlan)
                    .addComponent(chargerLiv)
                    .addComponent(redo)
                    .addComponent(undo)
                    .addComponent(debug))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pDroit, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pGauche, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clickChargerPlan(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clickChargerPlan
        // Lunch load of the map from the file URL asked
        this.controleurPrincipal.chargerPlan("");
       
    }//GEN-LAST:event_clickChargerPlan

    private void clickChargerLivraisons(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clickChargerLivraisons
        try {
            this.controleurPrincipal.chargerLiv("");
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }//GEN-LAST:event_clickChargerLivraisons

    private void clickDebug(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clickDebug
        // TODO add your handling code here:
        try {
            this.controleurPrincipal.chargerPlan("/Users/chouard/plan10x10.xml");
            this.controleurPrincipal.chargerLiv("/Users/chouard/livraison10x10-1.xml");
        } catch (MyException ex) {
            System.out.print(ex.toString());
        }
    }//GEN-LAST:event_clickDebug

    private void undoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoActionPerformed
        this.controleurPrincipal.undo();
    }//GEN-LAST:event_undoActionPerformed

    private void redoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoActionPerformed
        this.controleurPrincipal.redo();
    }//GEN-LAST:event_redoActionPerformed

    public void updateCommandState (Boolean possibleUndo, Boolean possibleRedo) {
        redo.setEnabled(possibleRedo);
        undo.setEnabled(possibleUndo);
    }
            
    public JScrollPane getpDroit() {
        return pDroit;
    }

    public JScrollPane getpGauche() {
        return pGauche;
    }
    
    public void log(String log, boolean error) {
        this.log.append("\n"+log);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chargerLiv;
    private javax.swing.JButton chargerPlan;
    private javax.swing.JButton debug;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea log;
    private javax.swing.JScrollPane pDroit;
    private javax.swing.JScrollPane pGauche;
    private javax.swing.JButton redo;
    private javax.swing.JButton undo;
    // End of variables declaration//GEN-END:variables
}
