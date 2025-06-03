package ChessFrancalancia;
import java.io.File;

import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

// emulates a square on the chess board
public class Spot extends Pane{
	// each spot keeps track of its row and column on the grid
	int row, col;
	// based on the position of the spot, it will either have a burly or white background
	Background b;
	Spot(){
		this.setMinSize(50, 50);
		// when a spot is selected by the click of the mouse, this usually means that the selected piece is
		// moving spots...
		this.setOnMouseClicked(m->{
			// check if a player has picked up a piece
			if (Chess.selectedPiece != null) {
				try {
					// check if the spot they're clicking is a valid spot for the piece they're holding
					if (Piece.availableSpots.contains(this)) {
						// if the move is valid, move the piece and change the row and column values for that piece
						this.getChildren().add(Chess.selectedPiece);
						Piece.availableSpots.clear();
						// if the piece is a pawn and it has moved for the first time, it can no longer move two spaces
						if (Chess.selectedPiece.isPawn) { 
							((Pawn)Chess.selectedPiece).hasMoved = true;
						}
						Chess.selectedPiece.row = this.row;
						Chess.selectedPiece.col = this.col;
						Chess.selectedPiece = null;
						// reset the backgrounds for all the spots on the board
						Chess.resetBackgrounds();
						
						// sound for a chess piece hitting the table
						File fsFile = new File(getClass().getResource("pieceSlide.mp3").toURI()); // filename
						String rh = fsFile.toURI().toString();
						System.out.println("about to play ..."+rh);
						Media media = new Media( rh );
						MediaPlayer mp = new MediaPlayer(media);
						mp.play();
						mp.setVolume(0.5);
					}
				} catch( Exception e) {
					System.out.println(e.getLocalizedMessage());
				}
			}
			// if there is not a piece that has been picked up (selected)... clear the available spots list and set selectedPiece to null
			else {
				Chess.selectedPiece = null;
				Piece.availableSpots.clear();
			}
			
		});
	}
	// tracks the absolute position of each spot on the grid
	public void setPosition(int row, int col) {
		this.row = row;
		this.col = col;
	}
}