package email;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bouton implements ActionListener {

	private MonPanneau p;
	
	private String nom;
	
	public Bouton(MonPanneau p, String nom) {
		this.p = p;
		this.nom = nom;
	}
	
	@Override
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
	}

}
