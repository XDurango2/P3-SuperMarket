/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uabc.labs.p3;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
/**
 *
 * @author Hector Duran
 */
public final class simulacionNoCR {

    
    private final ArrayList<caja> cajasNormales;
    private int cajasNormalesAbiertas=0;
    private int cantidadClientes=0;
    

    public simulacionNoCR( ) {
     this.cajasNormales=new ArrayList<>();
    
     
        crearCajasNormales();
     
         
      
    }
    public void crearCajasNormales(){
        caja c1 = new caja(false,false);
        cajasNormales.add(c1);
      
    }
   
    public int cantidadCajasNormales(){
        return cajasNormales.size();
    } 
   
    
    
    public ArrayList<cliente> createCliente(int cantidadClientes,int tiempoEntrada){
    Random rd = new Random();
    int articulosrd;
    ArrayList<cliente> clientes = new ArrayList<>();
    int tiempord;
    for(int k=0;k<cantidadClientes;k++){
        do {
            articulosrd = rd.nextInt(20); // Genera un número aleatorio entre 0 y 50
        } while (articulosrd == 0);
        // La fórmula general es: rand.nextInt((max - min) + 1) + min
        if(articulosrd<3&&articulosrd>0){
            tiempord  =rd.nextInt((10-5)+1)+5; //tiempo de espera entre 5 a 10 min
        }else{
            tiempord  =rd.nextInt((15-10)+1)+10; //tiempo de espera entre 10 a 15 min
        }


        cliente c1 = new cliente(articulosrd,tiempord,tiempoEntrada);
        clientes.add(c1);
    }
    return clientes;
    }
    
    public void addCliente(ArrayList<cliente> clientesCreados) {
    for (cliente c1 : clientesCreados) {
        // Aquí, 'c1' representa un objeto de tipo 'cliente' en cada iteración
        caja cajaNormalMenosClientes = getCajaNormalMenosClientes(); 
       
            cajaNormalMenosClientes.agregarCliente(c1);
        
        cantidadClientes++;
        
    }
}


    public ArrayList<caja> getCajasNormales() {
       
        return cajasNormales;
    }

   

    public String getCajasNormalesAbiertas() {
         StringBuilder str = new StringBuilder();
        for(int k=0;k<cajasNormales.size();k++){
           if(!cajasNormales.get(k).isEstaCerrado()){
               str.append(cajasNormales.get(k).toString());
            } 
        }
        
        return str.toString();
    }
    
   
    
    public int getCantidadClientes() {
        return cantidadClientes;
    }
    public caja getCajaNormalMenosClientes(){
        caja cajaNormalMenosClientes = cajasNormales.get(0);
        
       
        for(caja caja: cajasNormales){
            if(caja.getColaSize()<cajaNormalMenosClientes.getColaSize()){
                cajaNormalMenosClientes = caja;
            }
        }
        return cajaNormalMenosClientes;
    }
    
    public void moverClientes(){
        caja cajaNormalMenosClientes = cajasNormales.get(0);
        caja cajaNormalMasClientes = cajasNormales.get(0);
        
        for(caja caja: cajasNormales){
            if(caja.getColaSize()<cajaNormalMenosClientes.getColaSize()){
                cajaNormalMenosClientes = caja;
            }else if(caja.getColaSize()>cajaNormalMasClientes.getColaSize()){
                cajaNormalMasClientes=caja;
            }
        }
        
        
        while(cajaNormalMasClientes.getColaSize()>5&& cajaNormalMenosClientes.getColaSize()<5){
            cliente clienteMovido = cajaNormalMasClientes.getCliente();
            cajaNormalMenosClientes.agregarCliente(clienteMovido);
        }
        
    }
    
    
    /*
    ... ... ...
    */
    public void abrirCajas(){
        
        boolean cajasNormalesLlenas=false;       
            for (caja caja : cajasNormales) {
                if (!caja.isEstaCerrado()&& caja.getColaSize()>5) {
                    cajasNormalesLlenas = true;
                }
        }
        
            
        if(cajasNormalesLlenas&&cajasNormales.size()<10){
            crearCajasNormales();
        }
          
        
    }
    
    public void cerrarCajas(int tiempoSimulado){
    boolean primeraCajaNormal = true;
    moverClientes();
    // Cierra cajas normales
    Iterator<caja> iterator = cajasNormales.iterator();
    while (iterator.hasNext()) {
        caja caja = iterator.next();
        if (!caja.isEstaCerrado() && primeraCajaNormal) {
            primeraCajaNormal = false;
        } else {
            cajasNormalesAbiertas--;
            caja.cerrarCaja();
            
            if(caja.cola.isEmpty()){
                iterator.remove(); // Usa el iterador para eliminar de manera segura
                
            }
            
        }
    }
    
   
}

    public void atenderCajas(int tiempoSimulacion){
         for (caja caja : cajasNormales) {
                if (!caja.cola.isEmpty()) {
                   caja.atender(tiempoSimulacion);
                }
        }
        
           
            abrirCajas();
        }
   
    public int[] getCajaMasUsadas(){
        caja cajaMasUsada=cajasNormales.get(0);
        int numeroCajas=1;
        int[] numeros;
        numeros = new int[5];
        for(int k=0; k<cajasNormales.size();k++){
            caja caja=cajasNormales.get(k);
            if(caja.getCantidadClientesAtendidos()>cajaMasUsada.getCantidadClientesAtendidos()){
                cajaMasUsada=caja;
                numeroCajas=cajasNormales.indexOf(caja)+1;
            }else{
                numeroCajas=cajasNormales.indexOf(caja)+1;
            }
            numeros[0] = numeroCajas;
        }
        
        
            return numeros;
        }
    public double getPromedioTiempoCajasNormales(){
        double sumaPromedioTiempoCajasNormales=0;
        for(caja cajaNormal:cajasNormales){
            sumaPromedioTiempoCajasNormales+=cajaNormal.calcularPromedioTiempo();
        }
        return sumaPromedioTiempoCajasNormales/cajasNormales.size();
    }
   
         
    }



