/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import chess.util.FlowController;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Ivannia Rojas
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FlowController.getInstance().InitializeFlow(stage, null);
        stage.getIcons().add(new Image("chess/resources/icon_.png"));//Establece el icono del programa.
        stage.setTitle("Chess");
        FlowController.getInstance().goMain("Base");
        FlowController.getInstance().goView("StartOptions");

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
