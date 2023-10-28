/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uabc.labs.p3;

/**
 *
 * @author Hector Duran
 */
public class cliente {
    private final int cantidadArticulos;
    private final int cantidadTiempo;
    private final int tiempoEntrada;

    public cliente(int cantidadArticulos, int cantidadTiempo,int tiempoEntrada) {
        this.cantidadArticulos=cantidadArticulos;
        this.cantidadTiempo=cantidadTiempo;
        this.tiempoEntrada=tiempoEntrada;
    }

    @Override
    public String toString() {
        return "cliente{" + "cantidadArticulos=" + cantidadArticulos + ", cantidadTiempo=" + cantidadTiempo + ", tiempoEntrada=" + tiempoEntrada + '}';
    }

    public int getTiempoEntrada() {
        return tiempoEntrada;
    }

    public int getCantidadArticulos() {
        return cantidadArticulos;
    }

   
    public int getCantidadTiempo() {
        return cantidadTiempo;
    }
    
}
