/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.util.Iterator;

/**
 *
 * @author glorona
 * @param <E>
 */
public class ArrayList<E> implements List<E>, Iterable<E>{

    private E[] arreglo;
    private int capacidad;
    private int tam;
    
    public ArrayList(){
        capacidad = 10;
        arreglo =  (E[]) new Object[capacidad];
        tam = 0;
    }
    
    public ArrayList(int capacidad){
        this.capacidad = capacidad;
        arreglo =  (E[]) new Object[capacidad];
        tam = 0;
    }
    
    @Override
    public Iterator<E> iterator(){
        Iterator<E> it = new Iterator(){
            int i=0;
            @Override
            public boolean hasNext(){
                return i<tam && tam >=0;
            }
           
            @Override
            public E next(){
                return arreglo[i++];
            }
            
        };
        return it;
    }
    
    
    @Override
    public boolean addFirst(E e) {
        // si e viene vacio
        if (e.equals(null)){
            return false;
        }
        
        // la lista estaba vacia
        if (isEmpty()){
            arreglo[0] = e;
            tam = 1;
            return true;
        }
        
        // si el arreglo ya está lleno o a capacidad
        if (tam == capacidad){
            crecerArreglo();
        }
        
        // la insercion debe desplazar elementos
        for (int i=tam-1; i>0; i--){
            arreglo[i+1] = arreglo[i];
        }
        arreglo[0] = e;
        tam++;
        return true;
    }

    private void crecerArreglo(){
        capacidad = capacidad*2;
        E[] arreglo2 =  (E[]) new Object[capacidad];
        for (int i=0; i<tam; i++){
            arreglo2[i] = arreglo[i];
        }
        arreglo = arreglo2;
    }
    
    @Override
    public boolean addLast(E e) {
        if(e==null){
            return false;
        }
        
        if (tam==capacidad){
            crecerArreglo();
        }
        
        arreglo[tam++]=e;
        return true;
    }

    @Override
    public E getFirst() {
        if(isEmpty()){
            throw new IllegalStateException("La lista esta vacia");
        }
        return arreglo[0];
    }

    @Override
    public E getLast() {
        if(isEmpty()){
            throw new IllegalStateException("La lista esta vacia");
        }
        return arreglo[tam-1];
    }

    @Override
    public int indexOf(E e) {
        if(e == null){
            throw new NullPointerException("El objeto no tiene valor.");
        }
        
        if(isEmpty()){ //arreglo vacio
            return -1;
        }
        for(int i=0;i<tam;i++){
            if(e.equals(arreglo[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        //se inicializa como 0 no se debe agregar excepcion
        return tam;
    }

    @Override
    public boolean removeLast() {
        if(isEmpty()){
            return false;
        }
        
        arreglo[tam-1] = null;
        tam -- ;
        return true;
    }


    @Override
    public boolean removeFirst() {
        if (isEmpty()) return false;
        arreglo[0] = null;
        for(int i=0; i<tam-1;i++){
            arreglo[i] = arreglo[i+1];
        }
        removeLast();
        return true;
    }

    @Override
    public boolean insert(int index, E e) {
        if(index > tam){ //si el indice esta fuera del rango de la lista
            throw new IndexOutOfBoundsException("El indice esta fuera del rango de la lista.");
        }
        if(e.equals(null)){ //si el objeto es nulo
            return false;
        }
        if(tam == capacidad){ //si la lista esta a maxima capacidad
            crecerArreglo();
        }
        
        for(int i=0; i<tam-1;i++){ //recorremos la lista a la derecha
            arreglo[i+1]=arreglo[i];
        }
        arreglo[index] = e; //asignamos el valor en su celda
        tam++; //aumentamos el rango de la lista
        return true; //retornamos true


    }

    @Override
    public boolean set(int index, E e) {
        if(index > tam-1 ){ //si el indice esta fuera del rango de la lista
            throw new IndexOutOfBoundsException("El indice esta fuera del rango de la lista.");
        }
        if(e.equals(null)){ //si el objeto es nulo
            return false;
        }
        arreglo[index] = e;
        return true;
    }

    @Override
    public boolean isEmpty() { //listo
        if(tam==0){
            return true;
        }
        return false;
    }

    @Override
    public E get(int index) {
       if(index > tam-1){
        throw new IndexOutOfBoundsException("El indice esta fuera del rango de la lista.");
       }
       return arreglo[index];
    }

    @Override
    public boolean contains(E e) {
        for(int i=0; i<tam; i++)
        {
            if(arreglo[i].equals(e))
                return true;
        }
        return false;
    }

    @Override
    public boolean remove(int index) {
        if (index > tam -1) throw new IndexOutOfBoundsException("El indice pedido supera la capacidad del arreglo");
        if(index==0) return removeFirst();
        if(index == tam -1) return removeLast();
        if(isEmpty()) return false;
        else{
            for (int i = index; i<=tam-2;i++){
                arreglo[i]= arreglo[i+1];
            }
            return removeLast();
        }
    }
    
}
