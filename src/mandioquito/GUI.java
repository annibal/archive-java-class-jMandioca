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

// GUI é a classe principal, que gerencia a janela, o menu, e qual "tela" esta sendo exibida
// GUI significa Graphics User Interface, ou Interface Gráfica do Usuário
public class GUI {

	Product[] allProducts = new Product[0];
	JFrame frame = new JFrame();
	JPanel contents = new JPanel();
	GuiProductScreen productScreen = createProductScreen();
	GuiDashboardScreen dashboardScreen = createDashboardScreen();
	GuiFormVendas vendasScreen = createVendasScreen();
	
	// essas funçoes inicializam as outras classes de GUI
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
	
	// Component significa qqr coisa visual do JFrame, tipo JPanels e afins
	// essa função troca o conteudo da tela para outro Componente
	void setContents(Component screen) {
		frame.remove(contents);
		contents = new JPanel();
		contents.add(screen);
        frame.add(contents);
        // essa função diz pro jframe renderizar de novo
        frame.revalidate(); 
	}
	
	public GUI() {
		
		frame = new JFrame();
		contents = new JPanel();
		frame.add(contents);
        frame.setTitle("Gerenciador de Produtos");
        
        // criação do menu
        
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("Arquivo");
		JMenuItem saveItem = new JMenuItem("Salvar");
		JMenuItem loadItem = new JMenuItem("Carregar");
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		menuBar.add(fileMenu);
		
		// ActionListeners para os itens do menu "Arquivo"
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
//		viewMenu.add(dashItem); // nao tem grafico ainda
		viewMenu.add(produtosItem);
		viewMenu.add(vendasItem);
		menuBar.add(viewMenu);

		
		// Os itens do menu de tela chamam setContents() pra trocar de tela
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
		
		// a tela default é a de cadastro de produtos
        contents.add(productScreen);
		
		frame.pack();
//        frame.setSize(600, 480);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
		
	}
	
	// funcao que abre o explorador de arquivos pra salvar/carregar os dados
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
