package mandioquito;

// classe feita pra controlar IN e OUT de produto pra csv
// mapeia a estrutura para uma linha separada por virgulas
// e mapeia da linha pra estrutura
public class ProductParser {
	
	// de "nome,tipo,valor,qtd" para Product(nome, tipo, valor, qtd)
	public static Product fromStringRow(String row) {
		String[] parts = row.split(",");
		return new Product(
			parts[0],
			Product.parseType(parts[1]),
			Float.parseFloat(parts[2]),
			Float.parseFloat(parts[3])
		);
	}
	
	// de Product para uma linha ",,,"
	public static String toStringRow(Product product) {
		return product.getName()+","+
			Product.typeToString(product.getType())+","+
			product.getValue()+","+
			product.getQtd();
	}
}
