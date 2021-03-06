/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.views;

import devoo.h4301.controller.ControleurPrincipal;
import devoo.h4301.model.Itineraire;
import devoo.h4301.model.Troncon;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import static java.lang.Math.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author chouard
 */
public class VueTronconItineraire extends VueTroncon {
    protected Troncon troncon;
    protected LinkedList<VuePlageHoraire> vuePlageHoraires;
    
    /**
     * Creates new form VueItineraire
     */
    public VueTronconItineraire(Troncon troncon, VuePlageHoraire vuePlageHoraire, VuePlan vuePlan) {
        this.setTroncon(troncon);
        this.vuePlageHoraires = new LinkedList<VuePlageHoraire>();
        this.addVuePlageHoraire(vuePlageHoraire);
        this.vuePlan = vuePlan;
        initialize();
    }
    
    protected void initialize() {
        initComponents();

        this.setOpaque(false);
        this.setVisible(true);
        this.setBackground(Color.BLACK); 
    }

    public LinkedList<VuePlageHoraire> getVuePlageHoraire() {
        return vuePlageHoraires;
    }

    public void addVuePlageHoraire(VuePlageHoraire vuePlageHoraire) {
        this.vuePlageHoraires.add(vuePlageHoraire);
        this.updateUI();
    }
    
    public Troncon getTroncon() {
        return troncon;
    }

    public void setTroncon(Troncon troncon) {
        this.troncon = troncon;
    }
    
    /**
     * Définit l'affichage de l'itinéraire
     * Prend en charge les multi plages horaires.
     */
    @Override
    protected void dispLines(Graphics g, int xArrivee, int xDepart, int yArrivee, int yDepart) {
        Graphics2D g2D = (Graphics2D) g;
        
        //Delta pour les bordures
        double delta = ControleurPrincipal.largeurTraitTroncon;
        
        if ( (yArrivee - yDepart) < 0 ) {
            delta = -delta;
        }
        
        double x = abs(xDepart - xArrivee);
        double y = abs(yDepart - yArrivee);
        float longTroncon = (float) (Math.sqrt(x*x + y*y));
        float longDashes = longTroncon/this.vuePlageHoraires.size();
        for(int i = 0; i < this.vuePlageHoraires.size(); i++) {
            float deltaStart = longDashes*i;
            float deltaAfter = longDashes*(this.vuePlageHoraires.size()-1-i);
            float dash[] = {longDashes, };
            g2D.setStroke(new BasicStroke(
                    ControleurPrincipal.largeurTraitTroncon,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_ROUND,
                    10.0f, dash, deltaStart
            ));
            g2D.setPaint(this.vuePlageHoraires.get(i).getPlageColor());
            g2D.draw(getNewLine(delta, xArrivee, xDepart, yArrivee, yDepart));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
