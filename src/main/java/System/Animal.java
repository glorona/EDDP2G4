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

/**
 *
 * @author glorona
 */
public class Animal {
    
    private String nombre;
    private ArrayList<String> ruta_arb;
    
    public Animal(){
        
    }
    
    public Animal(String n){
        this.nombre = n;
        this.ruta_arb = new ArrayList<String>();
    }
    
    public String getAnimal(){
        return this.nombre;
    }
    
    public ArrayList<String> getRuta(){
        return this.ruta_arb;
    }
    
    public ArrayList<Animal> getAnimales(String ruta){
        ArrayList<Animal> listanimal = new ArrayList<Animal>();
         try(InputStream input = new URL("file:" + ruta).openStream()){
             BufferedReader lector = new BufferedReader(new InputStreamReader(input));
             String linea = null;
             while((linea = lector.readLine())!= null){
                 String[] datosline = linea.split(" ");
                 
                 String name = datosline[0];
                 Animal atemp = new Animal(name);
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
