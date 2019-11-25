package mandioquito;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

// classe que abstrai a tabela que mostra os produtos
public class GuiTableProduct extends JPanel {
	
	GuiListener<Product> guiListener;
	String [] colunas = {"Nome", "Valor", "Quantidade", "Tipo"};
	Product[] products = {};
	
	public GuiTableProduct() {
		this.renderTable();
	}
	
	// funcao de mostrar tabela separada para ser chamada sempre que a tabela precisar atualizar
	public void renderTable() {
		
		this.removeAll();
		this.revalidate();
		this.repaint();
		
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// é necessario separar os dados em uma matriz
		// onde cada coluna é um dos campos de Product
		// e cada linha é um registro, um produto
		String[][] dados = new String[this.products.length][this.colunas.length];
		for (int i=0; i<this.products.length; i++) {
			dados[i][0] = this.products[i].getName();
			dados[i][1] = this.products[i].getValue()+"";
			dados[i][2] = this.products[i].getQtd()+"";
			dados[i][3] = Product.typeToString(this.products[i].getType());
		}
		
		JTable tabela = new JTable();
		// removo a funcionalidade de editar cada campo da tabela
		tabela.setCellEditor(null);
		// crio o evento de clicar na linha
		tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	guiListener.action( products[tabela.getSelectedRow()] );
	        }
	    });
		// insiro os dados na tabela
		tabela.setModel(new DefaultTableModel(dados, colunas) {
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
	
		JScrollPane scrollPane = new JScrollPane(tabela);
		this.add(scrollPane);
	}
	
	// recebe os produtos de cima e atualiza a tabela
	public void setProducts(Product[] products) {
		this.products = products;
		this.renderTable();
	}
	// recebe a informação de quem sera avisado quando alguem clicar na linha
	public void onClick(GuiListener<Product> guiListener) {
		this.guiListener = guiListener;
	}
	// avisa que clicaram na linha
	public void selectProduct(Product product) {
		this.guiListener.action(product);
	}
}
