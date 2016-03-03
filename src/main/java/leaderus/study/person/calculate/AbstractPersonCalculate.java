package leaderus.study.person.calculate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import leaderus.study.person.entry.Person;

public abstract class AbstractPersonCalculate {
	
	public String [] rowKey = new String []{"6-11","16-18","other"};
	
	public String [] conKey = new String []{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","other"};
	
	private static final String local_file = "c:\\leaderus\\data\\persondb.txt";

	protected abstract void sortPersons(); //计算方法
	
	protected abstract void doColect(Person person);//加入到不同实现的collection中
	
	public final void calculate() {
		long begTime = System.currentTimeMillis(); 
		this.loadDataToCollection();
		this.sortPersons();
		long endTime = System.currentTimeMillis(); 
		System.out.println("used time = "+ (endTime-begTime));
	}
	
	protected final void loadDataToCollection() {
		File personFile = new File(local_file);
		String personStr = "";
		BufferedReader in = null;
		try {
			in =new BufferedReader(new FileReader(personFile));
	        while((personStr=in.readLine())!=null){
	        	this.doColect(new Person(personStr));
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
	}
	
	protected final int getrowKeyIndex(int age) { //根据年龄获取存取的索引
		if(age>=6 && age<=11){
			return 0;
		}else if(age>=16 && age<=18){
			return 1;
		}else{
			return 2;
		}
	}
	
	protected final int getConKeyIndex(String name) {//根据name首字符获取索引
		int temp = name.charAt(0);
		if(temp>=97 && temp<=122){
			return 	temp%97;
		}else{
			return  26;
		}
	}

}
