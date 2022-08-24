/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App;

import System.Animal;
import System.Pregunta;
import System.Sistema;
import Util.ArbolBinario;
import java.util.Scanner;

/**
 *
 * @author glorona
 */
public class AvanceMain {
    
    public static String rutaPreg = "Archivos/preguntas-avance.txt";
    
    
    public static String rutaResp = "Archivos/respuestas-avance.txt";
    
   
    public static Sistema sys = new Sistema();
    
   
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        boolean salida = true;
        
        ArbolBinario preguntas = sys.getCa().getArbolpreguntas();
        ArbolBinario animales = sys.getCa().getArbolanimales();
        
        int contador = 0;     
        
        System.out.println("Tratare de adivinar el animal en el que estas pensando\n(solo acepto respuestas si/no)\n");
        
        do {
            System.out.println(preguntas.data);
            
            String respuesta = sc.nextLine();
            
            
            if(respuesta.equals("si")) {
                animales = animales.izq;
                preguntas = preguntas.izq;
                contador++;
                continue;
            }
            if(respuesta.equals("no")) {
                animales = animales.der;
                preguntas = preguntas.der;
                contador++;
                continue;
            }
        }
        
        while(contador <= sys.getListaPr().size()-1);
        
        try {
            System.out.println(animales.data);
        }
        catch(NullPointerException Ex){
            System.out.println("No se del animal en que estes pensando :c");
        }
            
        
        /*
        for(Animal a:sys.getListaAn()){
        System.out.println(a.getAnimal());
        }
        
        
        for(Pregunta p: sys.getListaPr()){
        System.out.println(p.getPregunta());
        }
        */
    }
}
