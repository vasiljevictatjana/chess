package board;

import piece.Piece;

public abstract class Move {

	final Board board;
	final Piece movedPiece;
	final int destintionCoordinate;
	
	private Move(final Board board,final Piece movedPiece,final int destintionCoordinate) {
		this.board = board;
		this.movedPiece = movedPiece;
		this.destintionCoordinate = destintionCoordinate;
	}
	
	public static final class MajorMove extends Move{

		public MajorMove(final Board board, final Piece movedPiece, final int destintionCoordinate) {
			super(board, movedPiece, destintionCoordinate);
			
		}
		
	}
	
	public static final class AttactMove extends Move{

		final Piece attackedPiece;

		public AttactMove(final Board board, final Piece movedPiece, final int destintionCoordinate, final Piece attackedPiece) {
			super(board, movedPiece, destintionCoordinate);
			this.attackedPiece = attackedPiece;
			
		}
		
	}
	
}
