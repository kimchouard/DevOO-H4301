/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package devoo.h4301;
import devoo.h4301.controller.LecteurXml;
/**
 *
 * @author chouard
 */
public class DevOOH4301 {
    // DEFINE CONSTANTS***********
    public static final double EPSILON = 0.000001; 
    //****************************
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        LecteurXml lec = new LecteurXml();

        lec.construirePlanAPartirXML();
        lec.construireLivraisonAPartirXML();

        
    }
    
}