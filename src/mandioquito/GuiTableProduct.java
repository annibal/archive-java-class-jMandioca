package mandioquito;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GuiTableProduct extends JPanel {
	
	GuiListener<Product> guiListener;
	String [] colunas = {"Nome", "Valor", "Quantidade"};
	Product[] products = {};
	
	public GuiTableProduct() {
		this.renderTable();
	}
	
	public void renderTable() {
		
		this.removeAll();
		this.revalidate();
		this.repaint();
		
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		System.out.println(this.products.length+" - "+this.colunas.length);
		String[][] dados = new String[this.products.length][this.colunas.length];
		for (int i=0; i<this.products.length; i++) {
			dados[i][0] = this.products[i].getName();
			dados[i][1] = this.products[i].getValue()+"";
			dados[i][2] = this.products[i].getQtd()+"";
		}
		
		JTable tabela = new JTable(dados, colunas);
		JScrollPane scrollPane = new JScrollPane(tabela);
		this.add(scrollPane);
	}
	
	public void setProducts(Product[] products) {
		this.products = products;
		this.renderTable();
	}
	
	public void onClick(GuiListener<Product> guiListener) {
		this.guiListener = guiListener;
	}
	public void selectProduct(Product product) {
		this.guiListener.action(product);
	}
}
