package com.koreaIT.BAM.dto;

public class Article {

	public int id;
	public String regData;
	public String title;
	public String contents;
	
	public Article(int cnt, String regData, String title, String contents) {
		this.id = cnt;
		this.regData = regData;
		this.title = title;
		this.contents = contents;

	}
}
