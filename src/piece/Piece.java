package piece;

import java.util.Collection;

import board.Board;
import board.Move;
import packages.Alliance;

public abstract class Piece {

	protected final int piecePosition;
	protected final Alliance pieceAlliance;
	protected final boolean isFirstMove;
	
	Piece(final int piecePosition, final Alliance pieceAlliance) {
		this.piecePosition = piecePosition;
		this.pieceAlliance = pieceAlliance;
		this.isFirstMove = false;
	}
	
	public int getPiecePosition(){
		return this.piecePosition;
	}
	
	public Alliance getPieceAlliance(){
		return this.pieceAlliance;
	}
	
	public boolean isFirstMove(){
		return this.isFirstMove;
	}
	public abstract Collection<Move> calculateLegalMoves(final Board board);
	
	
}
