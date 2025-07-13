/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import chess.util.AppContext;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

/**
 *
 * @author Ivannia Rojas
 */
public abstract class Piece extends ImageView {

    protected Boolean active;//Revisar si la usa (la idea es que mantenga si está en juego o ya fue descartada)

    public enum COLOR {
        WHITE, BLACK
    }
    private Boolean isWhite;
    private Boolean firstMove;
    protected int value;
    private Boolean dragDetected;
    private boolean turnPlayer;

    public Piece() {

    }

    public Piece(COLOR color) {
        this.active = false;
        this.firstMove = true;

        if (color == COLOR.WHITE) {
            isWhite = true;
        } else {
            isWhite = false;
        }

        addDragSupport();

    }

    public void addDragSupport() {

        this.setOnMouseReleased(e -> {

            if (this.active == false) {
                this.setCursor(Cursor.MOVE);
            }

        });

        this.setOnMouseEntered(e -> {

            if (this.active == false) {
                this.setCursor(Cursor.MOVE);
            }

        });

        this.setOnDragDetected(e -> {

            //if(Objects.equals(this.isWhite, turn)){//si turn es true son blancas si es false son negras
            if (this.active == false) {
                dragDetected = true;
                Integer i = ((Cell) this.getParent()).getI();
                Integer j = ((Cell) this.getParent()).getJ();

                Cell logicMatrix[][] = ((Board) AppContext.getInstance().get("board")).getBoard();
                Cell cell = logicMatrix[i][j];

                //if(||(boolean)AppContext.getInstance().get("isBlackJaque")!=true||(boolean)AppContext.getInstance().get("isWhiteJaque")!=true){
                cell.getStyleClass().add("possMovDrag");//define un estilo a la celda cuando la pieza está siendo arrastrada.
                calculatePossMov();//calcula los movimientos posibles de cada pieza.
                //}

                Dragboard dragboard = this.startDragAndDrop(TransferMode.COPY);
                ClipboardContent content = new ClipboardContent();
                content.putImage(this.getImage());
                dragboard.setContent(content);
                e.consume();

                AppContext.getInstance().set("piece", this);//Guarda la pieza que se está tratando de arratrar para luego colocarla en una celda definida.
                AppContext.getInstance().set("changeState", cell);//Guarda la pieza que se está tratando de arratrar para luego se ser colocada cambiar el estado de la celda a desocupada.
                AppContext.getInstance().set("initialPosPiece", cell);

            }//fin active

            //}
        });
    }//termina metodo

    public Boolean getActive() {
        return active;

    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getIsWhite() {
        return isWhite;
    }

    public void setIsWhite(Boolean isWhite) {
        this.isWhite = isWhite;
    }

    //Obtiene la celda padre y y de ahí obtiene el indice que seteó al crearla
    public Integer getI() {
        return ((Cell) this.getParent()).getI();
    }
    //Obtiene la celda padre y y de ahí obtiene el indice que seteó al crearla

    public Integer getJ() {
        return ((Cell) this.getParent()).getJ();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Boolean getFirstMove() {
        return firstMove;
    }

    public void setFirstMove(Boolean firstMove) {
        this.firstMove = firstMove;
    }

    public Boolean getDragDetected() {
        return dragDetected;
    }

    public void setDragDetected(Boolean dragDetected) {
        this.dragDetected = dragDetected;
    }

    public boolean isTurnPlayer() {
        return turnPlayer;
    }

    public void setTurnPlayer(boolean turnPlayer) {
        this.turnPlayer = turnPlayer;
    }

    public abstract void calculatePossMov(); //todos sus hijos tiene movimientos distintos de acuerdo al tipo de pieza

}
