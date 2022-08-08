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
        //agregamos raiz
        this.arbolpreguntas = new ArbolBinario<String>(preguntas.get(0).getPregunta());
        this.arbolpreguntas.insertaNodo(true, preguntas.get(1).getPregunta());
        this.arbolpreguntas.insertaNodo(false, preguntas.get(1).getPregunta());
        
        
        for(int i=2; i<preguntas.size(); i++){ //arbol espejo
            rellenaPreguntas(preguntas.get(i).getPregunta(),this.arbolpreguntas.izq);
            rellenaPreguntas(preguntas.get(i).getPregunta(),this.arbolpreguntas.der);
            
        }

        
        this.arbolanimales = new ArbolBinario<String>("Raiz");
        
    }
    
    public void rellenaPreguntas(String pregunta, ArbolBinario<String> arbol){
        
        arbol.insertaNodo(true, pregunta);
        arbol.insertaNodo(false, pregunta);
        
    }
    
    
    
    
    public void buildArbolAnimales(ArrayList<Animal> listaA){
        
        for(Animal a: listaA){
            
            buildRutaTree(a,this.arbolanimales);
            
        }
        
    }
    
    public void buildRutaTree(Animal a, ArbolBinario<String> arbol){
        System.out.println("Animal: " + a.getAnimal());
        
        //creo la cola
        Queue<ArbolBinario<String>> q = new LinkedList<ArbolBinario<String>>();
        //obtengo el orden de los nodos
        for(String condicion: a.getRuta()){
                q.offer(new ArbolBinario<String>(condicion));
                
                
        }
        
        
        ArbolBinario<String> tmp = q.poll();
        ArbolBinario<String> bintreeact = creaRama(arbol,tmp);
        
        while(!q.isEmpty()){
            tmp = q.poll();
            bintreeact = creaRama(bintreeact,tmp);
            
        }
        
        bintreeact.data = a.getAnimal();
        
 
        
            
        
    }
    
    public ArbolBinario<String> creaRama(ArbolBinario<String> arbol, ArbolBinario<String> tmp){
           
            System.out.println("Valor considerado: " + tmp.data);
            if(tmp.data.equals("si")){
                
                    if(arbol.izq == null){
               
                    System.out.println("Asignacion");
                    arbol.izq = tmp;
                    return arbol.izq;
                    }
                    else{
                        return arbol.izq;
                    }
                    
            }
            if(tmp.data.equals("no")){
                    if(arbol.der == null){
               
                    System.out.println("Asignacion");
                    arbol.der = tmp;
                    return arbol.der;
                    }
                    else{
                        return arbol.der;
                    }
                    
                
            }
            
            return null;
    }
   
    
    
   
}
