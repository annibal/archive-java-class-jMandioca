package mandioquito;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.sun.javafx.binding.StringConstant;

public class GuiFormProduct extends JPanel {
	private JTextField nameField = new JTextField();
	private JTextField valueField = new JTextField();
	private JTextField qtdField = new JTextField();
	private JComboBox categoryComboBox = new JComboBox(Product.TYPES.values());
	private JLabel nameLabel = new JLabel("Nome", SwingConstants.RIGHT);
	private JLabel valueLabel = new JLabel("Valor", SwingConstants.RIGHT);
	private JLabel qtdLabel = new JLabel("Qtd", SwingConstants.RIGHT);
	private JLabel categoryLabel = new JLabel("Categoria", SwingConstants.RIGHT);
	private JButton clearButton = new JButton("Novo");
	private JButton saveButton = new JButton("Salvar");
	private JButton deleteButton = new JButton("Excluir");
	private JLabel titleLabel = new JLabel("");
	private GuiListener saveGuiListener;
	private GuiListener deleteGuiListener;
	private String productId = "";
	
	public GuiFormProduct() {
		super();
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0, 1, 6, 6));
		p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		p.add(titleLabel);
		
		JPanel nameContainer = new JPanel();
		nameContainer.setLayout(new GridLayout(0, 2, 6, 6));
		nameContainer.add(this.nameLabel);
		nameContainer.add(this.nameField);
		p.add(nameContainer);
		
		JPanel valueContaier = new JPanel();
		valueContaier.setLayout(new GridLayout(0, 2, 6, 6));
		valueContaier.add(this.valueLabel);
		valueContaier.add(this.valueField);
		p.add(valueContaier);
		
		JPanel qtdContainer = new JPanel();
		qtdContainer.setLayout(new GridLayout(0, 2, 6, 6));
		qtdContainer.add(this.qtdLabel);
		qtdContainer.add(this.qtdField);
		p.add(qtdContainer);
		
		JPanel categoryContainer = new JPanel();
		categoryContainer.setLayout(new GridLayout(0, 2, 6, 6));
		categoryContainer.add(this.categoryLabel);
		categoryContainer.add(this.categoryComboBox);
		p.add(categoryContainer);

		JPanel buttonsContainer = new JPanel();
		buttonsContainer.setLayout(new GridLayout(0, 3, 6, 6));
		buttonsContainer.add(this.deleteButton);
		buttonsContainer.add(this.clearButton);
		buttonsContainer.add(this.saveButton);
		this.deleteButton.setEnabled(false);
		p.add(buttonsContainer);

		this.clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				clear();
			}
		});
		this.saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				save();
			}
		});
		this.deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				delete();
			}
		});
		
		this.add(p);
	}
	
	public boolean isEditing() {
		return !this.productId.isEmpty();
	}
	public void setProduct(Product product) {
		this.nameField.setText(product.getName());
		this.categoryComboBox.setSelectedItem(product.getType());
		this.valueField.setText(product.getValue()+"");
		this.qtdField.setText(product.getQtd()+"");
		this.titleLabel.setText("Editando produto "+product.getName());
		this.productId = product.getId();
		this.deleteButton.setEnabled(true);
	}
	public void clear() {
		this.nameField.setText("");
		this.categoryComboBox.setSelectedItem(Product.TYPES.PRODUTO);
		this.valueField.setText("");
		this.qtdField.setText("");
		this.titleLabel.setText("Novo produto");
		this.productId = "";
		this.deleteButton.setEnabled(false);
	}
	public void onSave(GuiListener guiListener) {
		this.saveGuiListener = guiListener;
	}
	public void onDelete(GuiListener guiListener) {
		this.deleteGuiListener = guiListener;
	}
	public void save() {
		try {
			this.saveGuiListener.action(this.getProduct());	
			this.clear();
		} catch(ProductPropertyParseError e) {
			this.titleLabel.setText( e.getMessage() );
		}
	}
	public void delete() {
		if (this.isEditing()) {
			this.deleteGuiListener.action(this.productId);
			this.clear();
		}
	}

	public Product getProduct() throws ProductPropertyParseError {
		float value, qtd;
		String name;
		Product.TYPES type;
		
		name = this.nameField.getText();
		if (name.isEmpty()) { throw new ProductPropertyParseError("Nome é necessário"); }
		
		try {
			value = Float.parseFloat(this.valueField.getText());
		} catch(Exception e) {
			throw new ProductPropertyParseError("Valor deve ser um número válido");
		}
		
		try {
			qtd = Float.parseFloat(this.qtdField.getText());
		} catch(Exception e) {
			throw new ProductPropertyParseError("Quantidade deve ser um número válido");
		}
		
		System.out.println(this.categoryComboBox.getSelectedItem());
		type = Product.parseType( this.categoryComboBox.getSelectedItem().toString() );
		
		if (this.isEditing()) {
			return new Product(name, type, value, qtd, this.productId);
		} else {
			return new Product(name, type, value, qtd);
		}
	}
	
}
