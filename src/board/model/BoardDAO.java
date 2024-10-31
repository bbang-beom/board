package board.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

//model(function)
//DTO의 data들이 수행할 기능
public class BoardDAO {
	private ArrayList<BoardDTO> boardDatas; // 게시판 목록을 저장할 ArrayList
	public static final String FILEPATH = "boardfile.txt"; // file을 불러오거나 저장할 경로

	public BoardDAO() {
		this.boardDatas = new ArrayList<BoardDTO>();
	}

	// 파일 불러오기
	@SuppressWarnings("unchecked")
	public boolean readFile() {
		try (InputStream in = new FileInputStream(FILEPATH); // 경로는 fileboard.text 고정
				ObjectInputStream oIn = new ObjectInputStream(in);) {
			this.boardDatas = (ArrayList<BoardDTO>) oIn.readObject(); // 파일 불러오기
		} catch (ClassNotFoundException e) {
			return false; // 예외 발생 시 false
		} catch (IOException e) {
			return false;
		}
		return true;
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
		data.setDate(boardDTO.getDate());
		// 게시글 추가
		this.boardDatas.add(data);

		return true;
	}

	// 고유 index 지정
	public int isIndex(int index) {
		for (BoardDTO b : boardDatas) {
			if (b.getIndex() == index) { // index가 이미 있으면
				index++; // index 증가
			}
		}
		return index;
	}

	// List 목록 get
	public ArrayList<BoardDTO> getList(BoardDTO boardDTO) {
		return this.boardDatas;
	}

	// 게시물 선택
	public BoardDTO selectBoard(BoardDTO boardDTO) {
		for (BoardDTO data : this.boardDatas) {
			if (data.getIndex() == boardDTO.getIndex()) {
				data.setViewCount(data.getViewCount() + 1); // 조회 시 조회수 1 증가
				return data;
			}
		}
		return null;
	}

	// 추천 수 증가
	public BoardDTO increaseRecommendation(BoardDTO boardDTO) {
		for (BoardDTO data : this.boardDatas) {
			if (data.getIndex() == boardDTO.getIndex()) {
				data.setRecommendation(data.getRecommendation() + 1); // 조회 시 조회수 1 증가
				return data;
			}
		}
		return null;
	}

	// 추천 수 감소
	public BoardDTO decreaseRecommendation(BoardDTO boardDTO) {
		for (BoardDTO data : this.boardDatas) {
			if (data.getIndex() == boardDTO.getIndex()) {
				data.setRecommendation(data.getRecommendation() - 1); // 조회 시 조회수 1 증가
				return data;
			}
		}
		return null;
	}

	// 게시글 찾기
	public boolean findBoard(BoardDTO boardDTO) {
		for (BoardDTO data : this.boardDatas) {
			if (data.getIndex() == boardDTO.getIndex()) {
				return true;
			}
		}
		return false;
	}

	// 제목 수정
	public void updateTitle(BoardDTO boardDTO) {
		for (BoardDTO data : this.boardDatas) {
			if (data.getIndex() == boardDTO.getIndex()) {
				data.setTitle(boardDTO.getTitle());
				data.setDate(new Date()); // 게시글 수정 시 시간도 자동으로 수정
			}
		}
	}

	// 내용 수정
	public void updateContent(BoardDTO boardDTO) {
		for (BoardDTO data : this.boardDatas) {
			if (data.getIndex() == boardDTO.getIndex()) {
				data.setContent(boardDTO.getContent());
				data.setDate(new Date()); // 게시글 수정 시 시간도 자동으로 수정
			}
		}
	}

	// 게시글 삭제
	public void deleteBoard(BoardDTO boardDTO) {
		for (int i = 0; i < this.boardDatas.size(); i++) {
			if (boardDatas.get(i).getIndex() == boardDTO.getIndex()) {
				boardDatas.remove(i);
			}
		}
	}

	// 파일 저장
	public boolean saveFile() {
		try (OutputStream out = new FileOutputStream(FILEPATH); // 경로는 fileboard.text 고정
				ObjectOutputStream oOut = new ObjectOutputStream(out);) {
			oOut.writeObject(this.boardDatas); // 파일 쓰기
		} catch (FileNotFoundException e) {
			return false; // 예외 발생 시 false
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	// 추천 순 정렬
	public ArrayList<BoardDTO> sortReccomendation(BoardDTO boardDTO) {
		ArrayList<BoardDTO> sortRecommend = new ArrayList<BoardDTO>(this.boardDatas); // 생성자를 이용한 deep copy
		Collections.sort(sortRecommend, Comparator.comparing(BoardDTO::getRecommendation).reversed()); // 추천 순을 기준으로
																										// 내림차순 정렬
		return sortRecommend;
	}

	// 조회 순 정렬
	public ArrayList<BoardDTO> sortViewCount(BoardDTO boardDTO) {
		ArrayList<BoardDTO> sortRecommend = new ArrayList<BoardDTO>(this.boardDatas); // 생성자를 이용한 deep copy
		Collections.sort(sortRecommend, Comparator.comparing(BoardDTO::getViewCount).reversed()); // 조회 순을 기준으로 내림차순 정렬
		return sortRecommend;
	}

	// 시간 순 정렬
	public ArrayList<BoardDTO> sortDate(BoardDTO boardDTO) {
		ArrayList<BoardDTO> sortDate = new ArrayList<BoardDTO>(this.boardDatas); // 생성자를 이용한 deep copy
		Collections.sort(sortDate, Comparator.comparing(BoardDTO::getDate).reversed()); // 시간 순을 기준으로 정렬
		return sortDate;
	}
}
