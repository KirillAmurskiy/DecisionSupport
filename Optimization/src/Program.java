
public class Program {
	
	public static void main(String[] args) 
	{
		Func1 f1 = new Func1();
		double[] xF1 = new double[]{0,1};
		int nF1 = 50;
		double sF1 = 1;
		double resultF1 = GZ1(f1, xF1, sF1, nF1);
		output("Func1", resultF1, xF1, sF1, nF1);
		
		System.out.println();
		
		Func2 f2 = new Func2();
		double[] xF2 = new double[]{-1.2,1};
		int nF2 = 10000;
		double sF2 = 2;
		double resultF2 = GZ1(f2, xF2, sF2, nF2);
		output("Func2", resultF2, xF2, sF2, nF2);
		
		System.out.println();
		
		Func3 f3 = new Func3();
		double[] xF3 = new double[]{0,-1};
		int nF3 = 1000000;
		double sF3 = 0.3;
		double resultF3 = GZ1(f3, xF3, sF3, nF3);
		output("Func3", resultF3, xF3, sF3, nF3);
		
		System.out.println();
		
		Func4 f4 = new Func4();
		double[] xF4 = new double[]{3,-1,0,1};
		int nF4 = 100000;
		double sF4 = 0.5;
		double resultF4 = GZ1(f4, xF4, sF4, nF4);
		output("Func4", resultF4, xF4, sF4, nF4);
		
		System.out.println();
		
		Func5 f5 = new Func5();
		double[] xF5 = new double[]{2.7,90,1500,10};
		int nF5 = 100000;
		double sF5 = 1;
		double resultF5 = GZ1(f5, xF5, sF5, nF5);
		output("Func5", resultF5, xF5, sF5, nF5);
	}
	
	private static double GZ1(Func func, double[] x, double s, int N)
	{
		double F = func.J(x);
		
		double[] h = new double[x.length];
		for (int i = 0; i < h.length; i++) {
			h[i] = s;
		}
		
		for (int i = 0; i < N; i++) {
			for (int m = 0; m < x.length; m++) {
				x[m] += h[m];
				
				double F1 = func.J(x);
				if(F1 <= F)
				{
					h[m] *= 3;
					F = F1;
				}
				else
				{
					x[m] -= h[m];
					h[m] = -0.5*h[m];
				}
			}
		}
		
		return F;
	}
	
	private static interface Func
	{
		public double J(double[] x);
	}
	
	private static class Func1 implements Func
	{

		public double J(double[] x) {
			double x1 = x[0];
			double x2 = x[1];
			
			return Math.pow((x1-x2), 2) + Math.pow((x1+x2-10), 2)/9;
		}
		
	}
	
	private static class Func2 implements Func
	{

		public double J(double[] x) {
			double x1 = x[0];
			double x2 = x[1];
			
			return 100*Math.pow((x1*x1-x2), 2) + Math.pow((1 - x1), 2);
		}
		
	}
	
	private static class Func3 implements Func
	{

		public double J(double[] x) {
			double x1 = x[0];
			double x2 = x[1];
			
			return Math.pow((x1-3)/100, 2) - (x2 - x1) + Math.exp(20*(x2-x1));
		}
		
	}

	private static class Func4 implements Func
	{

		public double J(double[] x) {
			double x1 = x[0];
			double x2 = x[1];
			double x3 = x[2];
			double x4 = x[3];
			
			return Math.pow((x1 + 10*(x2*x2)), 2) + 5*Math.pow((x3-x4), 2) + Math.pow((x2 - 2*x3), 4) + 10*Math.pow(x1-x4, 4);
		}
		
	}
	
	private static class Func5 implements Func
	{
		
		private static double[] A = new double[]{0, 0.428, 1, 1.61, 2.09, 3.48, 5.25};
		
		private static double[] b = new double[]{7.391, 11.18, 16.44, 16.20, 22.2, 24.02, 31.32};
		
		public double J(double[] x) {
			double x1 = x[0];
			double x2 = x[1];
			double x3 = x[2];
			double x4 = x[3];
			
			double[] a = new double[A.length];
			for (int i = 0; i < a.length; i++) {
				a[i] = A[i] * 1E-3;
			}
			
			double sum = 0;
			
			for (int i = 0; i < 7; i++) {
				
				double temp1 = x1*x1 + x2*x2*a[i] + x3*x3*a[i]*a[i];
				double temp2 = 1 + x4*x4*a[i];
				
				sum += Math.pow((temp1/temp2 - b[i])/b[i], 2);
			}
			
			return sum * 1E4;
		}
		
	}
	
	private static void output(String name, double result, double[] x,double s, int N)
	{
		System.out.println(name + ":");
		System.out.println("S = " + s);
		System.out.println("N = " + N);
		System.out.println("Result = " + result);
		System.out.print("X:");
		for (double d : x) {
			System.out.print(" " + d + ";");
		}
		System.out.println();
	}
}
