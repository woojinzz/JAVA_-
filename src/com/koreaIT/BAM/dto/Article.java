package com.koreaIT.BAM.dto;

public class Article {

	public int id;
	public String regData;
	public int memberId;
	public String title;
	public String contents;
	
	public Article(int cnt, String regData, int memberId, String title, String contents) {
		this.id = cnt;
		this.regData = regData;
		this.memberId = memberId;
		this.title = title;
		this.contents = contents;

	}
}
