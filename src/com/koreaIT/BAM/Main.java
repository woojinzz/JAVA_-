package com.koreaIT.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		Scanner sc = new Scanner(System.in);
		
		List<Article> articles = new ArrayList<>();
		
		int cnt  = 0;
		
		while(true) {
			
			System.out.printf("명령어) ");
	
			String cmd = sc.nextLine().trim();	
			//명령어 입력
			if(cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}
			//끝
			if(cmd.equals("exit")) {
				break;
			}
			//글 작성
			if(cmd.equals("article write")) {
				cnt++;
				System.out.printf("제목 : ");
				String title = sc.nextLine();	
				System.out.printf("내용 : ");
				String contents = sc.nextLine();	
			
				Article article = new Article(title, contents, cnt);
				articles.add(article);
				
				System.out.println(cnt + "번 글이 생성되었습니다.");
			}
			//전체 조회
			else if(cmd.equals("article list")) {
				
				if(articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;
				}
				
				System.out.println("article write");
				System.out.println("번호	:	제목");
				
				for(int i = articles.size() - 1; i >= 0 ; i-- ) {
					Article article = articles.get(i);
					System.out.printf("%d	: 	%s \n"  , article.id, article.title);

				}
			} 
			//조회
			else if(cmd.startsWith("article detail ")) {
				
				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]); // 형변환
				
				Article foundArticle = null;// 아티클 담을 변수
			
				for(int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					
					if(article.id == id) {
						foundArticle = article;
						break;
					}
				}
				if (foundArticle == null) {
					System.out.printf("%d 번 게시글은 없습니다.\n", id);
					continue;
				}
				
				System.out.printf("== %d 번 게시글 자세히 보기 ==\n", foundArticle.id);
				System.out.printf("번호 %d\n", foundArticle.id);
				System.out.printf("제목 %s\n", foundArticle.title);
				System.out.printf("내용 %s\n", foundArticle.contents);

			}
			//삭제
			else if(cmd.startsWith("article delete ")) {
				
				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]); // 형변환
				
				//Article foundArticle = null;// 아티클 담을 변수
				int foundIndex = -1; //인덱스는 0부터 시작해서 비교를 위해 -1 넣음
			
				for(int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					
					if(article.id == id) {
						//foundArticle = article; 객체로 비교
						foundIndex = i;
						break;
					}
				}
				if (foundIndex == -1) {
					System.out.printf("%d 번 게시글은 없습니다.\n", id);
					continue;
				}
				
//				articles.remove(foundArticle);
				articles.remove(foundIndex);
				
				System.out.printf("%d 번 게시글을 삭제했습니다.\n", id);
				
			}
			else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}
		}

		sc.close();
		System.out.println("== 프로그램 종료 ==");
		
		
		
	}

}
class Article {
	String title;
	String contents;
	int id;
	
	Article(String title, String contents, int cnt) {
		this.title = title;
		this.contents = contents;
		this.id = cnt;
		
		
	}

	public Article get(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
