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
public class Player {

    String name;
    Boolean isWhitePiece;

    public Player(String name, Boolean isWhitePiece) {
        this.name = name;
        this.isWhitePiece = isWhitePiece;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsWhitePiece() {
        return isWhitePiece;
    }

    public void setIsWhitePiece(Boolean isWhitePiece) {
        this.isWhitePiece = isWhitePiece;
    }

}
