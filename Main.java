package email;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Main {

	public static void main(String[] args) {
		Toolkit tk  = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		String titre = "Reading Email";
		int x = 0;
		int y = 0;
		double l = dim.getWidth();
		double h = dim.getHeight();
		MaFenetre f1 = new MaFenetre(titre, x, y, (int)(l/1.5), (int)(h/1.5));
		f1.setLocationRelativeTo(null);
		f1.setResizable(false);
		f1.setVisible(true);
	}

}
