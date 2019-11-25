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
	
	void updateCheckboxPanel() {
		checkboxPanel.removeAll();
		Product[] products = productManager.getProducts();
		
		checkboxPanel.add(categoryTitle);
		checkboxPanel.add(categoryComboBox);
		
		boolean[] previouslyChecked = new boolean[checkboxes.length];
		for (int i=0; i<previouslyChecked.length; i++) {
			if (checkboxes[i] != null) {
				previouslyChecked[i] = checkboxes[i].isSelected();
			} else {
				previouslyChecked[i] = false;
			}
		}

		checkboxes = new JCheckBox[products.length];
		for (int i=0; i<products.length; i++) {
			checkboxes[i] = new JCheckBox(products[i].getName() + " ("+ products[i].getQtd() +")");
			if (previouslyChecked[i]) { checkboxes[i].setSelected(true); }
			if (products[i].getQtd() < 1) {
				checkboxes[i].setEnabled(false);
				checkboxes[i].setSelected(false);
			}
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
		
		sellButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Product[] products = productManager.getProducts();
				float qtd = (float) numberSpin.getValue();
				
				for (int i=0; i<checkboxes.length; i++) {
					if (checkboxes[i].isSelected()) {
						productManager.update(products[i], new Product(
							products[i].getName(),
							products[i].getType(),
							products[i].getValue(),
							products[i].getQtd() - qtd,
							products[i].getId()
						));
					}
				}
				
				updateCheckboxPanel();
				numberSpin.setValue(0);
				
				updateProductsListener.action(productManager.getProducts());
			}
		});
		
		categoryComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedCategory = Product.parseType(categoryComboBox.getSelectedItem().toString());
				updateCheckboxPanel();
			}
		});
		
	}
}
