package packages;

import board.Board;

public class Chess {

	public static void main(String[] args){
		
		Board board = Board.createStandardBoard();
		System.out.println(board);
	}
}
