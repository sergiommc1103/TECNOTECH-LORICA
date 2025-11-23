/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ALVARO ANDRES
 */
public class NodoProducto {
    public Producto dato;
    public NodoProducto sig;
    public NodoProducto ante;

    public NodoProducto(Producto dato) {
        this.dato = dato;
        this.sig = null;
        this.ante = null;
    }

    
}
