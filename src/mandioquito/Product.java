package mandioquito;

import java.util.Random;
 
public class Product extends Object {
	public enum TYPES {
		PRODUTO("Produto"),
		BRINDE("Brinde"),
		PACOTE("Pacote"),
		MATERIAL("Material");
		
		private String desc="";
		private TYPES(String desc) {
			this.desc = desc;
		}
		
		public String toString() {
			return this.desc;
		}
	};
	String id;
	String name;
	TYPES type;
	float value;
	float qtd;
	
	private static int currentIndex = 0;
	private static final String makeId() {
		Random random = new Random();
		return Math.abs(random.nextInt())+"-"+Math.abs(random.nextInt());
	}

	public Product(String name, TYPES type, float value, float qtd, String id) {
		this.name = name;
		this.type = type;
		this.value = value;
		this.qtd = qtd;
		this.id = id;
	}
	public Product(String name, TYPES type, float value, float qtd) {
		this.name = name;
		this.type = type;
		this.value = value;
		this.qtd = qtd;
		this.id = makeId();
	}
	public Product(String name) {
		this.name = name;
		this.type = TYPES.PRODUTO;
		this.value = 0;
		this.qtd = 0;
		this.id = makeId();
	}
	
	public static TYPES parseType(String type) {
		switch (type.toLowerCase()) {
			case "produto": return TYPES.PRODUTO;
			case "brinde": return TYPES.BRINDE;
			case "pacote": return TYPES.PACOTE;
			case "material": return TYPES.MATERIAL;
			default: return TYPES.PRODUTO;
		}
	}
	public static String typeToString(TYPES type) {
		switch (type) {
			case PRODUTO: return "Produto";
			case BRINDE: return "Brinde";
			case PACOTE: return "Pacote";
			case MATERIAL: return "Material";
			default: return "Produto";
		}
	}

	public String getId() { return this.id; }
	public String getName() { return this.name; }
	public float getValue() { return this.value; }
	public float getQtd() { return this.qtd; }
	public TYPES getType() { return this.type; }
	
	public void setName(String name) { this.name = name; }
 }
