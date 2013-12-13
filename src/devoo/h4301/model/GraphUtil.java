package devoo.h4301.model;

import devoo.h4301.outils.MyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;



/**
 * @author LouisePonton & Cedric Dupuis
 *
 */

public class GraphUtil implements Graph {
	private int nbVertices;
	private int maxArcCost;
	private int minArcCost;
	private int[][] cost; 
	private ArrayList<Itineraire> ensembleTrajets;
	private ArrayList<ArrayList<Integer>> succ; 

	/**
	 * Creates a graph such that each vertex is connected to the vertices which are in the same duration of the day , or the duration +1 and
	 * such that the cost of each arc is a calculated integer with the speed and the lenght of each arc
	 * @param n a number of vertices such that <code>n > 0</code>
	 * @param d a degree such that <code>0 < d < n</code>
	 * @param min a minimal arc cost such that <code>0 < min</code>
	 * @param max a maximal arc cost such that <code>min < max</code>
	 */
        
	public GraphUtil(Tournee ens) throws MyException
	{	
                LinkedList<Livraison> tabLivraison = ens.getLivraisons();
		nbVertices = tabLivraison.size();
		cost = new int[nbVertices][nbVertices];
		succ = new ArrayList<>();
		
		ArrayList<Integer> PlagesHoraires = getOrderedTabDuration(tabLivraison);
                enterIdSuccAndCost(PlagesHoraires,tabLivraison,ens);		
	}
        
       // CREATE AN ORDERED TAB OF DURATIONS
        private ArrayList<Integer> getOrderedTabDuration(LinkedList<Livraison> tabLivraison)
        {
            int i;
            int unePH;
            boolean find = false ; 
            ArrayList<Integer> PlagesHoraires =  new ArrayList<>();
            
            for(i=0; i<nbVertices; i++)
            {
                    // Finding all the differents durations for all the vertices
                    unePH = tabLivraison.get(i).getHoraire().getDebut().getMinutes();
                    // Finding if the duration is already in the tab "PlagesHoraires" if yes find = true
                    int j = 0;
                    while(unePH>=PlagesHoraires.get(j))
                    {
                            if(PlagesHoraires.get(j) == unePH)
                            {
                                    find = true;
                            }
                            j++;
                    }
                    // if (find = false) enter the duration in the tab in the right order
                    if(find == false)
                    {
                        PlagesHoraires.add(j,unePH);
                    }
                    find = false;
            }
            return PlagesHoraires;
        }
        
        
        // ENTERING THE VERTICES IN SUCC AND COST 
        private void enterIdSuccAndCost(ArrayList<Integer> PlagesHoraires, LinkedList<Livraison> tabLivraison, Tournee ens) throws MyException
        {
               	int PH1,PH2;
               // Insert entrepot at the beginning
                int i;
                ArrayList<Integer> suivantEntrepot = new ArrayList<>();
		for(i=0; i<nbVertices; i++)
		{
			PH1 = tabLivraison.get(i).getHoraire().getDebut().getMinutes();
			int indexPH1 = PlagesHoraires.indexOf(PH1);
			if(indexPH1 == 0)
			{
                                suivantEntrepot.add(tabLivraison.get(i).getDestination().getId());
			}
		}
                succ.add(ens.getEntrepot().getId(),suivantEntrepot);

		// For each livraison
		for(i=0; i<nbVertices; i++)
		{
			PH1 = tabLivraison.get(i).getHoraire().getDebut().getMinutes();
			int indexPH1 = PlagesHoraires.indexOf(PH1);
			int noeud1 = tabLivraison.get(i).getDestination().getId();
                        ArrayList<Integer> l = new ArrayList<>();

			int j;
			for(j=0; j<nbVertices; j++)
			{
				PH2 = tabLivraison.get(j).getHoraire().getDebut().getMinutes();
				int indexPH2 = PlagesHoraires.indexOf(PH2);
				int noeud2 = tabLivraison.get(j).getDestination().getId();
				// If duration of vertex2 is = or +1 of duration of vertex 1 -> enter in succ
				if((indexPH1 == indexPH2) || (indexPH2 == indexPH1+1))
				{
					l.add(noeud2);
                                        Itineraire iti = new Itineraire();
                                        iti.setPrevLivraisonId(noeud1);
                                        iti.setNextLivraisonId(noeud2);
                                        LinkedList<Troncon> ensembleTroncons = getPath(noeud1,noeud2,ens); 
                                        cost[noeud1][noeud2] = calculCost(ensembleTroncons);
                                        iti.setEnsembleTroncons(ensembleTroncons);
                                        ensembleTrajets.add(iti);
                                        setMinMax(i,j,cost[noeud1][noeud2]);
				}
			}
			
			// Insert entrepot at the end
			if(indexPH1 == PlagesHoraires.size()-1) 
			{
				l.add(ens.getEntrepot().getId());
                                Itineraire iti = new Itineraire();
                                iti.setPrevLivraisonId(noeud1);
                                iti.setNextLivraisonId(ens.getEntrepot().getId());
                                LinkedList<Troncon> ensembleTroncons = getPath(noeud1,ens.getEntrepot().getId(),ens); 
                                cost[noeud1][ens.getEntrepot().getId()] = calculCost(ensembleTroncons);
                                iti.setEnsembleTroncons(ensembleTroncons);
                                ensembleTrajets.add(iti);
                                setMinMax(-1,-1,cost[noeud1][ens.getEntrepot().getId()]);
			}
			
			succ.add(noeud1,l);
		}
        }
        
