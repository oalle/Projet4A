package email;

import java.awt.BorderLayout;

import javax.swing.JFrame;
/**
 * <b>MaFentre est la classe représentant notre Interface.</b>
 * Un Fenetre est caractérisé par les informations suivantes :
 * <ul>
 * <li>Le panneau.</li>
 * </ul>
 * @see MonPanneau
 * @author nathan besse, victor chantrel
 * @version 1.0
 */
public class MaFenetre extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private MonPanneau p;
	/**
	 * Crée la fenetre.
	 * @param titre nom de la fenetre
	 * @param x la position x
	 * @param y la position y
	 * @param l la largeur de la fenetre
	 * @param h la hauteur de la fenetre
	 */
	public MaFenetre(String titre, int x, int y, int l, int h) {
		setTitle(titre);
		setBounds(x, y, l, h);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		p = new MonPanneau();
		add(p, BorderLayout.CENTER);
	}
}
