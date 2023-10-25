/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uabc.labs.p3;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author us
 */
public class simulacion {

    
    private ArrayList<caja> cajasNormales;
    private ArrayList<caja> cajasRapidas;
    private int cajasNormalesAbiertas=0;
    private int cajasRapidasAbiertas=0;
    private int cantidadClientes=0;
    
    

    public simulacion( ) {
      this.cajasRapidas = new ArrayList<>();
      this.cajasNormales = new ArrayList<>();
        crearCajasNormales();
        crearCajasRapidas();
        
    }
    public void crearCajasNormales(){
        caja c1 = new caja(false,false);
        cajasNormales.add(c1);
      
    }
     public void crearCajasRapidas(){
                 
        caja c1 = new caja(false,true);
        cajasRapidas.add(c1);
    }
    public int cantidadCajasNormales(){
        return cajasNormales.size();
    } 
    public int cantidadCajasRapidas(){
        return cajasRapidas.size();
    }
    
    public ArrayList<cliente> createCliente(int cantidadClientes,int tiempoEntrada){
    Random rd = new Random();
    int articulosrd ;
    ArrayList<cliente> clientes = new ArrayList<>();
    int tiempord;
    for(int k=0;k<cantidadClientes;k++){
        do {
            articulosrd = rd.nextInt(51); // Genera un número aleatorio entre 0 y 50
        } while (articulosrd == 0);
        // La fórmula general es: rand.nextInt((max - min) + 1) + min
        if(articulosrd<3&&articulosrd>0){
            tiempord  =rd.nextInt((10-5)+1)+5;
        }else{
            tiempord  =rd.nextInt((15-10)+1)+10;
        }


        cliente c1 = new cliente(articulosrd,tiempord,tiempoEntrada);
        clientes.add(c1);
    }
    return clientes;
    }
    public void addCliente(ArrayList<cliente> clientesCreados){
        for(int k=0;k<clientesCreados.size();k++){
        cliente c1  =clientesCreados.remove(k);
        caja cajaMenosClientes=cajasNormales.get(0);
        if(c1.getCantidadArticulos()>10){
            for (caja caja : cajasNormales) {
                if (caja.getColaSize() < cajaMenosClientes.getColaSize()&&caja.isEstaCerrado()==false) {
                    cajaMenosClientes = caja;
                }
        }
        }else{
            for (caja caja : cajasRapidas) {
                if (caja.getColaSize() < cajaMenosClientes.getColaSize()&&caja.isEstaCerrado()==false) {
                    cajaMenosClientes = caja;
                }
        }
        }
        cantidadClientes++;
         cajaMenosClientes.agregarCliente(c1);
         
        }   
         
    }

    public ArrayList<caja> getCajasNormales() {
       
        return cajasNormales;
    }

    public ArrayList<caja> getCajasRapidas() {
        return cajasRapidas;
    }

    public String getCajasNormalesAbiertas() {
         StringBuffer str = new StringBuffer();
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

    public String getCajasRapidasAbiertas() {
         StringBuffer str = new StringBuffer();
        for(int k=0;k<cajasRapidas.size();k++){
           if(!cajasRapidas.get(k).isEstaCerrado()){
               str.append(cajasRapidas.get(k).toString());
            } 
        }
        
        return str.toString();
    }
    public void getCajasAbiertas(){
        for (caja caja : cajasNormales) {
                if (!caja.isEstaCerrado()) {
                   cajasNormalesAbiertas++;
                }
        }
        
            for (caja caja : cajasRapidas) {
                if (!caja.isEstaCerrado()) {
                    cajasRapidasAbiertas++;
                }
        }
            
        }
    public void abrirCajas(){
        getCajasAbiertas();
        boolean cajasLlenas=false;       
            for (caja caja : cajasNormales) {
                if (!caja.isEstaCerrado()&& caja.getColaSize()>5) {
                    cajasLlenas = true;
                }
        }
        
            for (caja caja : cajasRapidas) {
                if (!caja.isEstaCerrado()&& caja.getColaSize()>5) {
                    cajasLlenas = true;
                }
        }
        if(cajasLlenas&&cajasNormalesAbiertas<10){
            crearCajasNormales();
        }else{
            for(caja caja: cajasNormales){
                if(caja.isEstaCerrado()==true){
                    caja.abrirCaja();
                    break;
                }
            }
        }
        if(cajasLlenas&&cajasRapidasAbiertas<3){
            crearCajasRapidas();
        }else{
             for(caja caja: cajasRapidas){
                if(caja.isEstaCerrado()==true){
                    caja.abrirCaja();
                    break;
                }
            }
        }    
        
    }
    public void cerrarCajas(){
    boolean primeraCajaNormal = true;
    boolean primeraCajaRapida = true;
    
    for (caja caja : cajasNormales) {
        if (!caja.isEstaCerrado() && primeraCajaNormal) {
            primeraCajaNormal = false;
        } else {
            caja.cerrarCaja();
            caja=null;
            
        }
    }
    
    for (caja caja : cajasRapidas) {
        if (!caja.isEstaCerrado() && primeraCajaRapida) {
            primeraCajaRapida = false;
        } else {
            caja.cerrarCaja();
            caja=null;
        }
    }
}
    public void atenderCajas(int tiempoSimulacion){
         for (caja caja : cajasNormales) {
                if (!caja.isEstaCerrado()&&!caja.cola.isEmpty()) {
                   caja.atender(tiempoSimulacion);
                }
        }
        
            for (caja caja : cajasRapidas) {
                if (!caja.isEstaCerrado()&&!caja.cola.isEmpty()) {
                    caja.atender(tiempoSimulacion);
                }
        }
            abrirCajas();
        }
    public int[] getCajaMasUsadas(){
        caja cajaMasUsada=cajasNormales.get(0);
        caja cajaMasUsadaRapidas=cajasRapidas.get(0);
        int numeroCajas=1;
        int numeroCajasRapidas=1;
        int[] numeros;
        numeros = new int[5];
        for(int k=0; k<cajasNormales.size();k++){
            caja caja=cajasNormales.get(k);
            if(caja.cantidadClientesAtendidos>cajaMasUsada.cantidadClientesAtendidos){
                cajaMasUsada=caja;
                numeroCajas=cajasNormales.indexOf(caja);
            }else{
                numeroCajas=cajasNormales.indexOf(caja);
            }
            numeros[0] = numeroCajas;
        }
        for(int k=0;k<cajasRapidas.size();k++){
            caja caja=cajasRapidas.get(k);
            if(caja.cantidadClientesAtendidos>cajaMasUsadaRapidas.cantidadClientesAtendidos){
                cajaMasUsadaRapidas=caja;
                numeroCajasRapidas=cajasRapidas.indexOf(caja)+1;
            }else{
                numeroCajasRapidas=cajasRapidas.indexOf(caja)+1;

            }
            numeros[1]=numeroCajasRapidas;
        }
        
            return numeros;
        }

         
    }



