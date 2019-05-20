package email;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MaFenetre extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private MonPanneau p;
	
	public MaFenetre(String titre, int x, int y, int l, int h) {
		setTitle(titre);
		setBounds(x, y, l, h);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		p = new MonPanneau();
		add(p, BorderLayout.CENTER);
	}
}
