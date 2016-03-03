package leaderus.study.person.calculate.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import com.google.common.collect.ArrayTable;

import leaderus.study.person.calculate.AbstractPersonCalculate;
import leaderus.study.person.entry.Person;

/**
* @Description: 使用arrayTable实现分组
* @author 张福泉 zhang_91026@163.com 
* @date 2016年3月3日 下午6:23:18 
*/
public class MyPersonCaclByArrayTable extends AbstractPersonCalculate{
	
	private ArrayTable<String,String,Integer> personTable= ArrayTable.create(Arrays.asList(rowKey)
			,Arrays.asList(conKey));
	
	private int [] countIndex = new int[]{0,0,0};
	
	@Override
	protected final void sortPersons() {
		Set<String> rowSet = this.personTable.rowKeySet();
		String s = "", temp="";
		for (Iterator<String> iterator = rowSet.iterator(); iterator.hasNext();) {
			temp = (String) iterator.next();
			s = "年龄段:"+temp+" 的共有"+countIndex[getListIndex(temp)]+"个,首字母";
			for (String con : conKey) {
				Integer count = personTable.get(temp, con);
				s += "为"+con+"的"+(count==null?0:count)+"个,"; 
			}
			System.out.println(s);
		}
	}

	@Override
	protected final void doColect(Person person) {
		int age = person.getAge();
		String con = conKey[this.getConKeyIndex(person.getName())];
		String row = this.getRowKey(age);
		Integer count = personTable.get(row,  con);
		count = (count ==null?1:(count+1));
		personTable.put(row, con, count);
	}
	
	private final String getRowKey(int age) {
		int index = getrowKeyIndex(age);
		countIndex[index]++;
		return rowKey[index];
	}
	
	
	private int getListIndex(String row) {
		for (int i = 0; i < rowKey.length; i++) {
			if(row.equals(rowKey[i])){
				return i;
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		AbstractPersonCalculate calculate = new MyPersonCaclByArrayTable();
		calculate.calculate();
	}
	
}
