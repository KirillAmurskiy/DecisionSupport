import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Program {
	
	private static String fileName = "input.txt";
	
	private static ArrayList<Newspaper> nps = new ArrayList<Newspaper>();
	
	private static Newspaper comparable;
	
	public static void main(String[] args) {
		loadNewspapers();
		ArrayList<Newspaper> result = findBest();
		for (Newspaper np : result) {
			System.out.println("Best newspaper is " + np.Idx);
		}
	}
	
	private static ArrayList<Newspaper> findBest()
	{
		ArrayList<Newspaper> result = new ArrayList<Newspaper>();
		double max = 0;
		for (Newspaper np : nps) {
			if(np.K2 >= comparable.K2 &&
			   np.K3 >= comparable.K3 &&
			   np.K4 >= comparable.K4 &&
			   np.K5 >= comparable.K5 &&
			   np.K6 >= comparable.K6 &&
			   np.K7 >= comparable.K7 &&
			   np.K8 >= comparable.K8)
			{
				if(np.K1 > max)
				{
					max = np.K1;
					result.clear();
					result.add(np);
				}
			}
		}
		
		return result;
	}
	
 	private static void loadNewspapers()
	{
		try {
			
			FileInputStream fi = new FileInputStream(fileName);
			Scanner scanner = new Scanner(fi);
			
			//count newspapers
			int count = Integer.parseInt(scanner.nextLine()); 
			int currentIdx = 0;
			for (int i = 0; i < count; i++) 
			{
				String line = scanner.nextLine();
				nps.add(createNewspaper(line, currentIdx++));
			}
			comparable = createNewspaper(scanner.nextLine(), -1);
			
			scanner.close();
			fi.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 	
 	private static Newspaper createNewspaper(String line, int idx)
 	{
 		StringTokenizer st = new StringTokenizer(line);
		Newspaper np = new Newspaper();
		
		np.K1 = Double.parseDouble(st.nextToken());
		np.K2 = Double.parseDouble(st.nextToken());
		np.K3 = Double.parseDouble(st.nextToken());
		np.K4 = Integer.parseInt(st.nextToken());
		np.K5 = Integer.parseInt(st.nextToken());
		np.K6 = Integer.parseInt(st.nextToken());
		np.K7 = Double.parseDouble(st.nextToken());
		np.K8 = Integer.parseInt(st.nextToken());
		np.Idx = idx;
		
		return np;
 	}
	
	public static class Newspaper
	{
		public int Idx;
		
		public double K1;
		public double K2;
		public double K3;
		public int K4;
		public int K5;
		public int K6;
		public double K7;
		public int K8;
	}

}

