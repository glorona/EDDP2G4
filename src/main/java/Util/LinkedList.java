/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;
import java.util.Iterator;

/**
 *
 * @author glorona
 */
public class LinkedList<E> implements List<E>, Iterable<E> {
    
    
    //atributos linkedlist
    private Node<E> primero;
    private Node<E> ultimo;
    private int current;
    
    public LinkedList(){
        primero = null;
        ultimo = null;
        current = 0;
    }
    
    public Iterator<E> iterator(){
        Iterator<E> it = new Iterator<E>(){
        private Node<E> nodoiterator = primero;
        
        @Override
        public boolean hasNext(){
            return nodoiterator!=null;
        }
        
        
        @Override
        public E next(){
            E temporal = nodoiterator.data;
            nodoiterator = nodoiterator.next;
            return temporal;
        }
    };
        return it;
    }

    private class Node<E>{
        private E data;
        private Node<E> next;

        public Node(E data){
            this.data = data;
            this.next = null;
        }

    }

    


    @Override
    public boolean addFirst(E e) {
        if(e.equals(null)){
            return false;
        }
        Node<E> nodoinsertar = new Node<>(e);
        if(isEmpty()){
            primero = nodoinsertar; 
            ultimo = nodoinsertar; 
        }
        else{
        nodoinsertar.next = primero;
        primero = nodoinsertar;
        }
        current++;
        return true;

    }

    @Override
    public boolean addLast(E e) {
        if(e.equals(null)){
            return false;
        }
        Node<E> nodoinsertar = new Node<>(e); //crear nuevo nodo
        if(isEmpty()){
            primero = ultimo = nodoinsertar; //insertar nodo nuevo si la lista esta vacia
        }
        //hacer que el ultimo nodo apunte al nodo insertado
        ultimo.next = nodoinsertar;
        ultimo = nodoinsertar; //hacer que el nodo insertado sea ahora el ultimo
        current++;
        return true;
    }

    @Override
    public E getFirst() {
        if(isEmpty()){
            throw new IllegalStateException("La lista esta vacia");
        }
        return primero.data;
    }

    @Override
    public E getLast() {
        if(isEmpty()){
            throw new IllegalStateException("La lista esta vacia");
        }
        return ultimo.data;
    }

    @Override
    public int indexOf(E e) {
        if(isEmpty()){
            return -1;
        }
        if(e.equals(null)){
            return -1;
        }
        Node<E> nodofor = primero;
        for(int i=0; i<current-1;i++){
            if(nodofor.data.equals(e)){
                return i;
            }
            nodofor = nodofor.next;

        }
        return -1;
    }

    @Override
    public int size() {
        return current;
    }

    @Override
    public boolean removeLast() {
        if(isEmpty()){
            return false;
        }
        if(primero == ultimo){
            primero = null;
            ultimo = null;
        }
        ultimo.data = null;
        Node<E> nodofor = primero;
        while(nodofor.next != ultimo){
            nodofor = nodofor.next;
        }
        nodofor.next = null;
        ultimo = nodofor;
        current--;
        return true;
    }

    @Override
    public boolean removeFirst() {
        if(isEmpty()){
            return false;
        }
        else{
        Node<E> temporal = primero;
        primero = primero.next;
        temporal.data = null;
        temporal.next = null;
        }
        current--;
        return true;
        
    }

    @Override
    public boolean insert(int index, E e) {
        if(e.equals(null)){
            return false;
        }
        if(index>current || index<0){
            throw new IndexOutOfBoundsException("El indice supera el tamanio de la lista.");
        }
       
        if(index == 0){
            return addFirst(e);
        }
        if(index == current-1){
            return addLast(e);
        }
        Node<E> nodoinsertar = new Node<>(e);
        Node<E> nodoanterior = obtenerNodo(index-1);
        Node<E> nodosiguiente = nodoanterior.next;
        //conectar nodo anterior con nodo insertar
        nodoanterior.next = nodoinsertar;
        nodoinsertar.next = nodosiguiente;
        current++;
        return true;

        
    }

    @Override
    public boolean set(int index, E e) {
        if(index>current || index<0){
            throw new IndexOutOfBoundsException("El indice supera el tamanio de la lista.");
        }
        if(e.equals(null)){
            return false;
        }
        if(index == current-1){
            ultimo.data = e;
            return true;
        }
        if(index == 0){
            primero.data = e;
            return true;
        }
        Node<E> nodomodificando = obtenerNodo(index);
        nodomodificando.data = e;
        return true;
        
    }

    @Override
    public boolean isEmpty() {
        if(primero == null && ultimo == null){
            return true;
        }
        return false;
    }

    public Node<E> obtenerNodo(int index){ //en este no se verifica ya que se usa dentro de las funciones con verificacion
        Node<E> temporal = primero;
        for(int i=0; i<index;i++){
            temporal = temporal.next;
        }
        return temporal;

    }

    @Override
    public E get(int index) {
        if(index > current-1 || index<0){
            throw new IndexOutOfBoundsException("El indice supera el tamanio de la lista.");
        }
        if(index == 0){
            return primero.data;
        }
        if(index == current-1){
            return ultimo.data;
        }

        Node<E> cn = obtenerNodo(index);
        return cn.data;
    }

    @Override
    public boolean contains(E e) {
        if(e.equals(null)){
            return false;
        }
        for(int i=0; i<current-1;i++){
            Node<E> nodoverificar = obtenerNodo(i);
            if(nodoverificar.data.equals(e)){
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean remove(int index) {
        if(index > current-1 || index<0){
            throw new IndexOutOfBoundsException("El indice supera el tamanio de la lista.");
        }
        if(index == 0){
            return removeFirst();
        }
        if(index == current-1){
            return removeLast();
        }
        else{

        Node<E> anterior = obtenerNodo(index-1);
        Node<E> nodoElim = anterior.next;
        Node<E> siguiente = nodoElim.next;
        //apuntar el anterior con el siguiente 
  
        anterior.next = siguiente;
        nodoElim.data = null;
        nodoElim.next = null;
        current--;
        return true;
        }
        

    }
    
    
    
    
    
}
