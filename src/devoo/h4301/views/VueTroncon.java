/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.views;

import devoo.h4301.controller.ControleurPlan;
import devoo.h4301.controller.ControleurPrincipal;
import devoo.h4301.model.Troncon;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;

/**
 *
 * @author chouard
 */
public class VueTroncon extends javax.swing.JPanel {
    
    protected Troncon troncon;
    protected VuePlan vuePlan;
    
    /**
     * Creates new form VueTroncon
     */
    public VueTroncon() {
        initialize();
    }
    
    /**
     * Constructeur VueTroncon avec création du troncon
     */
    public VueTroncon(Troncon troncon, VuePlan vuePlan) {
        this.setTroncon(troncon);
        this.vuePlan = vuePlan;
        initialize();
    }
    
    protected void initialize() {
        initComponents();

        this.setOpaque(false);
        this.setVisible(true);
        this.setBackground(Color.BLACK); 
    }

    public Troncon getTroncon() {
        return troncon;
    }

    public void setTroncon(Troncon troncon) {
        this.troncon = troncon;
    }

    public VuePlan getVuePlan() {
        return vuePlan;
    }

    public void setVuePlan(VuePlan vuePlan) {
        this.vuePlan = vuePlan;
    }
   
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        int xDepart, yDepart, xArrivee, yArrivee;
        
        if (this.getWidth() <= ControleurPrincipal.xMinTroncon) {
            xDepart = ControleurPrincipal.xMinTroncon/2;
            xArrivee = ControleurPrincipal.xMinTroncon/2;
        } else if (this.getTroncon().getOrigine().getX() > this.getTroncon().getDestination().getX()) {
            xDepart = this.getWidth() - ControleurPrincipal.diamNoeud/2;
            xArrivee = ControleurPrincipal.diamNoeud/2;
        } else {
            xDepart = ControleurPrincipal.diamNoeud/2;
            xArrivee = this.getWidth() - ControleurPrincipal.diamNoeud/2;
        }
        
        if (this.getHeight() <= ControleurPrincipal.yMinTroncon) {
            yDepart = ControleurPrincipal.yMinTroncon/2;
            yArrivee = ControleurPrincipal.yMinTroncon/2;
        } else if (this.getTroncon().getOrigine().getY() > this.getTroncon().getDestination().getY()) {
            yDepart = this.getHeight() - ControleurPrincipal.diamNoeud/2;
            yArrivee = ControleurPrincipal.diamNoeud/2;
        } else {
            yDepart = ControleurPrincipal.diamNoeud/2;
            yArrivee = this.getHeight() - ControleurPrincipal.diamNoeud/2;
        }
        
        dispLines(g, xArrivee, xDepart, yArrivee, yDepart);
    }
    
    /**
     * Définit l'affichage du troncon
     */
    protected void dispLines(Graphics g, int xArrivee, int xDepart, int yArrivee, int yDepart) {
        Graphics2D g2D = (Graphics2D) g;
        
        //Delta pour les bordures
        double delta = ControleurPrincipal.largeurTraitTroncon + ControleurPrincipal.contourTroncon;
        
        // x2 car 2 troncons: aller / retour
        g2D.setStroke(new BasicStroke(ControleurPrincipal.largeurTraitTroncon*2));
        g2D.setPaint(ControleurPrincipal.blancMaps);
        g2D.draw(new Line2D.Float(
                xDepart,
                yDepart,
                xArrivee,
                yArrivee));
        
        g2D.setStroke(new BasicStroke(ControleurPrincipal.contourTroncon));
        g2D.setPaint(ControleurPrincipal.grisFonceMaps);
        g2D.draw(getNewLine(delta, xArrivee, xDepart, yArrivee, yDepart));
        
        g2D.setStroke(new BasicStroke(ControleurPrincipal.contourTroncon));
        g2D.setPaint(ControleurPrincipal.grisFonceMaps);
        g2D.draw(getNewLine(-delta, xArrivee, xDepart, yArrivee, yDepart));
    }
    
    /**
     * Retourne une nouvelle ligne fonction des positions envoyé en paramètre.
     * @return Line2D
     */
    protected Line2D getNewLine(double delta, int xArrivee, int xDepart, int yArrivee, int yDepart) {
        // Calcul de l'angle vis à vis de la verticale
        double x = xArrivee - xDepart;
        double y = yArrivee - yDepart;
        double alpha = Math.atan(x / y);
        
        int dX = (int) (delta * Math.cos(alpha));
        int dY = (int) (delta * Math.sin(alpha));
        
        return new Line2D.Float(
                xDepart - dX,
                yDepart + dY,
                xArrivee - dX,
                yArrivee + dY);
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
