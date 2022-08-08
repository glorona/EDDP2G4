/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author glorona
 * @param <E>
 */


public class ArbolBinario<E extends Comparable<E>>{
    
    public E data;
    public ArbolBinario<E> izq;
    public ArbolBinario<E> der;

    public ArbolBinario(E datos){
        this.data = datos;
        this.izq = this.der = null;
    }
    

    public boolean isLeaf(){
        return izq==null && der==null;
    }
    
    
     
    
    public boolean insertaNodo(boolean confirmacion,E datos){
       if(datos == null){
           return false;
       }
        
        if(confirmacion){
            if(this.izq != null){
                this.izq.insertaNodo(true,datos);
            }
            else{
                this.izq = new ArbolBinario<E>(datos);
                return true;
            }
        }
        else{
            if(this.der != null){
                this.der.insertaNodo(false, datos);
            }
            else{
                this.der = new ArbolBinario<E>(datos);
                return true;
            }
        }
        return false;
    }
    
    
    //metodo ya no se utiliza
    public boolean insert(E datos){
        if(!(datos != null)){
            return false;
        }
        
        if(datos.compareTo(this.data) <= 0){////si es igual se ira a la izq
            if(this.izq != null){
                this.izq.insert(datos);
            }
            else{
                this.izq = new ArbolBinario<E>(datos);
                return true;
            }
 
        }
        if(datos.compareTo(this.data) > 0){ 
            if(this.der != null){
                this.der.insert(datos);
            }
            else{
                this.der = new ArbolBinario<E>(datos);
                return true;
            }
            
        }
        return false;
    }
    
    public boolean contains(E datos){
        if(datos == null){
            return false;
        }
        
        if(datos.compareTo(this.data) > 0){
            this.der.contains(datos);
        }
        if(datos.compareTo(this.data) < 0){
            this.izq.contains(datos);
        }
        if(datos.compareTo(this.data) == 0 ){
            return true;
        }
        
        return true;
    }
    
    public boolean remove(E datos){
        if(datos == null){
            return false;
        }
        
        if(datos.compareTo(this.data) > 0 ){
            if(datos.compareTo(this.data) == 0){
                if(this.der.der != null){ //siempre se usara el de la derecha
                    ArbolBinario<E> tmp =  this.der.der;
                    while(tmp.der != null){
                        //irme a la derecha maxima
                        tmp =  tmp.der;
                        //revisar si estoy en el ultimo nodo para remover ese nodo
                        if(!(tmp.der.der != null)){
                            tmp.der = null;
                        }
                    }
                    tmp.izq = this.der.izq;
                    tmp.der = this.der.der;
                    this.der = tmp;
                    return true;
                }

                if(this.der.izq != null){

                    ArbolBinario<E> tmp =  this.der.izq;
                    while(tmp.der != null){
                        tmp = tmp.der;
                        //revisar si es el ultimo nodo
                        if(!(tmp.der.der != null)){
                            tmp.der = null;
                        }
                    }
                    tmp.izq = this.der.izq;
                    tmp.der = this.der.der;
                    this.der = tmp;
                    return true;

                }
                this.der = null;
                return true;
            }
            else{
            
            this.der.remove(datos);
            }
        }
        if(datos.compareTo(this.data) <= 0 ){
            if(datos.compareTo(this.data) == 0){
                if(this.izq.der != null){
                    ArbolBinario<E> tmp = this.izq.der;
                    while(tmp.der != null){
                        tmp = tmp.der;
                        if(!(tmp.der.der != null)){
                            tmp.der = null;
                        }
                    }

                    tmp.izq = this.izq.izq;
                    tmp.der = this.izq.der;
                    this.izq = tmp;

                    return true;
                }

                if(this.izq.izq != null){
                    ArbolBinario<E> tmp = this.izq.izq;
                    while(tmp.der != null){
                        tmp = tmp.der;
                        if(!(tmp.der.der != null)){
                            tmp.der = null;
                        }
                    }
                    tmp.izq = this.izq.izq;
                    tmp.der = this.izq.der;
                    this.izq = tmp;
                    return true;

                }
                this.izq = null; //si no nomas se elimina
                return true;
            }
            else{
                
                this.izq.remove(datos);
            }
        }
        return false;
        
        
        
    }
    
   


}


    
    
    
    
