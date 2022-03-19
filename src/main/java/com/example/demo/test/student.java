package com.example.demo.test;

public class student {

	
	private String name;
	private int no;
	private int aaa; // 학년 
	private int bbb; // 반
	private int[] sc;
	
	public String noNameAaa(int no, String name, int aaa) {
		return no+"/"+name+"/"+aaa;
	}

	public void noNameAaa2(int no, String name, int aaa) {
		System.out.println(no+"/"+name+"/"+aaa);
	}
	
	public String naNameAaa(student st) {
		return st.getNo()+"/"+st.getName()+"/"+st.getAaa();
	}
	
	
	public student() {
		// TODO Auto-generated constructor stub
	}

	public student(String name) {
		this.name = name;
	}
	public student(String name, int no) {
		this.name = name;
		this.no = no;
	}
	public student(String name, int no, int aaa, int[] ccc) {
		this.name = name;
		this.no = no;
		this.aaa = aaa;
		this.sc = ccc;
	}
	public student(String name, int no, int aaa) {
		this.name = name;
		this.no = no;
		this.aaa = aaa;
	}
	
	public String getName() {
		return name;
	}
	public int getNo() {
		return no;
	}
	public int getAaa() {
		return aaa;
	}
	
	public void avgS() {
		
		int sum = 0;
		for(int a : this.sc) {
			sum += a;
		}
		
		System.out.println(sum/sc.length);
	}

	public int getBbb() {
		return bbb;
	}

	public void setBbb(int bbb) {
		this.bbb = bbb;
	}

	public int[] getSc() {
		return sc;
	}

	public void setSc(int[] sc) {
		this.sc = sc;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public void setAaa(int aaa) {
		this.aaa = aaa;
	}
	
	
	
	
	
	
}
