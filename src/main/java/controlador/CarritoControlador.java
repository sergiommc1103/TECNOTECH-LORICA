/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.text.DecimalFormat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
// Ya no necesitamos 'Scene' ni 'Stage' aquí
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import modelo.AlmacenDatos;
import modelo.NodoCarrito;
import modelo.Producto;
import modelo.ListaCarrito;

/**
 *
 * @author ALVARO ANDRES
 */
public class CarritoControlador {

    @FXML
    private VBox listaItems;
    @FXML
    private Label lblTotal;

    @FXML
    public void initialize() {
        cargarCarrito();
    }

    private void cargarCarrito() {
        listaItems.getChildren().clear();
        double total = 0;

        if (AlmacenDatos.carrito == null) {
            AlmacenDatos.carrito = new ListaCarrito();
        }

        NodoCarrito actual = AlmacenDatos.carrito.getCabeza();

        if (actual == null) {
            Label vacio = new Label("Tu carrito está vacío :(");
            vacio.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");
            listaItems.getChildren().add(vacio);
        }

        while (actual != null) {
            Producto p = actual.producto;
            HBox fila = crearFilaProducto(p);
            listaItems.getChildren().add(fila);
            total += p.getPrecio();
            actual = actual.siguiente;
        }

        DecimalFormat formato = new DecimalFormat("#,###");
        lblTotal.setText("$ " + formato.format(total).replace(",", "."));
    }

    private HBox crearFilaProducto(Producto p) {
        HBox fila = new HBox();
        fila.setAlignment(Pos.CENTER_LEFT);
        fila.setSpacing(20);
        fila.setStyle("-fx-background-color: #121212; -fx-padding: 15; -fx-background-radius: 10; -fx-border-color: #333; -fx-border-radius: 10;");

        ImageView img = new ImageView();
        try {
            img.setImage(new Image(getClass().getResourceAsStream("/com/tiendavirtual/" + p.getImagen())));
        } catch (Exception e) {}
        img.setFitHeight(60);
        img.setFitWidth(60);
        img.setPreserveRatio(true);

        VBox datos = new VBox(5);
        Label nombre = new Label(p.getNombre());
        nombre.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px;");
        Label categoria = new Label(p.getCategoria());
        categoria.setStyle("-fx-text-fill: #888;");
        datos.getChildren().addAll(nombre, categoria);

        DecimalFormat formato = new DecimalFormat("#,###");
        Label precio = new Label("$ " + formato.format(p.getPrecio()).replace(",", "."));
        precio.setStyle("-fx-text-fill: #00b4d8; -fx-font-weight: bold; -fx-font-size: 18px;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        fila.getChildren().addAll(img, datos, spacer, precio);
        return fila;
    }

    @FXML
    private void volverTienda() throws IOException {
        // Cargar nuevamente la tienda
        Parent root = FXMLLoader.load(getClass().getResource("/com/tiendavirtual/tienda.fxml"));
        
        // --- CAMBIO CLAVE: USAR setRoot ---
        // Esto mantiene el tamaño de la ventana intacto al volver
        listaItems.getScene().setRoot(root);
    }

    @FXML
    private void vaciarCarrito() {
        AlmacenDatos.carrito = new ListaCarrito();
        cargarCarrito();
    }
}