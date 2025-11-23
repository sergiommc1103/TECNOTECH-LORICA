/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ALVARO ANDRES
 */
public class Producto {

    private String nombre;
    private double precio;
    private String imagen; // Nombre del archivo

    public Producto(String nombre, double precio, String imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getImagen() {
        return imagen;
    }

}
