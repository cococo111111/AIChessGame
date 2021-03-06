package gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.CheckMoveValidation;

/**
 * Created by yipin on 15/07/2017.
 */

public class ChessGame extends Application {

    public static final int TILE_SIZE = 80;
    public static final int WIDTH = 8; // The board has 8 columns
    public static final int HEIGHT = 8; // and 8 rows.

    private final Tile[][] board = new Tile[WIDTH][HEIGHT];

    private final Group tileGroup = new Group();
    private final Group pieceGroup = new Group();

    private Parent createContent() {
        BorderPane chess = new BorderPane();

        // Top and bottom area
        HBox top = new HBox(50);
        top.setAlignment(Pos.CENTER);

        Text txtPlayerB = new Text("Black Side");
        txtPlayerB.setFont(Font.font(STYLESHEET_MODENA, FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 35));
        LinearGradient fontGradient = new LinearGradient(
                .5,
                0,
                .5,
                1,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.SKYBLUE),
                new Stop(1, Color.SILVER)
        );
        txtPlayerB.setFill(fontGradient);
        top.getChildren().add(txtPlayerB);

        HBox bottom = new HBox(50);
        bottom.setAlignment(Pos.CENTER);
        Text txtPlayerW = new Text("White Side");
        txtPlayerW.setFont(Font.font(STYLESHEET_MODENA, FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 35));
        txtPlayerW.setFill(fontGradient);
        bottom.getChildren().add(txtPlayerW);

        Pane center = new Pane();
        center.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        center.getChildren().addAll(tileGroup, pieceGroup);

        // Create chess board
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[x][y] = tile;

