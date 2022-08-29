package Controllers;

import App.App;
import static App.AvanceMain.sys;
import System.Animal;
import System.Sistema;
import Util.ArbolBinario;
import Util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class JuegoController implements Initializable {
    private ArrayList<Path> paths = new ArrayList<>();
    
    private String rutaUser = "";
    
    private String rutaPreg;
    
    private String rutaResp;
    
    private String name;
    
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
    @FXML
    private HBox hbox;
    @FXML
    private Button bttCargarFoto;

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
        this.name = name;
        sys = new Sistema(rutaResp, rutaPreg);
        preguntas = sys.getPreguntas();
        txtName.setText(this.name);
        txtNumPreg.setText(Integer.toString(this.contador));
        juego(preguntas);
    }
    
    public void juego(ArbolBinario<String> arbol){
        mostrarPregunta(arbol);
    }
    
    public void mostrarPregunta(ArbolBinario<String> arbol){
        txtPregunta.setText(arbol.data);
    }
       
    private void cargarPantalla() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("menuAnimales.fxml"));
            Parent root = fxmlLoader.load();
            MenuAnimalesController mac = fxmlLoader.<MenuAnimalesController>getController();
            ArrayList<String> egl = endGame(preguntas);
            mac.initData(sys.getListaAn(), egl);
            App.scene.setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void bttSi(ActionEvent event) throws FileNotFoundException {
        try {
            this.rutaUser = sys.grabarRutaUsuario(rutaUser, "si");
            setImage(rutasFotos.get(random.nextInt(0, 3)));
            preguntas = preguntas.izq;
            actualizarContador();
            if(!preguntas.isLeaf()) {
                if(this.contador == 0) {
                    cargarPantalla();
                }
                else{
                    mostrarPregunta(preguntas);
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
            preguntas = preguntas.der;
            actualizarContador();
            if(!preguntas.isLeaf()) {
                if(this.contador == 0) {
                    cargarPantalla();
                } else {
                    mostrarPregunta(preguntas);
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
        } if(endgamefinal.isEmpty()) {
            throw new NullPointerException();
        }
        return endgamefinal;
    }
    
    private void respuesta(ArbolBinario<String> preguntas) throws FileNotFoundException {
        boolean conf = verificarRespuesta(preguntas.data, sys.getNomAn());
        if(conf){
            apagarBotones();
            txtPregunta.setText(preguntas.data);
            setImage("imagenes/felicidad.png");
            for(Animal an: sys.getListaAn()) {
                System.out.println(an.getAnimal().equals(preguntas.data));
                if (an.getAnimal().equals(preguntas.data)) {
                    Image image = new Image(new FileInputStream("imagenes/" + an.getRutaFoto()));
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(120);
                    imageView.setFitWidth(120);
                    imageView.setPreserveRatio(true);
                    hbox.getChildren().add(imageView);
                }
            }
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
        bttCargarFoto.setVisible(true);
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
        } else{
            Path pathOrigen = this.paths.get(1);
            Path pathDestino = this.paths.get(0);
            Files.copy(pathOrigen, pathDestino);
            String nombreArch = pathDestino.toString().substring(9);
            System.out.println(nombreArch);
            sys.escribirRutaUsuario(rutaUser, fieldNewAnimal.getText(), rutaResp, nombreArch);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Respuesta Guardada!");
            alert.setContentText("Su respuesta ha sido grabada. Gracias por jugar!");
            alert.show();
            regresarMenu();
        }
    }

    @FXML
    private void bttCargarFoto(ActionEvent event) {
        final ObservableList<String> fileExtensions = FXCollections.observableArrayList();
        fileExtensions.add("*.png");
        fileExtensions.add("*.jpg");
        fileExtensions.add("*.jpeg");
        final FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo de Image", fileExtensions));
        File f = fc.showOpenDialog(null);
        
        if(fieldNewAnimal.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No se cargó el archivo");
            alert.setContentText("Debe escribir el nombre del animal antes de cargar el archivo.");
            alert.show();
        } else {
            if(f != null){
                for(Path x: paths){
                    paths.remove(paths.indexOf(x));
                }
                String fileName = f.getName();
                String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, f.getName().length());
                Path pathDestino = Paths.get("imagenes/" + this.name.toLowerCase() + fieldNewAnimal.getText().toLowerCase() + "." + fileExtension);
                Path pathOrigen = Paths.get(f.getAbsolutePath());
                this.paths.addLast(pathDestino);
                this.paths.addLast(pathOrigen);
            }
        }
    }
    
}
    

