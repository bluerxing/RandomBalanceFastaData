package edu.bluer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class dataShuffle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inPath = args[0];
		String outPath = args[1];
		
		List<String> list_p = new ArrayList<String>();
		List<String> list_n = new ArrayList<String>();

		try {

			
			BufferedReader br = new BufferedReader(new FileReader(inPath));

			String str = "";
			int num_p = 0;
			int num_n = 0;
			while (br.ready()) {
				String title = br.readLine();
				String line = br.readLine();
				if (title.startsWith(">P")) {
					num_p++;
					str = title + "\r\n" + line;
					list_p.add(str);
				} else {
					num_n++;
					str = title + "\r\n" + line;
					list_n.add(str);
				}

			}

			Collections.shuffle(list_n);

			for (int i = 0; (double)num_n / num_p > 1; i++) {
				BufferedWriter bw = new BufferedWriter(new FileWriter(outPath.replace(".fasta", i+".fasta")));

				for (int j = 0; j < num_p; j++) {
					bw.write(list_p.get(j) + "\r\n");
				}
				for (int j = 0; j < num_p; j++) {
					bw.write(list_n.get(j) + "\r\n");
				}
				bw.flush();
				bw.close();
				br.close();
				
				num_n=num_n-num_p;
			}

			

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
