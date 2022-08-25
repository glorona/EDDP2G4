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
public class Pregunta {
    
    private String texto;
    private int id = 0;
    
    public Pregunta(){
        
    }
    
    public Pregunta(String t){
        this.id = id++;
        this.texto = t;
    }
    
    public ArrayList<Pregunta> getPreguntas(String ruta){
        ArrayList<Pregunta> preguntas =  new ArrayList<Pregunta>();
        
         try(InputStream input = new URL("file:" + ruta).openStream()){
             BufferedReader lector = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
             String linea = null;
             while((linea = lector.readLine())!= null){
                 String datos = linea.strip();
                 preguntas.addLast(new Pregunta(datos));
                 
             }
         }
         catch(IOException ex){
             System.out.println(ex.getMessage());
             
         }
         return preguntas;
        
        
    }
    
    public String getPregunta(){
        return this.texto;
    }
    
    
    
}
