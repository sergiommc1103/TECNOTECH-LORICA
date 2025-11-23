/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ALVARO ANDRES
 */
public class ListaProductosDoble {

    private NodoProducto cabeza;
    private NodoProducto cola;
    private NodoProducto actual; // Puntero para saber qué producto estamos viendo

    public ListaProductosDoble() {
        this.cabeza = null;
        this.cola = null;
        this.actual = null;
    }

    // Agregar al final
    public void agregar(Producto p) {
        NodoProducto nuevo = new NodoProducto(p);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
            actual = nuevo; // Inicializamos el puntero actual
        } else {
            cola.sig = nuevo;
            nuevo.ante = cola;
            cola = nuevo;
        }
    }

    // Navegación
    public Producto obtenerActual() {
        return (actual != null) ? actual.dato : null;
    }

    public void irSiguiente() {
        if (actual != null && actual.sig != null) {
            actual = actual.sig;
        }
    }

    public void irAnterior() {
        if (actual != null && actual.ante != null) {
            actual = actual.ante;
        }
    }

}