        private int calculCost(LinkedList<Troncon> itineraire)
        {
            double cout = 0;
            int Isize = itineraire.size();
            int i;
            for(i=0;i<Isize;i++){
                cout = cout + itineraire.get(i).getDuree();
            }
            return (int) cout;
        }
	
	
	//Calculating cost between pt1 and pt2 using the sortest path of Djikstra 
	public LinkedList<Troncon> getPath(int pt1, int pt2, Tournee ens) throws MyException
	{
		Plan P = ens.getPlan();
		ArrayList<Noeud> tabnoeuds = P.getNoeuds();
		int Psize = tabnoeuds.size();
		
		ArrayList<Integer> vu = new ArrayList<>();
		ArrayList<Integer> notvu = new ArrayList<>();
		Map<Integer, Integer> previous = new HashMap<>();
		Map<Integer, Integer> duree = new HashMap<>();
		
                // Remplissage duree avec -1 sauf pour pt1
                int i ;
		for(i=0;i<Psize;i++)
		{
			duree.put(tabnoeuds.get(i).getId(), -1); // Ou maxArcCost ? CF ALEXIS
		}
		
		duree.put(pt1,0);
		notvu.add(pt1);
		
		while(notvu.isEmpty() == false)
		{
			int newNoeud = getNoeudProche(notvu,tabnoeuds,duree);
			vu.add(newNoeud);
			notvu.remove(newNoeud);
			findDureeMini(newNoeud, ens, notvu,duree,previous);
		}
		
                //Find path
		LinkedList<Integer> path = new LinkedList<>();
                if (previous.get(pt2) == null) 
                {
                  return null;
                }
                path.add(pt2);
                while (previous.get(pt2) != null) 
                {
                  pt2 = previous.get(pt2);
                  path.add(pt2);
                }
                
                LinkedList<Troncon> pathFinal = transformNoeudTroncon(path,ens);
                return pathFinal;
	}
        
        // Transform group of Noeuds into group of Troncons
        private LinkedList<Troncon> transformNoeudTroncon(LinkedList<Integer> path,Tournee ens) throws MyException
        {   
                ArrayList<Troncon> tabtroncons = ens.getPlan().getTroncons();
                int tronSize = tabtroncons.size();
                LinkedList<Troncon> pathFinal = new LinkedList<>();
                Troncon inter;
                boolean findTron = false;
                int pathSize = path.size();
                int cptPath;
                for(cptPath=0;cptPath<pathSize-1;cptPath++)
                {
                    int debut = path.get(cptPath); 
                    int fin = path.get(cptPath+1);
                    
                    int cptTron = 0;
                    while((cptTron<tronSize)||(findTron==false))
                    {
                        if((tabtroncons.get(cptTron).getOrigine().getId()==debut ) 
                                &&(tabtroncons.get(cptTron).getDestination().getId()== fin)) 
                        {
                            pathFinal.add(tabtroncons.get(cptTron));
                            findTron = true ;
                        }
                        cptTron++;
                    }
                    if ((cptTron == tronSize)&&(findTron==false))
                    {
                        throw new MyException("Troncon non trouvé dans le plan pour les points" + debut + "et "+ fin);//EXCEPTION
                    }
                    findTron = false;
                }  
                return pathFinal;      
        }
	
