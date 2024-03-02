package com.koreaIT.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.dto.Member;
import com.koreaIT.BAM.util.Util;

public class App {

	private List<Article> articles;
	private List<Member> members;
	private Member loginedMember;


	App() {
		this.articles = new ArrayList<>();
		this.members = new ArrayList<>();
		this.loginedMember = null;
	}

	void run() {
		System.out.println("== 프로그램 시작 ==");
		makeTestData();

		Scanner sc = new Scanner(System.in);
		
		int lastArticleId =3;
		int lastMemberId =2;

		while (true) {
			System.out.printf("명령어) ");

			String cmd = sc.nextLine().trim();
			// 명령어 입력
			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}
			// 끝
			if (cmd.equals("exit")) {
				break;
			}
			// 회원가입
			if (cmd.equals("member join")) {
				
				if(this.loginedMember != null) {
					System.out.println("로그아웃 후 이용해주세요.");
					continue;
				}
				System.out.println("== member join ==");
				lastMemberId++;
				String regDate = Util.getDate();
				String loginId = null;
				String loginPw = null;
				String loginName = null;
				String loginPwChk = null;

				while (true) {
					System.out.printf("아이디 : ");
					loginId = sc.nextLine().trim();
					if (loginId.length() == 0) {
						System.out.println("아이디를 입력해주세요");
						continue;
					}

					if (idChk(loginId)) {
						System.out.printf("%s 아이디가 중복됩니다.\n", loginId);
					}
					System.out.printf("%s 는 사용가능한 아이디 입니다.\n", loginId);
					break;
				}

				while (true) {
					System.out.printf("비밀번호 : ");
					loginPw = sc.nextLine().trim();

					if (loginPw.length() == 0) {
						System.out.println("비밀번호를 입력해주세요");
						continue;
					}
					while (true) {
						System.out.printf("비밀번호 확인 : ");
						loginPwChk = sc.nextLine().trim();

						if (loginPwChk.length() == 0) {
							System.out.println("비밀번호 확인을 입력해주세요");
							continue;
						}
						break;
					}
					if (loginPw.equals(loginPwChk) == false) {
						System.out.println("비밀번호가 다릅니다.");
						continue;
					}
					break;
				}
				while (true) {
					System.out.printf("이름 : ");
					loginName = sc.nextLine().trim();

					if (loginName.length() == 0) {
						System.out.println("이름을 입력해주세요");
						continue;
					}
					break;
				}
				Member member = new Member(lastMemberId, regDate, loginId, loginPw, loginName);
				members.add(member);
				System.out.println(loginName + "님 회원가입이 완료되었습니다.");
			}
			// 로그인
			else if (cmd.equals("member login")) {
				
				if(this.loginedMember != null) {
					System.out.println("로그아웃 후 이용해주세요.");
					continue;
				}
				
				System.out.println("== member login ==");

				System.out.printf("아이디 : ");
				String loginId = sc.nextLine().trim();
				System.out.printf("비밀번호 : ");               
				String loginPw = sc.nextLine().trim();    
				
				Member member = getMemberByLoginId(loginId);
				
				if(member == null) {
					System.out.printf("%s 는 존재하지 않는 아이디 입니다.\n", loginId);
					continue;
				}
				if(member.loginId.equals(loginPw) == false) {
					System.out.printf("비밀번호를 확인해 주세요.\n", loginPw);
					continue;
				}
				this.loginedMember = member;
				System.out.printf("%s 님 환영합니다.\n", member.loginName);
			}
			//로그아웃
			else if(cmd.equals("member logout")) {
				
				if(this.loginedMember == null) {
					System.out.println("로그인 후 이용해주세요");
					continue;
				}
				this.loginedMember = null;
				System.out.println("로그아웃 되었습니다.");
			}
			// 글 작성
			else if (cmd.equals("article write")) {
				
				if(this.loginedMember == null) {
					System.out.println("로그인 후 이용해주세요.");
					continue;
				}
				
				System.out.println("== article write ==");
				lastArticleId++;
				String regDate = Util.getDate();
				String title = null;
				String contents = null;

				while (true) {
					System.out.printf("제목 : ");
					title = sc.nextLine();

					if (title.length() == 0) {
						System.out.println("제목을 입력해주세요");
						continue;
					}
					break;
				}

				while (true) {
					System.out.printf("내용 : ");
					contents = sc.nextLine();

					if (contents.length() == 0) {
						System.out.println("내용을 입력해주세요");
						continue;
					}
					break;
				}

				Article article = new Article(lastArticleId, regDate,  loginedMember.id, title, contents);
				articles.add(article);
				System.out.println(lastArticleId + "번 글이 생성되었습니다.");
			}
			// 전체 조회
			else if (cmd.startsWith("article list")) {

				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;
				}

				String search = cmd.substring("article list".length()).trim();// 검색어저장
				List<Article> forPrintArticle = articles;

				if (search.length() > 0) {
					
					System.out.println("검색어 : " + search);
					forPrintArticle = new ArrayList<>();
					for (Article article : articles) {
						if (article.title.contains(search)) {
							forPrintArticle.add(article);
						}
					}
					if(forPrintArticle.size() == 0) {
						System.out.println("검색결과가 없습니다.");
						continue;
					}
				}
				System.out.println("== article list ==");
				System.out.println("번호	:	날짜			 :	제목    :	작성자");

				for (int i = forPrintArticle.size() - 1; i >= 0; i--) {
					Article article = forPrintArticle.get(i);
					
					String writerName = getWriterNameByMemberId(article.memberId);
					
					System.out.printf("%d	:	%s	 :	%s :   %s\n", article.id, article.regData, article.title, writerName);
				}
			}

