import java.io.FileInputStream;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Program {
	
	private static String inputFile = "input.txt";
	
	private static int countS;
	private static int countA;
	private static double[][] A;
	
	public static void main(String[] args) {
		
		loadInput();
		
		Vald();
		
		Savage();
		
		Hurwitz(0.6);
		
		
	}
	
	private static void Hurwitz(double C)
	{
		double[] max = new double[countA];
		double[] min = new double[countA];
		
		for (int i = 0; i < countA; i++) {
			
			min[i] = Double.MAX_VALUE;
			max[i] = Double.MIN_VALUE;
			
			for (int j = 0; j < countS; j++) {
				
				if(A[i][j] > max[i])
					max[i] = A[i][j];
				
				if(A[i][j] < min[i])
					min[i] = A[i][j];
				
			}
		}
		
		double[] W = new double[countA];
		int idxOptim = 0;
		for (int i = 0; i < countA; i++) {
			
			W[i] = C*min[i] + (1-C)*max[i];
			
			if(W[idxOptim] < W[i])
				idxOptim = i;
		}
		
		System.out.println("Hurwitz: " + (idxOptim + 1));
	}
	
	private static void Savage()
	{
		
		double[] max = new double[countA];
		for (int i = 0; i < countA; i++) {
			
			max[i] = Double.MIN_VALUE;
			
			for (int j = 0; j < countS; j++) {
				
				if(A[i][j] > max[i])
					max[i] = A[i][j];
				
			}
		}
		
		double[][] rest = new double[countA][countS];
		double[] maxRest = new double[countA];
		for (int i = 0; i < countA; i++) {
			
			maxRest[i] = Double.MIN_VALUE;
			
			for (int j = 0; j < countS; j++) {
				
				rest[i][j] = max[i] - A[i][j];
				
				if(rest[i][j] > maxRest[i])
					maxRest[i] = rest[i][j];
			}
		}
		
		int optimIdx = 0;
		for (int i = 0; i < countA; i++) {
			
			if(maxRest[i] < maxRest[optimIdx])
				optimIdx = i;
			
		}
	
		System.out.println("Savage: " + (optimIdx + 1));
	}
	
	private static void Vald()
	{
		
		double[] subOptim = new double[countA];
		
		for (int i = 0; i < countA; i++) {
			
			subOptim[i] = Double.MAX_VALUE;
			
			for (int j = 0; j < countS; j++) {
				
				if(A[i][j] < subOptim[i])
					subOptim[i] = A[i][j];
				
			}
		}
		
		int optimIdx = 0;
		for (int i = 0; i < countA; i++) {
			if(subOptim[i] > subOptim[optimIdx])
				optimIdx = i;
		}
		
		System.out.println("Vald: " + (optimIdx + 1));
	}
	
	private static void loadInput()
	{
		try {
			
			FileInputStream fi = new FileInputStream(inputFile);
			Scanner sc = new Scanner(fi);
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			
			countS = Integer.parseInt(st.nextToken());
			countA = Integer.parseInt(st.nextToken());
			
			A = new double[countA][countS];
			
			for (int i = 0; i < countA; i++) {
				
				st = new StringTokenizer(sc.nextLine());
				
				for (int j = 0; j < countS; j++) {
					
					A[i][j] = -Double.parseDouble(st.nextToken());
					
				}
			}
			
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
