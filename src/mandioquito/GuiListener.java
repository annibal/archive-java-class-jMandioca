package mandioquito;

// interface para eventos entre os componentes
// <T> é uma variavel de tipo, e passa esse tipo pra funcao de action
// entao se eu quero que meu componente avise o outro que alguem clicou em um Produto
// crio a classe como new GuiListener<Product>
// se quero avisar apenas o nome do produto clicado
// crio a classe como new GuiListener<String>
// se quero passar todos os produtos de um componente pro outro
// crio a classe como new GuiListener<Product[]>
// e chamo a action de acordo: guiListener.action(products)
public interface GuiListener<T> {
	void action(T obj);
}
