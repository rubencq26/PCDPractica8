/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Modelo.Derecha;
import Modelo.Izquierda;
import Modelo.Pasarela;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author rubco
 */
public class Generador {

    public static Pasarela pasarela = new Pasarela();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random rd = new Random(System.currentTimeMillis());
        List<Object> colaHilos = new ArrayList<>();
        
        int nAlea = 0;
        for(int i = 0; i < 30; i++){
            nAlea = rd.nextInt(100);
            if(nAlea < 50){
                Derecha der = new Derecha(i);
                der.start();
                colaHilos.add(der);
            }else{
                Izquierda izq = new Izquierda(i);
                Thread hIzq = new Thread(izq);
                hIzq.start();
                colaHilos.add(hIzq);
            }
            
            try{
                Thread.sleep(1000 + rd.nextInt(1000));
            }catch(Exception e){
                
            }
            
        }
    }
    
}
