/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.controller;

import chess.util.Animation;
import chess.util.FlowController;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Ivannia Rojas
 */
public class StartOptionsController extends Controller implements Initializable {

    @FXML
    private VBox vb_startOpctions;

    @FXML
    private JFXButton btn_startPlay;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation.getInstance().animarBoton(btn_startPlay);
        //btn_startPlay.setDefaultButton(true);

    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onAction_play(ActionEvent event) {
        FlowController.getInstance().goView("Start");
    }

}
