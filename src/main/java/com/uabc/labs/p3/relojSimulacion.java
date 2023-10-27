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
     private int limite;
     private volatile boolean simulacionActiva=true;
     private int cantidadClientes=0;
     private simulacion s1= new simulacion();
     private UI2 ui;
     private Random rd;
    
    public relojSimulacion(int time) {
        this.rd = new Random();        
        this.ui = new UI2();
        ui.setVisible(true);  
        limite=time;
        
    }
    public void test(int time){
        int test=0;
         do{
                //ejecutarSimulacion(s1,test++);
                }while(test!=time);
    }
    
   
    // Método para iniciar la simulación
    public int iniciarSimulacion() {
        
        Thread hiloSimulacion = new Thread(() -> {
            while (tiempoSimulado<limite&&simulacionActiva) {
                try {
                    // Esperar 1 segundo (equivalente a 1 minuto real)
                    Thread.sleep(3000);
                    
                    // Incrementar el tiempo simulado
                    tiempoSimulado++;
                    
                    // Aquí puedes realizar cualquier acción que quieras que suceda cada minuto
                    // Por ejemplo, puedes llamar a un método que realice alguna tarea basada en el tiempo simulado.
                   //ejecutarSimulacion(s1,tiempoSimulado);
                   //ui.ejecutar(s1,tiempoSimulado);
                    System.out.println("Han pasado " + tiempoSimulado + " minutos simulados.");
                    s1.addCliente(s1.createCliente(rd.nextInt(10+1),tiempoSimulado));
                    System.out.println("cantidad de clientes:"+s1.getCantidadClientes());
                    System.out.println("cajas normales:"+s1.getCajasNormalesAbiertas());
                    System.out.println("cajas Rapidas:"+s1.getCajasRapidasAbiertas());
                    System.out.println("cantidad de cajas normales:"+s1.cantidadCajasNormales());
                    System.out.println("cantidad de cajas rapidas:"+s1.cantidadCajasRapidas());
                    s1.atenderCajas(tiempoSimulado);
                    cantidadClientes=s1.getCantidadClientes();

                if(tiempoSimulado==limite){
                    simulacionActiva=false;
                    resultsPopUp();

                }
                if (tiempoSimulado % 10 == 0) {
                // Realizar la acción que deseas cada 5 minutos
                s1.cerrarCajas(tiempoSimulado);                     }

                   
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        hiloSimulacion.start(); // Inicia el hilo
        return tiempoSimulado;
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
                
        
    }
    public void cerrarCajas(){
        s1.cerrarCajas(tiempoSimulado);
    }
    public void getResultsPopup(int CantidadClientes, String CR,String CN){
       resultsPopup dialog = new resultsPopup(new javax.swing.JFrame(), true, CantidadClientes);
               dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                   @Override
                   public void windowClosing(java.awt.event.WindowEvent e) {
                       System.exit(0);
                   }
               });
               dialog.setCajaMasUsada(s1.getCajaMasUsadas());
               dialog.setVisible(true);
   }
}
    
    
    