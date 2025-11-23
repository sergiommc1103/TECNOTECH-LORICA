/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ALVARO ANDRES
 */
public class AlmacenDatos {

    public static ListaUsuarios usuarios = new ListaUsuarios();
    public static ListaProductosDoble productos = new ListaProductosDoble();

    public static void inicializar() {
        // 1. Crear Usuarios
        usuarios.agregar(new Usuario("admin", "123", "Admin"));
        usuarios.agregar(new Usuario("cliente", "0000", "Cliente"));

        // 2. Crear Productos
        productos.agregar(new Producto("Laptop", 1500, "laptop.png"));
        productos.agregar(new Producto("Mouse", 20, "mouse.png"));
        productos.agregar(new Producto("Teclado", 50, "teclado.png"));

        System.out.println("--> Datos cargados en Estructuras Manuales.");
    }

}
