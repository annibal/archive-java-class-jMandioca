import java.util.Random;

public class Test_Random {
	public Test_Random() {
		Random random = new Random();
		System.out.println( Math.abs(random.nextInt())+"-"+Math.abs(random.nextInt()) );
	}
}
