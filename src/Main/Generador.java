/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Modelo.Derecha;
import Modelo.Izquierda;
import Modelo.Pasarela;
import Vista.Ventana;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author rubco
 */
public class Generador {

    public static Pasarela pasarela = new Pasarela();
    private static Ventana ven;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        ven = new Ventana();
        Random rd = new Random(System.currentTimeMillis());
        List<Thread> colaHilos = new ArrayList<>();
        
        int nAlea = 0;
        for(int i = 0; i < 30; i++){
            nAlea = rd.nextInt(100);
            if(nAlea < 50){
                Derecha der = new Derecha(i);
                pasarela.getColaDerecha().add(der);
                der.start();
                colaHilos.add(der);
            }else{
                Izquierda izq = new Izquierda(i);
                Thread hIzq = new Thread(izq);
                pasarela.getColaIzquierda().add(izq);
                hIzq.start();
                colaHilos.add(hIzq);
            }
            repintar();
            
            try{
                Thread.sleep(rd.nextInt(1000) + 1000);
            }catch(InterruptedException e){
                
            }
            
        }
        
        for(Thread h : colaHilos){
            h.join();
        }
        
       
        
        System.exit(0);
    }
    
    public static void repintar(){
        ven.getPasarelaPanel().repaint();
    }
    
}
