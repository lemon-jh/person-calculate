package leaderus.study.person.sort;

import leaderus.study.person.entry.Person;
import leaderus.study.person.sort.util.PersonLoadUtils;

public class PersonHeapSort {
	
	public static void sort(Comparable[] a) {

		for (int i = a.length / 2; i >= 0; i--) {
			percDown(a, i, a.length);
		} 

		for (int i = a.length - 1; i > 0; i--) {
			swapReferences(a, 0, i); /* 在数组中删除堆顶元素 */
			percDown(a, 0, i);
		}
	}
	 
	/**
	 * 
	 * 获取左侧叶子节点,在数组中的索引位置
	 * @param i the index of an item in the heap.
	 */
	private static int leftChild(int i) {
		return 2 * i + 1;
	}

	/**
	 * 
	 * 创建堆
	 * @param 需要排序的数组
	 * @param index 需要创建堆的根节点在数组中的索引
	 * @param arrayLength n 数组中长度
	 */
	private static void percDown(Comparable[] a, int index, int arrayLength) {
		int child;
		Comparable tmp;
		for (tmp = a[index]; leftChild(index) < arrayLength; index = child) {
			child = leftChild(index);
			if (child != arrayLength - 1 && a[child].compareTo(a[child + 1]) < 0){
				child++;
			}
			if (tmp.compareTo(a[child]) < 0){
				a[index] = a[child];
			}else{
				break;
			}
		}
		a[index] = tmp;
	}

	// 交换元素位置  
	public static final void swapReferences(Object[] a, int index1, int index2) {
		Object tmp = a[index1];
		a[index1] = a[index2];
		a[index2] = tmp;
	}
	
	public static void main(String[] args) {
		
		Person[] pers = PersonLoadUtils.loadAll();
		
		System.out.println(pers.length);
		
		long begTime = System.currentTimeMillis();
		
		PersonHeapSort.sort(pers);
		
		for (int i=0;i<100;i++) {
			System.out.println(pers[90*10000+i]);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("time = "+(end-begTime));
	}
}
