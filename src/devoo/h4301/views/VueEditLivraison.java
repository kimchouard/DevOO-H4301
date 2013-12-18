/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.views;

import devoo.h4301.controller.ControleurLivraison;
import devoo.h4301.model.Noeud;
import devoo.h4301.model.PlageHoraire;
import java.util.ArrayList;

/**
 *
 * @author Mimi
 */
public class VueEditLivraison extends javax.swing.JPanel {
     private ControleurLivraison controleurLivraison;
     private Noeud noeud;
    
    /**
     * Creates new form VueEditLivraison
     */
    public VueEditLivraison(ControleurLivraison controleurLivraison, Noeud noeud) {
        this.setControleurLivraison(controleurLivraison);
        this.setNoeud(noeud);
        initComponents();
    }
    
    public VueEditLivraison(ControleurLivraison controleurLivraison) {
        this.setControleurLivraison(controleurLivraison);
        initComponents();
    }

    public void setControleurLivraison(ControleurLivraison controleurLivraison) {
        this.controleurLivraison = controleurLivraison;
    }

    public void setNoeud(Noeud noeud) {
        this.noeud = noeud;
    }
    
    
    public void setPlagesHoraires(ArrayList<PlageHoraire> ph )
    {
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nomClient = new javax.swing.JLabel();
        typeColis = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        plageHoraire = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nom = new javax.swing.JTextField();
        colis = new javax.swing.JTextField();
        buttonConfirm = new javax.swing.JButton();
        boutonAnnuler = new javax.swing.JButton();
        ph1 = new javax.swing.JComboBox();
        ph2 = new javax.swing.JComboBox();

        setPreferredSize(new java.awt.Dimension(280, 100));

        nomClient.setText("Nom");

        typeColis.setText("Colis");

        plageHoraire.setText("Plage horaire : de");

        jLabel5.setText("à");

        nom.setText("nom");
        nom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomActionPerformed(evt);
            }
        });

        colis.setText("type de colis");
        colis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colisActionPerformed(evt);
            }
        });

        buttonConfirm.setText("Confirmer");
        buttonConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmActionPerformed(evt);
            }
        });

        boutonAnnuler.setText("Annuler");
        boutonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonAnnulerActionPerformed(evt);
            }
        });

        ph1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ph1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ph1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ph1ActionPerformed(evt);
            }
        });

        ph2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(plageHoraire)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ph1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ph2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nomClient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(typeColis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(colis, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(buttonConfirm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(boutonAnnuler)))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nom)
                            .addComponent(nomClient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(typeColis, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(colis, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(plageHoraire)
                            .addComponent(ph1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(ph2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonConfirm)
                    .addComponent(boutonAnnuler))
                .addGap(3, 3, 3)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        nom.getAccessibleContext().setAccessibleName("nomClient");
        colis.getAccessibleContext().setAccessibleName("colis");
    }// </editor-fold>//GEN-END:initComponents

    private void nomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_nomActionPerformed

    private void colisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_colisActionPerformed

    private void buttonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmActionPerformed
        // TODO add your handling code here:
      //this.controleurLivraison.ajoutLiv(this.liv);
        //try {
          this.controleurLivraison.creationLivraison(this.noeud );
        //  }
      //catch(Exception e){}*/
    }//GEN-LAST:event_buttonConfirmActionPerformed

    private void boutonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonAnnulerActionPerformed
        this.controleurLivraison.afficherListLivraisonInitiale();
    }//GEN-LAST:event_boutonAnnulerActionPerformed

    private void ph1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ph1ActionPerformed
        // TODO add your handling code here    
    }//GEN-LAST:event_ph1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boutonAnnuler;
    private javax.swing.JButton buttonConfirm;
    private javax.swing.JTextField colis;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField nom;
    private javax.swing.JLabel nomClient;
    private javax.swing.JComboBox ph1;
    private javax.swing.JComboBox ph2;
    private javax.swing.JLabel plageHoraire;
    private javax.swing.JLabel typeColis;
    // End of variables declaration//GEN-END:variables
}
