package piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import board.Board;
import board.BoardUtils;
import board.Move;
import packages.Alliance;
import piece.Piece.PieceType;

public class Pawn extends Piece {

	private final static int[] CANDIDATE_MOVE_COORDINATE = {7,8,9,16}; //calculate horizontally in left for white and right for black; not vertically
	
	public Pawn(Alliance pieceAlliance, final int piecePosition) {
		super(piecePosition, pieceAlliance);
	}

	@Override
	public Collection<Move> calculateLegalMoves(final Board board) {
		
		final List<Move> legalMoves = new ArrayList<>();
		
		for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE){
			
			final int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection() * currentCandidateOffset); //depends if it is white or black will go 8 or 9 
			
			if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
				continue;
			}
			//TODO more
			if(currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){ //legal move for one step further 
				legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
			} else if(currentCandidateOffset == 16 && this.isFirstMove() && (BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack())
					|| (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isWhite())){  //columns on board where are start positions of pawns
				
				final int behindCandidateDestionationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection() * 8);
				if(!board.getTile(behindCandidateDestionationCoordinate).isTileOccupied() && 
						!board.getTile(candidateDestinationCoordinate).isTileOccupied()){ //in front of us is free tile; not attacking and not jump over enemy pawn 
					legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
				}
			} else if(currentCandidateOffset == 7 && 
					!((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite()) || 
					(BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())) ){ //attack move attention
				
				if(board.getTile(candidateDestinationCoordinate).isTileOccupied()){
					final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
					if(this.pieceAlliance != pieceAlliance){
						//TODO more
						legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate)); //attacked piece of enemy; attacked move
					}
				}	
			} else if(currentCandidateOffset == 9 &&
					!((BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite()) || 
					  (BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()))){ //attack move attention
				
				if(board.getTile(candidateDestinationCoordinate).isTileOccupied()){
					final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
					if(this.pieceAlliance != pieceAlliance){
						//TODO more
						legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate)); //attacked piece of enemy; attacked move
					}
				}	
			}
		}
		return Collections.unmodifiableList(legalMoves);
	}
	
	@Override
	public String toString(){
		return PieceType.PAWN.toString();
	}

}
