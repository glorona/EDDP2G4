/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App;

import System.Animal;
import System.Pregunta;
import System.Sistema;
import Util.ArbolBinario;
import Util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author glorona
 */
public class AvanceMain {
    
    public static String rutaPreg = "archivos/preguntas-avance2.txt";
    
    
    public static String rutaResp = "archivos/respuestas-avance2.txt";
    
   
    public static Sistema sys = new Sistema(rutaResp,rutaPreg);
    
   
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        boolean salida = true;
        
        ArbolBinario preguntas = sys.getCa().getArbolpreguntas();
        int contador = 0;     
        
        System.out.println("Tratare de adivinar el animal en el que estas pensando\n(solo acepto respuestas si/no)\n");
        ArrayList<Animal> respuestasFin = sys.getListaAn();
        for(Animal a: respuestasFin){
            System.out.println(a.getAnimal() + " Ruta: " + a.getRutaFoto());
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
