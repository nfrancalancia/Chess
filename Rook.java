package ChessFrancalancia;


public class Rook extends Piece {
	String url;
	// can move vertically or horizontally
	Rook(int i) {
		// set the color ID to either white or black when a piece is initiated
		super.colorID = i;
		if (i == 1) {
			url = "rookWhite.png";
		}
		else {
			url = "rookBlack.png";
		}
		// display the image for that piece
		this.showImage(url);
	}
		
		@Override
		 public void check() {
			// every time you check then available spots for a rook...
			
			// reset the backgrounds of all the spots to their original colors
			Chess.resetBackgrounds();
			// set the rook as the selected piece
			Chess.selectedPiece = this;
			// clear the available spots list so new spots can be added for the rook
			availableSpots.clear();
			// Following code looks for spots vertically and horizontally, according to the position of the rook (pieceRow and pieceCol)
			// when it finds the first spot that is unavailable in that direction, the loop breaks
			// a spot is unavailable if it contains a piece of the same color OR it contains not the first spot with an opponent's piece, but the second spot
			int pieceRow = this.row;
			int pieceCol = this.col;
			pieceCol++;
			while (pieceCol < 8) {
				Spot spot = Chess.getSpot(pieceRow, pieceCol);
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
				if (spot.getChildren().size() > 0 && ((Piece)spot.getChildren().get(0)).colorID ==this.colorID) {
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
				if (spot.getChildren().size()>0 && ((Piece)spot.getChildren().get(0)).colorID==this.colorID) {
					break;
				}
				availableSpots.add(spot);
				if (spot.getChildren().size()>0) {
					break;
				}
				pieceCol--;
			}	
			// make the backgrounds for all of the bishop's available spots light up
			for (Spot s : availableSpots) {
				Chess.makeBackground(s);
			}
	}
}
