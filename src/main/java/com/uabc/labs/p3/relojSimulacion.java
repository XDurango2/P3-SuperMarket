/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uabc.labs.p3;
import java.util.Random;

/**
 *
 * @author us
 */
public class relojSimulacion {
     private int tiempoSimulado=0; // Contador para el tiempo simulado (en horas)
     private final int limite;
     private volatile boolean simulacionActiva;
     private simulacion s1;
     private int cantidadClientes=0;
    public relojSimulacion(int limiteSimulacion) {
        limite = limiteSimulacion; // Inicializamos el tiempo simulado a 0 horas
        simulacionActiva =true;
        s1 =new simulacion();
    }

    // Método para iniciar la simulación
    public void iniciarSimulacion() {
        Thread hiloSimulacion = new Thread(() -> {
            while (tiempoSimulado<limite&&simulacionActiva) {
                try {
                    // Esperar 1 segundo (equivalente a 1 minuto real)
                    Thread.sleep(3000);
                    
                    // Incrementar el tiempo simulado
                    tiempoSimulado++;
                    
                    // Aquí puedes realizar cualquier acción que quieras que suceda cada minuto
                    // Por ejemplo, puedes llamar a un método que realice alguna tarea basada en el tiempo simulado.
                   ejecutarSimulacion(tiempoSimulado);
                    
                    if(tiempoSimulado==limite){
                        simulacionActiva=false;
                        getResultsPopup(cantidadClientes);
                    }
                    if (tiempoSimulado % 5 == 0) {
                    // Realizar la acción que deseas cada 5 minutos
                    cerrarCajas();
                }
                
                   
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        hiloSimulacion.start(); // Inicia el hilo
    }

    // Método para obtener el tiempo simulado actual
    public int getTiempoSimulado() {
        return tiempoSimulado;
    }
    
    // Método para detener la simulación (opcional)
    public void detenerSimulacion() {
        simulacionActiva=false;
        // Puedes agregar código aquí para detener el hilo de simulación si es necesario
    }

    public boolean isSimulacionActiva() {
        return simulacionActiva;
    }

    // Ejemplo de método que se ejecuta cada minuto simulado
    public void ejecutarSimulacion(int tiempoSimulado) {
        Random rd = new Random();        
        System.out.println("Han pasado " + tiempoSimulado + " minutos simulados.");
        s1.addCliente(s1.createCliente(rd.nextInt(10+1),tiempoSimulado));
        System.out.println("cantidad de clientes:"+s1.getCantidadClientes());
        System.out.println("cajas normales:"+s1.getCajasNormalesAbiertas());
        System.out.println("cajas Rapidas:"+s1.getCajasRapidasAbiertas());
        System.out.println("cantidad de cajas normales:"+s1.cantidadCajasNormales());
        System.out.println("cantidad de cajas rapidas:"+s1.cantidadCajasRapidas());
        s1.atenderCajas(tiempoSimulado);
        cantidadClientes=s1.getCantidadClientes();
        //simulacion s1 = new simulacion();
        
    }
    public void cerrarCajas(){
        s1.cerrarCajas();
    }
    public int getCajasNormalesCantidad(){
        return s1.cantidadCajasNormales();
    }
    public int getCajasRapidasCantidad(){
        return s1.cantidadCajasRapidas();
    }
    