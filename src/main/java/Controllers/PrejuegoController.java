/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import App.App;
import System.Pregunta;
import Util.ArrayList;
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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ronal
 */
public class PrejuegoController implements Initializable {
    private String rutaPreg;

    private int maxNum;
    @FXML
    private ComboBox<File> cbxArchivosPreg;
    @FXML
    private Text txtArchResp;
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldNumPreg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<File> archivos = FXCollections.observableArrayList();
        ObservableList<Integer> numeros = FXCollections.observableArrayList();
        
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
                    maxNum = 0;
                    String[] separarTipo = cbxArchivosPreg.getValue().getName().split("-");
                    String nombre = separarTipo[1].substring(0, separarTipo[1].lastIndexOf("."));
                    StringBuilder sb = new StringBuilder();
                    sb.append("respuestas-");
                    sb.append(nombre);
                    sb.append(".txt");
                    if(ficheroEntrada.getName().equals(sb.toString())) {
                        txtArchResp.setText("archivos\\" + ficheroEntrada.getName());
                    }
                    
                    Pregunta pr = new Pregunta();
                    ArrayList<Pregunta> preguntas = pr.getPreguntas(rutaPreg);
                    for(Pregunta p: preguntas){
                        maxNum++;
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
            if(fieldNumPreg.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Numero de preguntas vacio!");
                alert.setContentText("Tiene que insertar la cantidad de preguntas que vas a jugar.");
                alert.show();
            }
            else{
            int numPregJ =  Integer.parseInt(fieldNumPreg.getText());
            if(numPregJ > maxNum) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Demasiadas preguntas");
                alert.setContentText("El número de preguntas seleccionadas sobrepasa el número de preguntas del"
                        + " archivo. El máximo número de preguntas admitido es " + maxNum);
                alert.show();
            }
            else {
                if(fieldName.getText().equals("")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Nombre de jugador vacio");
                    alert.setContentText("El nombre del jugador no puede estar vacio.");
                    alert.show();
                }
                else{
                    if(this.rutaPreg == null){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Preguntas invalidas");
                        alert.setContentText("Tienes que seleccionar un archivo de preguntas!.");
                        alert.show();
                    }
                    else{
                
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("juego.fxml"));
                Parent root = fxmlLoader.load();   

                JuegoController jc = fxmlLoader.<JuegoController>getController();
                jc.initData(this.rutaPreg, txtArchResp.getText(), numPregJ, fieldName.getText());

                App.scene.setRoot(root);
                }
                }
            }
            }
            
            } catch (IOException ex) {
        }
    }
    
}
