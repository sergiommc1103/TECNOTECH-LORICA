/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ALVARO ANDRES
 */
public class ListaFavoritos {

    private NodoProducto cabeza;

    public ListaFavoritos() {
        this.cabeza = null;
    }

    public void agregar(Producto p) {
        // Agregamos al inicio (es más fácil y rápido)
        NodoProducto nuevo = new NodoProducto(p);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            nuevo.sig = cabeza; // Respetando tu variable 'sig'
            cabeza = nuevo;
        }
    }

    public NodoProducto getCabeza() {
        return cabeza;
    }
    
    // Método para ver si ya existe (para no repetirlo)
    public boolean existe(Producto p) {
        NodoProducto actual = cabeza;
        while (actual != null) {
            if (actual.dato.getNombre().equals(p.getNombre())) {
                return true;
            }
            actual = actual.sig; // Respetando tu variable 'sig'
        }
        return false;
    }

    // --- MÉTODOS NUEVOS PARA EL BOTÓN 'VACIAR LISTA' ---
    
    public void vaciar() {
        this.cabeza = null; // Al romper la cabeza, se borra toda la lista
    }
    
    public boolean estaVacia() {
        return cabeza == null;
    }
}