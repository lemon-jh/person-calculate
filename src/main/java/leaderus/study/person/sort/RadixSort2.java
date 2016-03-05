package leaderus.study.person.sort;

import java.util.Arrays;
import java.util.Random;

public class RadixSort2 {
	
	private static int [] counter,bucker;
	
	private static int length;
	
	public static  void sort(int array[],int radix) {
		
		if((radix & (radix-1)) != 0){ //判断输入是否为2的N次方
			throw new RuntimeException("请输入2或2的N次方作为基数");
		}
		
		int capacity = 1,i=0, max=Integer.MIN_VALUE;
		
		int loopCap = 1;
		
		length = array.length;
		
		counter = new int[radix];
		
		bucker = new int[length];
		
		for(int loop=1;loop<=capacity;loop++){
			nilOfArray(counter);
			
			//放入计数时,顺序放入  10,20,30  ==> counter[0] = 1,2,3...
			//统计边界 桶[0] 的数组边界即为3;
			//循环数组,将数组放入桶中时,若 正序放入,count[边界-1] = 10 ,与原数组统计计数时的数组位置不符,故需要倒序遍历放入桶中
			//
			//计数:					
			//					0			1		2		3		4	...		
			//数组下标0	10-->   count+1=1
			//数组下标1	20-->	count+1=2  
			//数组下标2	60-->	count+1=3  
			//数组下标3	30-->	count+1=4
			//
			//数组边界:			4
			//
			//正序入桶:采用桶边界-1的方式,10-->数组下标为 4-1=3	
			//倒序入桶:采用通边界-1的方式,30-->[4-1],60-->[3-1],20-->[2-1],10-->[1-1];
			//
			for(i=0;i<length;i++){ //循环所得,每个桶中元素的个数
				int num = getDigit(array[i],radix,loop);
				counter[num]++;
				if(loopCap == 1){
					max = max>array[i]?max:array[i];
				}
			}
			
			for(i=1;i<radix;i++){ //计算桶中右侧边界值
				counter[i] = counter[i] + counter[i-1];
			}
			
			for(i=length-1;i>=0;i--){
				int num = getDigit(array[i],radix,loop);
				bucker[counter[num]-1] = array[i];  
				--counter[num];
			}
			
			for(i = 0;i <= length-1; ++i){
				array[i] = bucker[i];    
			}
			
			if(loopCap == 1){
				capacity =  (int) (Math.log(max) / Math.log(radix))+1;
			}
			
			loopCap = 0;
		}
	}
	
	private final static void printArray(int [] temp){
		System.out.println("beg-----------");
		StringBuffer buff = new StringBuffer();
		for(int t:temp){
			buff.append(t+" ");
		}
		System.out.println(buff+"-----------");
	}
	
	
	/**
	 * 获取数字在桶中的索引
	 * @return
	 */
	private static int getDigit(int num,int radix,int rowNum) {
		return (num / getNom(rowNum,radix)) % radix;
		
		//return (num / ( radix * rowNum)) & (radix-1);
	}
	
	private static int getNom(int rownum,int radix){
		return (int) Math.pow(radix, rownum-1);
	}
	
	/**
	 * 将数组个元素置为空置
	 * @param array
	 */
	private static void nilOfArray(int array[]) {
		for (int i = 0; i < array.length; i++) {
			array [i] = 0; 
		}
	}
	
	public static void testSort() {
		int  br[] = {70,20, 80, 90, 589, 998, 965, 852, 123, 456, 789};
		RadixSort2.sort(br, 10);
		for (int i : br) {
			System.out.println(i);
		}
	}
	
	private static int [] randomArrays(int num) {
		int [] array = new int[num];
		for(int i=0;i<num;i++){
			array[i] = new Random().nextInt(Integer.MAX_VALUE);
		}
		return array;
	}
	
	public static void main(String[] args) {
		int [] array = randomArrays(10000000);
		long start = System.nanoTime();
		Arrays.sort(array);
		System.out.println("arrays.sort="+(System.nanoTime()-start));
		start = System.nanoTime();
		RadixSort2.sort(array, 512);
		System.out.println("radixs.sort="+(System.nanoTime()-start));
		
//		int  br[] = {70,20, 80, 90, 589, 998, 965, 852, 123, 456, 789};
//		RadixSort2.sort(br, 16);
//		printArray(br);
	}


}
