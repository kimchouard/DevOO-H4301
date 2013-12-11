/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.views;

import devoo.h4301.model.Noeud;
import devoo.h4301.model.Troncon;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 *
 * @author chouard
 */
public class VuePlan extends javax.swing.JPanel {
    private ArrayList<VueNoeud> vueNoeuds = new ArrayList();
    private ArrayList<VueTroncon> vueTroncons = new ArrayList();
    
    private int minX, minY, maxX, maxY;
    protected double zoomScale = 1.0;
    public final int padding = 20;
    
    // Constantes Couleur
    public static final Color rougeMaps = new Color(217, 95, 87);
    public static final Color jauneMaps = new Color(248, 228, 122);
    public static final Color blancMaps = new Color(255, 255, 255);
    
    // Constantes Noeuds
    public static final int diamNoeud = 15;
    
    // Constantes Troncons
    public static final int largeurTraitTroncon = 4;
    public static final int yMinTroncon = 4;
    public static final int xMinTroncon = 4;

    /**
     * Creates new form VuePlan
     */
    public VuePlan() {
        this.maxX = this.padding;
        this.maxY = this.padding;
        
        initialize();
    }
    
    private void initialize() {
        initComponents();

        this.setOpaque(false);
        this.setVisible(true); 
    }
    
    public void reset() {
        this.removeAll();
        this.updateUI();
        this.vueNoeuds.clear();
        this.vueTroncons.clear();
    }
    
    public void ajouterNoeud(Noeud noeud) {
        VueNoeud v = new VueNoeud(noeud);
        this.placerNoeud(v);
        
        if (vueNoeuds.isEmpty()) {
            this.setMinX(v.getX());
            this.setMinY(v.getY());
            this.setMaxX(v.getX());
            this.setMaxY(v.getY());
        }
        else {
            if (v.getX() > this.maxX) {
                this.setMaxX(v.getX());
            }
            if (v.getY() > this.maxY) {
                this.setMaxY(v.getY());
            }
            if (v.getX() < this.minX) {
                this.setMinX(v.getX());
            }
            if (v.getY() < this.minY) {
                this.setMinY(v.getY());
            }
        }
        
        this.vueNoeuds.add(v);
        this.add(v);
        v.setVisible(true);
    }
    
    public void placerNoeud(VueNoeud vueNoeud) {
        Noeud noeud = vueNoeud.getNoeud();
        vueNoeud.setSize(diamNoeud, diamNoeud);
        
        int xLocation = this.scaledCoordinateHorizontal(noeud.getX()) - vueNoeud.getWidth()/2;
        int yLocation = this.scaledCoordinateVertical(noeud.getY()) - vueNoeud.getHeight()/2;
        vueNoeud.setLocation(xLocation, yLocation);
    }

    public void ajouterTroncon(Troncon troncon) {
        VueTroncon v = new VueTroncon(troncon);
        this.placerTroncon(v);
        this.vueTroncons.add(v);
        this.add(v);
    }
    
    public void placerTroncon(VueTroncon vueTroncon) {
        Troncon troncon = vueTroncon.getTroncon();
        
        int larg = Math.abs(troncon.getDestination().getX() - troncon.getOrigine().getX());
        int haut = Math.abs(troncon.getDestination().getY() - troncon.getOrigine().getY());
        if (larg < xMinTroncon) {
            larg = xMinTroncon;
        }
        if (haut < yMinTroncon) {
            haut = yMinTroncon;
        }
        
        int x = Math.min(troncon.getOrigine().getX(), troncon.getDestination().getX());
        int y = Math.min(troncon.getOrigine().getY(), troncon.getDestination().getY());
        
        vueTroncon.setSize(larg, haut);
        vueTroncon.setLocation(this.scaledCoordinateHorizontal(x) - diamNoeud/2, this.scaledCoordinateVertical(y) - diamNoeud/2);
    }
    
    public ArrayList<VueNoeud> getVueNoeuds() {
        return vueNoeuds;
    }
    
    private void setMinX(int minX) {
        this.minX = minX;
        this.updateVuePlanFrame();
    }

    private void setMinY(int minY) {
        this.minY = minY;
        this.updateVuePlanFrame();
    }

    private void setMaxX(int maxX) {
        this.maxX = maxX;
        this.updateVuePlanFrame();
    }

    private void setMaxY(int maxY) {
        this.maxY = maxY;
        this.updateVuePlanFrame();
    }
    
    public double getZoomScale() {
        return zoomScale;
    }

    public void setZoomScale(double zoomScale) {
        this.zoomScale = zoomScale;
        this.updateVuePlanFrame();
    }
    
    private void updateVuePlanFrame() {
        Dimension dimension = new Dimension(this.scaledSize(maxX) + padding*2, this.scaledSize(maxY) + padding*2);
        this.setPreferredSize(dimension);
    }
    
    private int scaledCoordinateVertical(int coordonate) {
        return (int)(this.zoomScale * (coordonate - minY)) + padding;
    }
    
    private int scaledCoordinateHorizontal(int coordonate) {
        return (int)(this.zoomScale * (coordonate - minX)) + padding;
    }
    
    private int scaledSize(int size) {
        return (int)(this.zoomScale * size);
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
