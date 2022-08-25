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

public class JuegoController implements Initializable {
    
    private String rutaPreg;
    
    private String rutaResp;
    
    public static Sistema sys;
    
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rutasFotos.addLast("imagenes/tortuga.png");
        rutasFotos.addLast("imagenes/huh.png");
        rutasFotos.addLast("imagenes/calculadora.png");
        setImage("imagenes/tortuga.png");
    }    

    public void initData(String rutaP, String rutaR) {
        this.rutaPreg = rutaP;
        this.rutaResp = rutaR;
        sys = new Sistema(rutaResp, rutaPreg);
        preguntas = sys.getPreguntas();
        juego(preguntas);
    }
    
    public void juego(ArbolBinario<String> arbol){
        mostrarPregunta(arbol);
    }
    
    public void mostrarPregunta(ArbolBinario<String> arbol){
        txtPregunta.setText(arbol.data);
        
    }
    
    
    
    @FXML
    private void bttSi(ActionEvent event) throws FileNotFoundException {
        try {
            setImage(rutasFotos.get(random.nextInt(0, 3)));
            if(!preguntas.isLeaf()) {
                preguntas = preguntas.izq;
                if(preguntas.isLeaf()){
                    respuesta(preguntas);
                }
                mostrarPregunta(preguntas);
            }
            else{
                respuesta(preguntas);
            
            }
        } catch(NullPointerException Ex){
            txtPregunta.setText("No se del animal en que estes pensando :c");
            apagarBotones();
        }
    }

    @FXML
    private void bttNo(ActionEvent event) throws FileNotFoundException {
        try {
            setImage(rutasFotos.get(random.nextInt(0, 3)));
            if(!preguntas.isLeaf()) {
                preguntas = preguntas.der;
                if(preguntas.isLeaf()){
                    respuesta(preguntas);
                }
                mostrarPregunta(preguntas);
            }
            else{
                respuesta(preguntas);
            
            }
        } catch(NullPointerException Ex){
            txtPregunta.setText("No se del animal en que estes pensando :c");
            apagarBotones();
        }
    } 
    
    private void respuesta(ArbolBinario<String> preguntas) {
        apagarBotones();
        txtPregunta.setText(preguntas.data);
        setImage("imagenes/felicidad.png");
    }
    
    private void setImage(String ruta) {
        try {
            imageMascota.setImage(new Image(new FileInputStream(ruta)));
            imageMascota.setFitHeight(150);
            imageMascota.setFitWidth(225);
            imageMascota.setPreserveRatio(true);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    private void apagarBotones() {
        bttSi.setVisible(false);
        bttNo.setVisible(false);
    }
    
    
    
}
