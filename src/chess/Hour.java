/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import chess.util.Mensaje;
import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

/**
 *
 * @author Ivannia Rojas
 */
public class Hour extends Thread {

    private final Label systemTime;

    public Hour(Label systemTime) {
        this.systemTime = systemTime;
    }

    public void init() {
        this.setDaemon(true);//La máquina virtual de Java se cerrará si solo se están ejecutando los hilos de Daemon.
        this.start();//Inicia el hilo.
    }

    @Override
    public void run() {

        while (true) {
            Date today = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");//Establece un formato definido para la hora.

            Platform.runLater(() -> {

                systemTime.setText(sdf.format(today));//Establece al label la hora del sistema.

            });

            try {
                sleep(1000);

            } catch (InterruptedException ex) {
                Mensaje.show(Alert.AlertType.ERROR, "Error", "Error:" + ex.getMessage());
            }
        }
    }

}
