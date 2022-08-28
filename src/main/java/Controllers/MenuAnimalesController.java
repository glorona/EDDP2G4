/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import App.App;
import System.Animal;
import Util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ronal
 */
public class MenuAnimalesController implements Initializable {
    ArrayList<Animal> animales;
    
    @FXML
    private FlowPane flowImagenes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initData(ArrayList<Animal> listaAn, ArrayList<String> listaEnd) throws FileNotFoundException {
        for(String s: listaEnd) {
            for(Animal a: listaAn) {
                if(a.getAnimal().equals(s)) {
                    animales.addLast(a);
                }
            }
        }
        
        for(Animal an: animales) {
            VBox vbox = new VBox();
            Text texto = new Text(an.getAnimal());
            Image image = new Image(new FileInputStream(an.getRutaFoto()));
            ImageView imageView = new ImageView(image);
            
            imageView.setFitHeight(80);
            imageView.setFitWidth(80);
            imageView.setPreserveRatio(true);
            
            vbox.getChildren().add(imageView);
            vbox.getChildren().add(texto);
            
            flowImagenes.getChildren().add(vbox);
        }
    }

    @FXML
    private void bttHome(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("menu.fxml"));
            Parent root = fxmlLoader.load();
            App.scene.setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
