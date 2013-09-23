import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Program {

	private static String fileInput = "input.txt";
	private static String fileOutput = "output.txt";
	
	private static int n;
	private static int[][] R1;
	private static int[][] R2;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File file = new File(fileOutput);
		file.delete();
		
		load();
		
		int[][] Rd1 = dual(R1);
		
		output(R1, "R1");
		output(Rd1, "Rd1");
		toGraph(R1, "R1.dot");
		toGraph(Rd1, "Rd1.dot");
		
		int[][] R02 = transitiveClosing(R2);
		output(R2, "R2");
		output(R02, "R02");
		toGraph(R2, "R2.dot");
		toGraph(R02, "R02.dot");
		
		int[][] R1oR2 = composition(R1, R2);
		output(R1oR2, "R1oR2");
		toGraph(R1oR2, "R1oR2.dot");
		
		System.out.println("Ok");
		
	}
	
	private static void load()
	{
		
		try {
			
			FileInputStream fi = new FileInputStream(fileInput);
			Scanner sc = new Scanner(fi);
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			
			n = Integer.parseInt(st.nextToken());
			R1 = new int[n][n];
			R2 = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(sc.nextLine());
				for (int j = 0; j < n; j++) {
					R1[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			sc.nextLine();
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(sc.nextLine());
				for (int j = 0; j < n; j++) {
					R2[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static int[][] reverse(int[][] r)
	{
		
		int[][] reverseR = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				
				reverseR[i][j] = r[j][i];
				
			}
		}
		return reverseR;
	}

	private static int[][] addition(int[][] r)
	{
		int[][] addR = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				
				addR[i][j] = (r[i][j] == 0) ? 1 : 0;
				
			}
		}
		return addR;
	}
	
	private static int[][] dual(int[][] r)
	{
		
		int[][] dualR = addition(reverse(r));
		return dualR;
	}

	private static int[][] transitiveClosing(int[][] r)
	{
		int[][] r1 = r.clone();
		do {
			int[][] r2 = union(r1, composition(r1,r1));
			if(equals(r1,r2))
			{
				r1 = r2;
				break;
			}
			r1 = r2;
		} while (true);
		
		return r1;
	}
	
	private static int[][] union(int[][] r1, int[][] r2)
	{
		int[][] un = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				un[i][j] = (r1[i][j] == 1 || r2[i][j] == 1) ? 1 : 0;
			}
		}
		
		return un;
	}
	
	private static int[][] composition(int[][] r1, int[][] r2)
	{
		int[][] composit = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int z = 0; z < n; z++) {
					if(r1[i][z] == 1 && r2[z][j] == 1)
						composit[i][j] = 1;
				}
			}
		}
		
		return composit;
	}
	
	private static boolean equals(int[][] r1, int[][] r2)
	{
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(r1[i][j] != r2[i][j])
					return false;
			}
		}
		
		return true;
	}
	
	//Output
	private static void output(int[][] r, String name)
	{
		try {
			
			PrintWriter pw = new PrintWriter(new FileOutputStream(fileOutput, true));
			
			pw.println(name);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					pw.print(r[i][j]);
					if(j < n-1)
						pw.print(',');
				}
				
				pw.println();
			}
			
			pw.println();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void toGraph(int[][] r, String fileName)
	{
		try {
			
			PrintWriter pw = new PrintWriter(fileName);
			
			pw.println("digraph G{");
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					
					if(r[i][j] == 1)
						pw.println(i + "->" + j + ";");
					
				}
			}
			
			pw.print("}");
			
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
