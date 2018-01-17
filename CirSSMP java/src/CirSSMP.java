import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CirSSMP {
	public static int L;//the number of iterations.
	public static int P;// The number of total ports.
	public static int N;// The number of total containers.
	public static int H;// The height limit of stack.
	public static int K;//allowed rehandles in each layout
	public static int UB;
	public static int height[]; // it is used to count height of each stack.
	public static Container[][] Layout;// it represents space of the ship;
	public static ArrayList<Container> ac= new ArrayList<Container>();
	public static ArrayList<Container> ac2= new ArrayList<Container>();
//	public static Collection<Container> ac= new ArrayList<Container>();
//	public static Collection<Container> ac2= new ArrayList<Container>();
	public static ArrayList<RecordResult> record= new ArrayList<RecordResult>();
	public static int Count_rehandle;
	public static int Count;
//	public static int aC[]= {50};//{1,10,20,50};;{200}//	
	public static int an[] = {5,10};//{5, 10, 20, 30 };//
	public static int am[] = {100,400};//{100,200,400,1000,2000};//
	public static int aR[] = {4,8,12};//{4, 8, 12 };//
	public static int aK[] = {0,5,10,15};//	{0, 10, 20, 50};//
	public static String file;

	public static void read() {
		ac.clear();
		ac2.clear();
		FileReader fileReader;
		try {
			fileReader = new FileReader(file);
			Scanner scanner = new Scanner(fileReader);
//			L=scanner.nextInt();
			P = scanner.nextInt(); // 将输入信息的下一个标记扫描为一个 int.
			N = scanner.nextInt();
			H = scanner.nextInt();
			K = scanner.nextInt();			
			// K=scanner.nextInt();
			for (int i = 1; i <= N; i++) {
				ac.add(new Container());
				ac.get(i - 1).o = scanner.nextInt();
				ac.get(i - 1).d = scanner.nextInt();
			    ac.get(i - 1).Seqnum = i;
			}
			scanner.close();
			fileReader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//deep copy
		Iterator<Container> iterator = ac.iterator(); 
		while(iterator.hasNext()){ 
			ac2.add(iterator.next().clone()); 
		}
		//output the container information to check 
//		for(int i=0;i<ac2.size();i++){
//			System.out.print(ac2.get(i).getO()+" ");			
//			System.out.println(ac2.get(i).getD());
//		}
	}	
	
	public static void heuristic(int seed) throws Exception {
		try {
			String str = "NUmber of circulation" + "," + "Number of ports" + "," + "Number of containers" + "," + "Limit height" + "," + "Allowed rehandle" + "," 
		+ "The Seqnumber of circulation" +","+ "Instance"+ "," + "NumofCir" + "," + "Numofport" + "," + "Numofsta"
		+ "," + "Number of used rehandle";
		     //+ "," + "Number of used rehandle";
			File file = new File("E:\\CirSSMP.csv");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWritter = new FileWriter("E:\\CirSSMP.csv", true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(str);
			bufferWritter.newLine();
			bufferWritter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		L=50;
		UB=(int) Math.ceil(2*N/H);
		height = new int[UB + 1];
		Layout = new Container[UB + 1][H + 1];
		for(Count=1;Count<=L;Count++){// record the number of circulation			
			for (int p = 1; p <= P; p++) {			
				Unloading_at_port(p);
				Loading_at_port(p);			
				int t = 0;
				for (int i = 1; i <= UB; i++) {
					if (height[i] != 0)
					t++;
					}
				RecordResult res= new RecordResult(Count,p,t);
			record.add(res);
			try {
				String str = L + ","+ P + "," + N + "," + H + "," + K + "," + Count + "," + seed
						+","+res.getNumofcir()+","+res.getSeqport()+","+res.getNumofsta()+","+Count_rehandle;
				//+ "," + Count_rehandle;
				FileWriter fileWritter = new FileWriter("E:\\CirSSMP.csv", true);
				BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
				bufferWritter.write(str);
				bufferWritter.newLine();
				bufferWritter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
//			System.out.print(res.getNumofcir()+" ");
//			System.out.print(res.getSeqport()+" ");
//			System.out.println(res.getNumofsta());
		}		
			//deep copy 
			Iterator<Container> iterator = ac2.iterator(); 
			while(iterator.hasNext()){ 
				ac.add(iterator.next().clone()); 
			}
			//output the container information to check 
//		for(int i=0;i<ac.size();i++){
//			System.out.print(ac.get(i).getO()+" ");			
//			System.out.println(ac.get(i).getD());
//		}
		
			//output the layout
//		 for(int t=H;t>=1;t--){
//			 for(int s=1;s<=UB;s++){
//			// for(int t=H;t>=1;t--){			
//			 if(Layout[s][t]!=null){
//			 System.out.print("("+Layout[s][t].o+","+Layout[s][t].d+")");
//			 }
//			 if(Layout[s][t]==null){
//			 System.out.print(" ");
//			 }
//			 }
//			 System.out.println("\n");
//			 }
//			 System.out.println("\n");
//			 System.out.println("\t");
			 
			 	
		
		System.out.println("Number of circulation: "+ L + "  Number of ports：" + P + "   Number of containers：" + N + "   Limit height：" + H
				+ "   Allowed rehandles：" + K + "  The Seqnumber of circulation: "+ Count + "   instance: " + seed +
				"  Number of current rehandle: "+ Count_rehandle);
		System.out.println("\t");
		}
//		for(int m=0;m<record.size();m++){
//			System.out.print(record.get(m).getNumofcir()+" ");
//			System.out.print(record.get(m).getSeqport()+" ");
//			System.out.println(record.get(m).getNumofsta());
	}
//		System.out.println("Number of circulation: "+ T + "  Number of ports：" + P + "   Number of containers：" + N + "   Limit height：" + H
//				+ "   Allowed rehandles：" + K + "  The Seqnumber of circulation: "+ Count + "   instance: " + seed +
//				"  Number of current rehandle: "+ Count_rehandle);
//		System.out.println("\t");
		//Count_rehandle=0;
	

	public static void Unloading_at_port(int p) {
		for (int s = 1; s <= UB; s++) {
			for (int t = height[s]; t >= 1; t--) {
				if (Layout[s][t].d == p) {
					Unloading_for_container(s, t, p);
				}
			}
		}
	}

	public static void Unloading_for_container(int stack, int tier, int port) {
		for (int t = height[stack]; t > tier; t--) {
			// handle blocking containers;
			Container copycn=Layout[stack][t];
	        copycn.o = port;
			ac.add(copycn);
			Layout[stack][t] = null;
			height[stack]--;
			Count_rehandle--;			
		}	
		// handle target container;
		Layout[stack][tier] = null;
		height[stack]--;
	}

	public static void Loading_at_port(int port) throws Exception {		
		int[] nearport = new int[UB + 1];
		int[] originport = new int[UB+1];
		
		for (int s = 1; s <= UB; s++) {			
			int dnext;//represents the D(c) when it is smaller than O(C)
			int conori;//determine which loop it belongs
			nearport[s] = 2*P-1;
			originport[s]=port;
			for (int j = 1; j <= H; j++) {
				if(Layout[s][j]==null)
					break;
				if(Layout[s][j].o>port)
					conori=Layout[s][j].o-P;
				else
					conori=Layout[s][j].o;
				if(Layout[s][j].d <conori){
					dnext=Layout[s][j].d+P;
				}
				else
					dnext=Layout[s][j].d;
				if (Layout[s][j] != null) {// the j-th tier is not null pointer, do the following											
					if (dnext < nearport[s])
						nearport[s] = dnext;
				} else // the j-th tier is null pointer, break the inner loop
					break;
			}
		}
		Collections.sort(ac);
		while (ac.size() != 0 && ac.get(0).o == port) {
			int cndes;
			// use ac.size()!=0 to ensure ac is not empty, avoid the error type "source not found"			
			Container cn = ac.remove(0);
			int st = Loading_for_container(cn, Layout, nearport);
			Layout[st][height[st] + 1] = cn;
			height[st]++;
			if(cn.d <cn.o)
				cndes=cn.d+P;		
			else
				cndes=cn.d;
			if(cndes < nearport[st])
				nearport[st] = cndes;			
		}
	}

	public static int Loading_for_container(Container con, Container[][] Layout, int[] nearport) throws Exception {

		if (Count_rehandle ==K) {
			return loading_rehandle_equalK(con, nearport);
		} else
			return loading_rehandle_lessK(con, nearport);
	}

	public static int loading_rehandle_lessK(Container con, int[] nearport) throws Exception {
		// TODO Auto-generated method stub
		int sbest;
		int condes;
		if(con.d>con.o)
			condes=con.d;
		else
			condes=con.d+P;		
		List<Integer> S1 = new ArrayList<Integer>();		
		List<Integer> S2 = new ArrayList<Integer>();		
		List<Integer> S3 = new ArrayList<Integer>();		
		for (int s = 1; s <= UB; s++) {
			if (height[s] == H)
				continue;
			else if (nearport[s] >= condes && nearport[s] != 2*P-1)
				S1.add(s);
			else if (nearport[s] < condes)
				S2.add(s);
			else
				S3.add(s);
		}
		
		if (S1.size() != 0) {
			int min=nearport[S1.get(0)];
			sbest=S1.get(0);
			for(int i=1;i<S1.size();i++){
				if(nearport[S1.get(i)]<min){
					min=nearport[S1.get(i)];
					sbest=S1.get(i);				
				}
					
			}
			S1.clear();
			return sbest;	
		}
		
		if (S2.size() != 0) {
			int min=nearport[S2.get(0)];
			sbest=S2.get(0);
			for(int i=1;i<S2.size();i++){
				if(nearport[S2.get(i)]<min){
					min=nearport[S2.get(i)];
					sbest=S2.get(i);				
				}
					
			}
			S2.clear();
			Count_rehandle++;
			return sbest;	
		} else
			return S3.get(0);
	}

	public static int loading_rehandle_equalK(Container con, int[] nearport) throws Exception {	
		// TODO Auto-generated method stub
		int sbest;
		int condes;
		if(con.d>con.o)
			condes=con.d;
		else
			condes=con.d+P;
		List<Integer> S0 = new ArrayList<Integer>();		
		for (int s = 1; s <= UB; s++) {
			if (height[s] == H)
				continue;
			if (nearport[s] >= condes) {
		  //if (nearport[s] >= condes && (nearport[s]>P && con.d < nearport[s]-P)) { // use this constraint condition to decide the added stack										
				S0.add(s);
				}
		}
		sbest=S0.get(0);
		int min=nearport[S0.get(0)];		
		for(int i=1;i<S0.size();i++){
			if(nearport[S0.get(i)]<min){
				min=nearport[S0.get(i)];
				sbest=S0.get(i);				
			}				
		}
		
		S0.clear();
		return sbest;	
		
	}


	public static void main(String[] args) throws Exception {
//		try {
//			String str = "NUmber of circulation" + "," + "Number of ports" + "," + "Number of containers" + "," + "Limit height" + "," + "Allowed rehandle" + "," + "Instance";
//		     //+ "," + "Number of used rehandle";
//			File file = new File("D:\\CirSSMP.csv");
//			if (!file.exists()) {
//				file.createNewFile();
//			}
//			FileWriter fileWritter = new FileWriter("D:\\CirSSMP.csv", true);
//			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
//			bufferWritter.write(str);
//			bufferWritter.newLine();
//			bufferWritter.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		long starTime=System.currentTimeMillis();		
//	for (int c:aC)
		for (int n : an)
			// 这是遍历数组的一种方式forEach...int 是数组元素类型 ，n是为数组元素起的别名，an是数组本身
			for (int m : am)
				for (int R : aR)
					for (int K : aK)
						for (int seed =0; seed < 5; seed++) {
					//	for (int seed = 0; seed < 5; seed++) {
							Count_rehandle = 0;
							file = "D:\\inputCir\\CZP_" + n + "_" + m + "_" + R + "_" + K + ".in" + seed;
							read();						
							heuristic(seed);														
							
//							try {
//								String str = T + ","+ P + "," + N + "," + H + "," + K + "," + seed;
//								//+ "," + Count_rehandle;
//								FileWriter fileWritter = new FileWriter("D:\\CirSSMP.csv", true);
//								BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
//								bufferWritter.write(str);
//								bufferWritter.newLine();
//								bufferWritter.close();
//							} catch (IOException e) {
//								e.printStackTrace();
//							}
						}
	long endTime=System.currentTimeMillis();
	long Time=endTime-starTime;
	System.out.println(Time);
	}
}
