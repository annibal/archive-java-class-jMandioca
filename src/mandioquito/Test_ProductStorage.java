package mandioquito;
import java.io.IOException;

public class Test_ProductStorage {
	public Test_ProductStorage() {
		
		try {
			Product[] products = ProductStorage.load();
			for (int i=0; i<products.length; i++) {
				System.out.println(products[i].getName()+", preço "+products[i].getValue()+", "+products[i].getQtd()+" em estoque");
			}
			
			String data = ProductStorage.parse(products);
			System.out.println(data);
			
			products[1].setName("Cigaro");
			ProductStorage.save(ProductStorage.parse(products));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
