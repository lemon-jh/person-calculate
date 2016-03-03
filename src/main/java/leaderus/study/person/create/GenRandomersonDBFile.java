package leaderus.study.person.create;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import leaderus.study.person.entry.Person;

/**
* @Description: 生成随机Person文件
* @author 张福泉 zhang_91026@163.com 
* @date 2016年3月3日 上午11:00:43 
*/
public class GenRandomersonDBFile {

	private static final String local_file = "c:\\leaderus\\data\\persondb.txt";
	
	public static void insertData(int num) {
		
		System.out.println("==>开始写入数据");
		
		File personFile  = new File(local_file);
		
		if(!personFile.getParentFile().exists()) {  
            if(!personFile.getParentFile().mkdirs()) {  //目标文件所在目录不存在，准备创建它！
                System.out.println("==>创建目标文件所在目录失败！");  
                return;  
            }  
        }  
		
		BufferedWriter out = null;
		
		try {
			
			if(!personFile.exists()){
				personFile.createNewFile();
			}
			
			out = new BufferedWriter(new FileWriter(personFile));  
			
			for(int i=0;i<num;i++){
				System.out.println("==>正在写入"+i+"条数据");
				out.write(new Person().fillRandomData().toString()+"\n");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			 try {
				out.flush(); // 把缓存区内容压入文件;
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
		System.out.println("==>写入数据结束");
	}
	
	public static void main(String[] args) {
		GenRandomersonDBFile.insertData(100000);
	}
	
	
}