	private void findDureeMini(int newNoeud, Tournee ens, ArrayList<Integer> notvu, Map<Integer, Integer> duree, Map<Integer, Integer> previous) 
	{
		ArrayList<Integer> voisins = getVoisins(newNoeud,ens);
		int voisinsSize = voisins.size() ;
		
                int i;
		for (i=0;i<voisinsSize;i++) 
		{
		  if (getShortestDuration(voisins.get(i),duree) > getShortestDuration(newNoeud,duree)+ getDuration(newNoeud, voisins.get(i), ens)) 
		  {
			duree.put(voisins.get(i), getShortestDuration(newNoeud,duree)+ getDuration(newNoeud, voisins.get(i), ens));
			previous.put(voisins.get(i), newNoeud);
			notvu.add(voisins.get(i));
		  }
		}
	}
	
	//Cherche les voisins d'un point
	private ArrayList<Integer> getVoisins(int noeud, Tournee ens)
	{
		ArrayList<Troncon> tabTroncons = ens.getPlan().getTroncons();
                ArrayList<Integer> voisins = new ArrayList<>();
                int Tsize = tabTroncons.size();
                int i =0;
                while(i<Tsize)
                {
		 if(tabTroncons.get(i).getOrigine().getId() == noeud)
                 {
                     voisins.add(tabTroncons.get(i).getDestination().getId());
                 }
                i++;
                }
                return voisins;
	}
	
	//De tous les noeuds dans not vu, on retourne le noeud de celui qui a la plus petite distance jusque la source
	private int getNoeudProche(ArrayList<Integer> notvu, ArrayList<Noeud> tabnoeuds, Map<Integer, Integer> duree)
	{
		int mini = -1;
		int Psize = tabnoeuds.size();
                int i ;
		for(i=0;i<Psize;i++)
		{
			if(mini == -1)
			{
				mini = tabnoeuds.get(i).getId();
			}
			else if(getShortestDuration(tabnoeuds.get(i).getId(),duree) < getShortestDuration(mini,duree))
			{
				mini = tabnoeuds.get(i).getId();
			}
		}
		return mini;
	}
         
    //Cherche la durée entre un point et un autre qui sont VOISINS s'ils ne sont pas voisins , retourne -1
	private int getDuration(int noeud1, int noeud2, Tournee ens)
	{
                ArrayList<Troncon> tabTroncons = ens.getPlan().getTroncons();
                int Tsize = tabTroncons.size();
                int i =0;
                while(i<Tsize)
                {
		 if((tabTroncons.get(i).getOrigine().getId() == noeud1)
                     && (tabTroncons.get(i).getDestination().getId() == noeud2))
                 {
                     return (int) tabTroncons.get(i).getDuree();
                 }
                i++;
                }
                return -1;
	}
	
	// Retourne la durée d'un noeud
	private int getShortestDuration(int pt, Map<Integer, Integer> duree ) {
    int d = duree.get(pt);
    if (d == -1) {
      return Integer.MAX_VALUE;
    } else {
      return d;
    }
  }
	
	
        @Override
	public int getMaxArcCost() {
		return maxArcCost;
	}

        @Override
	public int getMinArcCost() {
		return minArcCost;
	}

        @Override
	public int getNbVertices() {
		return nbVertices;
	}

        @Override
	public int[][] getCost(){
		return cost;
	}

        @Override
	public int[] getSucc(int i) throws ArrayIndexOutOfBoundsException{
		if ((i<0) || (i>=nbVertices))
			throw new ArrayIndexOutOfBoundsException();
		int[] tab = new int[succ.get(i).size()];
		for(int j=0;j<tab.length;j++){
			tab[j] = succ.get(i).get(j);
		}
		return tab;
	}


        @Override
	public int getNbSucc(int i) throws ArrayIndexOutOfBoundsException {
		if ((i<0) || (i>=nbVertices))
			throw new ArrayIndexOutOfBoundsException();
		return succ.get(i).size();
	}
        
                                                    
        // Defining min and max 
        private void setMinMax (int i, int j, int newCout)
        {
                if((i==0)&&(j==0)){
                    maxArcCost = newCout;
                    minArcCost = newCout;
                }
                else{
                    if(newCout>maxArcCost){
                        maxArcCost = newCout;
                    }
                    if(newCout<minArcCost){
                        minArcCost = newCout;
                    }
                }
        }


}