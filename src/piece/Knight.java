package piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import board.Board;
import board.BoardUtils;
import board.Move;
import board.Tile;
import packages.Alliance;
import piece.Piece.PieceType;

public class Knight extends Piece {

	private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};
	
	public Knight(Alliance pieceAlliance, final int piecePosition) {
		super(piecePosition, pieceAlliance);
	}
	
	@Override
	public Collection<Move> calculateLegalMoves(final Board board) {
		
		
		final List<Move> legalMoves = new ArrayList<>();
		
		for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
			
			final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset; // new position
			
			if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
				//if goes out of offset for 1,2,7,8 column
				if(isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) || 
						isSecondColumnExclusion(this.piecePosition, currentCandidateOffset) ||
						isSeventhColumnExclusion(this.piecePosition, currentCandidateOffset)||
						isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)){
					continue;
				}
				
				final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate); //tile you want move piece
				
				if(!candidateDestinationTile.isTileOccupied()){
					legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
				
				}else {
					final Piece pieceAtDestionation = candidateDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceAtDestionation.getPieceAlliance();
					if(this.pieceAlliance != pieceAlliance){ //if it is enemy piece black is not black
						legalMoves.add(new Move.AttactMove(board, this,candidateDestinationCoordinate, pieceAtDestionation)); //eat enemy piece  
					}
				}
			}
		}
		
		return Collections.unmodifiableList(legalMoves);
	}
	
	@Override
	public String toString(){
		return PieceType.KNIGHT.toString();
	}
	
	private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
		
		if (BoardUtils.FIRST_COLUMN[currentPosition] && 
				(candidateOffset == -17 || candidateOffset == -10 || candidateOffset == 6 || candidateOffset == 15)) {
			return true;
		}
			
		else return false;
	}
	
	private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset){
		
		if (BoardUtils.SECOND_COLUMN[currentPosition] && 
				(candidateOffset == -10 || candidateOffset == 6)) {
			return true;
		}
			
		else return false;
	}
	
	private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset){
		
		if (BoardUtils.SEVENTH_COLUMN[currentPosition] && 
				(candidateOffset == -6 || candidateOffset == 10)) {
			return true;
		}
			
		else return false;
	}
	
	private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
		
		if (BoardUtils.EIGHTH_COLUMN[currentPosition] && 
				(candidateOffset == -15 || candidateOffset == -6 || candidateOffset == 10 || candidateOffset == 17)) {
			return true;
		}
			
		else return false;
	}
	

}
