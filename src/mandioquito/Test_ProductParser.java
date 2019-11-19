package mandioquito;

public class Test_ProductParser {
	public Test_ProductParser() {
		String product = "nome,100.0,200.5";
		Product p = ProductParser.fromStringRow(product);
		String row = ProductParser.toStringRow(p);
		System.out.println(product);
		System.out.println(row);
		System.out.println(row.equals(product));
	}
}
