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
		int col = unknown.getLength()+1;
		int lign = known.getLength()+1;
		
		
		// matrice des resultats 
		int[][] g = new int[col][lign];
		
		// init de g à zero
		for(int i = 0; i<col; i++){
			for( int j = 0;j<lign; j++ ){
				g[i][j] = 0;
			}
		}
		
		// init de la 1ere ligne à inf
		for(int i =1; i< lign; i++){
			g[1][i] = Integer.MAX_VALUE;
		}
		for(int j =1; j< col; j++){
			g[j][1] = Integer.MAX_VALUE;
		}
		
		
		// matrice des distances entre unknown et known
		for(int i= 1; i<col;i++){
			for(int j=1; j<lign;j++){
				float dist = myMfCCDist.distance(unknown.getMFCC(i-1),known.getMFCC(j-1));
				g[i][j] = (int) Math.min(g[i-1][j]+w1*dist,
						             Math.min(g[i-1][j-1]+w2*dist,
						            		  g[i][j-1]+w3*dist));
			}
		}		
		
		// retourne d le score de la matrice 
		//System.out.println("n = " + col);
		//System.out.println("p = "+ lign);
		float d = (g[col-1][lign-1])/((col)+(lign));
		return d;
	}

}
