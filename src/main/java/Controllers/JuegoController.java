package Controllers;

import App.App;
import static App.AvanceMain.sys;
import System.Sistema;
import Util.ArbolBinario;
import Util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class JuegoController implements Initializable {
    private String rutaUser = "";
    
    private String rutaPreg;
    
    private String rutaResp;
    
    public static Sistema sys;
    
    public static ArbolBinario<String> preguntas;
    
    int contador; 
    
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
    @FXML
    private Text txtNumPreg;
    @FXML
    private Text txtName;
    @FXML
    private Button bttHome;
    @FXML
    private TextField fieldNewAnimal;
    @FXML
    private Text txtNewAnimal;
    @FXML
    private Button bttSaveAnimal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rutasFotos.addLast("imagenes/tortuga.png");
        rutasFotos.addLast("imagenes/huh.png");
        rutasFotos.addLast("imagenes/calculadora.png");
        setImage("imagenes/tortuga.png");
    }    

    public void initData(String rutaP, String rutaR, int numPreg, String name) {
        this.rutaPreg = rutaP;
        this.rutaResp = rutaR;
        this.contador = numPreg;
        sys = new Sistema(rutaResp, rutaPreg);
        preguntas = sys.getPreguntas();
        txtName.setText(name);
        txtNumPreg.setText(Integer.toString(this.contador));
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
            this.rutaUser = sys.grabarRutaUsuario(rutaUser, "si");
            setImage(rutasFotos.get(random.nextInt(0, 3)));
            
            if(!preguntas.isLeaf()) {
                if(this.contador < 1) {
                    txtPregunta.setText(endGame(preguntas).toString());
                    apagarBotones();
                }
                else {
                    preguntas = preguntas.izq;
                    if(preguntas.isLeaf()){
                        respuesta(preguntas);
                    } else{
                        mostrarPregunta(preguntas);
                        actualizarContador();
                    }
                }
            } else{
                respuesta(preguntas);
            }
        } catch(NullPointerException Ex){
            txtPregunta.setText("No se del animal en que estes pensando :c");
            apagarBotones();
            botonesNewAnimal();
        }
    }

    @FXML
    private void bttNo(ActionEvent event) throws FileNotFoundException {
        try {
            this.rutaUser = sys.grabarRutaUsuario(rutaUser, "no");
            setImage(rutasFotos.get(random.nextInt(0, 3)));
            
            if(!preguntas.isLeaf()) {
                if(this.contador < 1) {
                txtPregunta.setText(endGame(preguntas).toString());
                apagarBotones();
                } else {
                    preguntas = preguntas.der;
                    if(preguntas.isLeaf()){
                        respuesta(preguntas);
                    } else{
                        mostrarPregunta(preguntas);
                        actualizarContador();
                    }
                }
            } else{
                respuesta(preguntas);
            }
        } catch(NullPointerException Ex){
            txtPregunta.setText("No se del animal en que estes pensando :c");
            apagarBotones();
            botonesNewAnimal();
        }
    }
    
    private ArrayList<String> endGame(ArbolBinario<String> preguntas){
        ArrayList<String> endgamelista = new ArrayList<String>();
        endgamelista = sys.getRespuestasFinales(preguntas, endgamelista);
        ArrayList<String> endgamefinal = new ArrayList<String>();
        for(String egp: endgamelista){
            if(verificarRespuesta(egp,sys.getNomAn())){
               endgamefinal.addLast(egp);
            }
        }
        return endgamefinal;
    }
    
    private void respuesta(ArbolBinario<String> preguntas) {
        boolean conf = verificarRespuesta(preguntas.data, sys.getNomAn());
        if(conf){
            apagarBotones();
            txtPregunta.setText(preguntas.data);
            setImage("imagenes/felicidad.png");
        } else {
            txtPregunta.setText("No se del animal en que estes pensando :c");
            apagarBotones();
        }
    }
    
    private boolean verificarRespuesta(String respuesta, ArrayList<String> animales){
        if(animales.contains(respuesta)){
            return true;
        } else{
            return false;
        }
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
        bttHome.setVisible(true);
    }
    
    private void botonesNewAnimal() {
        txtNewAnimal.setVisible(true);
        fieldNewAnimal.setVisible(true);
        bttSaveAnimal.setVisible(true);
    }
    
    private void actualizarContador() {
        this.contador--;
        txtNumPreg.setText(Integer.toString(this.contador));
    }

    private void regresarMenu() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("menu.fxml"));
            Parent root = fxmlLoader.load();
            App.scene.setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void bttHome(ActionEvent event) throws IOException {
        regresarMenu();
    }

    @FXML
    private void bttSaveAnimal(ActionEvent event) throws IOException {
        if(fieldNewAnimal.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Nombre de animal vacio!");
                alert.setContentText("El nombre del animal no puede estar vacio si desea guardarlo.");
                alert.show();
        }
        else{
        sys.escribirRutaUsuario(rutaUser, fieldNewAnimal.getText(), rutaResp);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Respuesta Guardada!");
                alert.setContentText("Su respuesta ha sido grabada. Gracias por jugar!");
                alert.show();
        }
        
        regresarMenu();
    }
    
}
