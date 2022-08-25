/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package System;

import App.AvanceMain;
import Util.ArbolBinario;
import Util.ArrayList;

/**
 *
 * @author glorona
 */
public class Sistema {
    
    private Animal an = new Animal();
    private Pregunta pr = new Pregunta();
    private CreaArbol ca;
    
    private ArrayList<Animal> listaAn = new ArrayList<Animal>();
    private ArrayList<Pregunta> listaPr = new ArrayList<Pregunta>();
    private ArbolBinario<String> preguntas;

    public ArrayList<Animal> getListaAn() {
        return listaAn;
    }

    public ArrayList<Pregunta> getListaPr() {
        return listaPr;
    }

    public CreaArbol getCa() {
        return ca;
    }



    public ArbolBinario<String> getPreguntas() {
        return preguntas;
    }
    
    
    
        
    public Sistema(String rutaAni, String rutaPreg){
        
        listaPr = pr.getPreguntas(rutaPreg);
        listaAn = an.getAnimales(rutaAni);
        ca = new CreaArbol(listaAn, listaPr);
        preguntas = ca.getArbolpreguntas();   
        ca.rellenaRespuestas(preguntas, listaAn);
    }
    
    
}
