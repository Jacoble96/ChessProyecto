/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

/**
 *
 * @author Ivannia Rojas
 */
public class Board {

    Cell board[][];//matriz lógica usada para recorridos (este tablero es el que está guardado en AppContext)

    public Board() {

    }

    public Board(Cell[][] board) {
        this.board = board;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

}
