/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package System;

import App.AvanceMain;
import Util.ArbolBinario;
import Util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 *
 * @author glorona
 */
public class Sistema {
    
    private Animal an = new Animal();
    private Pregunta pr = new Pregunta();
    private CreaArbol ca;
    private String rutaUsuario = "";
    
    private ArrayList<Animal> listaAn = new ArrayList<Animal>();
    private ArrayList<Pregunta> listaPr = new ArrayList<Pregunta>();
    private ArrayList<String> nomAn = new ArrayList<String>();
    private ArbolBinario<String> preguntas;
    public static String rutaDefectoAnimal = "default.png";

    public ArrayList<Animal> getListaAn() {
        return listaAn;
    }

    public ArrayList<Pregunta> getListaPr() {
        return listaPr;
    }

    public CreaArbol getCa() {
        return ca;
    }

    public ArrayList<String> getNomAn() {
        return nomAn;
    }

    public ArbolBinario<String> getPreguntas() {
        return preguntas;
    }
    
    public String grabarRutaUsuario(String rutaConstruida, String rutaAgregar){
         StringBuilder stb = new StringBuilder();
         
         stb.append(rutaConstruida);
         stb.append(" ");
         stb.append(rutaAgregar);
         
         return stb.toString();
    }
    
    public void escribirRutaUsuario(String rutaFinal, String animal, String rutaArch, String nombreArch) throws FileNotFoundException,IOException{
        StringBuilder stb = new StringBuilder();
        
        stb.append(nombreArch);
        
        stb.append("#");
        stb.append(animal);
        stb.append(rutaFinal);
        stb.append("\n");
        
        FileOutputStream escritor = new FileOutputStream(rutaArch, true);
        OutputStreamWriter output  = new OutputStreamWriter(escritor);
        output.write(stb.toString());
        output.flush();
        output.close();
    }
    
     public Sistema(String rutaAni, String rutaPreg){
        listaPr = pr.getPreguntas(rutaPreg);
        listaAn = an.getAnimales(rutaAni);
        ca = new CreaArbol(listaAn, listaPr);
        preguntas = ca.getArbolpreguntas();   
        ca.rellenaRespuestas(preguntas, listaAn);
        
        for(Animal a: listaAn){
            nomAn.addLast(a.getAnimal());
        }
    }
    
    public ArrayList<String> getRespuestasFinales(ArbolBinario<String> arbol, ArrayList<String> respuestasFin){
        if(arbol.isLeaf()){
            respuestasFin.addLast(arbol.data);
        }
        else{
            if(arbol.izq != null){
                getRespuestasFinales(arbol.izq,respuestasFin);
            }
            if(arbol.der != null){
                getRespuestasFinales(arbol.der,respuestasFin);
            }
        }
        return respuestasFin;
    }
    
}
