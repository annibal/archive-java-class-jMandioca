package mandioquito;
import java.util.HashMap;

// classe que abstrai inserção, remoção e atualização de produtos
public class ProductManager {

	// hashmap é uma lista indexada, tipo
	// "fj94j" -> Product("Banana", 7, 12)
	// "bjd92" -> Product("Maca", 19, 2)
    HashMap<String, Product> products = new HashMap<String, Product>();
	
    // polimorfismo
	public ProductManager() {}
	public ProductManager(Product[] products) {
		// popula o hashmap de acordo com uma array de produtos
		this.products = new HashMap<String, Product>();
		for (int i = 0; i < products.length; i++) {
			this.add(products[i]);
		}
	}
	
	// insere um produto - a chave dele é o id
	public void add(Product product) {
		this.products.put(product.getId(), product);
	}
	
	// polimorfismo
	// remover mandando o produto inteiro pode assim como pode mandar so o id 
	public void remove(Product product) {
		this.remove(product.getId());
	}
	public void remove(String id) {
		this.products.remove(id);
	}
	
	// tendo a referencia do id do produto antigo,
	// é so atualizar no hashmap com os dados novos
	public void update(Product oldProduct, Product newProduct) {
		this.products.put(oldProduct.getId(), newProduct);
	}
	
	// retorna em array todos os produtos
	public Product[] getProducts() {
		Product[] products = new Product[this.products.size()];
		int index = 0;
		for (String i : this.products.keySet()) {
			products[index++] = this.products.get(i);
		}
		return products;
	}
	
}
