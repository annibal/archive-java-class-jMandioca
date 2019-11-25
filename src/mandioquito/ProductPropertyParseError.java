package mandioquito;

// erro que acontece quando o formulario tenta processar um produto com dados invalidos
// tipo "batata" no valor
public class ProductPropertyParseError extends Exception {
	public ProductPropertyParseError(String err) {
		super(err);
	}
}
