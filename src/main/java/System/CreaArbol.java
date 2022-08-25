/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package System;

import Util.ArbolBinario;
import Util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import System.Sistema;
import Util.List;
import java.util.Collections;

/**
 *
 * @author glorona
 */
public class CreaArbol{
    
    private ArbolBinario<String> arbolpreguntas;
    private ArbolBinario<String> arbolanimales;

    public ArbolBinario<String> getArbolpreguntas() {
        return arbolpreguntas;
    }

    public ArbolBinario<String> getArbolanimales() {
        return arbolanimales;
    }

    
    public CreaArbol(ArrayList<Animal> animales, ArrayList<Pregunta> preguntas){
        this.arbolpreguntas = obtenerArbolPreguntas(preguntas);
        this.arbolanimales = obtenerArbol(animales);

        
        
    }
    
    public void insertarAmbos(ArbolBinario<String> arbol, Queue<String> q){
        String info = q.poll();
        if(arbol.izq == null){
            arbol.izq = new ArbolBinario<String>(info);
        }
        else{
        Queue<String> temporal = new LinkedList<String>();
        temporal.offer(info);
        insertarAmbos(arbol.izq,temporal);
        }
        if(arbol.der == null){
            arbol.der = new ArbolBinario<String>(info);
        }
        else{
        Queue<String> temporal = new LinkedList<String>();
        temporal.offer(info);
        insertarAmbos(arbol.der,temporal);
        }
        
        
    }
    
    public ArbolBinario<String> obtenerArbolPreguntas(ArrayList<Pregunta> preguntas){
        
        ArbolBinario<String> arbolFinal = new ArbolBinario(preguntas.get(0).getPregunta());
        preguntas.remove(0);
        Queue<String> preguntaQ = new LinkedList<String>();
            
        for(Pregunta p: preguntas){
        
            preguntaQ.offer(p.getPregunta());
            
        }
    //no c como lo inicialices pero tiene que estar vacio
    while (!preguntaQ.isEmpty()){
      insertarAmbos(arbolFinal, preguntaQ);
    }
    return arbolFinal;  
    }
    
   
    
    public void rellenaPreguntas(String pregunta, ArbolBinario<String> arbol){
        
        arbol.insertaNodo(true, pregunta);
        arbol.insertaNodo(false, pregunta);
        
    }
    
    
    
    
    
  
    
    
    
  
    
     public void insertar(ArbolBinario<String> arbol, Queue<String> q){ //queue de info del animal ej si si no oso
    String info = q.poll();
    if(info.equals("si")){
      if (arbol.izq== null) {
        arbol.izq = new ArbolBinario<String>(info);
      } 
      insertar(arbol.izq, q);
    } 
    else if(info.equals("no")){
      if (arbol.der== null) {
        arbol.der = new ArbolBinario<String>(info);
      } 
      insertar(arbol.der, q);
    } else{
      arbol.data = info;
    }
  }
     
   public ArbolBinario<String> obtenerArbol(ArrayList<Animal> animales){
        
        Queue<Queue<String>> animalesInfo = new LinkedList<Queue<String>>();
            
        for(Animal a: animales){
            Queue<String> q = new LinkedList<String>();
        //obtengo el orden de los nodos
            for(String condicion: a.getRuta()){
                q.offer(condicion);
                
                
            }
            q.offer(a.getAnimal());
            animalesInfo.offer(q);
        }
    ArbolBinario<String> arbolFinal = new ArbolBinario("raiz"); //no c como lo inicialices pero tiene que estar vacio
    while (!animalesInfo.isEmpty()){
      insertar(arbolFinal, animalesInfo.poll());
    }
    return arbolFinal;
  }
}