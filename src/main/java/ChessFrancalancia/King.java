package ChessFrancalancia;
import java.util.ArrayList;

// *In this program, the king can not capture.


public class King extends Piece {
	String url;
	// can move one space in any direction
	King(int i) {
		// set the color of the king when it is initiated
		super.colorID = i;
		if (i == 1) {
			url = "kingWhite.png";
		}
		else {
			url = "kingBlack.png";
		}
		// show the image of the king to the user
		this.showImage(url);
	}
	
	@Override
	public void check()	{
		// each time a king checks its available spots... 
		
		// reset the backgrounds of all the spots on the board
		Chess.resetBackgrounds();
		// set the king as the selected piece
		Chess.selectedPiece = this;
		// clear the list of available spots
		availableSpots.clear();
		// make a temporary list to check all of the king's available spots
		ArrayList<Spot> checklist = new ArrayList<Spot>();
		// 8 possible moves if all spots are open
		Spot spot1 = Chess.getSpot(this.row+1, this.col);
		checklist.add(spot1);
		Spot spot2 = Chess.getSpot(this.row+1, this.col-1);
		checklist.add(spot2);
		Spot spot3 = Chess.getSpot(this.row+1, this.col+1);
		checklist.add(spot3);
		Spot spot4 = Chess.getSpot(this.row-1, this.col);
		checklist.add(spot4);
		Spot spot5 = Chess.getSpot(this.row-1, this.col+1);
		checklist.add(spot5);
		Spot spot6 = Chess.getSpot(this.row-1, this.col-1);
		checklist.add(spot6);
		Spot spot7 = Chess.getSpot(this.row, this.col+1);
		checklist.add(spot7);
		Spot spot8 = Chess.getSpot(this.row, this.col-1);
		checklist.add(spot8);
		// add all of these spots to the available spots list if they are open
		for (Spot s : checklist) {
			if (s.getChildren().size() == 0) {
				availableSpots.add(s);
			}
		}
		// make all of the available spots for the king light up
		for (Spot s : availableSpots) {
			Chess.makeBackground(s);
		}
	}
}