			// 조회
			else if (cmd.startsWith("article detail ")) {

				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]); // 형변환

				Article foundArticle = getArticleById(id);// 아티클 담을 변수

				if (foundArticle == null) {
					System.out.printf("%d 번 게시글은 없습니다.\n", id);
					continue;
				}
				
				String writerName = getWriterNameByMemberId(foundArticle.memberId);
				
				System.out.printf("== %d 번 게시글 자세히 보기 ==\n", foundArticle.id);
				System.out.printf("번호 %d\n", foundArticle.id);
				System.out.printf("작성일 %s\n", foundArticle.regData);
				System.out.printf("작성자 %s\n", writerName);
				System.out.printf("제목 %s\n", foundArticle.title);
				System.out.printf("내용 %s\n", foundArticle.contents);
			}
			// 수정
			else if (cmd.startsWith("article modify ")) {

				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]); // 형변환

				// Article foundArticle = null;// 아티클 담을 변수
				Article foundArticle = getArticleById(id);

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
			// 삭제
			else if (cmd.startsWith("article delete ")) {

				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]); // 형변환

				// Article foundArticle = null;// 아티클 담을 변수
				// int foundIndex = -1; //인덱스는 0부터 시작해서 비교를 위해 -1 넣음
				
				//int foundIndex = -1;

				Article foundArticle = getArticleById(id);
				
				if (foundArticle == null) {
					System.out.printf("%d 번 게시글은 없습니다.\n", id);
					continue;
				}

//				articles.remove(foundArticle);
				articles.remove(foundArticle);

				System.out.printf("%d 번 게시글을 삭제했습니다.\n", id);
			} else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}
		}
		sc.close();
		System.out.println("== 프로그램 종료 ==");
	}
	
	private String getWriterNameByMemberId(int memberId) {
		
		for (Member member : members) {
			if(member.id == memberId) {
				return member.loginName;
			}
		}
		return null;
	}
	
	
	

	private Member getMemberByLoginId(String loginId) {
		
		for (Member member : members) {
			if(member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	private Article getArticleById(int id) {

		for (Article article : articles) {
			if (article.id == id) {
				return article;
			}
		}
		return null;
	}

	private boolean idChk(String loginId) {// retutn 만나면 종료

		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return true;
			}
		}
		return false;
	}

	private void makeTestData() {

		articles.add(new Article(1, Util.getDate(), 1, "제목1", "내용1"));
		articles.add(new Article(2, Util.getDate(), 2, "제목2", "내용2"));
		articles.add(new Article(3, Util.getDate(), 1, "제목3", "내용3"));

		members.add(new Member(1, Util.getDate(), "test1", "test1", "유저1" ));
		members.add(new Member(2, Util.getDate(), "test2", "test2", "유저2" ));
		System.out.println("테스트 게시물 3개 생성 ,  회원 데이터 2개 생성");
	}   

}
