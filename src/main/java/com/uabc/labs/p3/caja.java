/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uabc.labs.p3;
import java.util.LinkedList;
import java.util.Queue;
/**
 *
 * @author us
 */
public class caja {
    boolean estaCerrado;
    boolean esCajaRapida;
    int cantidadClientesAtendidos=0; 
    Queue<cliente> cola= new LinkedList<>();
    
    
     
     /**
     * Constructor que toma el estado de cierre, tipo de caja y una cola de clientes.
     * @param estaCerrado Indica si la caja está cerrada.
     * @param esCajaRapida Indica si la caja es de tipo rápido.
     * @param cola La cola de clientes.
     */
    public caja(boolean estaCerrado, boolean esCajaRapida) {
        this.estaCerrado = estaCerrado;
        this.esCajaRapida = esCajaRapida;
        
    }
    public void agregarCliente(cliente c1){
         if(!estaCerrado){
             cola.add(c1);
         } 
        
    }
    public void atender(int tiempoSimulacion){
        cliente c1 = cola.peek();
        int tiempoTotal=c1.getCantidadTiempo()+c1.getTiempoEntrada();
        if(tiempoSimulacion>tiempoTotal){
            cola.remove();
            cantidadClientesAtendidos++;
        }
        
        
    }
    public int getColaSize(){
       
        return cola.size();
    }

    public int getCantidadClientesAtendidos() {
        return cantidadClientesAtendidos;
    }

    public boolean isEstaCerrado() {
        return estaCerrado;
    }
    public void cerrarCaja() {
        if (cola.size() < 5) {
            estaCerrado = true;
        } else {
            estaCerrado = false;
        }
}

    public void abrirCaja(){
        estaCerrado=false;
        
    }

    @Override
    public String toString() {
        return "caja{" + "estaCerrado=" + estaCerrado + ", esCajaRapida=" + esCajaRapida + ", cola=" + cola.size() + '}';
    }
    
    
}
