/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import chess.controller.GameController;
import chess.util.Animation;
import chess.util.AppContext;
import chess.util.FlowController;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Ivannia Rojas
 */
public class Cell extends StackPane {

    ImageView piece;
    Piece pieces;
    int i;
    int j;
    Boolean isOccupied;
    Boolean isPossibleMov;
    boolean droppedPiece;
    Boolean dropFirstMove;
    String color;

    //Constructor vacío. Inicializa una celda sin coordenadas y sin pieza.
    public Cell() {

        this.isOccupied = false;
        this.isPossibleMov = false;
        this.droppedPiece = false;
        this.dropFirstMove = true;
        this.color = "";

        this.i = 0;
        this.j = 0;
        this.pieces = null;
        initCell();
    }

    //Representa cada cuadrito del tablero
    //Crea una celda en la posición (i, j) del tablero, sin pieza. Útil para representar su ubicación lógica.
    public Cell(int i, int j) {
        this.isOccupied = false;
        this.isPossibleMov = false;
        this.droppedPiece = false;
        this.dropFirstMove = true;

        this.color = "";
        this.i = i;
        this.j = j;
        this.pieces = null;

        initCell();
    }

    //Representa cada cuadrito del tablero que recibe directamente la pieza
    //Crea una celda con nodos hijos (por ejemplo, una pieza) ya añadidos. Útil cuando se crea visualmente con elementos.
    public Cell(Node... children) {
        super(children);
        this.isOccupied = false;
        this.isPossibleMov = false;
        this.droppedPiece = false;
        this.dropFirstMove = true;

        this.color = "";
        this.i = 0;
        this.j = 0;
        this.pieces = null;
        initCell();//

    }

    //Inicializa las configuraciones básicas de la celda (estética)
    //Inicializa la celda visualmente: le da tamaño y carga la hoja de estilo chessStyle.css. También activa el soporte para arrastrar y soltar piezas (drag & drop).
    private void initCell() {
        this.setPrefSize(60, 60);
        addDropSupport();
        //obtiene lista de hojas de estilos y le añade la propia
        this.getStylesheets().add("chess/view/chessStyle.css");
    }

    //Métodos como getI(), setJ(), getPiece(), etc., permiten acceder y modificar los atributos de la celda como su posición, si está ocupada, si es un movimiento posible, o qué pieza contiene.
    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public ImageView getPiece() {
        return piece;
    }

    public void setPiece(ImageView piece) {
        this.piece = piece;
    }

    public Boolean getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(Boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public Boolean getIsPossibleMov() {
        return this.isPossibleMov;
    }

    public void setIsPossibleMov(Boolean isPossibleMov) {
        this.isPossibleMov = isPossibleMov;
    }

    public Piece getPieces() {
        return pieces;
    }

    public void setPieces(Piece pieces) {
        this.pieces = pieces;
    }

    @Override
    public String toString() {
        return "Cell{" + "i=" + i + ", j=" + j + '}';
    }

    
    //
    private void addDropSupport() {

        this.setOnDragOver(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasImage()) {
                e.acceptTransferModes(TransferMode.COPY);
            }
            e.consume();
        });

