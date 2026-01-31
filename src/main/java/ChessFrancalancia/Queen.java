package ChessFrancalancia;


public class Queen extends Piece {
	String url;
	// can move vertically/horizontally/diagonally in any direction
	Queen(int i) {
		// set the color ID to either white or black when a piece is initiated
		super.colorID = i;
		if (i == 1) {
			url = "queenWhite.png";
		}
		else {
			url = "queenBlack.png";
		}
		// display the image for that piece
		this.showImage(url);
	}
	
	@Override
	public void check() {
		// every time you check then available spots for a queen...
		
		// reset the backgrounds of all the spots to their original colors
		Chess.resetBackgrounds();
		// set the queen as the selected piece
		Chess.selectedPiece = this;
		// clear the available spots list so new spots can be added for the queen
		availableSpots.clear();
		// Following code looks for spots vertically, horizontally, and diagonally, according to the position of the queen (pieceRow and pieceCol)
		// when it finds the first spot that is unavailable in that direction, the loop breaks
		// a spot is unavailable if it contains a piece of the same color OR it contains not the first spot with an opponent's piece, but the second spot
		int pieceRow = this.row;
		int pieceCol = this.col;
		pieceCol++;
		while (pieceCol < 8) {
			Spot spot = Chess.getSpot(pieceRow, pieceCol);
			// check to see whether there is a colored obstacle in the way --> need to make colorID available for any piece
			if (spot.getChildren().size() > 0 && ((Piece)spot.getChildren().get(0)).colorID ==this.colorID) {
				break;
			}
			availableSpots.add(spot);
			if (spot.getChildren().size()>0) {
				break;
			}
			pieceCol++;
		}
		pieceRow = this.row;
		pieceCol = this.col;
		pieceRow++;
		while (pieceRow < 8) {
			Spot spot = Chess.getSpot(pieceRow, pieceCol);
			if (spot.getChildren().size() > 0 && ((Piece)spot.getChildren().get(0)).colorID == this.colorID) {
				break;
			}
			availableSpots.add(spot);
			if (spot.getChildren().size()>0) {
				break;
			}
			pieceRow++;
		}
		pieceRow = this.row;
		pieceCol = this.col;
		pieceRow--;
		while (pieceRow >=0) {
			Spot spot = Chess.getSpot(pieceRow, pieceCol);
			if (spot.getChildren().size() > 0 && ((Piece)spot.getChildren().get(0)).colorID ==this.colorID) {
				break;
			}
			availableSpots.add(spot);
			if (spot.getChildren().size()>0) {
				break;
			}
			pieceRow--;
		}
		pieceRow = this.row;
		pieceCol = this.col;
		pieceCol--;
		while (pieceCol >= 0) {
			Spot spot = Chess.getSpot(pieceRow, pieceCol);
			if (spot.getChildren().size() > 0 && ((Piece)spot.getChildren().get(0)).colorID ==this.colorID) {
				break;
			}
			availableSpots.add(spot);
			if (spot.getChildren().size()>0) {
				break;
			}
			pieceCol--;
		}	
		pieceRow = this.row;
		pieceCol = this.col;
		pieceRow++;
		pieceCol++;
		while (pieceRow < 8 && pieceCol < 8) {
			Spot spot = Chess.getSpot(pieceRow, pieceCol);
			// checkin to see whether there is a colored obstacle in the way --> need to make colorID available for any piece
			if (spot.getChildren().size() > 0 && ((Piece)spot.getChildren().get(0)).colorID ==this.colorID) {
				break;
			}
			availableSpots.add(spot);
			if (spot.getChildren().size()>0) {
				break;
			}
			pieceRow++;
			pieceCol++;
		}
		pieceRow = this.row;
		pieceCol = this.col;
		pieceRow++;
		pieceCol--;
		while (pieceRow < 8 && pieceCol >= 0) {
			Spot spot = Chess.getSpot(pieceRow, pieceCol);
			if (spot.getChildren().size() > 0 && ((Piece)spot.getChildren().get(0)).colorID ==this.colorID) {
				break;
			}
			availableSpots.add(spot);
			if (spot.getChildren().size()>0) {
				break;
			}
			pieceRow++;
			pieceCol--;
		}
		pieceRow = this.row;
		pieceCol = this.col;
		pieceRow--;
		pieceCol++;
		while (pieceRow >=0 && pieceCol < 8) {
			Spot spot = Chess.getSpot(pieceRow, pieceCol);
			if (spot.getChildren().size() > 0 && ((Piece)spot.getChildren().get(0)).colorID ==this.colorID) {
				break;
			}
			availableSpots.add(spot);
			if (spot.getChildren().size()>0) {
				break;
			}
			pieceRow--;
			pieceCol++;
		}
		pieceRow = this.row;
		pieceCol = this.col;
		pieceRow--;
		pieceCol--;
		while (pieceRow >= 0 && pieceCol >= 0) {
			Spot spot = Chess.getSpot(pieceRow, pieceCol);
			if (spot.getChildren().size() > 0 && ((Piece)spot.getChildren().get(0)).colorID ==this.colorID) {
				break;
			}
			availableSpots.add(spot);
			if (spot.getChildren().size()>0) {
				break;
			}
			pieceRow--;
			pieceCol--;
		}	
		// make the backgrounds for all of the bishop's available spots light up
		for (Spot s : availableSpots) {
			Chess.makeBackground(s);
		}
	}
}
