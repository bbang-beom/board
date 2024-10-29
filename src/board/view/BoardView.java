package board.view;

import java.util.ArrayList;
import java.util.Scanner;

import board.model.BoardDTO;

//view
//사용자에게 보여줄 화면을 작성
public class BoardView {
	private Scanner scanner = new Scanner(System.in);

	// 사용자에게 보여줄 메뉴
	public void displayMenu() {
		System.out.println("\t\t\t 게시판 Menu");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("0. 종료 | 1. 게시물 작성 | 2. 게시물 목록 | 3. 게시물 내용 보기 | 4. 게시물 수정 | 5. 게시물 삭제");
		System.out.println("---------------------------------------------------------------------------------");
	}

	// 종료 안내문
	public void exit() {
		System.out.println("프로그램 종료.");
	}

	// 게시물 작성
	public BoardDTO write() {
		BoardDTO boardData = new BoardDTO();
		
		System.out.print("이름 입력: ");
		String name = scanner.nextLine();  // 이름
		
		System.out.print("제목 입력: ");
		String title = scanner.nextLine(); // 제목

		System.out.print("내용 입력: ");
		String content = scanner.nextLine(); // 내용
		
		boardData.setName(name);
		boardData.setTitle(title);
		boardData.setContent(content);

		return boardData;
	}

	// 선택 범위 초과 에러 메시지
	public void menuOver() {
		System.out.println("잘못된 입력.");
	}

	// 게시물 출력
	public void displayView(ArrayList<BoardDTO> datas) {
		if (datas.size() <= 0) { // 게시물이 없을 때 안내 메시지
			notExist();
		} else {
			System.out.println("옵션을 고르세요");
			System.out.println("1. 전체 보기 | 2. 검색");  // 전체보기와 검색하여 보기 옵션
			System.out.print("옵션 입력: ");
			try {
				int option = Integer.parseInt(scanner.nextLine());
				if (option == 1) {      			// 전체 보기
					System.out.println("\t게시물 목록");
					System.out.println("번호\t| 작성자\t| 제목\t| 조회수");
					for (BoardDTO data : datas) {
						System.out.println(data.getIndex() + "\t| " + data.getName() + "\t| " + data.getTitle() + "\t| " 
								+ data.getViewCount());
					}
				} else if(option == 2) { 			// 검색어로 보기
					System.out.print("검색어 입력: ");
					String search = scanner.nextLine();  // 검색어를 입력받고
					System.out.println(search + "(으)로 검색한 게시물 목록");
					System.out.println("번호\t| 작성자\t| 제목\t| 조회수");
					for (BoardDTO data : datas) {			// 리스트를 돌면서
						if(data.getTitle().contains(search)) {	// 검색어가 포함된 게시물만 출력		
							System.out.println(data.getIndex() + "\t| " + data.getName() + "\t| " + data.getTitle() + "\t| " 
								+ data.getViewCount());
						}
					}
				} else {
					menuOver();
				}
			} catch (NumberFormatException e) { // 사용자가 숫자를 입력하지 않았을 때(올바른 입력을 하지 않았을 때) 예외처리
				menuOver();
			}
		}
	}
	// 내용 출력
	public void displayContent(BoardDTO boardData) {
		System.out.println("번호\t| 작성자\t| 제목\t| 내용\t| 조회수");
		System.out.println(boardData.getIndex() + "\t| " + boardData.getName() +  "\t| " + boardData.getTitle() + "\t| "
							+ boardData.getContent() + "\t| " + boardData.getViewCount() + "\t ");
	}
	// 추천 수 출력
	public void displayRecommendation(BoardDTO boardData) {
		System.out.println("추천수: " + boardData.getRecommendation());
	}
	// 추천 수 출력
	public void notExist() {
		System.out.println("게시물이 없습니다.");
	}
	// 변경할 제목 입력
	public String change() {
		System.out.print("변경할 제목 혹은 내용 입력: ");
		String str = scanner.nextLine();
		return str;
	}
	// 작성 완료 안내
	public void writeOK() {
		System.out.println("작성 완료");
	}
	// 작성 실패 안내
	public void writeFail() {
		System.out.println("작성 실패");
	}
	// 제목 공백 불가 안내
	public void wrtieTitleFail() {
		System.out.println("제목은 공백이 불가능합니다.");
	}
	// 삭제 완료 안내
	public void deleteOK() {
		System.out.println("삭제 완료");
	}
}
