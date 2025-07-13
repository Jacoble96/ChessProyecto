/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.controller;

import chess.Data;
import chess.Hour;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Ivannia Rojas
 */
/*Es la clase principal, tiene un encabezado 
que muestra la pieza y cada jugador cuando es el turno de
jugar, además aquí se cargan las demás vistas del proyecto.*/
public class BaseController extends Controller implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private HBox hB_header;
    @FXML
    private ImageView img_piece;

    @FXML
    private StackPane st_background;
    @FXML
    private ImageView img_background;

    static String namePlayerW, namePlayerB;

    int turn1, turn2 = 0;
    @FXML
    private Button btn_player;
    @FXML
    private Label lb_hour;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniThread();
        lb_hour.setStyle("-fx-text-fill: white");
        styleFormat();
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            Double width = newValue.doubleValue();
            root.setMaxSize(width * 2, width);
            //System.out.println("nuevo valor:"+newValue+"stackPane_visualizador"+flowPane_contenedor.getMaxWidth());
        });

    }

    @Override
    public void initialize() {

    }

    public void iniThread() {

        Hour hour = new Hour(lb_hour);
        hour.init();

    }
    //Define el estilo inicial para esta clase.

    public void styleFormat() {
        //hB_header.setStyle("-fx-background-color: #003333");
        img_background.fitWidthProperty().bind(root.widthProperty());
        img_background.fitHeightProperty().bind(root.heightProperty());

    }

    //Actualiza los datos del jugador en el encabezado.
    public void updatePlayerDataW() {
        btn_player.setVisible(true);

        for (int j = 0; j < Data.getPlayers().size(); j++) {
            if (Data.getPlayers().get(j).getIsWhitePiece() == true) {
                btn_player.setText("Turno: " + Data.getPlayers().get(j).getName());
                img_piece.setImage(Data.getWhiteFlag());
            }
        }

    }

    public void updatePlayerDataB() {
        btn_player.setVisible(true);

        for (int i = 0; i < Data.getPlayers().size(); i++) {
            if (Data.getPlayers().get(i).getIsWhitePiece() == false) {
                btn_player.setText("Turno: " + Data.getPlayers().get(i).getName());
                img_piece.setImage(Data.getBlackFlag());
            }
        }

    }

}
