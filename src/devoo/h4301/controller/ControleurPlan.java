/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301.controller;

import devoo.h4301.model.Itineraire;
import devoo.h4301.model.Livraison;
import devoo.h4301.model.Noeud;
import devoo.h4301.model.PlageHoraire;
import devoo.h4301.model.Plan;
import devoo.h4301.model.Tournee;
import devoo.h4301.model.Troncon;
import devoo.h4301.views.FenetrePrincipale;
import devoo.h4301.views.VueNoeud;
import devoo.h4301.views.VuePlan;
import devoo.h4301.views.VueTroncon;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;

/**
 *
 * @author chouard
 */
public class ControleurPlan {
    private ControleurPrincipal controleurPrincipal;
    
    private VuePlan vuePlan;

    public ControleurPlan(ControleurPrincipal controleurPrincipal) {
        this.vuePlan = new VuePlan(this);
        this.controleurPrincipal = controleurPrincipal;
    }
    
    //--------------------------------
    //  Public functions
    
    /**
     * Lie la vue plan avec le scroll pannel de la fenetre principale.
     * @param  JScrollPane panneauPlan sur lequel le lier
     */
    public void afficherPlan(JScrollPane panneauPlan) {
        panneauPlan.setViewportView(vuePlan);
        this.vuePlan.updateUI();
    }
    
    /**
     * Reconstruit entièrement la vue a partir de la Tournée. Reset le plan en premier .
     * @param  Tournee
     */
    public void rafraichirVuePlan(Tournee tournee) {
        this.resetPlan();
        this.vuePlan.setTournee(tournee);
        Plan plan =  tournee.getPlan();
        
        ArrayList<Noeud> noeuds = plan.getNoeuds();
        for (Noeud n : noeuds) {
            this.vuePlan.ajouterNoeud(n);
        }
        
        ArrayList<Livraison> livs =  controleurPrincipal.getControleurGraph().getLivOrdered();
//        LinkedList<Livraison> livs =  tournee.getLivraisons();
        int start = 0;
        for (Livraison l : livs) {
            //Si c'est l'entrepot
            if (start == 0) {
                this.vuePlan.ajouterEntrepot(l);
                start++;
            } else {
                this.vuePlan.ajouterLiv(l);
            }
        }
        
        ArrayList<Itineraire> itineraires = controleurPrincipal.getControleurGraph().getItineraires();
        for (Itineraire i : itineraires) {
            PlageHoraire ph = i.getPrevLivraison().getHoraire();
            for (Troncon t : i.getEnsembleTroncons()) {
                this.vuePlan.ajouterItineraire(t, ph);
            }
        }
        
        ArrayList<Troncon> troncons = plan.getTroncons();
        for (Troncon t : troncons) {
            this.vuePlan.ajouterTroncon(t);
        }
        
        this.afficherPlan(this.controleurPrincipal.getPanneauPlan());
    }
    
    /**
     * Calcul le scale du plan pour avoir une remplissage optimal lors de l'affichage.
     */
    public void scaleAutoVuePlan() {
        Plan p =  vuePlan.getTournee().getPlan();
        
        double planWidth = p.getMaxX() - p.getMinX() + 2*ControleurPrincipal.padding + ControleurPrincipal.diamNoeud;
        double planHeight = p.getMaxY() - p.getMinY() + 2*ControleurPrincipal.padding + ControleurPrincipal.diamNoeud;
        double panneauWidth = this.controleurPrincipal.getPanneauPlan().getWidth();
        double panneauHeight = this.controleurPrincipal.getPanneauPlan().getHeight();
        double scaleX = panneauWidth / planWidth;
        double scaleY = panneauHeight / planHeight;
        
        if (scaleX < scaleY) {
            vuePlan.setZoomScale(scaleX);
        }
        else {
            vuePlan.setZoomScale(scaleY);
        }
    }
    
    /**
     * Réinitialise le plan (sous traité a VuePlan).
     */
    public void resetPlan() {
        this.vuePlan.reset();
    }
    
    /**
     * Met à jour le zoom du plan
     * @param  pourcent Le poucentage à appliquer au zoom actuel.
     */
    public void zoomChange(double pourcent) {
        vuePlan.setZoomScale(vuePlan.getZoomScale() * pourcent);
        this.rafraichirVuePlan(Tournee.getInstance());
    }
    
    /**
     * Demande au controleur principale d'afficher les détails d'une livraison
     * Binde le click sur une livraison depuis le plan.
     * @param  Livraion la livraison a afficher
     */
    public void selectLivraison(Livraison liv) {
        this.controleurPrincipal.selectLivraison(liv);
    }
    
    /**
     * Revenir à la liste des livraisons (délègue au controleur principal).
     */
    public void unSelectLivraisons() {
        this.controleurPrincipal.unSelectLivraisons();
    }
    
    /**
     * Demande au controleur principale d'afficher la création d'une nouvelle livraison
     * Binde le click sur un noeud vide depuis le plan.
     * @param  Noeud ou créer la nouvelle livraison
     */
    public void createLiv(Noeud noeud) throws Exception {
        this.controleurPrincipal.createLiv(noeud);
    }
    
    //--------------------------------
    //  Geter - Seter

    public VuePlan getVuePlan() {
        return vuePlan;
    }

    public void setTournee(Tournee tournee) {
        this.vuePlan.setTournee(tournee);
        this.vuePlan.setBackground(ControleurPrincipal.grisFond);
    }
}
