package leaderus.study.person.sort.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import leaderus.study.person.entry.Person;

public class PersonLoadUtils {

	private static final String local_file = "c:\\leaderus\\data\\persondb.txt";

	public static Person[] loadAll(){
		
		Person [] person = new Person[100*10000];
		
		File personFile = new File(local_file);
		String personStr = "";
		BufferedReader in = null;
		try {
			in =new BufferedReader(new FileReader(personFile));
			int i=0;
	        while((personStr=in.readLine())!=null){
	        	person[i++] = new Person(personStr);
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
				in = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return person;
	}

}
