package email;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * <b>MonBoutton est la classe représentant les boutons dans notre interface.</b>

 * Un bouton est caractérisé par les informations suivantes :
 * <ul>
 * <li>Le panneau où sont mis les boutons.</li>
 * <li>Le mot qui définira l'action a effectuer lors de l'appui d'un des boutons.</li>
 * </ul>

 * @see MonPanneau
 * @author nathan besse, victor chantrel
 * @version 1.0
 */
public class Bouton implements ActionListener {

	private MonPanneau p;
	
	private String nom;
	/**
         * Initialise le bouton avec ses différents attributs
         * @param p Panneau où est le bouton
         * @param nom le nom du bouton
         */
	public Bouton(MonPanneau p, String nom) {
		this.p = p;
		this.nom = nom;
	}
	
	@Override
        /**Différencie les différentes actions, suivant pour passer au mail suivant
         * précédent pour passer au mail précédent et son pour lancer la voix qui lit le mail choisit.
         * @param e L'évenement déclenché
         */
	public void actionPerformed(ActionEvent e) {
		if(this.nom == "suivant" && p.i!=p.imax )
                {
			p.i++;
                        p.repaint();
                }
		if(this.nom == "precedent" && p.i != 0)
                {
			p.i--;
                        p.repaint();
                }
                if(this.nom=="son")
                    p.ecoute();
	}

}
