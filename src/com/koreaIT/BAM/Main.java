package com.koreaIT.BAM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			String cmd = sc.nextLine();	
			String title = sc.nextLine();	
			String contents = sc.nextLine();	
			int cnt  = 0;
			
		
			System.out.println("명령어 : " + cmd);
		
			if(cmd.equals("list")) {
				System.out.println("게시글이 없습니다.");
			}
			else if(cmd.equals("write")) {
				System.out.println("제목 : " + title);
				System.out.println("내용 : " + contents);
				cnt++;
				System.out.println(cnt + "글입니다.");
			}
			else if(cmd.equals("exit")) {
				break;
			}
			else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}
		}

		sc.close();
		System.out.println("== 프로그램 종료 ==");
		
		
		
	}

}
