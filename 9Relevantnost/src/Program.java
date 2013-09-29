
public class Program {

	private static double R1 = 0;
	private static double R2 = 1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		calcB();
		
	}
	
	private static void calcB()
	{
		int n;
		
		for (n = 2; n < 7; n++) {
			double d = 2.0 / n;
			double h1 = hRavn(-1, 1, d);
			double b1 = h1 * R1;
			
			double h2 = hNorm(d, 0.3);
			double b2 = h2 * R2;
			
			System.out.println(n + " " + d + "   " + h1 + " " + b1 + "   " + h2 + " " + b2);
		}
		
		for (n = 10; n < 21; n += 10) {
			double d = 2.0 / n;
			double h1 = hRavn(-1, 1, d);
			double b1 = h1 * R1;
			
			double h2 = hNorm(d, 0.3);
			double b2 = h2 * R2;
			
			System.out.println(n + " " + d + "   " + h1 + " " + b1 + "   " + h2 + " " + b2);
		}
		
		for (n = 50; n < 51; n += 10) {
			double d = 2.0 / n;
			double h1 = hRavn(-1, 1, d);
			double b1 = h1 * R1;
			
			double h2 = hNorm(d, 0.3);
			double b2 = h2 * R2;
			
			System.out.println(n + " " + d + "   " + h1 + " " + b1 + "   " + h2 + " " + b2);
		}
	}
	
	private static double hRavn(double x1, double x2, double d)
	{
		return Math.log((x2 - x1)/d);
	}
	
	private static double hNorm(double d, double sigma)
	{
		double temp = 2 * Math.PI * Math.exp(sigma*sigma / (d*d));
		return Math.log(temp)/2;
	}
	
}
