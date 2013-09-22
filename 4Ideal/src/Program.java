import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Program {

	private static String inputFile = "input.txt";
	
	private final static int countP = 5;
	
	private static int countMkos;
	private static Mko w;
	
	public static void main(String[] args) {
		
		ArrayList<Mko> mkos = loadMkos();
		
		int i = 1;
		int before;
		int after;
		do {
			System.out.println("Iteration " + i);
			printMkos(mkos);
			
			before = mkos.size();
			
			iteration(mkos);
			
			after = mkos.size();
			i++;
		} while (before > after);
		
		System.out.println("Result:");
		printMkos(mkos);
	}
	
	private static void iteration(ArrayList<Mko> mkos)
	{
		Mko worse = getWorse(mkos);
		Mko ideal = getIdeal(mkos);
		
		ArrayList<D> d = getD(ideal, worse, mkos);
		
		System.out.println("L:");
		calcL(d);
		
		printL(d);
		
		ArrayList<D>[] ordered = getOrders(d);
		
		System.out.println("Ordered:");
		printOrder(ordered);
		
		excludeWorstMkos(ordered, mkos);
	}
	
	private static void excludeWorstMkos(ArrayList<D>[] ordered, ArrayList<Mko> mkos)
	{
		ArrayList<Integer> idxMkoToExclude = new ArrayList<Integer>();
		
		for (int i = ordered[0].size() - 1; i > 0; i--) {
			
			int idx = ordered[0].get(i).idx;
			for (int p = 0; p < countP; p++) {
				
				if(ordered[p].get(i).idx != idx)
				{
					idx = -1;
					break;
				}
			}
			
			if(idx == -1)
				break;
			idxMkoToExclude.add(idx);
		}
		
		for (Integer idx : idxMkoToExclude) {
			
			for (int i = 0; i < mkos.size(); i++) {
				
				Mko mko = mkos.get(i);
				if(mko.idx == idx)
				{
					mkos.remove(mko);
					break;
				}
				
			}
			
		}
	}	
	
	private static ArrayList<D>[] getOrders(ArrayList<D> ds)
	{
		ArrayList<D>[] ordered = new ArrayList[countP];
		
		for (int p = 0; p < countP; p++) {
			ordered[p] = getOrder(ds, p);
		}
		
		return ordered;
	}
	
	private static ArrayList<D> getOrder(ArrayList<D> ds, int p)
	{
		ArrayList<D> ordering = new ArrayList<D>();
		for (D d : ds) {
			ordering.add(d);
		}
		
		for (int i = 0; i < ds.size(); i++) {
			
			for (int j = i + 1; j < ds.size(); j++) {
				
				D first = ordering.get(i);
				D second = ordering.get(j);
				if(first.l[p] < second.l[p])
				{
					ordering.remove(j);
					ordering.add(j, first);
					
					ordering.remove(i);
					ordering.add(i, second);
				}
				
			}
		}
		
		return ordering;
	}
	
	private static void printMkos(ArrayList<Mko> mkos)
	{
		for (Mko mko : mkos) {
			System.out.print((mko.idx + 1) + " ");
		}
		System.out.println();
	}
	
	private static void printOrder(ArrayList<D>[] orders)
	{
		for (int p = 0; p < countP; p++) {
			
			for (D d : orders[p]) {
				
				System.out.print((d.idx + 1) + " ");
				
			}
			System.out.println();
		}
	}
	
	private static void printL(ArrayList<D> ds)
	{
		for (int i = 0; i < countP; i++) {
			
			for (D d : ds) {
				
				System.out.format("%.2f ", d.l[i]);
				
			}
			
			System.out.println();
			
		}
	}
	
	private static void calcL(ArrayList<D> ds)
	{
		for (D d : ds) {
			
			d.l = new double[countP];
			
			for (int i = 0; i < countP; i++) {
				
				double p = i + 1;
				double sum = 0;
				
				sum += Math.pow(w.k1*(1 - d.k1), p); 
				sum += Math.pow(w.k2*(1 - d.k2), p);
				sum += Math.pow(w.k3*(1 - d.k3), p);
				
				d.l[i] = Math.pow(sum, 1.0/p);
			}
		}
	}
	
	private static ArrayList<D> getD(Mko ideal, Mko worse, ArrayList<Mko> mkos)
	{
		ArrayList<D> d = new ArrayList<D>();
		
		for (Mko mko : mkos) {
			
			D newD = new D();
			newD.idx = mko.idx;
			
			newD.k1 = (ideal.k1 - mko.k1)/(ideal.k1 - worse.k1);
			newD.k2 = (ideal.k2 - mko.k2)/(ideal.k2 - worse.k2);
			newD.k3 = (ideal.k3 - mko.k3)/(ideal.k3 - worse.k3);
			
			d.add(newD);
		}
		
		return d;
	}
	
	private static Mko getIdeal(ArrayList<Mko> mkos)
	{
		Mko ideal = new Mko();
		ideal.k1 = Double.MAX_VALUE;
		ideal.k3 = Double.MAX_VALUE;
		
		for (Mko mko : mkos) {
			
			if(mko.k1 < ideal.k1)
				ideal.k1 = mko.k1;
			
			if(mko.k2 > ideal.k2)
				ideal.k2 = mko.k2;
			
			if(mko.k3 < ideal.k3)
				ideal.k3 = mko.k3;
		}
		
		return ideal;
	}
	
	private static Mko getWorse(ArrayList<Mko> mkos)
	{
		Mko worse = new Mko();
		worse.k2 = Double.MAX_VALUE;
		
		for (Mko mko : mkos) {
			if(mko.k1 > worse.k1)
				worse.k1 = mko.k1;
			
			if(mko.k2 < worse.k2)
				worse.k2 = mko.k2;
			
			if(mko.k3 > worse.k3)
				worse.k3 = mko.k3;
		}
		
		return worse;
	}
	
	
	private static ArrayList<Mko> loadMkos()
	{
		try {
			
			FileInputStream fi = new FileInputStream(inputFile);
			Scanner sc = new Scanner(fi);
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			
			countMkos = Integer.parseInt(st.nextToken());
			
			ArrayList<Mko> mkos = new ArrayList<Mko>();
			for (int i = 0; i < countMkos; i++) {
				
				Mko mko = new Mko();
				mko.idx = i;
				
				st = new StringTokenizer(sc.nextLine());
				
				mko.k1 = Double.parseDouble(st.nextToken());
				mko.k2 = Double.parseDouble(st.nextToken());
				mko.k3 = Double.parseDouble(st.nextToken());
				
				mkos.add(mko);
			}
			
			st = new StringTokenizer(sc.nextLine());
			
			w = new Mko();
			w.idx = -1;
			w.k1 = Double.parseDouble(st.nextToken());
			w.k2 = Double.parseDouble(st.nextToken());
			w.k3 = Double.parseDouble(st.nextToken());
			
			sc.close();
			
			return mkos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static class D extends Mko
	{
		public double[] l;
	}
	
	private static class Mko
	{
		public int idx;
		
		public double k1;
		public double k2;
		public double k3;
	}
}
