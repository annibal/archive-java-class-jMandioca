package mandioquito;

import java.awt.GridLayout;

import javax.swing.JPanel;

// Tela de cadastro, edicao, deleção e visualização de produtos
public class GuiProductScreen extends JPanel {
	
	GuiTableProduct productTable = new GuiTableProduct();
	GuiFormProduct productForm = new GuiFormProduct();
	ProductManager productManager = new ProductManager();
	
	// é criada recebendo uma lista de produtos e um action listener customizado
	public GuiProductScreen(Product[] products, GuiListener<Product[]> updateProductsListener) {
		productManager = new ProductManager(products);

		this.setLayout(new GridLayout(0, 2));
		
		// productForm é o componente de formulario com os campos de nome etc
		// onSave é o ActionListener customizado que diz o que acontece quando o usuario clica no botao salvar
		productForm.onSave(new GuiListener<Product>() {
			public void action(Product product) {
				// usuario salvou um produto
				// é novo ou estava editando?
				if (productForm.isEditing()) {
					// se estava editando, atualiza os dados desse produto na lista
					productManager.update(product, product);
				} else {
					// se nao adiciona
					productManager.add(product);
				}
				
				Product[] products = productManager.getProducts();
				// manda a tabela atualizar
				productTable.setProducts(products);
				// avisa o GUI que os produtos mudaram
				updateProductsListener.action(products);
			}
		});
		// onDelete é o callback do botao de deletar
		productForm.onDelete(new GuiListener<String>() {
			public void action(String productId) {
				// usuario removeu um produto
				// remove da lista de produtos
				productManager.remove(productId);
				// manda a tabela atualizar, agora sem o produto removido
				productTable.setProducts(products);
				// avisa o GUI que os produtos mudaram
				updateProductsListener.action(products);
			}
		});
		
		// coloca esse componente na tela
		this.add(productForm);
		
		// callback de quando o usuario clica em uma linha da tabela
		productTable.onClick(new GuiListener<Product>() {
			public void action(Product product) {
				// usuario clicou na tabela
				// a linha representa um produto, sendo passado por parametro
				// diz pro componente de formulario que é pra mostrar esse produto
				// ele mostra e entende que está editando
				productForm.setProduct(product);
			};
		});
		
		// coloca a tabela na tela
		this.add(productTable);
		productTable.setProducts(productManager.getProducts());
	}
}
