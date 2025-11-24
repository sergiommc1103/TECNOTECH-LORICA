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
        //Usuarios
        usuarios.agregar(new Usuario("admin", "123", "Admin"));
        usuarios.agregar(new Usuario("cliente", "0000", "Cliente"));

        //Crear Productos
        productos.agregar(new Producto("iPhone 15 Pro Max", 5200000, "p1.png"));
        productos.agregar(new Producto("Samsung S24 Ultra", 4800000, "p2.png"));
        productos.agregar(new Producto("Xiaomi Note 13", 890000, "p3.png"));
        productos.agregar(new Producto("ZTE Blade A54", 420000, "p4.png"));

        System.out.println("--> Datos cargados en Estructuras Manuales.");
    }

}
