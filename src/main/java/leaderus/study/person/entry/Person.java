package leaderus.study.person.entry;

import java.util.Random;

public class Person implements Comparable{

	private long id;//在不确定整形长度的情况下,使用商业大整形数据类型
	
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
		this.id = Long.valueOf(id);
		this.age = Short.valueOf(age);
	}
	
	public Person(String str) {
		String [] temp = str.split(separator);
		this.name = temp[1];
		this.id = Long.valueOf(temp[0]);
		this.age = Short.valueOf(temp[2]);
	}
	

	
	public Person fillRandomData() {
		this.age = (short) (random.nextInt(18)+1);
		for(int i=0;i<8;i++){
			this.name += chars.charAt((int)(Math.random() * chars.length()));
		}
		this.id = (long)(Math.random()*1000000000);
		return this;
	}


	public int compareTo(Object o) {
		if (o == null) {
            return -1;
        }
		if (!(o instanceof Person)){
			return -1;
		}
        if (getClass() != o.getClass()) {
            return -1;
        }
        Person p = (Person) o;
        
        return this.id>p.getId()?1:this.id==p.getId()?0:-1;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
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
