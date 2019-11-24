package mandioquito;
import java.util.HashMap;

public class ProductManager {

    HashMap<String, Product> products = new HashMap<String, Product>();
	
	public ProductManager() {}
	public ProductManager(Product[] products) {
		this.products = new HashMap<String, Product>();
		for (int i = 0; i < products.length; i++) {
			this.add(products[i]);
		}
	}
	
	public void add(Product product) {
//		System.out.println("Added "+product.getId());
		this.products.put(product.getId(), product);
	}
	public void remove(Product product) {
		this.remove(product.getId());
	}
	public void remove(String id) {
		this.products.remove(id);
	}
	public void update(Product oldProduct, Product newProduct) {
//		System.out.println(oldProduct.getId()+" | "+newProduct.getId());
//		System.out.println(oldProduct.getId().equals(newProduct.getId()));
		this.products.put(oldProduct.getId(), newProduct);
	}
	public Product[] getProducts() {
		Product[] products = new Product[this.products.size()];
		int index = 0;
		for (String i : this.products.keySet()) {
			products[index++] = this.products.get(i);
		}
		return products;
	}
	
}
