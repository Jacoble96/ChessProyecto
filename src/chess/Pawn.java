/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import chess.util.AppContext;

/**
 *
 * @author Ivannia Rojas
 */
public class Pawn extends Piece {//Peon

    public Pawn(COLOR color) {

        super(color);
        this.setValue(1);

        if (color == COLOR.WHITE) {
            this.setImage(Data.getPawnW());
            this.setId("pawnW");
        } else {
            this.setImage(Data.getPawnB());
            this.setId("pawnB");
        }

    }

    @Override
    public Boolean getActive() {
        return active;
    }

    @Override
    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public void calculatePossMov() {

        int f, c;
        int fa, fb;
        Cell logicMatrix[][] = ((Board) (AppContext.getInstance().get("board"))).getBoard();//matriz de AppContext
        Integer i = this.getI();//i de la celda en la que está(sacada de pieza)
        Integer j = this.getJ();//j de la celda en la que está(sacada de pieza)
        Cell cellIsJaque = (Cell) AppContext.getInstance().get("cellIsJaque");
        if (!this.getIsWhite()) {//si las piezas son negras
            if (AppContext.getInstance().get("isWhiteJaque") == null || (boolean) AppContext.getInstance().get("isWhiteJaque") == false) {
                //movimiento adelante si está vacía y si es la primera vez.
                if (this.getFirstMove() == true) {//si es la primer vez se puede mover dos veces o 1 vez.
                    fa = i + 2;
                    fb = i + 1;
                    if (fb <= 7 && fa <= 7 && !logicMatrix[fb][j].getIsOccupied() && !logicMatrix[fa][j].getIsOccupied()) {//si las celdas de en frente no está ocupadas o ocupada.
                        for (fa = i + 2; fa > i; fa--) {
                            logicMatrix[fa][j].setIsPossibleMov(true);
                            Data.drawPossibleMove(fa, j, true, logicMatrix);
                        }
                    } else if (fb <= 7 && !logicMatrix[fb][j].getIsOccupied() && logicMatrix[fa][j].getIsOccupied()) {
                        f = i + 1;
                        if (i <= 7 && !logicMatrix[f][j].getIsOccupied()) {//si la celda de en frente no está ocupada
                            logicMatrix[f][j].setIsPossibleMov(true);
                            Data.drawPossibleMove(f, j, true, logicMatrix);
                        }
                    }
                    //this.setFirstMove(false);

                    //movimiento adelante si está vacía y si es la segunda vez.
                } else if (this.getFirstMove() == false) {
                    f = i + 1;
                    if (f <= 7 && !logicMatrix[f][j].getIsOccupied()) {//si la celda de en frente no está ocupada
                        logicMatrix[f][j].setIsPossibleMov(true);

                        Data.drawPossibleMove(f, j, true, logicMatrix);
                    }
                }

                /*movimiento de las piezas 
			para capturar una pieza del 
			equipo contrario en este caso
			las piezas negras*/
                //movimiento diagonal izquierda adelante
                f = i + 1;
                c = j - 1;
                if (f <= 7 && c >= 0 && logicMatrix[f][c].getIsOccupied() && logicMatrix[f][c].getPieces().getIsWhite()) {//si la celda está ocupada y si la pieza es blanca
                    logicMatrix[f][c].setIsPossibleMov(true);
                    Data.drawPossibleMove(f, c, true, logicMatrix);
                }

                //movimiento diagonal derecha adelante
                f = i + 1;
                c = j + 1;
                if (f <= 7 && c <= 7 && logicMatrix[f][c].getIsOccupied() && logicMatrix[f][c].getPieces().getIsWhite()) {//si la celda está ocupada y si la pieza es blanca
                    logicMatrix[f][c].setIsPossibleMov(true);
                    Data.drawPossibleMove(f, c, true, logicMatrix);
                }
            } else {//si es jaque

                //movimiento diagonal izquierda adelante
                f = i + 1;
                c = j - 1;
                if (f <= 7 && c >= 0 && logicMatrix[f][c] == cellIsJaque && logicMatrix[f][c].getIsOccupied() && logicMatrix[f][c].getPieces().getId().equals(((Piece) AppContext.getInstance().get("pieceIsJaque")).getId())) {//si la celda está ocupada y si la pieza es blanca
                    logicMatrix[f][c].setIsPossibleMov(true);
                    Data.drawPossibleMove(f, c, true, logicMatrix);
                }

                //movimiento diagonal derecha adelante
                f = i + 1;
                c = j + 1;
                if (f <= 7 && c <= 7 && logicMatrix[f][c] == cellIsJaque && logicMatrix[f][c].getIsOccupied() && logicMatrix[f][c].getPieces().getId().equals(((Piece) AppContext.getInstance().get("pieceIsJaque")).getId())) {//si la celda está ocupada y si la pieza es blanca
                    logicMatrix[f][c].setIsPossibleMov(true);
                    Data.drawPossibleMove(f, c, true, logicMatrix);
                }

            }
        } else {//si las piezas son blancas
            if (AppContext.getInstance().get("isBlackJaque") == null || (boolean) AppContext.getInstance().get("isBlackJaque") == false) {
                /*marca los movimientos
			posibles de las piezas*/

                //movimiento adelante si está vacía y si es la primera vez.
                if (this.getFirstMove() == true) {//si es la primer vez se puede mover dos veces o 1 vez.
                    fa = i - 2;
                    fb = i - 1;
                    if (fa >= 0 && fb >= 0 && !logicMatrix[fb][j].getIsOccupied() && !logicMatrix[fa][j].getIsOccupied()) {//si las celdas de en frente no está ocupadas o ocupada.
                        for (fa = i - 2; fa < i; fa++) {
                            logicMatrix[fa][j].setIsPossibleMov(true);
                            Data.drawPossibleMove(fa, j, true, logicMatrix);
                        }

                    } else if (fb >= 0 && !logicMatrix[fb][j].getIsOccupied() && logicMatrix[fa][j].getIsOccupied()) {
                        f = i - 1;
                        if (fb >= 0 && !logicMatrix[f][j].getIsOccupied()) {//si la celda de en frente no está ocupada
                            logicMatrix[f][j].setIsPossibleMov(true);
                            Data.drawPossibleMove(f, j, true, logicMatrix);
                        }
                    }
                    //this.setFirstMove(false);

                    //movimiento adelante si está vacía y si es la segunda vez.
                } else if (this.getFirstMove() == false) {
                    f = i - 1;
                    if (f >= 0 && !logicMatrix[f][j].getIsOccupied()) {//si la celda de en frente no está ocupada
                        logicMatrix[f][j].setIsPossibleMov(true);
                        Data.drawPossibleMove(f, j, true, logicMatrix);

                    }

                }

                /*movimiento de las piezas 
			para capturar una pieza del 
			equipo contrario en este caso
			las piezas negras*/
                //movimiento diagonal izquierda adelante
                f = i - 1;
                c = j - 1;
                if (f >= 0 && c >= 0 && logicMatrix[f][c].getIsOccupied() && !logicMatrix[f][c].getPieces().getIsWhite()) {//si la celda está ocupada y si la pieza es negra
                    logicMatrix[f][c].setIsPossibleMov(true);
                    Data.drawPossibleMove(f, c, true, logicMatrix);

                }

                //movimiento diagonal derecha adelante
                f = i - 1;
                c = j + 1;
                if (f >= 0 && c <= 7 && logicMatrix[f][c].getIsOccupied() && !logicMatrix[f][c].getPieces().getIsWhite()) {//si la celda está ocupada y si la pieza es negra
                    logicMatrix[f][c].setIsPossibleMov(true);
                    Data.drawPossibleMove(f, c, true, logicMatrix);
                }
            } else {//si es jaque
                //movimiento diagonal izquierda adelante
                f = i - 1;
                c = j - 1;
                if (f >= 0 && c >= 0 && logicMatrix[f][c] == cellIsJaque && logicMatrix[f][c].getIsOccupied() && logicMatrix[f][c].getPieces().getId().equals(((Piece) AppContext.getInstance().get("pieceIsJaque")).getId())) {//si la celda está ocupada y si la pieza es negra
                    logicMatrix[f][c].setIsPossibleMov(true);
                    Data.drawPossibleMove(f, c, true, logicMatrix);

                }

                //movimiento diagonal derecha adelante
                f = i - 1;
                c = j + 1;
                if (f >= 0 && c <= 7 && logicMatrix[f][c] == cellIsJaque && logicMatrix[f][c].getIsOccupied() && logicMatrix[f][c].getPieces().getId().equals(((Piece) AppContext.getInstance().get("pieceIsJaque")).getId())) {//si la celda está ocupada y si la pieza es negra
                    logicMatrix[f][c].setIsPossibleMov(true);
                    Data.drawPossibleMove(f, c, true, logicMatrix);
                }

            }
        }
    }

}
