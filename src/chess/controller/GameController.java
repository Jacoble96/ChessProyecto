/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.controller;

import chess.Board;
import chess.Cell;
import chess.Data;
import chess.Pawn;
import chess.Piece;
import chess.Piece.COLOR;
import chess.Rook;
import chess.util.Animation;
import chess.util.AppContext;
import chess.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ivannia Rojas
 */
public class GameController extends Controller implements Initializable {

    @FXML
    private TilePane tp_boardGraphic;// tablero gráfico

    Board logicBoard;// tablero lógico

    public static int hourB, minuteB, secondB = 0;

    public static int hourW, minuteW, secondW = 0;

    public static boolean runningThreadB, runningThreadW = false;

    public static boolean startThreadB, startThreadW = true;

    @FXML
    private HBox hb_letters1;
    @FXML
    private VBox vb_numbers1;
    @FXML
    private VBox vb_numbers2;
    @FXML
    private HBox hb_letters2;
    @FXML
    private Label lb_blackPlayer;
    @FXML
    private Label lb_whitePlayer;
    @FXML
    private FlowPane fp_containerBlackPieces;
    @FXML
    private FlowPane fp_containerWhitePieces;

    private boolean turn;
    @FXML
    private Label lb_whiteScore;
    @FXML
    private Label lb_blackScore;
    @FXML
    private Label lb_player1;
    @FXML
    private Label lb_player2;
    @FXML
    private StackPane root;
    @FXML
    private HBox hb_game;
    @FXML
    private AnchorPane ap_board;
    @FXML
    private Label lb_graphicWhiteJaque;
    @FXML
    private Label lb_graphicBlackJaque;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            Double width = newValue.doubleValue();
            root.setMaxSize(width * 2, width);
            //System.out.println("nuevo valor:"+newValue+"stackPane_visualizador"+flowPane_contenedor.getMaxWidth());
        });

        logicBoard = new Board();//contiene la matriz de celdas lógica (para guardarla en AppContext y acceder a esa matriz desde cualquier lado)
        AppContext.getInstance().set("board", logicBoard);

        ((BaseController) FlowController.getInstance().getController("Base")).updatePlayerDataW();//Inicia el jugador con piezas blancas

        setVisibleUp(false);
        setVisibleLow(true);
        lb_whiteScore.setText(String.valueOf(0));
        lb_blackScore.setText(String.valueOf(0));

        fillBoard();
        //changeTurn();
        setNamePlayers();

        lb_player1.setText("Jugador 1:");
        lb_player2.setText("Jugador 2:");

    }

    @Override
    public void initialize() {

    }

    public void setNamePlayers() {

        for (int i = 0; i < Data.getPlayers().size(); i++) {
            if (Data.getPlayers().get(i).getIsWhitePiece() == true) {

                lb_whitePlayer.setText(Data.getPlayers().get(i).getName());
            } else {
                lb_blackPlayer.setText(Data.getPlayers().get(i).getName());
            }

        }
    }
    //cambia la rotacion de las piezas

    public void changePieceRotation(Boolean rotationPieces) {

        if (rotationPieces == true) {
            setVisibleUp(true);
            setVisibleLow(false);
            tp_boardGraphic.getChildren().stream().forEach(p -> {
                p.setRotate(180);
            });
        } else {
            setVisibleUp(false);
            setVisibleLow(true);
            tp_boardGraphic.getChildren().stream().forEach(p -> {
                p.setRotate(360);
            });
        }
    }

    public void changeTurn(boolean turnPlayer) {

        if (turnPlayer == true) {
            //turno para el equipo negro
            //FlowController.getInstance().goView("Winner");
            turn = false;

            changeBoardRotation(true);
            changePieceRotation(true);

            ((BaseController) FlowController.getInstance().getController("Base")).updatePlayerDataB();

        } else {
            //turno para el equipo blanco
            turn = true;
            changeBoardRotation(true);
            changePieceRotation(false);
            ((BaseController) FlowController.getInstance().getController("Base")).updatePlayerDataW();

        }

    }
    
    public void checkEndGame() {
        
        Cell[][] board = logicBoard.getBoard();
        int whiteCount =0;
        int blackCount =0;
        
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (board[i][j].getIsOccupied()){
                    Piece p = board[i][j].getPieces();
                    if (p.getIsWhite()){
                        whiteCount++;                   
                    }
                    else{
                        blackCount++;                   
                    }               
                }                
            }            
        } 
        
        if (whiteCount ==0 || blackCount == 0){
              String winner;
            if (whiteCount == 0) {
              winner = "Equipo negro";
            } 
            else {
             winner = "Equipo blanco";
            }
            
            javafx.application.Platform.runLater(() -> {
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                alert.setTitle("Fin de partida");
                alert.setHeaderText(null);
                alert.setContentText("¡Juego terminado! Ha ganado: " + winner);
                alert.showAndWait();
            });
            
            
        }                      
    }
    
  

    public void setVisibleUp(Boolean visible) {
        vb_numbers2.setVisible(visible);
        hb_letters2.setVisible(visible);
    }

    public void setVisibleLow(Boolean visible) {
        hb_letters1.setVisible(visible);
        vb_numbers1.setVisible(visible);

    }
    //cambia la rotacion del tablero

    public void changeBoardRotation(Boolean changeRotation) {

        if (changeRotation == true) {

            RotateTransition rt = new RotateTransition(Duration.millis(1000), tp_boardGraphic);
            rt.setByAngle(180);
            rt.setCycleCount(1);
            rt.play();
        } else {
            RotateTransition rt = new RotateTransition(Duration.millis(1000), tp_boardGraphic);
            rt.setByAngle(0);
            rt.setCycleCount(1);
            rt.play();
        }
    }
    

    /*añade estilo dependiendo de la celda añade una clase de la hoja de estilos
	(la hoja de estilos se añadió desde el constructor de celda)*/
    public void paintBoard(Cell cell, int i, int j) {
        if (i % 2 == 0) {
            if (j % 2 == 0) {
                cell.getStyleClass().clear();
                cell.getStyleClass().add("cellBoard");//claro
                cell.setColor("claro");
            } else {
                cell.getStyleClass().clear();
                cell.getStyleClass().add("cell2Board");//oscuro
                cell.setColor("oscuro");
            }
        } else {
            if (j % 2 == 0) {
                cell.getStyleClass().clear();
                cell.getStyleClass().add("cell2Board");//oscuro
                cell.setColor("oscuro");
            } else {
                cell.getStyleClass().clear();
                cell.getStyleClass().add("cellBoard");//claro
                cell.setColor("claro");
            }

        }

    }

    public void fillBoard() {
        Cell[][] matrixAux = new Cell[8][8];//matriz auxiliar para llenar y luego setearla al tablero
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                Cell cell = new Cell(i, j);//cuadrito del tablero

                matrixAux[i][j] = cell;//añade la misma celda gráfica a la matriz lógica(mismo puntero)
                cell.setIsPossibleMov(false);
                if (i == 7 && j == 0 || i == 7 && j == 7) {
                    Rook rookW = new Rook(COLOR.WHITE);
                    cell.getChildren().add(rookW);
                    cell.setPieces(rookW);
                    cell.setIsOccupied(true);

                } else if (i == 0 && j == 0 || i == 0 && j == 7) {
                    Rook rookB = new Rook(COLOR.BLACK);
                    cell.getChildren().add(rookB);
                    cell.setPieces(rookB);
                    cell.setIsOccupied(true);

                }

                if (i == 6) {
                    Pawn pawnW = new Pawn(COLOR.WHITE);
                    cell.getChildren().add(pawnW);
                    cell.setPieces(pawnW);
                    cell.setIsOccupied(true);

                } else if (i == 1) {
                    Pawn pawnB = new Pawn(COLOR.BLACK);
                    cell.getChildren().add(pawnB);
                    cell.setPieces(pawnB);
                    cell.setIsOccupied(true);
                }
                paintBoard(cell, i, j);//pinta el tablero

                tp_boardGraphic.getChildren().add(cell);//mismo que en la matriz lógica, ya quedan relacionadas la gráfica y lógica

            }

        }
        logicBoard.setBoard(matrixAux);//setea la matriz de celdas que se llenó en los ciclos

    }

    public void finalScore(Label highScore) {

        int whiteScore = Integer.parseInt(lb_whiteScore.getText());
        int blackScore = Integer.parseInt(lb_blackScore.getText());
        if (whiteScore > blackScore) {
            highScore.setText("Equipo blanco, puntaje mayor: " + whiteScore);

        } else {
            highScore.setText("Equipo negro, puntaje mayor: " + blackScore);
        }
        if (whiteScore == blackScore) {
            highScore.setText("Los equipos han empetado, puntaje igual: " + whiteScore + ", " + blackScore);
        }

    }

    public void calculateValuesPieces(Boolean valuePiece) {

        int value = 0;

        if (fp_containerBlackPieces.getChildren() != null && fp_containerWhitePieces != null) {
            if (valuePiece == true) {
                for (int i = 0; i < fp_containerBlackPieces.getChildren().size(); i++) {
                    value = value + ((Piece) fp_containerBlackPieces.getChildren().get(i)).getValue();
                    lb_whiteScore.setText(String.valueOf(value));
                }
                //System.out.println("valor piezas negras"+value);
            } else {
                for (int i = 0; i < fp_containerWhitePieces.getChildren().size(); i++) {
                    value = value + ((Piece) fp_containerWhitePieces.getChildren().get(i)).getValue();
                    lb_blackScore.setText(String.valueOf(value));

                }
            }

        }

    }

    public void capturedPieces(Boolean piece) {

        if ((Piece) AppContext.getInstance().get("capturedPiece") != null) {
            if (piece == true) {
                Animation.getInstance().aumentarTamaño((Piece) AppContext.getInstance().get("capturedPiece"));
                fp_containerBlackPieces.getChildren().add((Piece) AppContext.getInstance().get("capturedPiece"));
            } else {
                Animation.getInstance().aumentarTamaño((Piece) AppContext.getInstance().get("capturedPiece"));
                fp_containerWhitePieces.getChildren().add((Piece) AppContext.getInstance().get("capturedPiece"));
            }
        }
    }

    public void resetCapturedPieces() {
        resetCalculateValuesPieces();
        for (int i = 0; i < fp_containerBlackPieces.getChildren().size(); i++) {
            if (fp_containerBlackPieces.getChildren().get(i) != null) {
                fp_containerBlackPieces.getChildren().remove(i);
            }
            if (fp_containerWhitePieces.getChildren().get(i) != null) {
                fp_containerWhitePieces.getChildren().remove(i);
            }

        }

    }

    public void resetCalculateValuesPieces() {
        lb_whiteScore.setText("");
        lb_blackScore.setText("");
    }
    
}
