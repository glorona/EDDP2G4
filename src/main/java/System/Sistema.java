/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package System;

import App.AvanceMain;
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

    public ArrayList<Animal> getListaAn() {
        return listaAn;
    }

    public ArrayList<Pregunta> getListaPr() {
        return listaPr;
    }

    public CreaArbol getCa() {
        return ca;
    }
    
        
    public Sistema(){
        
        listaPr = pr.getPreguntas(AvanceMain.rutaPreg);
        listaAn = an.getAnimales(AvanceMain.rutaResp);
        ca = new CreaArbol(listaAn, listaPr);
        
        ca.buildArbolAnimales(listaAn);
        
                
    }
    
}
