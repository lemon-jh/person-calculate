package leaderus.study.person.sort;

import java.util.Random;

import leaderus.study.person.entry.Person;
import leaderus.study.person.sort.util.PersonLoadUtils;

/**
 * 基数排序
 * 
 * @author zhangfq
 * 
 */
public class PersonRadixSortByNode {
    private static final int RADIX = 10;

    public static void sort(Person[] data) {
    	
        long start = System.nanoTime();

        // 遍历一遍，找到最大值，确定最高位数
        long max = Long.MIN_VALUE;
        
        int i = 0;
        
        for (; i < data.length; i++) {
            if (data[i].getId() > max) {
                max = data[i].getId();
            }
        }
        
        // 计算最高位数
        int maxDigit = String.valueOf(max).length();
        Bucket[] temp = new Bucket[RADIX];// 用于保存各个位数的bucket
        for (int num = 0; num < maxDigit; num++) {
            // 将每个元素指定位数的值放入对应的bucket中
            for (i = 0; i < data.length; i++) {
                long val = data[i].getId() / pow(RADIX, num);
                Node node = new Node(data[i], null);
                Bucket tmp = temp[(int)(val % RADIX)];
                if (null == tmp) {
                    temp[(int)(val % RADIX)] = new Bucket(node, node);
                } else {
                    tmp.getTail().setNext(node);
                    tmp.setTail(node);
                }
            }
            // 将temp中保存的元素按顺序更新到原数组中
            int index = 0;
            for (i = 0; i < temp.length; i++) {
                if (null == temp[i]) {
                    continue;
                }
                Node node = temp[i].getHead();
                data[index++] = node.getValue();
                while (null != node.getNext()) {
                    data[index++] = node.getNext().getValue();
                    node = node.getNext();
                }
                // 顺便重置temp
                temp[i] = null;
            }
        }

        System.out.println("use time:" + (System.nanoTime() - start) / 1000000);
    }

    /**
     * 计算指定基数的指定幂的值
     * 
     * @param digit
     *            基数，如10
     * @param num
     *            幂次值，从0开始，10^0=1
     * @return
     */
    private static int pow(int digit, int num) {
        int val = 1;
        for (int i = 0; i < num; i++) {
            val *= digit;
        }
        return val;
    }

    /**
     * <p>
     * 桶，用于存放数组
     * </p>
     * 
     * @author Vicky
     * @date 2015-8-21
     */
    private static class Bucket {
        private Node head;
        private Node tail;

        public Bucket(Node head, Node tail) {
            super();
            this.head = head;
            this.tail = tail;
        }

        public Node getHead() {
            return head;
        }

        public Node getTail() {
            return tail;
        }

        public void setTail(Node tail) {
            this.tail = tail;
        }
    }

    /**
     * <p>
     * 节点，用于解决桶中存放多个元素问题
     * </p>
     * 
     * @author Vicky
     * @date 2015-8-21
     */
    private static class Node {
        private Person value;
        private Node next;

        public Node(Person value, Node next) {
            super();
            this.value = value;
            this.next = next;
        }

        public Person getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
    	
    	Person[] pers = PersonLoadUtils.loadAll();
		
		System.out.println(pers.length);
		
		long begTime = System.currentTimeMillis();
		PersonRadixSortByNode.sort(pers);
		for (int i=0;i<100;i++) {
			System.out.println(pers[90*10000+i]);
		}
		long end = System.currentTimeMillis();
		System.out.println("time = "+(end-begTime));
        // SortUtils.printArray(data);
        // SortUtils.printArray(data2);
        // SortUtils.printArray(data3);
    }
}
