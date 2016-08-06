package piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import board.Board;
import board.BoardUtils;
import board.Move;
import board.Tile;
import board.Move.AttactMove;
import packages.Alliance;

public class Bishop extends Piece {

	private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9,-7,7,9};
	
	public Bishop(Alliance pieceAlliance, final int piecePosition) {
		super(piecePosition, pieceAlliance);
			
	}

	@Override
	public Collection<Move> calculateLegalMoves(final Board board) {
		
		final List<Move> legalMoves = new ArrayList<>();
		
		for (final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
			
			int candidateDestinationCoordinate = this.piecePosition;
			
			while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
				if(isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset) || isEighthColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)){
					break; //it is in while loop so we do not put continue but break
				}
				candidateDestinationCoordinate += candidateCoordinateOffset; //does diagonally 
				
				if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
					
					final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate); //tile you want move piece on
					
					if(!candidateDestinationTile.isTileOccupied()){
						legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
					
					}else {
						final Piece pieceAtDestionation = candidateDestinationTile.getPiece();
						final Alliance pieceAlliance = pieceAtDestionation.getPieceAlliance();
						if(this.pieceAlliance != pieceAlliance){ //if it is enemy piece ; black is not black
							legalMoves.add(new AttactMove(board, this,candidateDestinationCoordinate, pieceAtDestionation)); //eat enemy piece add to attacked move 
						}
						break; //there is blocking piece you ate you can't move more 
					}
					
				}
			}
		}
		return Collections.unmodifiableList(legalMoves);
	}
	
	@Override
	public String toString(){
		return PieceType.BISHOP.toString();
	}
	
	private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
		if (BoardUtils.FIRST_COLUMN[currentPosition] && 
				(candidateOffset == -9 || candidateOffset == 7)) {
			return true;
		}
			
		else return false;
	}
	

	private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
		if (BoardUtils.FIRST_COLUMN[currentPosition] && 
				(candidateOffset == -7 || candidateOffset == 9)) {
			return true;
		}
			
		else return false;
	}
	
	

}
