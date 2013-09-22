import java.io.FileInputStream;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Program {

	private static String fileInput = "input.txt";
	
	private static int countK;
	private static int countObj;
	
	private static double[][][] A;
	private static double[][] A0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		load();
		
		double[] W0 = calcW(A0, countK);
		
		double[][] W = new double[countK][];
		for (int i = 0; i < countK; i++) {
			W[i] = calcW(A[i], countObj);
		}
		
		double[] result = calcResult(W0, W, countK, countObj);
		
		int best = 0;
		for (int i = 0; i < countObj; i++) {
			System.out.println((i + 1) + ": " + result[i]);
			if(result[best] < result[i])
				best = i;
		}
		
		System.out.println("Best object: " + (best + 1));
		
	}

	private static void load()
	{
		try {
			
			FileInputStream fi = new FileInputStream(fileInput);
			Scanner sc = new Scanner(fi);
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			
			countK = Integer.parseInt(st.nextToken());
			
			A0 = new double[countK][countK];
			
			for (int i = 0; i < countK; i++) {
				
				st = new StringTokenizer(sc.nextLine());
				
				for (int j = 0; j < countK; j++) {
					
					A0[i][j] = Double.parseDouble(st.nextToken());
					
				}
			}
			
			sc.nextLine();
			st = new StringTokenizer(sc.nextLine());
			
			countObj = Integer.parseInt(st.nextToken());
			
			A = new double[countK][countObj][countObj];
			
			for (int i = 0; i < countK; i++) {
				
				sc.nextLine();
				
				for (int j = 0; j < countObj; j++) {

					st = new StringTokenizer(sc.nextLine());

					for (int k = 0; k < countObj; k++) {
						
						A[i][j][k] = Double.parseDouble(st.nextToken());
						
					}
				}
			}
			
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static double[] calcW(double[][] A, int count)
	{
		double[] W = new double[count];
		
		double[] mul = new double[count];
		for (int i = 0; i < count; i++) {
			mul[i] = 1;
		}
		
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				mul[i] *= A[i][j]; 
			}
		}
		
		double sum = 0;
		for (int i = 0; i < count; i++) {
			W[i] = Math.sqrt(mul[i]) * count;
			sum += W[i];
		}
		
		for (int i = 0; i < count; i++) {
			W[i] /= sum;
		}
		
		return W;
	}
	
	private static double[] calcResult(double[] W0, double[][] W, int countK, int countObj)
	{
		double[] result = new double[countObj];
		
		for (int i = 0; i < countK; i++) {
			
			for (int j = 0; j < countObj; j++) {
				
				result[j] += W0[i] * W[i][j];
				
			}
			
		}
		
		return result;
	}
}
