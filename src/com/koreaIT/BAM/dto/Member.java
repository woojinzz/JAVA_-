package com.koreaIT.BAM.dto;

public class Member {

	public int id;
	public String regData;
	public String loginId;
	public String loginPw;
	public String loginName;
	
	public Member(int id, String regData, String loginId, String loginPw, String loginName) {
		this.id = id;
		this.regData = regData;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.loginName = loginName;

	}
}
