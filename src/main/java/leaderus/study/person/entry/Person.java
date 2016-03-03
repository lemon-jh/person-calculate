package leaderus.study.person.entry;

import java.math.BigInteger;
import java.util.Random;

public class Person {

	private BigInteger id;//在不确定整形长度的情况下,使用商业大整形数据类型
	
	private String name;
	
	private short age;
	
	private static final Random random = new Random();
	
	private static final String chars = "abcdefghijklmnopqrstuvwxyz1234567890";
	
	private static final String separator = ",";
	
	public Person() {
		super();
		this.name = "";
	}
	
	public Person(String id,String name,String age) {
		super();
		this.name = name;
		this.id = new BigInteger(id);
		this.age = Short.valueOf(age);
	}
	
	public Person(String str) {
		String [] temp = str.split(separator);
		this.name = temp[1];
		this.id = new BigInteger(temp[0]);
		this.age = Short.valueOf(temp[2]);
	}
	

	
	public Person fillRandomData() {
		this.age = (short) (random.nextInt(18)+1);
		for(int i=0;i<8;i++){
			this.name += chars.charAt((int)(Math.random() * chars.length()));
		}
		this.id = new BigInteger(64,random);
		return this;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getAge() {
		return age;
	}

	public void setAge(short age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return id + separator + name + separator + age ;
	}
	
}
