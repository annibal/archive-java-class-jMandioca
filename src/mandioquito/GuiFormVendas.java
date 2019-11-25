package mandioquito;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

// Esta é a tela de vendas
// permite escolher quais produtos esta vendendo
// e quantos esta vendendo
// ela atualiza o produto alterando sua quantidade
public class GuiFormVendas extends JPanel {
	
	GuiListener<Product[]> updateProductsListener;
	ProductManager productManager = new ProductManager();
	JPanel checkboxPanel = new JPanel();
	JLabel titleLabel = new JLabel("Vendas");
	JCheckBox[] checkboxes;
	JButton sellButton = new JButton("Vender");
	GuiNumero numberSpin = new GuiNumero(0, 10);
	JComboBox categoryComboBox = new JComboBox(Product.TYPES.values());
	JLabel categoryTitle = new JLabel("Categoria", SwingConstants.RIGHT);
	Product.TYPES selectedCategory = Product.TYPES.PRODUTO;
	
	// separada a logica de criar os checkboxes para ser chamado sempre que algum produto for vendido
	// ou quando filtrar por tipo de produto
	void updateCheckboxPanel() {
		// remove todos os items primeiro
		checkboxPanel.removeAll();
		Product[] products = productManager.getProducts();
		
		// insere o filtro
		checkboxPanel.add(categoryTitle);
		checkboxPanel.add(categoryComboBox);
		
		// verifica quais checkboxes estavam selecionados
		boolean[] previouslyChecked = new boolean[checkboxes.length];
		for (int i=0; i<previouslyChecked.length; i++) {
			if (checkboxes[i] != null) {
				previouslyChecked[i] = checkboxes[i].isSelected();
			} else {
				previouslyChecked[i] = false;
			}
		}

		// recria os checkboxes de acordo com os produtos
		checkboxes = new JCheckBox[products.length];
		for (int i=0; i<products.length; i++) {
			checkboxes[i] = new JCheckBox(products[i].getName() + " ("+ products[i].getQtd() +")");
			// se estava selecionado, continua
			if (previouslyChecked[i]) { checkboxes[i].setSelected(true); }
			// se tem menos de um, des-seleciona e deixa cinzinha, inclicavel
			if (products[i].getQtd() < 1) {
				checkboxes[i].setEnabled(false);
				checkboxes[i].setSelected(false);
			}
			// se for do tipo que to filtrando, mostra
			if (products[i].getType() == this.selectedCategory) {
				checkboxPanel.add(checkboxes[i]);
			}
		}
		
		checkboxPanel.revalidate();
	}
	
	public GuiFormVendas(Product[] products, GuiListener<Product[]> updateProductsListener) {
		this.updateProductsListener = updateProductsListener;
		this.productManager = new ProductManager(products);
		checkboxes = new JCheckBox[products.length];
		
		this.setLayout(new GridLayout(0, 2, 20, 20));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(3, 0, 10, 10));
		formPanel.add(titleLabel);
		formPanel.add(sellButton);
		
		formPanel.add(numberSpin);
		numberSpin.setValue(0);

		checkboxPanel.setLayout(new GridLayout(0,2,10,10));
		this.add(checkboxPanel);
		updateCheckboxPanel();
		
		this.add(formPanel);
		
		// acao de clicar em vender
		sellButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Product[] products = productManager.getProducts();
				float qtd = (float) numberSpin.getValue();
				
				// pega todos os produtos selecionados
				for (int i=0; i<checkboxes.length; i++) {
					if (checkboxes[i].isSelected()) {
						// atualiza com a quantidade reduzida
						productManager.update(products[i], new Product(
							products[i].getName(),
							products[i].getType(),
							products[i].getValue(),
							products[i].getQtd() - qtd,
							products[i].getId()
						));
					}
				}
				
				// atualiza os checkboxes para mostrar a nova qtd
				updateCheckboxPanel();
				numberSpin.setValue(0);
				
				updateProductsListener.action(productManager.getProducts());
			}
		});
		
		// acao de trocar o filtro
		categoryComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedCategory = Product.parseType(categoryComboBox.getSelectedItem().toString());
				updateCheckboxPanel();
			}
		});
		
	}
}