                tileGroup.getChildren().add(tile);
            }
        }

        // Add chess pieces
        Piece bRook1 = makePiece(PieceType.BROOK, 0, 0);
        Piece bKnight1 = makePiece(PieceType.BKNIGHT, 1, 0);
        Piece bBishop1 = makePiece(PieceType.BBISHOP, 2, 0);
        Piece bQueen = makePiece(PieceType.BQUEEN, 3, 0);
        Piece bKing = makePiece(PieceType.BKING, 4, 0);
        Piece bBishop2 = makePiece(PieceType.BBISHOP, 5, 0);
        Piece bKnight2 = makePiece(PieceType.BKNIGHT, 6, 0);
        Piece bRook2 = makePiece(PieceType.BROOK, 7, 0);

        Piece bPawn1 = makePiece(PieceType.BPAWN, 0, 1);
        Piece bPawn2 = makePiece(PieceType.BPAWN, 1, 1);
        Piece bPawn3 = makePiece(PieceType.BPAWN, 2, 1);
        Piece bPawn4 = makePiece(PieceType.BPAWN, 3, 1);
        Piece bPawn5 = makePiece(PieceType.BPAWN, 4, 1);
        Piece bPawn6 = makePiece(PieceType.BPAWN, 5, 1);
        Piece bPawn7 = makePiece(PieceType.BPAWN, 6, 1);
        Piece bPawn8 = makePiece(PieceType.BPAWN, 7, 1);
        pieceGroup.getChildren().addAll(bRook1, bKnight1, bBishop1, bQueen, bKing, bBishop2, bKnight2, bRook2);
        pieceGroup.getChildren().addAll(bPawn1, bPawn2, bPawn3, bPawn4, bPawn5, bPawn6, bPawn7, bPawn8);
        Piece wRook1 = makePiece(PieceType.WROOK, 0, 7);
        Piece wKnight1 = makePiece(PieceType.WKNIGHT, 1, 7);
        Piece wBishop1 = makePiece(PieceType.WBISH0P, 2, 7);
        Piece wQueen = makePiece(PieceType.WQUEEN, 3, 7);
        Piece wKing = makePiece(PieceType.WKING, 4, 7);
        Piece wBishop2 = makePiece(PieceType.WBISH0P, 5, 7);
        Piece wKnight2 = makePiece(PieceType.WKNIGHT, 6, 7);
        Piece wRook2 = makePiece(PieceType.WROOK, 7, 7);

        Piece wPawn1 = makePiece(PieceType.WPAWN, 0, 6);
        Piece wPawn2 = makePiece(PieceType.WPAWN, 1, 6);
        Piece wPawn3 = makePiece(PieceType.WPAWN, 2, 6);
        Piece wPawn4 = makePiece(PieceType.WPAWN, 3, 6);
        Piece wPawn5 = makePiece(PieceType.WPAWN, 4, 6);
        Piece wPawn6 = makePiece(PieceType.WPAWN, 5, 6);
        Piece wPawn7 = makePiece(PieceType.WPAWN, 6, 6);
        Piece wPawn8 = makePiece(PieceType.WPAWN, 7, 6);
        pieceGroup.getChildren().addAll(wPawn1, wPawn2, wPawn3, wPawn4, wPawn5, wPawn6, wPawn7, wPawn8);
        pieceGroup.getChildren().addAll(wRook1, wKnight1, wBishop1, wQueen, wKing, wBishop2, wKnight2, wRook2);

        chess.setTop(top);
        chess.setBottom(bottom);
        chess.setCenter(center);

        return chess;
    }

    private int toBoard(double pixel) {
        return (int)(pixel + TILE_SIZE /2) / TILE_SIZE;
    }

    Stage window;
    Scene gameScene;
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        gameScene = new Scene(createContent());
        window.setTitle("Chess Like Game");
        window.setScene(gameScene);
        window.show();
    }

    private int x0, y0;
    /* Move the chess piece */
    private Piece makePiece (PieceType type, int x, int y) {
        Piece piece = new Piece(type, x, y);

        piece.setOnMousePressed(e -> {
            x0 = toBoard(piece.getOldX());
            y0 = toBoard(piece.getOldY());

            int tempX, tempY;

            CheckMoveValidation moveValidation = new CheckMoveValidation();
            for (tempX = 0; tempX < 8; tempX++) {
                for (tempY = 0; tempY < 8; tempY++) {
                    if (moveValidation.checkValidMove(piece.getType(), tempX, tempY, x0, y0)) {
                        highlightTile(tempX, tempY);
                        board[tempX][tempY].setHighlight(true);
                    }
                }
            }
        });

        piece.setOnMouseDragged(e -> {
            piece.relocate(e.getSceneX() - TILE_SIZE/2, e.getSceneY() - TILE_SIZE);
        });

        piece.setOnMouseReleased(e -> {
            int newX = toBoard(piece.getLayoutX());
            int newY = toBoard(piece.getLayoutY());
            int tempX, tempY;
            boolean isMovable = true;

            for (tempX = 0; tempX < 8; tempX++) {
                for (tempY = 0; tempY < 8; tempY++) {
                    if (board[tempX][tempY].getHighlight()) {
                        stopHighlightTile(tempX, tempY);
                    }
                }
            }

            if (isMovable == false) {
                piece.abortMove();
            } else if (isMovable == true) {
                if (newX == x0 && newY == y0) {
                    piece.abortMove();
                } else if (board[newX][newY].hasPiece() == true) {
                    pieceGroup.getChildren().remove(board[newX][newY].getPiece());
                    board[newX][newY].setPiece(null);
                }
                piece.move(newX, newY);
                board[x0][y0].setPiece(null);
                board[newX][newY].setPiece(piece);
                /* Save the new position of x, y */
                x0 = newX;
                y0 = newY;
            }
        });

        return piece;
    }

    /* Highlighting the tiles */
    private void highlightTile(int x, int y) {
        if (board[x][y].getFill().equals(Color.DARKGRAY)) {
            board[x][y].setPreviousTileColor(true);
        } else {
            board[x][y].setPreviousTileColor(false);
        }
        board[x][y].setFill(Color.YELLOW);
    }

    private void stopHighlightTile(int x, int y) {
        if (board[x][y].getPreviousTileColor() == true) {
            board[x][y].setFill(Color.DARKGRAY);
        } else {
            board[x][y].setFill(Color.WHITE);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
