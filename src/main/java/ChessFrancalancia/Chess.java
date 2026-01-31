package ChessFrancalancia;
import java.util.ArrayList;


// Description: This program functions a chess teaching program, where players can evaluate
// certain chess moves, captures, etc.

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Chess extends Application {
	
	// static variables for all racks, pieces, and visuals that must be accessed outside of this file 
	public static Pane root;
	public static GridPane board;
	public static ArrayList<Spot> spotList;
	public static Piece selectedPiece;
	public static HBox playerOneRack, playerTwoRack;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	public void start(Stage stage) {
		// Program is called Nick's gambit !!!
		stage.setTitle("Nick's Gambit");
		root = new Pane();
		Scene scene = new Scene(root, 600, 600);
		stage.setScene(scene);
		stage.show();
		stage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});
		// Grey background
		root.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		// initialize the spot list
		spotList = new ArrayList<Spot>();
		// set up board
		setBoard();
		// set racks for each player --> this will be where captured pieces will go to
		setRacks();
	}
	
	public void setRacks() {
		// the rack for white's pieces will be at the top of the screen
		playerOneRack = new HBox();
		playerOneRack.setLayoutY(0);
		Pane whiteRack = new Pane();
		whiteRack.setMinSize(500,75);
		whiteRack.setBackground(new Background(new BackgroundFill(Color.SADDLEBROWN, CornerRadii.EMPTY, Insets.EMPTY)));
		whiteRack.setLayoutX(50);
		whiteRack.setLayoutY(10);
		root.getChildren().add(whiteRack);
		whiteRack.getChildren().add(playerOneRack);
		// the rack for black's pieces will be at the bottom of the screen
		playerTwoRack = new HBox();
		playerTwoRack.setLayoutY(0);
		Pane blackRack = new Pane();
		blackRack.setMinSize(500,75);
		blackRack.setBackground(new Background(new BackgroundFill(Color.SADDLEBROWN, CornerRadii.EMPTY, Insets.EMPTY)));
		blackRack.setLayoutX(50);
		blackRack.setLayoutY(525);
		// add both of the player's racks to the root Pane
		root.getChildren().add(blackRack);
		blackRack.getChildren().add(playerTwoRack);
	}

	public void setBoard() {
		// the chess board can be emulated in the form of a grid pane
		board = new GridPane();
		board.setMinSize(400, 400);
		board.setLayoutX(100);
		board.setLayoutY(100);
		board.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		for (int i = 0; i < 64; i++) {
			Spot spot = new Spot();
			// set color of each spot on the board --> a spot is either burly or white colored 
			// new variable j handles the difference of colors between each row
			Background burly = new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY));
			Background white = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
			int j;
			if ((i > 7 && i < 16) || (i > 23 && i < 32) || (i > 39 && i < 48) || (i > 55 && i < 64)) {
				j = i + 1;
			} else {
				j = i;
			}
			if (j % 2 == 0) {
				spot.b = burly;
				spot.setBackground(burly);
			} else {
				spot.b = white;
				spot.setBackground(white);
			}
			// add that spot to the chess board
			board.add(spot, i % 8, i / 8);
			// set the position of each spot on the grid
			spot.setPosition(i/8, i%8);
			// 	add that spot to the spot list ( so that we can check valid moves later on )
			spotList.add(spot);
		}
		// add the board to the root pane
		root.getChildren().add(board);
		// set initial chess board pieces for one player
		setInitialPiecesFarSide();
		// set initial chess pieces for the other player
		setInitialPiecesCloseSide();
	}

	public void setInitialPiecesFarSide() {
		// on the first and second rows of each side, add the exact chess pieces
		Piece r1 = new Rook(0);
		r1.col = spotList.get(0).col;
		r1.row = spotList.get(0).row;
		Piece r2 = new Rook(0);
		r2.col = spotList.get(7).col;
		r2.row = spotList.get(7).row;
		Piece k1 = new Knight(0);
		k1.col = spotList.get(1).col;
		k1.row = spotList.get(1).row;
		Piece k2 = new Knight(0);
		k2.col = spotList.get(6).col;
		k2.row = spotList.get(6).row;
		Piece b1 = new Bishop(0);
		b1.col = spotList.get(2).col;
		b1.row = spotList.get(2).row;
		Piece b2 = new Bishop(0);
		b2.col = spotList.get(5).col;
		b2.row = spotList.get(5).row;
		// add the king and the queen
		Piece q = new Queen(0);
		q.col = spotList.get(3).col;
		q.row = spotList.get(3).row;
		Piece k = new King(0);
		k.col = spotList.get(4).col;
		k.row = spotList.get(4).row;
		// add rooks, bishops, and knights to their according initial spots
		spotList.get(0).getChildren().add(r1);
		spotList.get(7).getChildren().add(r2);
		spotList.get(1).getChildren().add(k1);
		spotList.get(6).getChildren().add(k2);
		spotList.get(2).getChildren().add(b1);
		spotList.get(5).getChildren().add(b2);
		// add the king and queen to their according initial spots
		spotList.get(3).getChildren().add(q);
		spotList.get(4).getChildren().add(k);
		// add all of the pawns to their initial spots
		for (int i = 8; i < 16; i++) {
			Piece p = new Pawn(0);
			p.row = spotList.get(i).row;
			p.col = spotList.get(i).col;
			spotList.get(i).getChildren().add(p);
		}
		// other side of the board
	}
	
	public void setInitialPiecesCloseSide() {
		
		// on the first and second rows of each side, add the exact chess pieces
		Piece r1 = new Rook(1);
		r1.col = spotList.get(56).col;
		r1.row = spotList.get(56).row;
		Piece r2 = new Rook(1);
		r2.col = spotList.get(63).col;
		r2.row = spotList.get(63).row;
		Piece k1 = new Knight(1);
		k1.col = spotList.get(57).col;
		k1.row = spotList.get(57).row;
		Piece k2 = new Knight(1);
		k2.col = spotList.get(62).col;
		k2.row = spotList.get(62).row;
		Piece b1 = new Bishop(1);
		b1.col = spotList.get(58).col;
		b1.row = spotList.get(58).row;
		Piece b2 = new Bishop(1);
		b2.col = spotList.get(61).col;
		b2.row = spotList.get(61).row;
		// add the king and the queen
		Piece q = new Queen(1);
		q.col = spotList.get(59).col;
		q.row = spotList.get(59).row;
		Piece k = new King(1);
		k.col = spotList.get(60).col;
		k.row = spotList.get(60).row;
		// add rooks, bishops, and knights to their according initial spots
		spotList.get(56).getChildren().add(r1);
		spotList.get(63).getChildren().add(r2);
		spotList.get(57).getChildren().add(k1);
		spotList.get(62).getChildren().add(k2);
		spotList.get(58).getChildren().add(b1);
		spotList.get(61).getChildren().add(b2);
		// add the king and queen to their according initial spots
		spotList.get(59).getChildren().add(q);
		spotList.get(60).getChildren().add(k);
		// add all of the pawns to their initial spots
		for (int i = 48; i < 56; i++) {
			Piece p = new Pawn(1);
			p.row = spotList.get(i).row;
			p.col = spotList.get(i).col;
			spotList.get(i).getChildren().add(p);
		}
	}
	public static void resetBackgrounds() {
		// sets all the chess spot backgrounds to their original colors
		// this happens after a new piece is selected
		for (Spot s : spotList) {
			s.setBackground(s.b);
			s.setBorder(Border.EMPTY);
		}
	}
	
	// returns a specific spot on the chess board based on their row and column numbers
	public static Spot getSpot(int row, int col) {
		Spot s = new Spot();
		for (Spot spot : spotList) {
			if (spot.row == row && spot.col == col) {
				return spot;
			}
		}
		return s;
	}
	// make available spots light up with different background colors when a chess piece is selected
	public static void makeBackground(Spot s) {
		// if the player is playing white pieces, their available spots will turn blue
		// otherwise, available spots will turn green
		if (Chess.selectedPiece.colorID == 0) {
			s.setBackground(new Background(new BackgroundFill(Color.LAWNGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		}
		else {
			s.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		}
		s.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DOTTED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
}
