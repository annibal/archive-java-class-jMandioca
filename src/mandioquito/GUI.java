package mandioquito;

import java.awt.Component;
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
import javax.swing.JPanel;

public class GUI {

	Product[] allProducts = new Product[0];
	JFrame frame = new JFrame();
	JPanel contents = new JPanel();
	GuiProductScreen productScreen = createProductScreen();
	GuiDashboardScreen dashboardScreen = createDashboardScreen();
	GuiFormVendas vendasScreen = createVendasScreen();
	
	GuiProductScreen createProductScreen() {
		return new GuiProductScreen(allProducts, new GuiListener<Product[]>() {
			public void action(Product[] products) {
				allProducts = products;
			}
		});
	}
	GuiDashboardScreen createDashboardScreen() {
		return new GuiDashboardScreen(allProducts);
	}
	GuiFormVendas createVendasScreen() {
		return new GuiFormVendas(allProducts, new GuiListener<Product[]>() {
			public void action(Product[] products) {
				allProducts = products;
			}
		});
	}
	
	void setContents(Component screen) {
		frame.remove(contents);
		contents = new JPanel();
		contents.add(screen);
        frame.add(contents);
        frame.revalidate(); 
	}
	
	public GUI() {
		
		frame = new JFrame();
		contents = new JPanel();
		frame.add(contents);
        frame.setTitle("Gerenciador de Produtos");
        
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("Arquivo");
		JMenuItem saveItem = new JMenuItem("Salvar");
		JMenuItem loadItem = new JMenuItem("Carregar");
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		menuBar.add(fileMenu);
		
		loadItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductStorage.filePath = getCaminhoArquivo(false);
				allProducts = ProductStorage.load();
				productScreen = createProductScreen();
				setContents(productScreen);
			}
		});
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductStorage.filePath = getCaminhoArquivo(true);
				ProductStorage.save(allProducts);
			}
		});

		JMenu viewMenu = new JMenu("Tela");
		JMenuItem dashItem = new JMenuItem("Dashboard");
		JMenuItem produtosItem = new JMenuItem("Produtos");
		JMenuItem vendasItem = new JMenuItem("Vendas");
		viewMenu.add(dashItem);
		viewMenu.add(produtosItem);
		viewMenu.add(vendasItem);
		menuBar.add(viewMenu);

		dashItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dashboardScreen = createDashboardScreen();
				setContents(dashboardScreen);
			}
		});
		produtosItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productScreen = createProductScreen();
				setContents(productScreen);
			}
		});
		vendasItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vendasScreen = createVendasScreen();
				setContents(vendasScreen);
			}
		});
		
		frame.setJMenuBar(menuBar);
		
        contents.add(productScreen);
		
		frame.pack();
//        frame.setSize(600, 480);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
		
	}
	
	public String getCaminhoArquivo(boolean abrir) {
		String txtCaminho = "";
		JFileChooser telaEscolhe = new JFileChooser(new File(System.getProperty("user.home")));
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
