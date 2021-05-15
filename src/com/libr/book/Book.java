package com.libr.book;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
	private String kinds = null;
	private String bookid = null;
	private String bookname = null;
	private String author = "";
	private String publisher = "";
	private String publishtime = "";
	private int total = 0;
	private int surplus = 0;
	private int borrowcount = 0;
	private String borrowdate;
	private String backdate;
	private String overdue;
	private boolean isPermitted = true;
	public void setIsPermitted(boolean isPermitted) {
		this.isPermitted = isPermitted;
	}
	public boolean getIsPermitted() {
		return isPermitted;
	}
	public String getOverdue() {
		return overdue;
	}
	public void setOverdue(String overdue) {
		this.overdue = overdue;
	}
	public void overdueAppend(String message) {
		this.overdue+=message;
	}
	public void setBorrowdate(String borrowdate) {
		this.borrowdate = borrowdate;
	}
	public String getBorrowdate() {
		return borrowdate;
	}
	public void setBackdate(String backdate) {
		this.backdate = backdate;
	}
	public String getBackdate() {
		return backdate;
	}
	public Book() {	
	}
	
//	public void setAutobookid() {
		
//	}
	public void setBorrowcount(int borrowcount) {
		this.borrowcount = borrowcount;
	}
	public int getBorrowcount() {
		return borrowcount;
	}
	public void setKinds(String kinds) {
		this.kinds = kinds;
	}
	public String getKinds() {
		return kinds;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public void setPublishtime(String publishtime) {
		if(publishtime!=null&&publishtime.length()>0) {
			this.publishtime = publishtime;
		}else {
			this.publishtime = formDate();
		}
	}
	private String formDate() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String datestr = sf.format(date);
		return datestr;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public void setSurplus(int surplus) {
		this.surplus = surplus;
	}
	public String getBookid() {
		return bookid;
	}
	public String getBookname() {
		return bookname;
	}
	public String getAuthor() {
		return author;
	}
	public String getPublisher() {
		return publisher;
	}
	public String getPublishtime() {
		return publishtime;
	}
	public int getTotal() {
		return total;
	}
	public int getSurplus() {
		return surplus;
	}
}
