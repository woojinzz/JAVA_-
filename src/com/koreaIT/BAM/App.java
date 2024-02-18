package com.koreaIT.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.dto.Member;
import com.koreaIT.BAM.util.Util;

public class App {
	
	List<Article> articles;
	List<Member> members;
	
	int cnt;
	App() {
		cnt = 0;
		articles = new ArrayList<>();
		members = new ArrayList<>();
		
	}
	void run() {
		System.out.println("== 프로그램 시작 ==");
		makeTestData();
		
		Scanner sc = new Scanner(System.in);
	
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
			//회원가입
			if(cmd.equals("member join")) {
				cnt++;
				System.out.println("== member join ==");
				String regDate = Util.getDate();
				
				System.out.printf("아이디 : ");
				String loginId = sc.nextLine();	
				System.out.printf("비밀번호 : ");
				String loginPw = sc.nextLine();	
				System.out.printf("비밀번호 확인 : ");
				String loginPwChk = sc.nextLine();	
				System.out.printf("이름 : ");
				String loginName = sc.nextLine();	
			
				Member member = new Member(cnt, regDate, loginId, loginPw, loginName);
				
				members.add(member);
				
				System.out.println(loginName + "님 회원가입이 완료되었습니다.");
			}

			//글 작성
			else if(cmd.equals("article write")) {
				cnt++;
				System.out.println("== article write ==");
				String regDate = Util.getDate();
				System.out.printf("제목 : ");
				String title = sc.nextLine();	
				System.out.printf("내용 : ");
				String contents = sc.nextLine();	
			
				Article article = new Article(cnt, regDate, title, contents);
				
				articles.add(article);
				
				System.out.println(cnt + "번 글이 생성되었습니다.");
			}
			//전체 조회
			else if(cmd.startsWith("article list")) {	
				
				if(articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;
				}
				
				String search = cmd.substring("article list".length()).trim();// 검색어저장
				List<Article> forPrintArticle = articles;
				
				if(search.length() > 0) {
					forPrintArticle = new ArrayList<>();
					for(Article article : articles) {
						if(article.title.contains(search)) {
							forPrintArticle.add(article);
						}
					}
				}
				
				System.out.println("== article list ==");
				System.out.println("번호	:	날짜			 :	제목");
				
				for(int i = forPrintArticle.size() - 1; i >= 0 ; i-- ) {
					Article article = forPrintArticle.get(i);
					System.out.printf("%d	:	%s	 :	%s \n"  , article.id, article.regData, article.title);
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
				System.out.printf("작성일 %s\n", foundArticle.regData);
				System.out.printf("제목 %s\n", foundArticle.title);
				System.out.printf("내용 %s\n", foundArticle.contents);
			}
			//수정
			else if(cmd.startsWith("article modify ")) {
				
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
				System.out.printf("수정할 제목 : ");
				String title = sc.nextLine();	
				System.out.printf("수정할 내용 : ");
				String contents = sc.nextLine();	
				
				foundArticle.title = title;
				foundArticle.contents = contents;
				
				System.out.printf("%d 번 게시글이 수정되었습니다.\n", id);
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
	void makeTestData() {
		
		articles.add(new Article(++cnt, Util.getDate(), "제목1", "내용1")); 
		articles.add(new Article(++cnt, Util.getDate(), "제목2", "내용2")); 
		articles.add(new Article(++cnt, Util.getDate(), "제목32", "내용3")); 
	
		System.out.println("테스트 게시물 3개 생성");
	}
	
	

}
