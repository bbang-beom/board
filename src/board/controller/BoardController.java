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
				view.menuOver();  // 안내 메시지 출력
				continue;
			}
			switch (choice) {
			// 종료
			case 0 -> {
				view.exit();   // 종료 메시지
				return;
			}
			// 생성
			case 1 -> {
				BoardDTO boardData = view.write();
				BoardDTO boardDTO = new BoardDTO();
				
				if(boardData.getName().equals("")) {    // 사용자가 이름을 입력하지 않으면
					boardDTO.setName("익명");				// 익명으로 설정
				}
				boardDTO.setName(boardData.getName());
				if (boardData.getTitle().trim().equals("")) { // 제목에 공백은 불가능
					System.out.println("제목에 공백은 불가능합니다.");
					continue;
				} else {		 // 입력한 내용들 저장
					boardDTO.setTitle(boardData.getTitle());
					boardDTO.setContent(boardData.getContent());
					boardDTO.setIndex(INDEX++);
				}
				boolean flag = dao.create(boardDTO);   // 게시판에 작성
				if (flag) {
					System.out.println("작성 완료");
				} else {
					System.out.println("작성 실패");
				}
				System.out.println(boardDTO);
			}
			// 읽기
			case 2 -> {
				BoardDTO boardDTO = new BoardDTO();
				view.printView(dao.getList(boardDTO));  // view 출력
				}
			}
		}
	}
}