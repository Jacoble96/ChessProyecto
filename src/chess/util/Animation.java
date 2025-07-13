
package chess.util;

import static javafx.animation.Animation.INDEFINITE;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * El siguiente single tone permite modelar la animaciones de manera
 * que se ejecuten únicamente desde esta clase y darle más modularidad al
 * software.
 * 
 * @author
 * Ivannia
 * Rojas
 
 */
public class Animation {
    //Instancia de si mismo
    private static final Animation SINGLETONE = new Animation();
    
    //Constructor básico
    private Animation(){
        //Carga los datos básicos
    }
    
    //Getter de la instancia, es la única forma de acceso al SINGLETONE
    public static Animation getInstance(){
        return SINGLETONE;
    }
    
 
    public void animarTexto(Node texto){
        //Animación de escala
        ScaleTransition St = new ScaleTransition(Duration.millis(500), texto);
        //Se debe indicar la escala inicial y la final en los ejes X,Y y Z,
        //segun se desee.
        St.setFromX(1.0);
        St.setFromY(1.0);
        
        St.setToX(0.5);
        St.setToY(0.5);
        //El auto reverse indica si la animación se realiza en dos sentidos
        St.setAutoReverse(true);
        /*
        El Cyrcle count, indica la cantidad de veces que se repita la animación
        En el caso de estar el auto reverse activado, se toma cada ciclo como
        una animación independiente
        */
        St.setCycleCount(INDEFINITE);
        //Inicia la animación
        St.play();
    }

    public void animarImagen(Node imagen){
        //Animación de desvanecimiento
        FadeTransition Ft = new FadeTransition(Duration.millis(5000), imagen);
        Ft.setFromValue(1.0);
        Ft.setToValue(0.0);
        Ft.play();
    }    
  
    public void animarBoton(Node boton){
  
        ScaleTransition St = new ScaleTransition(Duration.millis(500), boton);
        St.setFromX(1.0);
        St.setFromY(1.0);
        St.setToX(0.8);//entre mas grande sea el numero se mueve a menos distancia.
        St.setToY(0.8);
        St.setAutoReverse(true);
        St.setCycleCount(INDEFINITE);
        St.play();
    }
    
	public void animarBoton2(Node boton){
	 
	   ScaleTransition St = new ScaleTransition(Duration.millis(500), boton); //Animación de escala
	   //Se debe indicar la escala inicial y la final en los ejes X,Y y Z,
	   St.setFromX(1.1);
	   St.setFromY(1.1);
	   St.setToX(0.8);//entre mas grande sea el numero se mueve a menos distancia.
	   St.setToY(0.8);
	   St.setAutoReverse(true);
	   St.setCycleCount(INDEFINITE);
	   St.play();//Inicia la animación
   }

      public void animaciónHorizontal(Node nodo){
        TranslateTransition tt = new TranslateTransition(Duration.millis(2000), nodo);
        tt.setByX(30f);//Especifica el valor incrementado de la coordenada X de parada, desde el comienzo, de este TranslateTransition
        tt.setCycleCount(INDEFINITE);//repite la animacion.
        tt.setAutoReverse(true);//se devuelve.
 
        tt.play();
	  }
  
    public void animarTexto1(Node nodo){
        //Animación de escala
        ScaleTransition St = new ScaleTransition(Duration.millis(500), nodo);
        //Se debe indicar la escala inicial y la final en los ejes X,Y y Z,
        //segun se desee.
        St.setFromX(5.0);
        St.setFromY(1.0);
        St.setFromZ(5.5);
        
        St.setToX(0.8);
        St.setToY(0.8);
        St.setToZ(0.2);
        
       /* El Cyrcle count, indica la cantidad de veces que se repita la animación
        En el caso de estar el auto reverse activado, se toma cada ciclo como
        una animación independiente*/
        
        St.setCycleCount(INDEFINITE);
        //El auto reverse indica si la animación se realiza en dos sentidos
        St.setAutoReverse(true);
        //Inicia la animación
        St.play();
    }
    
    public void animarImage(Node nodo){
        //Animación de desvanecimiento
        FadeTransition Ft = new FadeTransition(Duration.millis(5000), nodo);
        Ft.setFromValue(1.0);
        Ft.setToValue(0.0);
        Ft.play();
    }
	 public void animaciónDesvanecimiento(Node nodo){
        FadeTransition Ft = new FadeTransition(Duration.millis(5000), nodo);
        Ft.setFromValue(1.0);
        Ft.setToValue(0.0);
        Ft.play();
    }
	 public void animaciónBoomerang(Node nodo){
        TranslateTransition tt = new TranslateTransition(Duration.millis(1000), nodo);
        /*tt.setByX(0f);//Especifica el valor incrementado de la coordenada X de parada, desde el comienzo, de este TranslateTransition
       tt.setCycleCount(2);
        tt.setAutoReverse(true);*/
 
        RotateTransition rotar=new RotateTransition(Duration.seconds(1), nodo);
        rotar.setFromAngle(0.0);
		rotar.setToAngle(100.0);
       
		 
        rotar.setAutoReverse(true);
		
        rotar.setCycleCount(2);
		 rotar.setToAngle(-100.0);
        
        ParallelTransition paralelo = new ParallelTransition();
        paralelo.getChildren().addAll(
           tt,rotar
        );
        
        paralelo.play();
    }
	 public void desplazar(Node nodo, Double fromX ,Double toX ,Double fromY,Double toY){
        TranslateTransition desplazamiento = new TranslateTransition(Duration.millis(1000), nodo);
        desplazamiento.setFromX(fromX);
        desplazamiento.setToX(toX);
        desplazamiento.setFromY(fromY);
        desplazamiento.setToY(toY);
        /*desplazamiento.setOnFinished(e->{
                ((FontAwesomeIconView)nodo).setX(toX);
                ((FontAwesomeIconView)nodo).setY(toY);
        });*/
        desplazamiento.play();
    }
	 public void aumentarTamaño(Node nodo){
        //Animación de escala
        ScaleTransition St = new ScaleTransition(Duration.millis(100), nodo);//700
        //Se debe indicar la escala inicial y la final en los ejes X,Y y Z,
        //segun se desee.
        St.setFromX(1.0);
        St.setFromY(1.0);
        
        St.setToX(1.18);
        St.setToY(1.18);
        //El auto reverse indica si la animación se realiza en dos sentidos
        St.setAutoReverse(true);
		St.setCycleCount(6);
        /*
        El Cyrcle count, indica la cantidad de veces que se repita la animación
        En el caso de estar el auto reverse activado, se toma cada ciclo como
        una animación independiente
        */
        //Inicia la animación
        St.play();
    }
	 public void rotarNodo(Node nodo){
		 RotateTransition rt=new RotateTransition(Duration.millis(1000),nodo);
			rt.setByAngle(360);
			rt.setCycleCount(1);
			rt.play();
	 }
       
}
