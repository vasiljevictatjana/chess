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

public class King extends Piece{

	private final static int[] CANDIDATE_MOVE_COORDINATE = {-9,-8,-7,-1,1,7,8,9};
	private static final int candidateOffset = 0; 
	
	public King(Alliance pieceAlliance, final int piecePosition) {
		super(piecePosition, pieceAlliance);
	}

	@Override
	public Collection<Move> calculateLegalMoves(final Board board) {
		
		final List<Move> legalMoves = new ArrayList<>();
		
		for(final int currentCandidateOffset: CANDIDATE_MOVE_COORDINATE){
			final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
			
			if(isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) || 
					isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)){
				continue;
			}
			
			if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
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
		return PieceType.KING.toString();
	}
	
	private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
		
		if (BoardUtils.FIRST_COLUMN[currentPosition] && 
				(candidateOffset == -9 || candidateOffset == -1 || candidateOffset == 7 )) {
			return true;
		}
			
		else return false;
	}
	
	private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
		
		if (BoardUtils.EIGHTH_COLUMN[currentPosition] && 
				(candidateOffset == -7 || candidateOffset == 1 || candidateOffset == 9)) {
			return true;
		}
			
		else return false;
	}

}
