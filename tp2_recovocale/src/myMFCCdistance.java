import fr.enseeiht.danck.voice_analyzer.MFCC;
import fr.enseeiht.danck.voice_analyzer.MFCCHelper;

public class myMFCCdistance extends MFCCHelper {

	@Override
	public float distance(MFCC mfcc1, MFCC mfcc2) {
		// calcule la distance entre 2 MFCC
		//float n = Math.sqrt(Math.sum(mfcc2 - mfcc1)^2);
	    float dist = 0;
	    for (int i = 0; i < mfcc1.getLength(); i++) {
	       dist += Math.pow(Math.abs(mfcc1.getCoef(i) - mfcc2.getCoef(i)), 2);
	    }
	    return (float) Math.sqrt(dist);
	    
	}

	@Override
	public float norm(MFCC mfcc) {
		// retourne la valeur de mesure de la MFCC (coef d'indice 0 dans la MFCC) 
		// cette mesure permet de determiner s'il s'agit d'un mot ou d'un silence
		return (mfcc.getCoef(0));
	}

	@Override
	public MFCC unnoise(MFCC mfcc, MFCC noise) {
		// supprime le bruit de la MFCC passee en parametre
		// soustrait chaque coef du bruit a chaque coef du la MFCC 
		// passee en parametre
		// supprime le bruit de la MFCC passee en parametre
        // soustrait chaque coef du bruit a chaque coef du la MFCC
        // passee en parametre

        float tab[] = new float[mfcc.getLength()];
        for (int i =0;i<mfcc.getLength(); i++){
            tab[i] = mfcc.getCoef(i) - noise.getCoef(i);
        }

        return new MFCC(tab, mfcc.getSignal());
	}

}
