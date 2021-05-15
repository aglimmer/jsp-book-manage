package com.libr.admin.add.deal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;

/**
 * @author Lenovo
 * 直接调用
 * 构造方法：1.FormSerial(String kinds,int count)
 * 			2.public FormSerial(String kinds)//默认生成1个
 *返回：public Stack<String> getFormSerial()
 */
public class SerialFormat {
//	private Map<Integer,String> map = new HashMap<>();
	private Stack<String> stack = new Stack<>();
//	public static void main(String[] args) {
//		String kinds = "romantic_faction";
//		int sum = 15;
//		StringDeal p = new StringDeal(kinds,10);
//		p.display();
		
//	}
//	map.put("roman","romantic_faction");
//	map.put("lite","literary_novel");
//	map.put("hist","history_biography");
//	map.put("comp","computer_science");
	public SerialFormat() {
	}

	private void display() {
		int len = stack.size();
		System.out.println("len:"+len);
		while(!stack.isEmpty()) {
			System.out.println(stack.pop());
			
		}	
	}
	
	private String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		return sdf.format(date);
//		return "20190822";
	}
	
	public Stack<String> makeSerials(String kinds,String originalSerial,int sum) {
		if(originalSerial!=null&&originalSerial.length()>0) {
		String prefix = originalSerial.substring(0,4);
		System.out.println("prefix:"+prefix);
		String baseSequence = prefix+getDate();
		if(originalSerial.contains(baseSequence)) {
			String lastNum = originalSerial.substring(originalSerial.length()-4);
			System.out.println("lastNum:"+lastNum);
			int num = Integer.valueOf(lastNum);
			System.out.println("Num:"+num);
			for(int i = 0; i<sum&&num<9999; i++) {
				num = num + 1;
				String numstr = String.format("%04d", num);
				String newName = baseSequence+numstr;
				stack.push(newName);
				System.out.println("newName:"+newName);
			}
		}else {
			int num = -1;
			for(int i = 0; i<sum&&num<9999; i++) {
				num = num + 1;
				String numstr = String.format("%04d", num);
				String newName = baseSequence+numstr;
				stack.push(newName);
				System.out.println("oldName:"+newName);
			}
		}
		}
		return stack;
	}
	
	}

