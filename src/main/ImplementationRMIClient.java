package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ImplementationRMIClient implements IRMIClient {
	public ImplementationRMIClient() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String sendme(String name, int line) {
		BufferedReader br = null;
		FileReader fr = null;
		String sreturn ="";
		try {
			fr = new FileReader(System.getProperty("user.dir")+"/src/data/"+name);
			br = new BufferedReader(fr);

			String sCurrentLine;
			while (((sCurrentLine = br.readLine()) != null)) {
				String[] lines = sCurrentLine.split("-");
				if( Integer.parseInt(lines[0]) == line) {
					sreturn = lines[0]+"-"+lines[1];
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sreturn;
	}

	@Override
	public ArrayList<Integer> whichline(String name) {
		BufferedReader br = null;
		FileReader fr = null;
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		try {
			fr = new FileReader(System.getProperty("user.dir")+"/src/data/"+name);
			br = new BufferedReader(fr);

			String sCurrentLine;
			while ( (sCurrentLine = br.readLine()) != null) {
				String[] lines = sCurrentLine.split("-");
				int index = Integer.parseInt(lines[0]);
				numbers.add(index);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return numbers;
	}

}
