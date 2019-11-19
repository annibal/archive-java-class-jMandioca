package mandioquito;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Stack;

public class ProductStorage {
	static final String filePath = "data/thing.csv";

	public static Product[] load() throws IOException {
		System.out.println("Test File");
		String row = "";
		BufferedReader csvReader = null;
		Stack<Product> stackProducts = new Stack<>();
		
		try {
			csvReader = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			System.out.println("File "+filePath+" not found");
		}
		
		while ((row = csvReader.readLine()) != null) {
			if (row.length() > 0) {
		    	stackProducts.add(ProductParser.fromStringRow(row));
			}
		}
		csvReader.close();
		
		Product[] products = new Product[stackProducts.size()];
		for (int i=0; i<stackProducts.size(); i++) {
			products[i] = stackProducts.get(i);
		}
		
		return products;
	}
	
	public static String parse(Product[] products) {
		String data = "";
		for (int i=0; i<products.length; i++) {
			data += ProductParser.toStringRow(products[i]) + "\n";
		}
		return data;
	}
	
	public static void save(String data) {
		OutputStream os = null;
        try {
            os = new FileOutputStream(new File(filePath));
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
