import java.awt.List;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FileWriter {

	public static String getTokens(String str,int n) {
	    ArrayList <String> tokens = new ArrayList<>();
	    StringTokenizer tokenizer = new StringTokenizer(str, "-");
	    while (tokenizer.hasMoreElements()) {
	        tokens.add(tokenizer.nextToken());
	    }
	   return tokens.get(n);
	}
	
	public static void escritor(ArrayList <String> lines,String name) throws Exception{
		ArrayList <String> linesOrder=new ArrayList<>();
		PrintWriter writer = new PrintWriter(name, "UTF-8");
		
		for(int i=0;i<lines.size();i++) {
		linesOrder.add(" ");
		System.out.println("Size of order"+linesOrder.size());
		}
			for(int i=0;i<lines.size();i++) {
				String aux=lines.get(i);
				int index;
				index=Integer.parseInt(getTokens(aux,0));
				linesOrder.set(index-1, getTokens(aux,1));
			}
		
			for(int i=0;i<linesOrder.size();i++) {
				System.out.println(i+linesOrder.get(i));
				writer.println(linesOrder.get(i));
			}
			writer.close();
	}
	
	public static void main(String[] args) {
		
	String name="Myfile.txt";
	ArrayList <String> lines=new ArrayList<>();
	
	lines.add("5-EEEEE");
	lines.add("2-BBBBB");
	lines.add("7-GGGGG");
	lines.add("1-AAAAA");
	lines.add("6-FFFFF");
	lines.add("3-CCCCC");
	lines.add("4-DDDDD");
	//crear archivo
	try {
		escritor(lines,"NEWFILE.txt");
	}
	catch(Exception e) {}
	
	}

}
