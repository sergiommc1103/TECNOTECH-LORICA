/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ALVARO ANDRES
 */
public class ListaCarrito {

    private NodoCarrito cabeza;
    private int tamano; // Para contar rápido cuántos productos hay

    public ListaCarrito() {
        this.cabeza = null;
        this.tamano = 0;
    }

    // Método para agregar producto al carrito
    public void agregar(Producto p) {
        NodoCarrito nuevo = new NodoCarrito(p);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            // Recorremos hasta el final para agregar (como una Cola)
            NodoCarrito actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        tamano++; // Aumentamos el contador
    }

    // Método para saber cuántos hay
    public int getTamano() {
        return tamano;
    }

    // Método para obtener la cabeza (necesario para recorrer después)
    public NodoCarrito getCabeza() {
        return cabeza;
    }

}
