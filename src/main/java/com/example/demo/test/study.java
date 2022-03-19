package com.example.demo.test;

import java.util.Scanner;

public class study {

	public study() {
		// TODO Auto-generated constructor stub

		student st = new student();
		
		student st2 = new student("홍길동");
		st2.getName();
		
		student st3 = new student("이재명", 1);
		st3.getName();
		st3.getNo();

		student st4 = new student("이재명", 1, 2, new int[] {1, 3, 5, 6, 7});
		st4.avgS();
		

		
		student stsumin = new student();
		String safsdf = stsumin.noNameAaa(30, "수민", 20);
		System.out.println(safsdf);
		
		stsumin.setNo(30);
		stsumin.setName("수민");
		stsumin.setAaa(20);
		String fdsfsd = stsumin.naNameAaa(stsumin);
		System.out.println(fdsfsd);
	}
	
	public void name() {
		
		
		int s2 = 95;
		
		if(s2>=90) {
			if(s2>=95) {
				System.out.println("a+");
				
			}
			
			else {
				System.out.println("a");
			}
		}
		
		
	}
}
