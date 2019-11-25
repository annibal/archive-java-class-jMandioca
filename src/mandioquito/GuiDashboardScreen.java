package mandioquito;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GuiDashboardScreen extends JPanel {
	
	public GuiDashboardScreen(Product[] products) {
		JLabel label = new JLabel("Dashboard");
		this.add(label);
		// aqui vao os graficos
	}
}
