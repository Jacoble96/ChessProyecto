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
public class Rook extends Piece {//Torre

    public Rook(COLOR color) {
        super(color);
        this.value = 5;

        if (color == COLOR.WHITE) {
            this.setImage(Data.getRookW());
            this.setId("rookW");
        } else {
            this.setImage(Data.getRookB());
            this.setId("rookB");
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
        //ArrayList<Integer> positionOtherPiece = new ArrayList<>();
        int f;
        int c;
        Cell[][] logicMatrix = ((Board) (AppContext.getInstance().get("board"))).getBoard();//matriz de AppContext
        Integer i = this.getI();//i de la celda en la que está(sacada de pieza)
        Integer j = this.getJ();//j de la celda en la que está(sacada de pieza)

        if (this.getIsWhite()) {//si es blanca

                f = i - 1;
                while (f >= 0) {
                    if (!logicMatrix[f][j].getIsOccupied()) {
                        logicMatrix[f][j].setIsPossibleMov(true);
                        Data.drawPossibleMove(f, j, true, logicMatrix);
                    } else {
                        if (!logicMatrix[f][j].getPieces().getIsWhite()) {
                            logicMatrix[f][j].setIsPossibleMov(true);
                            Data.drawPossibleMove(f, j, true, logicMatrix);
                            break;
                        }
                        break;
                    }
                    f--;
                }//Movimento hacia delante

                f = i + 1;
                while (f <= 7) {
                    if (!logicMatrix[f][j].getIsOccupied()) {
                        logicMatrix[f][j].setIsPossibleMov(true);
                        Data.drawPossibleMove(f, j, true, logicMatrix);
                    } else {
                        if (!logicMatrix[f][j].getPieces().getIsWhite()) {
                            logicMatrix[f][j].setIsPossibleMov(true);
                            Data.drawPossibleMove(f, j, true, logicMatrix);
                            break;
                        }
                        break;
                    }
                    f++;
                }//Movimiento hacia atras

                c = j + 1;
                while (c <= 7) {
                    if (!logicMatrix[i][c].getIsOccupied()) {
                        logicMatrix[i][c].setIsPossibleMov(true);
                        Data.drawPossibleMove(i, c, true, logicMatrix);
                    } else {
                        if (!logicMatrix[i][c].getPieces().getIsWhite()) {
                            logicMatrix[i][c].setIsPossibleMov(true);
                            Data.drawPossibleMove(i, c, true, logicMatrix);
                            break;
                        }
                        break;
                    }
                    c++;
                }//Movimiento hacia la derecha

                c = j - 1;
                while (c >= 0) {

                    if (!logicMatrix[i][c].getIsOccupied()) {
                        logicMatrix[i][c].setIsPossibleMov(true);
                        Data.drawPossibleMove(i, c, true, logicMatrix);

                    } else {
                        if (!logicMatrix[i][c].getPieces().getIsWhite()) {
                            logicMatrix[i][c].setIsPossibleMov(true);
                            Data.drawPossibleMove(i, c, true, logicMatrix);
                            break;
                        }
                        break;
                    }

                    c--;
                }//Movimiento hacia la izquierda

            
         
        } else {//si es negra

                f = i - 1;
                while (f >= 0) {
                    if (!logicMatrix[f][j].getIsOccupied()) {
                        logicMatrix[f][j].setIsPossibleMov(true);
                        Data.drawPossibleMove(f, j, true, logicMatrix);
                    } else {
                        if (logicMatrix[f][j].getPieces().getIsWhite()) {
                            logicMatrix[f][j].setIsPossibleMov(true);
                            Data.drawPossibleMove(f, j, true, logicMatrix);
                            break;
                        }
                        break;
                    }
                    f--;
                }//Movimento hacia atras

                f = i + 1;
                while (f <= 7) {
                    if (!logicMatrix[f][j].getIsOccupied()) {
                        logicMatrix[f][j].setIsPossibleMov(true);
                        Data.drawPossibleMove(f, j, true, logicMatrix);
                    } else {
                        if (logicMatrix[f][j].getPieces().getIsWhite()) {
                            logicMatrix[f][j].setIsPossibleMov(true);
                            Data.drawPossibleMove(f, j, true, logicMatrix);
                            break;
                        }
                        break;
                    }
                    f++;
                }//Movimiento hacia delante

                c = j + 1;
                while (c <= 7) {

                    if (!logicMatrix[i][c].getIsOccupied()) {
                        logicMatrix[i][c].setIsPossibleMov(true);
                        Data.drawPossibleMove(i, c, true, logicMatrix);

                    } else {
                        if (logicMatrix[i][c].getPieces().getIsWhite()) {
                            logicMatrix[i][c].setIsPossibleMov(true);
                            Data.drawPossibleMove(i, c, true, logicMatrix);
                            break;
                        }
                        break;
                    }

                    c++;
                }//Movimiento hacia la derecha

                c = j - 1;
                while (c >= 0) {

                    if (!logicMatrix[i][c].getIsOccupied()) {
                        logicMatrix[i][c].setIsPossibleMov(true);
                        Data.drawPossibleMove(i, c, true, logicMatrix);

                    } else {
                        if (logicMatrix[i][c].getPieces().getIsWhite()) {
                            logicMatrix[i][c].setIsPossibleMov(true);
                            Data.drawPossibleMove(i, c, true, logicMatrix);
                            break;
                        }
                        break;
                    }

                    c--;
                }//Movimiento hacia la izquierda

            

        }//fin else

    }//fin funcion
}
