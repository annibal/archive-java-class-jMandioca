import java.util.Scanner;

public class TerminalInterface {
	Scanner c = new Scanner(System.in);
	enum MENU_OPTIONS {
		MENU, LIST, DETAILS, ADD, REMOVE, UPDATE, SAVE, LOAD, EXIT
	}
	
	void log(String text) { System.out.println(text); }
	void logProduct(Product product) {
		log("  Nome: "+product.getName());
		log("  Valor: "+product.getValue());
		log("  Quantidade: "+product.getQtd());
	}
	float read(String text) {
		log(text);
		return c.nextFloat();
	}
	String readName(String text) {
		log(text);
		return c.next();
	}
	
	void logMenu() {
		log(" ========= ");
		log(" M E N U");
		log(" ========= ");
		log("");
		log("1 - Show this menu");
		log("2 - List all products");
		log("3 - Details of a product");
		log("4 - Add a new product");
		log("5 - Remove a product");
		log("6 - Update a existing product");
		log("7 - Save data to file");
		log("8 - Load data from file");
		log("9 - Exit program");
		log(" ======= ");
		log("");
	}
	
	
	
	public TerminalInterface() {
		int option = 1;
		
//		while ()
		
	}
	
}
