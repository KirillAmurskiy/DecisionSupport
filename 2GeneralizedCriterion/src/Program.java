import java.io.FileInputStream;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Program {

	private static String fileName = "input.txt";
	static int countFlats;
	static int countValues;
	
	private static double[][] flats;
	private static double[] coefs;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		loadInput();
		
		add();
		
		mul();
		
		min();
		
		max();
	}
	
	private static void max()
	{
		double[] result = new double[countFlats];
		
		for (int i = 0; i < countFlats; i++) {
			
			for (int j = 0; j < countValues; j++) {
				
				if(result[i] < flats[i][j]*coefs[j])
					result[i] = flats[i][j]*coefs[j];
				
			}
			
		}
		
		int bestIdx = 0;
		for (int i = 0; i < countFlats; i++) {
			if(result[i] > result[bestIdx])
				bestIdx = i;
		}
		
		System.out.println("Max best: " + (bestIdx + 1) + ", value = " + result[bestIdx]);
	}
	
	private static void min()
	{
		double[] result = new double[countFlats];
		for (int i = 0; i < countFlats; i++) {
			result[i] = Double.MAX_VALUE;
		}
		
		for (int i = 0; i < countFlats; i++) {
			
			for (int j = 0; j < countValues; j++) {
				
				if(result[i] > flats[i][j]/coefs[j])
					result[i] = flats[i][j]/coefs[j];
				
			}
			
		}
		
		int bestIdx = 0;
		for (int i = 0; i < countFlats; i++) {
			if(result[i] > result[bestIdx])
				bestIdx = i;
		}
		
		System.out.println("Min best: " + (bestIdx + 1) + ", value = " + result[bestIdx]);
	}
	
	private static void add()
	{
		double[] result = new double[countFlats];
		
		for (int i = 0; i < countFlats; i++) {
			
			for (int j = 0; j < countValues; j++) {
				
				result[i] += flats[i][j]*coefs[j];
				
			}
			
		}
		
		int bestIdx = 0;
		for (int i = 0; i < countFlats; i++) {
			if(result[i] > result[bestIdx])
				bestIdx = i;
		}
		
		System.out.println("Addition best: " + (bestIdx + 1) + ", value = " + result[bestIdx]);
	}

	private static void mul()
	{
		double[] result = new double[countFlats];
		for (int i = 0; i < countFlats; i++) {
			result[i] = 1;
		}
		
		for (int i = 0; i < countFlats; i++) {
			
			for (int j = 0; j < countValues; j++) {
				
				result[i] *= Math.pow(flats[i][j], coefs[j]);
				
			}
			
		}
		
		int bestIdx = 0;
		for (int i = 0; i < countFlats; i++) {
			if(result[i] > result[bestIdx])
				bestIdx = i;
		}
		
		System.out.println("Multiplication best: " + (bestIdx + 1) + ", value = " + result[bestIdx]);
	}
	
	private static void loadInput()
	{
		try {
			
			FileInputStream fi = new FileInputStream(fileName);
			Scanner scanner = new Scanner(fi);
			
			//counts
			StringTokenizer st = new StringTokenizer(scanner.nextLine());
			countFlats = Integer.parseInt(st.nextToken());
			countValues = Integer.parseInt(st.nextToken());
			
			flats = new double[countFlats][];
			for (int i = 0; i < countFlats; i++) {
				flats[i] = new double[countValues];
			}
			coefs = new double[countValues];
			
			for (int i = 0; i < countValues; i++) 
			{
				st = new StringTokenizer(scanner.nextLine());
				for (int j = 0; j < countFlats; j++) {
					flats[j][i] = Double.parseDouble(st.nextToken());
				}
				coefs[i] = Double.parseDouble(st.nextToken());
			}
			
			scanner.close();
			fi.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
