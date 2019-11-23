package mandioquito;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

public class GUI {
	public GUI() {
		
		JFrame frame = new JFrame();
        frame.setTitle("Gerenciador de Produtos");
		frame.setLayout(new GridLayout(0, 2));

		GuiFormProduct productForm = new GuiFormProduct();
		productForm.onSave(new GuiListener<Product>() {
			public void action(Product product) {
				System.out.println("Saved product "+product.getName()+" with "+product.getQtd()+" items of value "+product.getValue());
			}
		});
		frame.add(productForm);
		
		GuiTableProduct productTable = new GuiTableProduct();
		productTable.onClick(new GuiListener<Product>() {
			public void action(Product product) {
				productForm.setProduct(product);
			};
		});
		frame.add(productTable);
		
		frame.pack();
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
		
	}
	
		
	// start jframe
	// load form
		// button onclick returns new product
		// has function to populate with product
	// load table
		// has function to put a list
		// has onclick that returns a product
	// load menu
		// save receives a list of products
		// load searches file system
	// dashboard with charts
}
