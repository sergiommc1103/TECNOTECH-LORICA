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

        // CELULARES (CORREGIDO: Faltaba agregar "Celulares" al final)
        productos.agregar(new Producto("iPhone 15 Pro Max", 5200000, "p1.png", "Celulares"));
        productos.agregar(new Producto("Samsung S24 Ultra", 4800000, "p2.png", "Celulares"));
        productos.agregar(new Producto("Xiaomi Note 13", 890000, "p3.png", "Celulares"));
        productos.agregar(new Producto("ZTE Blade A54", 420000, "p4.png", "Celulares"));
        
        // 2. COMPUTADORES
        productos.agregar(new Producto("Laptop Asus TUF", 4500000, "pc1.png", "Computadores"));
        productos.agregar(new Producto("MacBook Air M2", 5800000, "pc2.png", "Computadores"));
        productos.agregar(new Producto("HP Pavilion", 2300000, "pc3.png", "Computadores"));
        
        // 3. ACCESORIOS
        productos.agregar(new Producto("Audífonos Sony", 350000, "acc1.png", "Accesorios"));

        System.out.println("--> Datos cargados en Estructuras Manuales.");
    }

}
