package board;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import piece.Piece;

public abstract class Tile {

	protected final int tileCoordinate;
	
	private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();
	
	private Tile(final int tileCoordinate) {
		this.tileCoordinate = tileCoordinate;
	}
	
	//learn Map interface
	private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
		
		final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
		
		for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
			emptyTileMap.put(i, new EmptyTile(i));
		}
		
		return Collections.unmodifiableMap(emptyTileMap);
	}
	//only this you can use others are private
	public static Tile createTile(final int tileCoordinate, final Piece piece){
		if(piece != null)
			return new OccupiedTile(tileCoordinate, piece);
		else 
			return EMPTY_TILES_CACHE.get(tileCoordinate);
	}
	
	public abstract Piece getPiece();
	public abstract boolean isTileOccupied();
	
	public static final class EmptyTile extends Tile{
		
		private EmptyTile(final int coordinate) {
			super(coordinate);
		}

		@Override
		public Piece getPiece() {
			
			return null;
		}

		@Override
		public boolean isTileOccupied() {
			
			return false;
		}
		
		
	}
	
	public static final class OccupiedTile extends Tile{
		
		private final Piece pieceOnTile;
		
		private OccupiedTile(int tileCoordinate, final Piece pieceOnTile) {
			super(tileCoordinate);
			this.pieceOnTile = pieceOnTile;
		}
		@Override
		public Piece getPiece() {
			
			return this.pieceOnTile;
		}
		@Override
		public boolean isTileOccupied() {
			
			return true;
		}
	}
}
