package mandioquito;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

public class GUI {

	GuiTableProduct productTable = new GuiTableProduct();
	GuiFormProduct productForm = new GuiFormProduct();
	ProductManager productManager = new ProductManager();
	
	public GUI() {
		
		JFrame frame = new JFrame();
        frame.setTitle("Gerenciador de Produtos");
		frame.setLayout(new GridLayout(0, 2));
		productManager = new ProductManager(ProductStorage.load());

		productForm.onSave(new GuiListener<Product>() {
			public void action(Product product) {
				
				if (productForm.isEditing()) {
					productManager.update(product, product);
				} else {
					productManager.add(product);
				}
				productTable.setProducts(productManager.getProducts());
			}
		});
		frame.add(productForm);
		
		productTable.onClick(new GuiListener<Product>() {
			public void action(Product product) {
				productForm.setProduct(product);
			};
		});
		frame.add(productTable);
		productTable.setProducts(productManager.getProducts());
		
		frame.pack();
//        frame.setSize(600, 480);
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
