package ChessFrancalancia;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

// super-class Piece encompasses the shared traits of ANY chess piece
public class Piece extends Pane {
	// keeps track of the position and color of each chess piece
	int row, col, colorID;
	// each piece holds a shared list of available spots
	public static ArrayList<Spot> availableSpots;
	// because of move variations, we need to know whether a piece is a pawn or not
	Boolean isPawn;
	// once a piece is taken over, it becomes the "captured piece" momentarily
	Piece capturedPiece;
	Piece() {
		isPawn = false;
		// the length and width of a spot are 50, meaning that each piece should be a bit smaller
		this.setMinSize(45, 45);
		// centering the layout of the piece on each 'spot'
		this.setLayoutX(2.5);
		this.setLayoutY(2.5);
		// initialize that there are no available moves for any piece
		availableSpots = new ArrayList<Spot>();
		// if a piece is clicked on, it is either being...
			// 1. Selected, (in the else statement at the bottom, this is what the function check() will accomplish
			// 2. Captured by another piece
		this.setOnMouseClicked(m->{
			if (availableSpots.size() == 0) {
				Chess.selectedPiece = null;
			}
			// function that takes in the selected piece (with its position), returns a list of spots
			if (Chess.selectedPiece != null) {
				Spot spot = Chess.getSpot(this.row, this.col);
				if (!availableSpots.isEmpty()) {
					// capture method within click action --> 
					if (availableSpots.contains(spot)) {
						capturedPiece = this;
						if (Chess.selectedPiece.isPawn) {((Pawn)Chess.selectedPiece).hasMoved = true;}
						Chess.selectedPiece.row = spot.row;
						Chess.selectedPiece.col = spot.col;
						Chess.resetBackgrounds();
						availableSpots.clear();
						
						// animation for a captured piece
						// translates a piece either down or up towards the bin
						TranslateTransition tt = new TranslateTransition();
						tt.setNode(capturedPiece);
						tt.setDuration( new Duration(1000));
						tt.setCycleCount(1);
						
						// get board to front
						// get spot to front
						// get captured piece to front
						Chess.board.toFront();
						spot.toFront();
						capturedPiece.toFront();
						
						// based on the color of the piece, either move it down or up
						double setBy;
						if (this.colorID == 0) {
							setBy = 550-m.getSceneY();
						}
						else {
							setBy = -m.getSceneY() + 25;
						}
						tt.setByY(setBy);
				
						tt.setOnFinished(e->
						    {
						    	// when the animation is finished, reset the Y position of the node
						    	capturedPiece.setTranslateY(0);
								tt.setNode(null); 
								getChildren().remove(capturedPiece);
						    	spot.getChildren().remove(this);
						    	// based on the color of the piece, either add it to player one's rack or player two's rack
						    	  if (capturedPiece.colorID == 0){
									  Chess.playerTwoRack.getChildren().add(capturedPiece);
									  capturedPiece.setLayoutY(0);
								   }
								   else {
									   Chess.playerOneRack.getChildren().add(capturedPiece);
									   capturedPiece.setLayoutY(0);
								   }
						    	
							 
						    }
						 );
						// play the animation sequence
						 tt.playFromStart();
						 // add the new piece (the one that captured the clicked piece) to the new spot
						 spot.getChildren().add(Chess.selectedPiece);
						
						try {
							// sound for capturing a piece
							File fsFile = new File(getClass().getResource("capture.mp3").toURI()); // filename
							String rh = fsFile.toURI().toString();
							System.out.println("about to play ..."+rh);
							Media media = new Media( rh );
							MediaPlayer mp = new MediaPlayer(media);
							mp.play();
							mp.setVolume(0.7);
							} catch (Exception e) {
								System.out.println(e.getLocalizedMessage());
							}
					}
					else {
						if (spot.getChildren().size() >0) {
							((Piece)spot.getChildren().get(0)).check();
						}
						else {
							Chess.selectedPiece = null;
						}
						availableSpots.clear();
					}
				}
				// check method for each piece assesses their available moves and assigns new spots to available spots
			} else {
				this.check();
			}
		});
	}
	
	// show images for all of the chess pieces... each piece as a separate URL and can be accesses in the source folder
	public void showImage(String url) {
		try {
			System.out.println("Before printing paths..");
			System.out.println("Path2: "+ getClass().getResource(url).getPath());

			FileInputStream inputStream = new FileInputStream(new File(getClass().getResource(url).toURI()));
			Image image = new Image(inputStream);
			ImageView iv = new ImageView();
			iv.setImage(image);
			iv.setFitHeight(45);
			iv.setFitWidth(45);
			this.getChildren().add(iv);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	public void check() {}
}
