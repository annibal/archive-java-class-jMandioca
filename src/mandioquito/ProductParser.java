package mandioquito;

public class ProductParser {
	
	public static Product fromStringRow(String row) {
		String[] parts = row.split(",");
		return new Product(
			parts[0],
			Product.parseType(parts[1]),
			Float.parseFloat(parts[2]),
			Float.parseFloat(parts[3])
		);
	}
	
	public static String toStringRow(Product product) {
		return product.getName()+","+
			Product.typeToString(product.getType())+","+
			product.getValue()+","+
			product.getQtd();
	}
}
