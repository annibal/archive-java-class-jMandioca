package mandioquito;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test_File {
	public Test_File() throws IOException {
		System.out.println("Test File");
		String pathToCsv = "data/thing.csv";
		String row = "";
		BufferedReader csvReader = null;
		int lineIndex = 0;
		
		try {
			csvReader = new BufferedReader(new FileReader(pathToCsv));
		} catch (FileNotFoundException e) {
			System.out.println("File "+pathToCsv+" not found");
		}
		
		while ((row = csvReader.readLine()) != null) {
		    String[] data = row.split(",");
		    if (lineIndex > 0) {
		    	
		    }
		    lineIndex++;
		}
		csvReader.close();
	}
}
