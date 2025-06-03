package ChessFrancalancia;

public class Pawn extends Piece {
	String url;
	Boolean hasMoved;
	// can only move forward in moves and diagonal if the spot is filled
	Pawn(int i) {
		isPawn = true;
		hasMoved = false;
		// set the color ID to either white or black when a piece is initiated
		super.colorID = i;
		if (i == 1) {
			url = "pawnWhite.png";
		}
		else {
			url = "pawnBlack.png";
		}
		// show the image of the pawn to the user
		this.showImage(url);
	}
	
	@Override
	public void check() {
		// each time a pawn checks its available spots... 
		
		// reset the backgrounds of all the spots on the board
		Chess.resetBackgrounds();
		// set the pawn as the selected piece
		Chess.selectedPiece = this;
		// clear the list of available spots
		availableSpots.clear();
		// tracks the position of the pawn in relation to the chess board
		int pieceRow = this.row;
		int pieceCol = this.col;
		// if the pawn is black it is only able to move towards white's side
		if (this.colorID == 0) {
			Spot spot = Chess.getSpot(pieceRow+1, pieceCol);
			if (spot.getChildren().size()==0) {
				availableSpots.add(spot);
				Spot spot2 = Chess.getSpot(pieceRow+2,pieceCol);
				// if the pawn has not moved yet in the game, it will be able to initially move two spots
				if (spot2.getChildren().size() == 0 && !hasMoved) {
					availableSpots.add(spot2);
				}
			}
			// check diagonals for spots that the pawn may capture
			Spot diag = Chess.getSpot(pieceRow+1, pieceCol+1);
			Spot diag2 = Chess.getSpot(pieceRow+1, pieceCol-1);
			if (diag.getChildren().size() >0) {
				if (((Piece)diag.getChildren().get(0)).colorID != super.colorID) {
					availableSpots.add(diag);
				}
			}
			if (diag2.getChildren().size() >0) {
				if (((Piece)diag2.getChildren().get(0)).colorID != super.colorID) {
					availableSpots.add(diag2);
				}
			}
		}
		// otherwise, if the pawn is white, it is only able to move toward's black's side
		else {
			Spot spot = Chess.getSpot(pieceRow-1, pieceCol);
			if (spot.getChildren().size()==0) {
				availableSpots.add(spot);
				// if the pawn has not moved yet in the game, it will be able to initially move two spots
				Spot spot2 = Chess.getSpot(pieceRow-2,pieceCol);
				if (spot2.getChildren().size() == 0 && !hasMoved) {
					availableSpots.add(spot2);
				}
			}
			// check diagonals for spots that the pawn may capture
			Spot diag = Chess.getSpot(pieceRow-1, pieceCol+1);
			Spot diag2 = Chess.getSpot(pieceRow-1, pieceCol-1);
			if (diag.getChildren().size() >0) {
				if (((Piece)diag.getChildren().get(0)).colorID != super.colorID) {
					availableSpots.add(diag);
				}
			}
			if (diag2.getChildren().size() >0) {
				if (((Piece)diag2.getChildren().get(0)).colorID != super.colorID) {
					availableSpots.add(diag2);
				}
			}
		}
		
		// make all the available spots for the pawn light up
		for (Spot s : availableSpots) {
			Chess.makeBackground(s);
		}
	}
}
