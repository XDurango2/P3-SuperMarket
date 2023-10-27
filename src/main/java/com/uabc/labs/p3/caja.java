/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uabc.labs.p3;
import java.util.LinkedList;
import java.util.Queue;
/**
 *
 * @author Hector Duran
 */
public class caja {
    private boolean estaCerrado;
    private boolean esCajaRapida;
    private int cantidadClientesAtendidos=0; 
    Queue<cliente> cola;
    
    
     
     /**
     * Constructor que toma el estado de cierre, tipo de caja y una cola de clientes.
     * @param estaCerrado Indica si la caja está cerrada.
     * @param esCajaRapida Indica si la caja es de tipo rápido.
     * @param cola La cola de clientes.
     */
    public caja(boolean estaCerrado, boolean esCajaRapida) {
        this.estaCerrado = estaCerrado;
        this.esCajaRapida = esCajaRapida;
        this.cola=new LinkedList<>();
    }
    public void agregarCliente(cliente c1){
         if(!estaCerrado){
              cola.add(c1);
         } 
        
    }
    public void atender(int tiempoSimulacion){
        if(!cola.isEmpty()){
            cliente c1 = cola.peek(); // se obtiene el cliente a atender
            int tiempoTotal=c1.getCantidadTiempo()+c1.getTiempoEntrada();
            if(tiempoSimulacion>tiempoTotal){ // se determina si es tiempo de atenderlo
                System.out.println("cliente Atendido");
                cola.remove();
                cantidadClientesAtendidos++;
            }
        }else{
            System.out.println("Error: cola vacia");
        }  
        
    }
    public cliente getCliente(){
        return cola.poll();
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
        return "caja{" + "estaCerrado=" + estaCerrado + ", esCajaRapida=" + esCajaRapida + ", cola=" + cola+ '}';
    }
    
    
}
