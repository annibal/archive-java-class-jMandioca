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
	static String filePath = "data/thing.csv";
	
	public static Product[] load(){
		String row = "";
		BufferedReader csvReader = null;
		Stack<Product> stackProducts = new Stack<>();
		
		try {
			csvReader = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			System.out.println("File "+filePath+" not found");
		}
		
		try {
			while ((row = csvReader.readLine()) != null) {
				if (row.length() > 0) {
			    	stackProducts.add(ProductParser.fromStringRow(row));
				}
			}
			csvReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	public static void save(Product[] products) {
		save(parse(products));
	}
}
