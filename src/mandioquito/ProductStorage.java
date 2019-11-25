package mandioquito;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Stack;

// classe que lida com salvar e carregar dados do csv
public class ProductStorage {

	// caminhoDoArquivo
	// o GUI altera essa variavel pra salvar em arquivos diferentes
	static String filePath = "data/thing.csv";
	
	// abre o arquivo e retorna a lista de Product() dele
	public static Product[] load(){
		String row = "";
		BufferedReader csvReader = null;
		// Stack para armazenar os produtos porque nao necessita de tamanho pre definido
		Stack<Product> stackProducts = new Stack<>();
		
		try {
			// abre - pode dar exception tipo o arquivo nao existir
			csvReader = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			System.out.println("File "+filePath+" not found");
		}
		
		try {
			// processa cada linha em um novo produto
			// pode dar erro se a linha estiver fora de padrao
			while ((row = csvReader.readLine()) != null) {
				if (row.length() > 0) {
			    	stackProducts.add(ProductParser.fromStringRow(row));
				}
			}
			
			// fechar a conexao ao arquivo pode dar erro também
			csvReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// converte de stack em array simples
		Product[] products = new Product[stackProducts.size()];
		for (int i=0; i<stackProducts.size(); i++) {
			products[i] = stackProducts.get(i);
		}
		
		return products;
	}
	
	public static String parse(Product[] products) {
		// recebe uma array de produtos e retorna um texto cheio de linhas com virgulas
		
		String data = "";
		for (int i=0; i<products.length; i++) {
			data += ProductParser.toStringRow(products[i]) + "\n";
		}
		return data;
	}
	
	public static void save(String data) {
		// recebe um texto cheio de linhas com virgulas e salva em um arquivo
		
		OutputStream os = null;
        try {
        	// abre o arquivo e insere os dados
        	// pode dar diversos erros
            os = new FileOutputStream(new File(filePath));
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
        	// dando erro ou nao
            try {
            	// fecha a conexao
                os.close();
            } catch (IOException e) {
            	// fechar a conexao pode dar mais erros
                e.printStackTrace();
            }
        }
	}
	
	// polimorfismo que transforma e salva os produtos
	public static void save(Product[] products) {
		save(parse(products));
	}
}
