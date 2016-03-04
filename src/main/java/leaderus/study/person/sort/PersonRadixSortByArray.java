package leaderus.study.person.sort;

import leaderus.study.person.entry.Person;
import leaderus.study.person.sort.util.PersonLoadUtils;

public class PersonRadixSortByArray {
	
	private static void radixSort(Person[] array,int radix) {
		
		int n = 1;// 代表位数对应的数：1,10,100...
		
		int k = 0;// 保存每一位排序后的结果用于下一位的排序输入
		
		int d = radix;
		
		int length = array.length;
		
		Person[][] bucket = new Person[radix][length];// 排序桶用于保存每次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
		
		int[] order = new int[radix];// 用于保存每个桶里有多少个数字
		
		long max = 0;//用于比较最大值与基数,自动增加位数
		
		long bi;
		
		while (n < d) {
			for (Person person : array){ // 将数组array里的每个数字放在相应的桶里
				bi = person.getId();
				int digit = (int)((bi / n) % radix); //从低位查找
				bucket[digit][order[digit]++] = person;
				max = (bi>max?bi:max);
			}
			
			for (int i = 0; i < radix; i++){// 将前一个循环生成的桶里的数据覆盖到原数组中用于保存这一位的排序结果
				if (order[i] != 0){// 这个桶里有数据，从上到下遍历这个桶并将数据保存到原数组中
					for (int j = 0; j < order[i]; j++) {
						array[k++] = bucket[i][j];
					}
				}
				order[i] = 0;// 将桶里计数器置0，用于下一次位排序
			}
			
			n *= radix;
			
			if(max/n>0){ 
				d = d*radix;
			}
			k = 0;// 将k置0，用于下一轮保存位排序结果
		}
	}
	
	@SuppressWarnings("unused")
	private static void printArray(Person [][] temp){
		System.out.println("beg-----------");
		StringBuffer buff = new StringBuffer();
		for(Person [] t:temp){
			for(Person x:t){
				if(x!=null){
					buff.append(x.getId()+" ");
				}
			}
			buff.append("\n");
		}
		System.out.println(buff+"-----------");
	}

	public static void main(String[] args) {
		
		Person[] pers = PersonLoadUtils.loadAll();
		
		System.out.println(pers.length);
		
		long begTime = System.currentTimeMillis();
		
		PersonRadixSortByArray.radixSort(pers,10);
		
		for (int i=0;i<100;i++) {
			System.out.println(pers[90*10000+i]);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("time = "+(end-begTime));
	}
}
