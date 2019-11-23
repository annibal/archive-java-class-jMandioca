package mandioquito;

import java.util.Random;

public class Product extends Object {
	String id;
	String name;
	float value;
	float qtd;
	
	private static int currentIndex = 0;
	private static final String makeId() {
		Random random = new Random();
		return Math.abs(random.nextInt())+"-"+Math.abs(random.nextInt());
	}

	public Product(String name, float value, float qtd) {
		this.name = name;
		this.value = value;
		this.qtd = qtd;
		this.id = makeId();
	}
	public Product(String name) {
		this.name = name;
		this.value = 0;
		this.qtd = 0;
		this.id = makeId();
	}

	public String getId() { return this.id; }
	public String getName() { return this.name; }
	public float getValue() { return this.value; }
	public float getQtd() { return this.qtd; }
	
	public void setName(String name) { this.name = name; }
 }
