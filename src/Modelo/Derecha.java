/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Main.Generador;
import java.util.Random;

/**
 *
 * @author rubco
 */
public class Derecha extends Thread{
    
    private int id;
    
    public Derecha(int id){
        this.id = id;
    }
    
    @Override
    public void run(){
        Random rd = new Random(System.nanoTime());
        
        try{
            Generador.pasarela.entraDerecha(this);
            Generador.repintar();
            Thread.sleep(4000 + rd.nextInt(2000));
            Generador.pasarela.saleDerecha(this);
            Generador.repintar();
        }catch(InterruptedException e){
            System.out.println("Error: " + e.getMessage());
        }
        
    }
    
    
    public int getIdent(){
        return id;
    }
    
}
