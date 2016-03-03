package leaderus.study.person.calculate.impl;

import leaderus.study.person.calculate.AbstractPersonCalculate;
import leaderus.study.person.entry.Person;

/**
* @Description: 使用二维数组实现分组
* @author 张福泉 zhang_91026@163.com 
* @date 2016年3月3日 下午6:23:41 
*/
public class MyPersonCaclByMy extends AbstractPersonCalculate{
	
	public int[][] target = new int [rowKey.length][conKey.length];
	
	@Override
	protected final void sortPersons() {
	
		for(int i=0;i<target.length;i++){
			int count = 0;
			String s = "";
			for(int j=0;j<target[i].length;j++){
				count += target[i][j];
				s += "为"+conKey[j]+"的"+target[i][j]+"个,"; 
			}
			s = "年龄段:"+rowKey[i]+" 的共有"+count+"个,首字母"+s;
			System.out.println(s);
		}
	}

	@Override
	protected final void doColect(Person person) {
		
		int rowIndex = this.getrowKeyIndex(person.getAge());
		
		int conIndex = this.getConKeyIndex(person.getName());
		
		target[rowIndex][conIndex]++;
	}
	
	public static void main(String[] args) {
		AbstractPersonCalculate calculate = new MyPersonCaclByMy();
		
		calculate.calculate();
	}
}
