/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.controller;

import chess.Data;
import chess.Player;
import chess.util.FlowController;
import chess.util.Mensaje;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Ivannia Rojas
 */
public class StartController extends Controller implements Initializable {

    @FXML
    private JFXTextField txt_playerName;
    @FXML
    private JFXRadioButton rb_black;
    @FXML
    private JFXRadioButton rb_white;
    @FXML
    private JFXButton btn_save;

    @FXML
    private AnchorPane root;
    private int result;
    @FXML
    private ToggleGroup tg_pieces;

    int c;
    @FXML
    private StackPane st_backgroundStart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            Double width = newValue.doubleValue();
            st_backgroundStart.setMaxSize(width * 2, width);
        });



    }

    @Override
    public void initialize() {

    }


    /*guarda los jugadores y que estos no se puedan repetir ni el equipo*/
    @FXML
    private void onAction_save(ActionEvent event) {
        c = 0;

        ArrayList<Player> players = Data.getPlayers();
        String playerName = txt_playerName.getText();
        if (players.size() <= 1) {
            if (!txt_playerName.getText().isEmpty()) {
                if (!players.stream().anyMatch(i -> i.getName().equals(playerName))) {
                    Data.getPlayers().add(new Player(playerName, rb_white.isSelected()));
                    if (players.size() <= 1) {
                        Mensaje.show(Alert.AlertType.INFORMATION, "Information", "El jugador " + players.size() + " se guardó correctamente");
                    } else if (players.size() >= 2) {
                        result = Data.getPlayers().get(c).getIsWhitePiece().compareTo(Data.getPlayers().get(c + 1).getIsWhitePiece());
                        if (result == 0) {//piezas iguales
                            Mensaje.show(Alert.AlertType.ERROR, "Error", "Pieza repetida, ingrese nuevamente el jugador");
                            Data.getPlayers().remove(c);
                        } else {
                            Mensaje.show(Alert.AlertType.INFORMATION, "Information", "El jugador " + players.size() + " se guardó correctamente");
                        }
                    }
                    txt_playerName.clear();
                } else {
                    Mensaje.show(Alert.AlertType.ERROR, "Error", "El nombre del jugador ya existe");
                    txt_playerName.clear();
                }
            } else {
                Mensaje.show(Alert.AlertType.ERROR, "Error", "Ingrese el nombre del jugador");
            }
        } else {
            Mensaje.show(Alert.AlertType.WARNING, "Warning", "No puede registrar más de " + players.size() + " jugadores");
            txt_playerName.clear();
        }
        if (players.size() == 2) {
             FlowController.getInstance().goView("Game");
        }

    }


    @FXML
    private void keyTaped_textPlayer(KeyEvent event) {//Restringe el campo de texto del nombre de jugador con un máximo de 10 caracteres.

        if (txt_playerName.getText().length() == 10) {
            Mensaje.show(Alert.AlertType.INFORMATION, "INFORMATION", "10 caracteres máximo");
            event.consume(); //elimina los caracteres que sean introducidos cuando se alcanza el límite.
        }

    }

}