        this.setOnDragDropped(e -> {
            Cell logicMatrix[][] = ((Board) AppContext.getInstance().get("board")).getBoard();
            Cell cell = logicMatrix[i][j];
            Cell changeState = (Cell) AppContext.getInstance().get("changeState");

            Dragboard db = e.getDragboard();
            boolean success = false;
            if (db.hasImage()) {

                if (cell.getIsPossibleMov() == true) {

                    droppedPiece = true;
                    AppContext.getInstance().set("droppedPiece", droppedPiece);
                    //Guarda la pieza capturada y la define fuera de fuego.
                    //El equipo blanco captura piezas negras
                    if (cell.getPieces() != null && !cell.getPieces().getIsWhite()) {//si está apuntando a una pieza y si la pieza es negra.
                        Piece capturedPiece = cell.getPieces();
                        capturedPiece.active = true;//La pieza fue capturada, está fuera de juego.
                        AppContext.getInstance().set("capturedPiece", capturedPiece);
                        ((GameController) FlowController.getInstance().getController("Game")).capturedPieces(true);
                        Animation.getInstance().rotarNodo((Piece) AppContext.getInstance().get("piece"));
                        ((GameController) FlowController.getInstance().getController("Game")).calculateValuesPieces(true);
                        

                        ////El equipo negro captura piezas blancas
                    } else if (cell.getPieces() != null && cell.getPieces().getIsWhite()) {//si está apuntando a una pieza y si la pieza es blanca.
                        Piece capturedPiece = cell.getPieces();
                        capturedPiece.active = true;//La pieza fue capturada, está fuera de juego.
                        AppContext.getInstance().set("capturedPiece", capturedPiece);
                        ((GameController) FlowController.getInstance().getController("Game")).capturedPieces(false);//Piezas blancas
                        Animation.getInstance().rotarNodo((Piece) AppContext.getInstance().get("piece"));
                        ((GameController) FlowController.getInstance().getController("Game")).calculateValuesPieces(false);

                    }

                    cell.getChildren().clear();
                    cell.getChildren().add((Piece) AppContext.getInstance().get("piece"));//Agrega a la celda la pieza que fue arrastrada.
                    cell.setPieces((Piece) AppContext.getInstance().get("piece"));//Agrega logicamente la pieza.
                    cell.setIsOccupied(true);//Cambia la casilla a ocupada porque ahora hay una nueva celda.

                    changeState.setIsOccupied(false);//Cambia la casilla de donde se encontraba originalmente la pieza a desocupada, porque ahora ya no hay pieza en esa celda.
                    changeState.setPieces(null);
                    changeState.setIsPossibleMov(false);

                    if (cell.getPieces().getFirstMove() == true) {//si coloca la pieza la primera vez, activa que ya la pieza se ha movido.
                        cell.getPieces().setFirstMove(false);
                    }

                    turn();//Cambia de turno cada vez que el jugador realiza una jugada.
                    ((GameController) FlowController.getInstance().getController("Game")).checkEndGame();

                }//fin movimiento posible

                resetBoard(logicMatrix);

                success = true;
            }
            e.setDropCompleted(success);
            e.consume();
        });

    }

    public void resetBoard(Cell logicMatrix[][]) {
        //resetea los movimientos posibles lógica y gráficamente
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Cell resetCell = logicMatrix[x][y];
                resetCell.setIsPossibleMov(false);

                if (x % 2 == 0) {
                    if (y % 2 == 0) {
                        resetCell.getStyleClass().clear();
                        resetCell.getStyleClass().add("cellBoard");//claro
                        resetCell.setColor("claro");
                    } else {
                        resetCell.getStyleClass().clear();
                        resetCell.getStyleClass().add("cell2Board");//oscuro
                        resetCell.setColor("oscuro");
                    }
                } else {
                    if (y % 2 == 0) {
                        resetCell.getStyleClass().clear();
                        resetCell.getStyleClass().add("cell2Board");//oscuro
                        resetCell.setColor("oscuro");
                    } else {
                        resetCell.getStyleClass().clear();
                        resetCell.getStyleClass().add("cellBoard");//claro
                        resetCell.setColor("claro");
                    }

                }
            }
        }
    }

    public Boolean getDropFirstMove() {
        return dropFirstMove;
    }

    public void setDropFirstMove(Boolean dropFirstMove) {
        this.dropFirstMove = dropFirstMove;
    }

    public void turn() {
        if (this.getPieces().getIsWhite() == true) {
            ((GameController) FlowController.getInstance().getController("Game")).changeTurn(true);
        } else {
            ((GameController) FlowController.getInstance().getController("Game")).changeTurn(false);
        }

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
