/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import App.App;
import Util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author ronal
 */
public class OpcionesController implements Initializable {
    
    private ArrayList<Path> pathsP = new ArrayList<>(); 
    private ArrayList<Path> pathsR = new ArrayList<>(); 

    private Text txtNombreArch;
    @FXML
    private Text txtNumPregun;
    @FXML
    private Text txtAnimales;
    @FXML
    private Text txtNombreArch1;
    @FXML
    private Text txtNumPregun1;
    @FXML
    private Text txtAnimales1;
    @FXML
    private ComboBox<?> cbxArchivos;
    @FXML
    private Button bttEliminarCancelar;
    @FXML
    private Button bttEliminarAceptar;
    @FXML
    private Text txtNombreArchP;
    @FXML
    private Text txtNombreArchR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void bttCargarAceptar(ActionEvent event) throws IOException {
        Files.copy(pathsP.getFirst(), pathsP.getLast());
        Files.copy(pathsR.getFirst(), pathsR.getLast());
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("menu.fxml"));
            Parent root = fxmlLoader.load();                
            App.scene.setRoot(root);
            } catch (IOException ex) {
        }
    }

    @FXML
    private void bttCargarCancelar(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("menu.fxml"));
            Parent root = fxmlLoader.load();                
            App.scene.setRoot(root);
            } catch (IOException ex) {
        }
    }

    @FXML
    private void bttCargarArchP(ActionEvent event) {
        final ObservableList<String> fileExtensions = FXCollections.observableArrayList();
        fileExtensions.add("*.txt");
            
        final FileChooser fc = new FileChooser();
        
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo de texto", fileExtensions));
        File f = fc.showOpenDialog(null);
        
        if(f != null){
            for(Path x: pathsP){
                pathsP.remove(pathsP.indexOf(x));
            }
            String fileName = f.getName();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, f.getName().length());
            Path pathDestinoP = Paths.get("archivos/" + fileName);
            Path pathOrigenP = Paths.get(f.getAbsolutePath());
            pathsP.addLast(pathOrigenP);
            pathsP.addLast(pathDestinoP);
            txtNombreArchP.setText(fileName);
        }
    }

    @FXML
    private void bttCargarArchR(ActionEvent event) {
        final ObservableList<String> fileExtensions = FXCollections.observableArrayList();
        fileExtensions.add("*.txt");
            
        final FileChooser fc = new FileChooser();
        
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo de texto", fileExtensions));
        File f = fc.showOpenDialog(null);
        
        if(f != null){
            for(Path x: pathsR){
                pathsR.remove(pathsR.indexOf(x));
            }
            String fileName = f.getName();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, f.getName().length());
            Path pathDestinoR = Paths.get("archivos/" + fileName);
            Path pathOrigenR = Paths.get(f.getAbsolutePath());
            pathsR.addLast(pathOrigenR);
            pathsR.addLast(pathDestinoR);
            txtNombreArchR.setText(fileName);
        }
    }

    @FXML
    private void bttInfo(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setContentText("Los archivos de preguntas y respuestas deben tener un formato específico."
                + " (preguntas/respuestas)-(nombre del archivo). Ejemplo: preguntas-mamiferos.txt y "
                + "respuestas-mamiferos.txt");
        alert.show();
    }
    
}
