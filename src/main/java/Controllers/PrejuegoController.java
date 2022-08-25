/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import App.App;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ronal
 */
public class PrejuegoController implements Initializable {
    private String rutaPreg;

    @FXML
    private ComboBox<File> cbxArchivosPreg;
    @FXML
    private Text txtArchResp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<File> archivos = FXCollections.observableArrayList();
        
        final File carpeta = new File("archivos");
        for (final File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isDirectory()) {
            } else {
                if(ficheroEntrada.getName().startsWith("preguntas")) {
                    archivos.add(ficheroEntrada);
                }
            }
        }
        
        cbxArchivosPreg.getItems().setAll(archivos);
        
        cbxArchivosPreg.setOnAction(e -> {
            for (final File ficheroEntrada : carpeta.listFiles()) {
                if (ficheroEntrada.isDirectory()) {
                } else {
                    this.rutaPreg = "archivos\\" + cbxArchivosPreg.getValue().getName();
                    String[] separarTipo = cbxArchivosPreg.getValue().getName().split("-");
                    String nombre = separarTipo[1].substring(0, separarTipo[1].lastIndexOf("."));
                    StringBuilder sb = new StringBuilder();
                    sb.append("respuestas-");
                    sb.append(nombre);
                    sb.append(".txt");
                    if(ficheroEntrada.getName().equals(sb.toString())) {
                        txtArchResp.setText("archivos\\" + ficheroEntrada.getName());
                    }
                }
            }
        });
    }    

    @FXML
    private void bttInfo(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setContentText("El archivo de respuestas se selecciona automáticamente si se importaron"
                + " correctamente los archivos. Esto para evitar errores de compatibilidad en el juego.");
        alert.show();
    }

    @FXML
    private void bttRetroceder(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("menu.fxml"));
            Parent root = fxmlLoader.load();                
            App.scene.setRoot(root);
            } catch (IOException ex) {
        }
    }

    @FXML
    private void bttJugar(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("juego.fxml"));
            Parent root = fxmlLoader.load();   
            
            JuegoController jc = fxmlLoader.<JuegoController>getController();
            jc.initData(this.rutaPreg, txtArchResp.getText());
            
            App.scene.setRoot(root);
            } catch (IOException ex) {
        }
    }
    
}
