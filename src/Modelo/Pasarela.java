/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author rubco
 */
public class Pasarela {
    private int personasDerecha = 0;
    private int personasIzquierda = 0;
    
    private Lock lock = new ReentrantLock();
    
    private final Condition puedePasarDerecha = lock.newCondition();
    private final Condition puedePasarIzquierda = lock.newCondition();
    
    private List<Derecha> pasarelaDer = new ArrayList<>();
    private List<Izquierda> pasarelaIzq = new ArrayList<>();
    
    private List<Derecha> colaDerecha = new ArrayList<>();
    private List<Izquierda> colaIzquierda = new ArrayList<>();
    
    public Pasarela(){
        
    }
    
    public void entraDerecha(Derecha der) throws InterruptedException{
        lock.lock();
        try{
            while(personasDerecha == 3 || (personasDerecha == 2 && personasIzquierda == 0) || colaDerecha.getFirst() != der){
                puedePasarDerecha.await();
            }
            colaDerecha.remove(der);
            personasDerecha++;
            pasarelaDer.add(der);
            puedePasarIzquierda.signal();
        }finally{
            lock.unlock();
        }
    }
    
    public void saleDerecha(Derecha der) throws InterruptedException{
        lock.lock();
        try{
            personasDerecha--;
            pasarelaDer.remove(der);
            puedePasarDerecha.signal();
        }finally{
            lock.unlock();
        }
    }
    
    public void entraIzquierda(Izquierda izq) throws InterruptedException{
        lock.lock();
        try{
             while(personasIzquierda == 3 || (personasIzquierda == 2 && personasDerecha == 0) || colaIzquierda.getFirst() != izq){
                puedePasarIzquierda.await();
            }
             colaIzquierda.remove(izq);
            personasIzquierda++;
            pasarelaIzq.add(izq);
            puedePasarDerecha.signal();
            
        }finally{
            lock.unlock();
        }
    }
    
    public void saleIzquierda(Izquierda izq) throws InterruptedException{
        lock.lock();
        try{
            personasIzquierda--;
            pasarelaIzq.remove(izq);
            puedePasarIzquierda.signal();
        }finally{
            lock.unlock();
        }
    }

    public int getPersonasDerecha() {
        return personasDerecha;
    }

    public int getPersonasIzquierda() {
        return personasIzquierda;
    }

    public List<Derecha> getPasarelaDer() {
        return pasarelaDer;
    }

    public List<Izquierda> getPasarelaIzq() {
        return pasarelaIzq;
    }

    public List<Derecha> getColaDerecha() {
        return colaDerecha;
    }

    public List<Izquierda> getColaIzquierda() {
        return colaIzquierda;
    }
    
    
    
}
