/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import static App.AvanceMain.sys;
import System.Sistema;
import Util.ArbolBinario;
import Util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ronal
 */
public class JuegoController implements Initializable {
    
    public static String rutaPreg = "Archivos/preguntas-avance.txt";
    
    public static String rutaResp = "Archivos/respuestas-avance.txt";
    
    public static Sistema sys = new Sistema();
    
    public static ArbolBinario<String> animales;
    
    public static ArbolBinario<String> preguntas;
    
   
    int contador = 0; 
    
    ArrayList<String> rutasFotos = new ArrayList<>();
    
    Random random = new Random();
    
    @FXML
    private ImageView imageMascota;
    @FXML
    private Text txtPregunta;
    @FXML
    private Button bttSi;
    @FXML
    private Button bttNo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            animales = sys.getAnimales();
    
            preguntas = sys.getPreguntas();
    
            rutasFotos.addLast("imagenes/tortuga.png");
            rutasFotos.addLast("imagenes/huh.png");
            rutasFotos.addLast("imagenes/calculadora.png");
            
            imageMascota.setImage(new Image(new FileInputStream("imagenes/tortuga.png")));
            imageMascota.setFitHeight(150);
            imageMascota.setFitWidth(225);
            imageMascota.setPreserveRatio(true);
            txtPregunta.setText(preguntas.data.toString());
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }    

    @FXML
    private void bttSi(ActionEvent event) throws FileNotFoundException {
        imageMascota.setImage(new Image(new FileInputStream(rutasFotos.get(random.nextInt(0, 2)))));
        imageMascota.setFitHeight(150);
        imageMascota.setFitWidth(225);
        imageMascota.setPreserveRatio(true);
        
        animales = animales.izq;
        preguntas = preguntas.izq;
        contador++;
        if(contador <= sys.getListaPr().size()-1) {
            txtPregunta.setText(preguntas.data.toString());
        }
        respuesta();
    }

    @FXML
    private void bttNo(ActionEvent event) throws FileNotFoundException {
        imageMascota.setImage(new Image(new FileInputStream(rutasFotos.get(random.nextInt(0, 2)))));
        imageMascota.setFitHeight(150);
        imageMascota.setFitWidth(225);
        imageMascota.setPreserveRatio(true);
        
        animales = animales.der;
        preguntas = preguntas.der;
        contador++;
        if(contador <= sys.getListaPr().size()-1) {
            txtPregunta.setText(preguntas.data.toString());
        }
        respuesta();
    }
    
    private void respuesta() {
        if(contador > sys.getListaPr().size()-1) {
            try {
                txtPregunta.setText(animales.data.toString());
                imageMascota.setImage(new Image(new FileInputStream("imagenes/felicidad.png")));
                imageMascota.setFitHeight(150);
                imageMascota.setFitWidth(225);
                imageMascota.setPreserveRatio(true);
                bttSi.setVisible(false);
                bttNo.setVisible(false);
            } catch(NullPointerException Ex){
                txtPregunta.setText("No se del animal en que estes pensando :c");
                bttSi.setVisible(false);
                bttNo.setVisible(false);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }    
    
    
}
