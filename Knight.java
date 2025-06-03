package ChessFrancalancia;


public class Knight extends Piece {
	String url;
	// can move two spaces forward and one space to the side in any direction
	Knight(int i) {
		// set the color ID to either white or black when a piece is initiated
		super.colorID = i;
		if (i == 1) {
			url = "knightWhite.png";
		}
		else {
			url = "knightBlack.png";
		}
		// show the image of the knight to the user
		this.showImage(url);
	}
	
	@Override
	public void check() {
		// each time a knight checks its available spots... 
		
		// reset the backgrounds of all the spots on the board
		Chess.resetBackgrounds();
		// set the knight as the selected piece
		Chess.selectedPiece = this;
		// clear the available spots list
		availableSpots.clear();
		
		// Every knight has 8 available spots it can move towards, where it is two spots away in one direction and one spot away in another
		for (Spot s : Chess.spotList) {
			if ((s.row-2 == this.row && s.col+1 == this.col) || (s.row-2 == this.row && s.col-1 == this.col) ||
			(s.row-1 == this.row && s.col+2 == this.col) || (s.row-1 == this.row && s.col-2 == this.col) ||
			(s.row+2 == this.row && s.col+1 == this.col) || (s.row+2 == this.row && s.col-1 == this.col) ||
			(s.row+1 == this.row && s.col+2 == this.col) || (s.row+1 == this.row && s.col-2 == this.col)){
				// only spots that are open or contain a piece of opposite color can be available spots for the knight
				if (s.getChildren().size()>0){
					if (((Piece)s.getChildren().get(0)).colorID !=this.colorID) {
						availableSpots.add(s);
					}
				}
				else {
					availableSpots.add(s);
				}
			}
		}
		// make all of the available spots for the knight light up
		for (Spot s : availableSpots) {
			Chess.makeBackground(s);
		}
	}
	
}
