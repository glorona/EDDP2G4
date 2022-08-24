/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import App.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author ronal
 */
public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void bttInicar(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("prejuego.fxml"));
            Parent root = fxmlLoader.load();                
            App.scene.setRoot(root);
            } catch (IOException ex) {
        }
    }

    @FXML
    private void bttOpciones(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("opciones.fxml"));
            Parent root = fxmlLoader.load();                
            App.scene.setRoot(root);
            } catch (IOException ex) {
        }
    }

    @FXML
    private void bttSalir(ActionEvent event) {
    }
    
}
