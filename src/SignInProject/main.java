package SignInProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class main {

	public static void main(String[] args) {

		try {
			BufferedReader br= new BufferedReader(new FileReader("C:/Users/Dell/Downloads/NetBeansProjects/try.txt"));
			String st;
			while((st=br.readLine())!=null) {
				String str[]=st.split(",");
				for(String s: str)
					System.out.println(s.trim());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
