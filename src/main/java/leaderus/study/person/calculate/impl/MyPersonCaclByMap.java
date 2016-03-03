package leaderus.study.person.calculate.impl;

import java.util.HashMap;
import java.util.Map;

import leaderus.study.person.calculate.AbstractPersonCalculate;
import leaderus.study.person.entry.Person;

/**
* @Description: 通过hashMap实现
* @author 张福泉 zhang_91026@163.com 
* @date 2016年3月3日 下午6:06:34 
*/
public class MyPersonCaclByMap extends AbstractPersonCalculate{
	
	public Map<String,Map<String,Integer>> map;
	
	public MyPersonCaclByMap() {
		
		map = new HashMap<String, Map<String,Integer>>(this.rowKey.length);
		
		for(int i=0;i<this.rowKey.length;i++){
			map.put(this.rowKey[i], new HashMap<String,Integer>(27));
		}
		
	}
	
	@Override
	protected final void sortPersons() {
		
	}

	@Override
	protected final void doColect(Person person) {

	}
	
	public static void main(String[] args) {
		AbstractPersonCalculate calculate = new MyPersonCaclByMap();
		calculate.calculate();
	}
}
