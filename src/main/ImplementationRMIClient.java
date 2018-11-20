package main;

import java.io.BufferedReader;
import java.io.File;
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
			try {
				fr = new FileReader("./data/"+name);
				br = new BufferedReader(fr);

				String sCurrentLine;
				int cont = 0;
				while (((sCurrentLine = br.readLine()) != null) && cont<line) {
					System.out.println(sCurrentLine);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		return null;
	}

	@Override
	public ArrayList<Integer> whichline(String name) {
		BufferedReader br = null;
		FileReader fr = null;
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		try {
			fr = new FileReader("./data/"+name);
			br = new BufferedReader(fr);

			String sCurrentLine;
			while ( (sCurrentLine = br.readLine()) != null) {
				String[] lines = sCurrentLine.split("-");
				int index = Integer.parseInt(lines[0]);
				numbers.add(index);
				System.out.println(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return numbers;
	}

}
