import fr.enseeiht.danck.voice_analyzer.DTWHelper;
import fr.enseeiht.danck.voice_analyzer.Field;
import fr.enseeiht.danck.voice_analyzer.MFCC;

public class myDTW extends DTWHelper {

	@Override
	public float DTWDistance(Field unknown, Field known) {
		// Methode qui calcule le score de la DTW 
		// entre 2 ensembles de MFCC
		
		myMFCCdistance myMfCCDist = new myMFCCdistance();
		// contrainte locale
		int w1 = 1;
		int w2 = 1;
		int w3 = 1;
				
		// taille de la matrice DTW
		int p = unknown.getLength()+1;
		int n = known.getLength()+1;
		
		// matrice des resultats 
		int[][] g = new int[n][p];
		
		// init de g à zero
		for(int i = 0; i<n; i++){
			for( int j = 0;j<p; j++ ){
				g[i][j] = 0;
			}
		}
		
		// init de la 1ere ligne à inf
		for(int i =1; i<n; i++){
			g[1][i] = Integer.MAX_VALUE;
		}
		for(int j =1; j<p; j++){
			g[j][1] = Integer.MAX_VALUE;
		}
		
		
		// matrice des distances entre unknown et known
		for(int i= 1; i<n;i++){
			for(int j=1; j<p;j++){
				float dist = myMfCCDist.distance(unknown.getMFCC(i-1),known.getMFCC(j-1));
				g[i][j] = (int) Math.min(g[i-1][j]+w1*dist,
						             Math.min(g[i-1][j-1]+w2*dist,
						            		  g[i][j-1]+w3*dist));
			}
		}		
		
		// retourne d le score de la matrice 
		float d = (g[n][p])/((n-1)+(p-1));
		return d;
	}

}
