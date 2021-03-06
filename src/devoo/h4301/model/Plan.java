/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devoo.h4301.model;

import devoo.h4301.outils.MyException;
import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Classe Plan représentant une zone urbaine, contenant une liste de noeud(point
 * ou adresse de la zone) et une liste de tronçon (rue empreintable).
 *
 * @author Leslie Breynat
 */
public class Plan {

    /**
     * Liste de noeud du plan
     */
    protected ArrayList<Noeud> noeuds;

    /**
     * Liste de tronçon du plan
     */
    protected ArrayList<Troncon> troncons;
    
    /**
     * Taille zone de travail
     */
    private int minX, minY, maxX, maxY;

    /**
     * Constructeur initialisant la liste de noeud et la liste de tronçon.
     */
    public Plan() {
        noeuds = new ArrayList<>();
        troncons = new ArrayList<>();

    }

    /**
     * Getter sur la liste de noeud du plan
     *
     * @return noeuds du plan
     */
    public ArrayList<Noeud> getNoeuds() {
        return noeuds;
    }

    /**
     * Ajout d'un noeud à la liste de noeud
     *
     * @param noeud noeud a ajouter
     * @throws java.lang.Exception
     */
    public void addNoeud(Noeud noeud) throws Exception {
        Boolean trouvee = false;
        for (int i = 0; i < this.noeuds.size() && !trouvee; i++) {
            if (this.noeuds.get(i).getId().equals(noeud.id)) {
                trouvee = true;
            }
        }
        
        if (trouvee) {
            throw new Exception("Noeud avec un id identique existe deja");
        } else {
            if (noeuds.isEmpty()) {
                this.minX = noeud.getX();
                this.minY = noeud.getY();
                this.maxX = noeud.getX();
                this.maxY = noeud.getY();
            } else {
                if (noeud.getX() > this.maxX) {
                    this.maxX = noeud.getX();
                }
                if (noeud.getY() > this.maxY) {
                    this.maxY = noeud.getY();
                }
                if (noeud.getX() < this.minX) {
                    this.minX = noeud.getX();
                }
                if (noeud.getY() < this.minY) {
                    this.minY = noeud.getY();
                }
            }

            this.noeuds.add(noeud);
        }
    }

    /**
     * Retourner la position minimale du plan en X
     * @return minX
     */
    public int getMinX() {
        return minX;
    }

    /**
     * Retourner la position minimale du plan en Y
     * @return minY
     */
    public int getMinY() {
        return minY;
    }

    /**
     * Retourner la position maximale du plan en X
     * @return maxX
     */
    public int getMaxX() {
        return maxX;
    }

    /**
     * Retourner la position maximale du plan en Y
     * @return maxY
     */
    public int getMaxY() {
        return maxY;
    }

    /**
     * Enlèvement d'un noeud de la liste de noeuds du plan
     *
     * @param idNoeud id du noeud a retirer du plan
     * @throws Exception souleverer par la recherche du noeud
     */
    public void removeNoeud(Integer idNoeud) throws Exception {
        Noeud noeud = this.getNoeudById(idNoeud);
        this.noeuds.remove(noeud);
    }

    /**
     * Getter sur la liste de tronçon
     *
     * @return la liste de tronçon du plan
     */
    public ArrayList<Troncon> getTroncons() {
        return troncons;
    }

    /**
     * Ajout d'un tronçon à la liste de tronçons du plan
     *
     * @param troncon a ajouter à la liste
     */
    public void addTroncon(Troncon troncon) {
        this.troncons.add(troncon);
    }

    /**
     * Getter sur le noeud dont l'id est donné.
     *
     * @param id du noeud que l'on souhaite récupérer
     * @return le noeud recherché
     * @throws Exception levée si le noeud n'existe pas dans le plan
     */
    public Noeud getNoeudById(Integer id) throws Exception {

        Noeud noeudCherche = null;
        Boolean find = false;
        ArrayList<Noeud> list = this.getNoeuds();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                noeudCherche = this.noeuds.get(i);
                find = true;
            }
        }

        if (find.booleanValue() == false) {
            MyException e = new MyException("Appel à un noeud inexistant");
            throw e;
        }
        return noeudCherche;
    }

    /**
     * Constructeur à partir d'un noeudDOMXML. Parcours les attributs et les
     * noeuds DOMXML sous-jacents pour remplir l'objet plan appelant. Récupère
     * la liste des noeuds et fait appel à chaque fois au constructeur DomXML
     * puis reparcoure cette liste pour en extraire les tronçons.
     *
     * @param racine noeud DOMXML parcouru
     * @throws Exception de synthaxe ou de modèle levées lors de la lecteur du
     * fichierXML
     */
    public void construireAPartirDomXML(Element racine) throws Exception {

// Traitement des noeuds
        NodeList list = racine.getElementsByTagName("Noeud");

        for (int i = 0; i < list.getLength(); i++) {
            Element noeudElem = (Element) list.item(i);
            Noeud noeudNouveau = new Noeud();
            noeudNouveau.construireAPartirDomXML(noeudElem);
            this.addNoeud(noeudNouveau);
        }

        String tag = "TronconSortant";
        for (int i = 0; i < list.getLength(); i++) {
            Element noeudElem = (Element) list.item(i);
            NodeList listeTroncon = noeudElem.getElementsByTagName(tag);

            Integer idOrigine = Integer.parseInt(noeudElem.getAttribute("id"));
            Noeud origine = getNoeudById(idOrigine);

            for (int j = 0; j < listeTroncon.getLength(); j++) {
                Element tronconElem = (Element) listeTroncon.item(j);
                Troncon tronconNouveau = new Troncon();

                tronconNouveau.setOrigine(origine);
                tronconNouveau.construireAPartirDomXML(tronconElem, this);

                this.addTroncon(tronconNouveau);
            }
        }

    }
}
