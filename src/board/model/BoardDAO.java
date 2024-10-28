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
}
