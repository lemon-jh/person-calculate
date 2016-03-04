package leaderus.study.person.sort;

import leaderus.study.person.entry.Person;
import leaderus.study.person.sort.util.PersonLoadUtils;



/**
 * @author zhang_91026@163.com 张福泉
 * 有关快速排序：http://blog.csdn.net/morewindows/article/details/6684558
 */
public class PersonQuicksort {
	
    public static void quickSort(Person[] array){ 
        if(array != null){  
            quickSort(array, 0, array.length-1);  
        }  
    }  
      
    private static void quickSort(Person [] array,int beg,int end){  
        if(beg >= end || array == null)  
            return;  
        int p = partition(array, beg, end);  
        
        quickSort(array, beg, p-1);  
        quickSort(array, p+1, end);  
    } 
    
	private static int partition(Person s[], int l, int r) {
		int i = l, j = r;
		Person x = s[l]; // s[l]即s[i]就是第一个坑
		while (i < j) {
			// 从右向左找小于x的数来填s[i]
			while (i < j && s[j].compareTo(x)>=0){
				j--;
			}
			if (i < j) {
				s[i] = s[j]; // 将s[j]填到s[i]中，s[j]就形成了一个新的坑
				i++;
			}
			// 从左向右找大于或等于x的数来填s[j]
			while (i < j && s[i].compareTo(x)<0) {
				i++;
			}
			if (i < j) {
				s[j] = s[i]; // 将s[i]填到s[j]中，s[i]就形成了一个新的坑
				j--;
			}
		}
		s[i] = x;//退出时，i等于j。将x填到这个坑中。
		return i;
	}


	public static void main(String[] args) {
		
		Person[] pers = PersonLoadUtils.loadAll();
		
		System.out.println(pers.length);
		long begTime = System.currentTimeMillis();
		PersonQuicksort.quickSort(pers);
		for (int i=0;i<100;i++) {
			System.out.println(pers[90*10000+i]);
		}
		long end = System.currentTimeMillis();
		System.out.println("time = "+(end-begTime));
		
	}
}
