package board.controller;

import java.util.Scanner;
import board.model.BoardDAO;
import board.model.BoardDTO;
import board.view.BoardView;

// controller
// model의 동작을 제어, 관리 
public class BoardController {
	private BoardView view;
	private BoardDAO dao;
	private int INDEX;
	static Scanner scanner = new Scanner(System.in);

	public BoardController() {
		this.view = new BoardView(); // view
		this.dao = new BoardDAO(); // model
		this.INDEX = 1; // index
	}

	// 사용자가 프로그램 실행
	public void appStart() {
		int choice;
		while (true) {
			view.displayMenu(); // 시작화면
			try {
				System.out.print("메뉴 선택: ");
				choice = Integer.parseInt(scanner.nextLine()); // 사용자에게 메뉴 입력받음
			} catch (NumberFormatException e) { // 사용자가 숫자를 입력하지 않았을 때(올바른 입력을 하지 않았을 때) 예외처리
				view.menuOver(); // 안내 메시지 출력
				continue;
			}
			switch (choice) {
			// 종료
			case 0 -> {
				view.exit(); // 종료 메시지
				return;
			}
			// 생성
			case 1 -> {
				BoardDTO boardData = view.write();
				BoardDTO boardDTO = new BoardDTO();

				if (boardData.getName().equals("")) { // 사용자가 이름을 입력하지 않으면
					boardDTO.setName("익명"); // 익명으로 설정
				}
				boardDTO.setName(boardData.getName());
				if (boardData.getTitle().trim().equals("")) { // 제목에 공백은 불가능
					view.wrtieTitleFail();
					continue;
				} else { // 입력한 내용들 저장
					boardDTO.setTitle(boardData.getTitle());
					boardDTO.setContent(boardData.getContent());
					boardDTO.setIndex(INDEX++);
				}
				boolean flag = dao.create(boardDTO); // 게시판에 작성
				if (flag) {
					view.writeOK();
				} else {
					view.writeFail();
				}
				System.out.println(boardDTO);
			}
			// 읽기
			case 2 -> {
				BoardDTO boardDTO = new BoardDTO();
				view.displayView(dao.getList(boardDTO)); // view 출력
			}
			// 게시물 내용 출력
			case 3 -> {
				int index;
				System.out.print("조회할 게시글 번호 입력: ");
				try {
					index = Integer.parseInt(scanner.nextLine());
				} catch (NumberFormatException e) { // 사용자가 숫자를 입력하지 않았을 때(올바른 입력을 하지 않았을 때) 예외처리
					view.menuOver(); // 안내 메시지 출력
					continue;
				}
				boolean flag = false;     // 글이 있으면 true 없으면 false
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setIndex(index);
				BoardDTO boardData = dao.selectBoard(boardDTO);
				if (boardData != null) {  // 찾는 글이 있으면
					flag = true;
					view.displayContent(boardData);
				} else {				  // 없으면
					view.notExist();
				}
				if (flag) {
					int recommendation;
					System.out.println("이 게시물을 추천하시나요?"); 	// 글 추천 여부
					try {
						System.out.println("0. 종료 | 1. 추천 | 2. 비추천"); // 종료시 생략
						System.out.print("입력: ");
						recommendation = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e) {
						view.menuOver();
						continue;
					}
					if (recommendation == 0) {
						continue;
					} else if (recommendation == 1) {  // 추천
						boardData = dao.increaseRecommendation(boardDTO);
						view.displayRecommendation(boardData);  // 추천 수 출력
					} else if (recommendation == 2) {  // 비추천
						boardData = dao.decreaseRecommendation(boardDTO);
						view.displayRecommendation(boardData); // 추천 수 출력
					} else {
						view.menuOver();
					}
				}
			}
			// 게시물 수정
			case 4 -> {
				int index;
				System.out.print("수정할 게시글 번호 입력: ");
				try {
					index = Integer.parseInt(scanner.nextLine());
				}catch (NumberFormatException e) { // 사용자가 숫자를 입력하지 않았을 때(올바른 입력을 하지 않았을 때) 예외처리
					view.menuOver(); // 안내 메시지 출력
					continue;
				}
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setIndex(index);
				boolean flag = dao.findBoard(boardDTO);     // 글이 있으면 true 없으면 false
				if(flag) {
					int option;
					try {
						System.out.println(" 0.종료 | 1. 제목 수정 | 2. 내용 수정");
						System.out.print("옵션 입력: ");
						option = Integer.parseInt(scanner.nextLine());
					}catch (NumberFormatException e) { // 사용자가 숫자를 입력하지 않았을 때(올바른 입력을 하지 않았을 때) 예외처리
						view.menuOver(); // 안내 메시지 출력
						continue;
					}
					if(option == 0) {  // 종료 시 생략
						continue;
					}else if(option == 1) {   // 제목 변경
						String title = view.change();  // 변경할 제목 입력
						if(title.trim().equals("")) {  // 변경할 제목도 공백은 불가
							view.wrtieTitleFail();
							continue;
						} else {   // 공백이 아니면
							boardDTO.setTitle(title); 
							dao.updateTitle(boardDTO); // 제목 변경
							view.writeOK();
						}
					}else if(option == 2) {   // 내용 변경
						String content = view.change(); // 변경할 내용 입력
							boardDTO.setContent(content); // 내용은 공백 가능
							dao.updateContent(boardDTO);
							view.writeOK();
					} else {
						view.menuOver();
					}
				}else {
					view.notExist();
				}
			}
			// 게시물 삭제
			case 5 -> {
				int index;
				System.out.print("삭제할 게시글 번호 입력: "); // 삭제할 index 입력
				try {
					index = Integer.parseInt(scanner.nextLine());
				}catch (NumberFormatException e) { // 사용자가 숫자를 입력하지 않았을 때(올바른 입력을 하지 않았을 때) 예외처리
					view.menuOver(); // 안내 메시지 출력
					continue;
				}
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setIndex(index);
				boolean flag = dao.findBoard(boardDTO);     // 글이 있으면 true 없으면 false
				if(flag) {	// 있으면 삭제
					dao.deleteBoard(boardDTO);
					view.deleteOK();  // 삭제 완료 메시지
				} else {  // 없으면 안내 메시지
					view.notExist();
				}
			}
			
			}
		}
	}
}