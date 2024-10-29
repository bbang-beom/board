package board.model;

import java.util.ArrayList;

//model(function)
//DTO의 data들이 수행할 기능
public class BoardDAO {
	private ArrayList<BoardDTO> boardDatas; // 게시판 목록을 저장할 ArrayList

	public BoardDAO() {
		this.boardDatas = new ArrayList<BoardDTO>();
	}

	// data 생성
	public boolean create(BoardDTO boardDTO) {
		BoardDTO data = new BoardDTO();
		// view.write()에서 입력받은 내용 저장
		data.setIndex(boardDTO.getIndex());
		data.setName(boardDTO.getName());
		data.setTitle(boardDTO.getTitle());
		data.setContent(boardDTO.getContent());
		data.setRecommendation(0);
		data.setViewCount(0);
		// 게시글 생성
		this.boardDatas.add(data);

		return true;
	}
	
	// List 목록 get
	public ArrayList<BoardDTO> getList(BoardDTO boardDTO) {
		return this.boardDatas;
	}
	// 게시물 선택
	public BoardDTO selectBoard(BoardDTO boardDTO) {
		for(BoardDTO data: this.boardDatas) {
			if(data.getIndex() == boardDTO.getIndex()) {
				data.setViewCount(data.getViewCount() + 1);   // 조회 시 조회수 1 증가
				return data;
			}
		}
		return null;
	}
	// 게시글 찾기
	public boolean findBoard(BoardDTO boardDTO) {
		for(BoardDTO data: this.boardDatas) {
			if(data.getIndex() == boardDTO.getIndex()) {   // 조회 시 조회수 1 증가
				return true;
			}
		}
		return false;
	}
	// 추천 수 증가
	public BoardDTO increaseRecommendation(BoardDTO boardDTO) {
		for(BoardDTO data: this.boardDatas) {
			if(data.getIndex() == boardDTO.getIndex()) {
				data.setRecommendation(data.getRecommendation() + 1);   // 조회 시 조회수 1 증가
				return data;
			}
		}
		return null;
	}
	// 추천 수 감소
	public BoardDTO decreaseRecommendation(BoardDTO boardDTO) {
		for(BoardDTO data: this.boardDatas) {
			if(data.getIndex() == boardDTO.getIndex()) {
				data.setRecommendation(data.getRecommendation() -1);   // 조회 시 조회수 1 증가
				return data;
			}
		}
		return null;
	}
	// 제목 수정
	public void updateTitle(BoardDTO boardDTO) {
		for(BoardDTO data: this.boardDatas) {
			if(data.getIndex() == boardDTO.getIndex()) {
				data.setTitle(boardDTO.getTitle());
			}
		}
	}
	// 내용 수정
	public void updateContent(BoardDTO boardDTO) {
		for(BoardDTO data: this.boardDatas) {
			if(data.getIndex() == boardDTO.getIndex()) {
				data.setContent(boardDTO.getContent());
			}
		}
	}
	// 게시글 삭제
	public void deleteBoard(BoardDTO boardDTO) {
		for(int i = 0; i <this.boardDatas.size(); i++) {
			if(boardDatas.get(i).getIndex() == boardDTO.getIndex()) {
				boardDatas.remove(i);
			}
		}
	}
}
