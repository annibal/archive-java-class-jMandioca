package mandioquito;

public class ProductParser {
	
	public static Product fromStringRow(String row) {
		String[] parts = row.split(",");
		return new Product(
			parts[0],
			Float.parseFloat(parts[1]),
			Float.parseFloat(parts[2])
		);
	}
	
	public static String toStringRow(Product product) {
		return product.getName()+","+
			product.getValue()+","+
			product.getQtd();
	}
}
