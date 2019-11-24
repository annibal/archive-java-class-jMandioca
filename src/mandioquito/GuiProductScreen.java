package mandioquito;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class GuiProductScreen extends JPanel {
	
	GuiTableProduct productTable = new GuiTableProduct();
	GuiFormProduct productForm = new GuiFormProduct();
	ProductManager productManager = new ProductManager();
	
	public GuiProductScreen(Product[] products, GuiListener<Product[]> updateProductsListener) {
		productManager = new ProductManager(products);

		this.setLayout(new GridLayout(0, 2));
		
		productForm.onSave(new GuiListener<Product>() {
			public void action(Product product) {
				
				if (productForm.isEditing()) {
					productManager.update(product, product);
				} else {
					productManager.add(product);
				}
				
				Product[] products = productManager.getProducts();
				productTable.setProducts(products);
				updateProductsListener.action(products);
			}
		});
		this.add(productForm);
		
		productTable.onClick(new GuiListener<Product>() {
			public void action(Product product) {
				productForm.setProduct(product);
			};
		});
		this.add(productTable);
		productTable.setProducts(productManager.getProducts());
	}
}
