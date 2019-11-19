package mandioquito;

public class Test_ProductManager {
	public Test_ProductManager() {

		ProductManager pm = new ProductManager();
		Product p = new Product("Huehue", 1, 1);
		pm.add(new Product("Bolota", 90, 90));
		pm.add(new Product("Xablau", 80, 70));
		pm.add(p);
		
		Product oldP = p;
		p = new Product("Huehue", 999, 999);
		pm.update(oldP, p);
		
		Product[] products = pm.getProducts();
		for (int i=0; i<products.length; i++) {
			System.out.println(products[i].name+", "+products[i].qtd);
		}
		
		/**
		 	Huehue, 999.0
			Bolota, 90.0
			Xablau, 70.0
		 */
	}
}
