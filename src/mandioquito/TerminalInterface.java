package mandioquito;
import java.io.IOException;
import java.util.Scanner;

public class TerminalInterface {
	Scanner c = new Scanner(System.in);
	enum MENU_OPTIONS {
		MENU, LIST, DETAILS, ADD, REMOVE, UPDATE, SAVE, LOAD, EXIT, UNKNOWN
	}
	
	void log(String text) { System.out.println(text); }
	float read(String text) {
		log(text);
		return c.nextFloat();
	}
	String readString(String text) {
		log(text);
		return c.next();
	}
	boolean readYN(String text) {
		log(text+" (s/n) /: ");
		String s = c.next();
		return s.equalsIgnoreCase("s") || s.equalsIgnoreCase("sim") || s.equalsIgnoreCase("y");
	}
	
	void logProduct(Product product) {
		log("  Nome: "+product.getName());
		log("  Valor: "+product.getValue());
		log("  Quantidade: "+product.getQtd());
	}
	void logProducts(Product[] products) {
		for (int i=0; i<products.length; i++) {
			log("  #"+i+": "+products[i].getName());
		}
	}
	Product newProduct() {
		log("Criando novo produto");
		
		String name = "";
		while(name == "") {
			name = readString("  Nome: ");
			if (name == "") { log("  ( X ) Informe um nome para o produto"); }
		}
		
		float value = 0;
		value = read("  Valor: ");
		
		float qtd = -1;
		while (qtd < 0) {
			qtd = read("  Quantidade: ");
			if (qtd < 0) { log("  ( X ) Quantidade deve ser 0 ou mais"); }
		}
		
		return new Product(name, value, qtd);
	}
	
	Product updatedProduct(Product product) {
		log("Atualizando produto "+product.getName());
		log("(Insira um espaço para nao mudar \" \")");
		
		String name = "";
		name = readString("  Nome ("+product.getName()+"): ");
		if (name == "") { name = product.getName(); }
		
		float value = Float.NaN;
		value = read("  Valor ("+product.getValue()+"): ");
		if (value == Float.NaN) { value = product.getValue(); }
		
		float qtd = -1;
		while(qtd < 0) {
			qtd = read("  Quantidade ("+product.getQtd()+"): ");
			if (qtd == -1) { qtd = product.getQtd(); }
			if (qtd < 0) { log("  ( X ) Quantidade deve ser 0 ou mais"); }
		}
		
		return new Product(name, value, qtd);
	}
	
	void logMenu() {
		log(" ========= ");
		log(" M E N U");
		log(" ========= ");
		log("");
		log("1 - Mostrar este menu");
		log("2 - Listar produtos");
		log("3 - Detalhes de um produto");
		log("4 - Adicionar novo produto");
		log("5 - Remover um produto");
		log("6 - Atualizar um produto");
		log("7 - Salvar dados");
		log("8 - Carregar dados do arquivo");
		log("9 - Sair");
		log(" ======= ");
		log("");
	}
	
	MENU_OPTIONS mapStringToMenuOptions(String number) {
		switch (number) {
			case "1": return MENU_OPTIONS.MENU;
			case "2": return MENU_OPTIONS.LIST;
			case "3": return MENU_OPTIONS.DETAILS;
			case "4": return MENU_OPTIONS.ADD;
			case "5": return MENU_OPTIONS.REMOVE;
			case "6": return MENU_OPTIONS.UPDATE;
			case "7": return MENU_OPTIONS.SAVE;
			case "8": return MENU_OPTIONS.LOAD;
			case "9": return MENU_OPTIONS.EXIT;
			default: return MENU_OPTIONS.UNKNOWN;
		}
	}
	
	public TerminalInterface() {
		MENU_OPTIONS option = MENU_OPTIONS.MENU;
		ProductManager productManager = new ProductManager();
		Product[] products;
		int index;
		boolean yn = false;
		String filePath = "data/thing.csv";
		String newFilePath = "";
		
		while (option != MENU_OPTIONS.EXIT) {
			switch(option) {
				case MENU:
					logMenu();
					break;
				case LIST:
					logProducts(productManager.getProducts());
					break;
				case DETAILS:
					index = (int) read("Qual o # do produto? ");
					products = productManager.getProducts();
					if (index < 0 || index >= products.length) {
						log("Número (#) além dos limites, há apenas "+products.length+" produtos");
					} else {
						logProduct(products[index]);
					}
					break;
				case ADD:
					productManager.add(newProduct());
					log("Produto inserido com sucesso");
					break;
				case REMOVE:
					index = (int) read("Qual o # do produto? ");
					products = productManager.getProducts();
					if (index < 0 || index >= products.length) {
						log("Número (#) além dos limites, há apenas "+products.length+" produtos");
					} else {
						yn = readYN("Deseja remover o produto "+products[index].getName()+"?");
						if (yn) {
							productManager.remove(products[index]);
							log("Produto removido com sucesso");
						} else {
							log("Remoção cancelada");
						}
					}
					break;
				case UPDATE:
					index = (int) read("Qual o # do produto? ");
					products = productManager.getProducts();
					if (index < 0 || index >= products.length) {
						log("Número (#) além dos limites, há apenas "+products.length+" produtos");
					} else {
						productManager.update(products[index], updatedProduct(products[index]));
						log("Produto atualizado com sucesso");
					}
					break;
				case SAVE:
					newFilePath = readString("Caminho do arquivo a salvar ("+ProductStorage.filePath+"): ");
					if (newFilePath != "") {
						ProductStorage.filePath = newFilePath;
					};
					ProductStorage.save( productManager.getProducts() );
					log("Produtos salvos com sucesso");
					break;
				case LOAD:
					newFilePath = readString("Caminho do arquivo a carregar os dados ("+ProductStorage.filePath+"): ");
					if (newFilePath != "") {
						ProductStorage.filePath = newFilePath;
					};
					
					products = productManager.getProducts();
					if (products.length > 0) {
						yn = readYN(" ( ! ) carregar os produtos irá sobrescrever os atuais, deseja continuar?");
						if (!yn) { break; }
					}
					
					try {
						products = ProductStorage.load();
						productManager = new ProductManager(products);
						log("Carregados "+products.length+" produtos");
					} catch (IOException e) {
						log(" ( X ) Falha ao carregar os produtos:");
						e.printStackTrace();
					}
					break;
				case EXIT:
					log("Vlw flw");
				case UNKNOWN:
					log("Opção inválida");
			}
			
			if (option != MENU_OPTIONS.EXIT) {
				option = mapStringToMenuOptions( readString(">: ") );
			}
		}
		
	}
}
