package mandioquito;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GuiFormProduct extends JPanel {
	private JTextField nameField = new JTextField();
	private JTextField valueField = new JTextField();
	private JTextField qtdField = new JTextField();
	private JLabel nameLabel = new JLabel("Nome", SwingConstants.RIGHT);
	private JLabel valueLabel = new JLabel("Valor", SwingConstants.RIGHT);
	private JLabel qtdLabel = new JLabel("Qtd", SwingConstants.RIGHT);
	private JButton clearButton = new JButton("Novo");
	private JButton saveButton = new JButton("Salvar");
	private JLabel titleLabel = new JLabel("");
	private GuiListener guiListener;
	
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

		JPanel buttonsContainer = new JPanel();
		buttonsContainer.setLayout(new GridLayout(0, 2, 6, 6));
		buttonsContainer.add(this.clearButton);
		buttonsContainer.add(this.saveButton);
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
		
		this.add(p);
	}
	
	public void setProduct(Product product) {
		this.nameField.setText(product.getName());
		this.valueField.setText(product.getValue()+"");
		this.qtdField.setText(product.getQtd()+"");
		this.titleLabel.setText("Editando produto "+product.getName());
	}
	public void clear() {
		this.nameField.setText("");
		this.valueField.setText("");
		this.qtdField.setText("");
		this.titleLabel.setText("Novo produto");
	}
	public void onSave(GuiListener guiListener) {
		this.guiListener = guiListener;
	}
	public void save() {
		try {
			this.guiListener.action(this.getProduct());	
			this.clear();		
		} catch(ProductPropertyParseError e) {
			this.titleLabel.setText( e.getMessage() );
		}
	}

	public Product getProduct() throws ProductPropertyParseError {
		float value, qtd;
		String name;
		
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
		
		return new Product(name, value, qtd);
	}
	
}
