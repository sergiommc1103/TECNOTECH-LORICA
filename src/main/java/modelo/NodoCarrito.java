/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ALVARO ANDRES
 */
public class NodoCarrito {

    public Producto producto;
    public NodoCarrito siguiente;

    public NodoCarrito(Producto producto) {
        this.producto = producto;
        this.siguiente = null;
    }

}
