/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author Ivannia Rojas
 */
public abstract class Data {

    private static final ArrayList<Player> players = new ArrayList<>();
    private static final Image whiteFlag = new Image("chess/resources/whiteFlag.png");
    private static final Image blackFlag = new Image("chess/resources/blackFlag.png");
    private static final Image rookW = new Image("chess/resources/rookW.png");//torre blanca
    private static final Image rookB = new Image("chess/resources/rookB.png");//torre negra
    private static final Image pawnW = new Image("chess/resources/pawnW.png");//peon blanco
    private static final Image pawnB = new Image("chess/resources/pawnB.png");//peon negro

    public static void drawPossibleMove(int i, int j, boolean isPossMov, Cell logicMatrix[][]) {
        Cell cell = logicMatrix[i][j];

        if (cell.getIsPossibleMov() == true) {

            cell.getStyleClass().add("possMov");
        } else {
            cell.setStyle("-fx-border-color: transparent");
        }
    }

    public static void resetBoard(Cell logicMatrix[][]) {
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

    public static Image getWhiteFlag() {
        return whiteFlag;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static Image getRookW() {
        return rookW;
    }

    public static Image getRookB() {
        return rookB;
    }

    public static Image getPawnW() {
        return pawnW;
    }

    public static Image getPawnB() {
        return pawnB;
    }

    public static Image getBlackFlag() {
        return blackFlag;
    }

}
