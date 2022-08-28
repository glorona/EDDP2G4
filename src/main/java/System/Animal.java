/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package System;

import Util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author glorona
 */
public class Animal {
    
    private String nombre;
    private String rutaFoto;
    private ArrayList<String> ruta_arb;
    
    public Animal(){
        
    }
    
    public Animal(String n){
        this.nombre = n;
        this.ruta_arb = new ArrayList<String>();
        this.rutaFoto = "";
    }
    
    public String getAnimal(){
        return this.nombre;
    }
    
    public ArrayList<String> getRuta(){
        return this.ruta_arb;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }
    
     
    
    
    
    
    public ArrayList<Animal> getAnimales(String ruta){
        ArrayList<Animal> listanimal = new ArrayList<Animal>();
        try(InputStream input = new URL("file:" + ruta).openStream()){
            BufferedReader lector = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
            String linea = null;
            while((linea = lector.readLine())!= null){
                String rutafoto = Sistema.rutaDefectoAnimal;
                String[] datosfoto = linea.split("#");
                if(datosfoto.length > 1){
                    if(datosfoto[0].equals("")){
                        rutafoto = Sistema.rutaDefectoAnimal;
                    }
                    else{
                    rutafoto = datosfoto[0]; 
                    }
                }
                
                String[] datosline = datosfoto[datosfoto.length-1].split(" ");

                String name = datosline[0];
                Animal atemp = new Animal(name);
                atemp.setRutaFoto(rutafoto);
                for(int i = 1; i<datosline.length; i++){
                    atemp.ruta_arb.addLast(datosline[i]);
                }
                listanimal.addLast(atemp);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());

        }
        return listanimal;
    }
    
    
    
}
