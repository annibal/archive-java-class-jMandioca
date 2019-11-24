package mandioquito;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GUI {

	Product[] allProducts = new Product[0];
	JFrame frame = new JFrame();
	GuiProductScreen productScreen = createProductScreen();
	
	GuiProductScreen createProductScreen() {
		return new GuiProductScreen(allProducts, new GuiListener<Product[]>() {
			public void action(Product[] products) {
				allProducts = products;
			}
		});
	}
	
	public GUI() {
		
		frame = new JFrame();
        frame.setTitle("Gerenciador de Produtos");
        
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("Arquivo");
		JMenuItem saveItem = new JMenuItem("Salvar");
		JMenuItem loadItem = new JMenuItem("Carregar");
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		menuBar.add(fileMenu);

		JMenu viewMenu = new JMenu("Tela");
		JMenuItem dashItem = new JMenuItem("Dashboard");
		JMenuItem produtosItem = new JMenuItem("Produtos");
		viewMenu.add(dashItem);
		viewMenu.add(produtosItem);
		menuBar.add(viewMenu);
		
		loadItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.remove(productScreen);
				ProductStorage.filePath = getCaminhoArquivo(false);
				allProducts = ProductStorage.load();
				productScreen = createProductScreen();
		        frame.add(productScreen);
		        frame.revalidate(); 
			}
		});
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductStorage.filePath = getCaminhoArquivo(true);
				ProductStorage.save(allProducts);
			}
		});
		
		frame.setJMenuBar(menuBar);
		
        frame.add(productScreen);
		
		frame.pack();
//        frame.setSize(600, 480);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
		
	}
	
	public String getCaminhoArquivo(boolean abrir) {
		String txtCaminho = "";
		File diretorioOrigem = new File("C:\\");
		JFileChooser telaEscolhe = new JFileChooser(diretorioOrigem);
//		telaEscolhe.setFileFilter(new FiltroExtensao("csv"));
		telaEscolhe.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		int ret;
		if (abrir) {
			ret = telaEscolhe.showSaveDialog(frame);
		} else {
			ret = telaEscolhe.showOpenDialog(frame);
		}

		if (ret == JFileChooser.APPROVE_OPTION) {
			File fileArquivo = telaEscolhe.getSelectedFile();
			txtCaminho = fileArquivo.getAbsolutePath();
		}
		return txtCaminho;
	}
}
