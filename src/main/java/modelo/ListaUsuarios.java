/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ALVARO ANDRES
 */
public class ListaUsuarios {

    private NodoUsuario cab;

    public ListaUsuarios() {
        this.cab = null;
    }

    public void agregar(Usuario u) {
        NodoUsuario nuevo = new NodoUsuario(u);
        if (cab == null) {
            cab = nuevo;
        } else {
            nuevo.sig = cab;
            cab = nuevo;
        }
    }

    public Usuario buscar(String user, String pass) {
        NodoUsuario actual = cab;
        while (actual != null) {
            if (actual.dato.getUsername().equals(user)
                    && actual.dato.getPassword().equals(pass)) {
                return actual.dato;
            }
            actual = actual.sig;
        }
        return null;
    }

}
