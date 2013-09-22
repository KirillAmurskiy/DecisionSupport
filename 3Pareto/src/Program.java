import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Program {
	
	private static String fileName = "input.txt";
	static int countFlats;
	static int countKs;
	
	private static Double[][] flats;
	
	public static void main(String[] args) {
		
		loadInput();
		
		Pareto();
		
	}

	private static void Pareto()
	{
		ArrayList<Integer> idxsPareto = new ArrayList<Integer>();
		ArrayList<Integer> idxsToRemove = new ArrayList<Integer>();
		boolean isAddFlat = true;
		
		for (int i = 0; i < countFlats; i++){
			
			for (Integer parIdx : idxsPareto) {
				
				boolean betterAll = true;
				boolean betterAny = false;
				
				for (int j = 0; j < countKs; j++) {
					if(flats[i][j] > flats[parIdx][j])
						betterAny = true;
					else if(flats[i][j] < flats[parIdx][j])
						betterAll = false;
				}
				
				if(!betterAll && !betterAny)
				{
					isAddFlat = false;
					break;
				}
				
				if(betterAll && betterAny)
				{
					isAddFlat = true;
					idxsToRemove.add(parIdx);
				}
				else
					isAddFlat = true;
				
			}
			
			for (Integer idxToRemove : idxsToRemove) {
				idxsPareto.remove(idxToRemove);
			}
			
			if(isAddFlat)
				idxsPareto.add(i);
			
			idxsToRemove.clear();
			isAddFlat = false;
		}
		
		for (Integer item : idxsPareto) {
			System.out.println((item + 1));
		}
		
	}
	
	private static void loadInput()
	{
		try {
			
			FileInputStream fi = new FileInputStream(fileName);
			Scanner scanner = new Scanner(fi);
			
			//counts
			StringTokenizer st = new StringTokenizer(scanner.nextLine());
			countFlats = Integer.parseInt(st.nextToken());
			countKs = Integer.parseInt(st.nextToken());
			
			flats = new Double[countFlats][];
			for (int i = 0; i < countFlats; i++) {
				flats[i] = new Double[countKs];
			}
			
			for (int i = 0; i < countKs; i++) 
			{
				st = new StringTokenizer(scanner.nextLine());
				for (int j = 0; j < countFlats; j++) {
					flats[j][i] = Double.parseDouble(st.nextToken());
				}
			}
			
			scanner.close();
			fi.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
