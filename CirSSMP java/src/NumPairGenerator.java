import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class NumPairGenerator {
//	public static int aC[]= {1,10,20,50,200};
	public static int an[] = {5, 10, 20, 30};
	public static int am[] = {100,400,1000,2000};
	public static int aR[] = {4,8,12};
	public static int aK[]= {0,5,10,15};
	public static void main(String args[]) {
		try {
//			for (int c:aC)
			for (int n: an)
			for (int m: am)
			for (int R: aR)
			for (int K:aK)
			for (int seed = 0; seed < 5; seed++){
				Random random = new Random(seed);
				FileWriter out = new FileWriter("D:\\inputCir\\CZP_" + n + "_" + m + "_" + R +"_"+ K + ".in" + seed);
//				out.write(c + "\r\n");
				out.write(n + "\r\n");
				out.write(m + "\r\n");
				out.write(R + "\r\n");
				out.write(K+"\r\n");
				int i, x, y;
				for (i = 0; i < m; i++) {
					do {
						x = random.nextInt(n) + 1;
						y = random.nextInt(n) + 1;
					} while (x == y);
					out.write(x + " " + y + "\r\n");
				}
				out.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


