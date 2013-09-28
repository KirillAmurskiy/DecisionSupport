import java.io.FileInputStream;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Program {

	private static String file = "input.txt";
	
	private static int countPair;
	private static int[][] Pair;
	
	private static int countPreferE;
	private static int countPreferZ;
	private static int[][] Prefer;
	
	private static int countRangeE;
	private static int countRangeZ;
	private static int[][] Range;
	
	public static void main(String[] args) {
		
		load();
		
		pair();
		
		prefer();
		
		range();
		
		System.out.println("OK!");

	}
	
	private static void range()
	{
		double sumAll = 0;
		
		for (int i = 0; i < countRangeE; i++) {
			for (int j = 0; j < countRangeZ; j++) {
				sumAll += Range[i][j];
			}
		}
		
		double[][] norm = new double[countRangeE][countRangeZ];
		double[] sum = new double[countRangeZ];
		
		for (int i = 0; i < countRangeE; i++) {
			for (int j = 0; j < countRangeZ; j++) {
				
				norm[i][j] = Range[i][j] / sumAll;
				sum[j] += norm[i][j];
			}
		}
		
		int idxResult = 0;
		
		for (int i = 0; i < countRangeZ; i++) {
			if(sum[i] > sum[idxResult])
				idxResult = i;
		}
		
		System.out.println("Range method: " + (idxResult + 1));
		
	}
	
	private static void prefer()
	{
		int[][] modifPrefer = new int[countPreferE][countPreferZ];
		double[] sum = new double[countPreferZ];
		double sumAll = 0;
		
		for (int i = 0; i < countPreferE; i++) {
			for (int j = 0; j < countPreferZ; j++) {
				
				modifPrefer[i][j] = countPreferZ - Prefer[i][j];
				sum[j] += modifPrefer[i][j];
				sumAll += modifPrefer[i][j];
			}
		}
		
		for (int i = 0; i < countPreferZ; i++) {
			
			sum[i] /= sumAll;
			
		}
		
		int idxResult = 0;
		for (int i = 0; i < countPreferZ; i++) {
			
			if(sum[i] > sum[idxResult])
				idxResult = i;
			
		}
		
		System.out.println("Prefer method: " + (idxResult + 1));
	}
	
	private static void pair()
	{
		double[] v = new double[countPair];
		double sum = 0;
		
		for (int i = 0; i < countPair; i++) {
			for (int j = 0; j < countPair; j++) {
				v[i] += Pair[i][j];
				sum += Pair[i][j];
			}
		}
		
		for (int i = 0; i < countPair; i++) {
			v[i] /= sum;
		}
		
		int idxResult = 0;
		for (int i = 0; i < countPair; i++) {
			if(v[i] > v[idxResult])
				idxResult = i;
		}
		
		System.out.println("Pair method: " + (idxResult + 1));
		
	}
	
	private static void load()
	{
		try {
			
			FileInputStream fi = new FileInputStream(file);
			Scanner sc = new Scanner(fi);
			StringTokenizer st = new StringTokenizer(sc.nextLine());
					
			countPair = Integer.parseInt(st.nextToken());
			Pair = new int[countPair][countPair];
			
			for (int i = 0; i < countPair; i++) {
				
				st = new StringTokenizer(sc.nextLine());
				
				for (int j = 0; j < countPair; j++) {
					
					Pair[i][j] = Integer.parseInt(st.nextToken());
					
				}
			}
			
			st = new StringTokenizer(sc.nextLine());
			countPreferE = Integer.parseInt(st.nextToken());
			countPreferZ = Integer.parseInt(st.nextToken());
			Prefer = new int[countPreferE][countPreferZ];
			
			for (int i = 0; i < countPreferE; i++) {
				
				st = new StringTokenizer(sc.nextLine());
				
				for (int j = 0; j < countPreferZ; j++) {
					
					Prefer[i][j] = Integer.parseInt(st.nextToken());
					
				}
			}
			
			st = new StringTokenizer(sc.nextLine());
			countRangeE = Integer.parseInt(st.nextToken());
			countRangeZ = Integer.parseInt(st.nextToken());
			Range = new int[countRangeE][countRangeZ];
			
			for (int i = 0; i < countRangeE; i++) {
				
				st = new StringTokenizer(sc.nextLine());
				
				for (int j = 0; j < countRangeZ; j++) {
					
					Range[i][j] = Integer.parseInt(st.nextToken());
					
				}
			}
			
			sc.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
