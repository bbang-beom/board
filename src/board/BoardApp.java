package board;

import board.controller.BoardController;

//사용자가 사용할 프로그램
public class BoardApp {
    public static void main(String[] args) {
        BoardController app = new BoardController();
        app.appStart(); // 프로그램 실행
    }
}
