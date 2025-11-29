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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.AlmacenDatos;
import modelo.NodoCarrito;
import modelo.Producto;

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

        // RECORRER TU LISTA ENLAZADA DE CARRITO
        NodoCarrito actual = AlmacenDatos.carrito.getCabeza();

        if (actual == null) {
            Label vacio = new Label("Tu carrito está vacío :(");
            vacio.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");
            listaItems.getChildren().add(vacio);
        }

        while (actual != null) {
            Producto p = actual.producto;

            // Crear fila visual
            HBox fila = crearFilaProducto(p);
            listaItems.getChildren().add(fila);

            // Sumar al total
            total += p.getPrecio();

            // Avanzar
            actual = actual.siguiente;
        }

        // Mostrar Total con puntos
        DecimalFormat formato = new DecimalFormat("#,###");
        lblTotal.setText("$ " + formato.format(total).replace(",", "."));
    }

    private HBox crearFilaProducto(Producto p) {
        HBox fila = new HBox();
        fila.setAlignment(Pos.CENTER_LEFT);
        fila.setSpacing(20);
        fila.setStyle("-fx-background-color: #121212; -fx-padding: 15; -fx-background-radius: 10; -fx-border-color: #333; -fx-border-radius: 10;");

        // Imagen Pequeña
        ImageView img = new ImageView();
        try {
            img.setImage(new Image(getClass().getResourceAsStream("/com/tiendavirtual/" + p.getImagen())));
        } catch (Exception e) {
        }
        img.setFitHeight(60);
        img.setFitWidth(60);
        img.setPreserveRatio(true);

        // Datos
        VBox datos = new VBox(5);
        Label nombre = new Label(p.getNombre());
        nombre.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px;");
        Label categoria = new Label(p.getCategoria());
        categoria.setStyle("-fx-text-fill: #888;");
        datos.getChildren().addAll(nombre, categoria);

        // Precio
        DecimalFormat formato = new DecimalFormat("#,###");
        Label precio = new Label("$ " + formato.format(p.getPrecio()).replace(",", "."));
        precio.setStyle("-fx-text-fill: #00b4d8; -fx-font-weight: bold; -fx-font-size: 18px;");

        // Espaciador para empujar precio a la derecha
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        fila.getChildren().addAll(img, datos, spacer, precio);
        return fila;
    }

    @FXML
    private void volverTienda() throws IOException {
        // Volver a cargar la tienda
        Parent root = FXMLLoader.load(getClass().getResource("/com/tiendavirtual/tienda.fxml"));
        Stage stage = (Stage) listaItems.getScene().getWindow();
        stage.setScene(new Scene(root, 1000, 800));
    }

    @FXML
    private void vaciarCarrito() {
        // Truco rápido: Reiniciamos la lista creando una nueva vacía
        AlmacenDatos.carrito = new modelo.ListaCarrito();
        cargarCarrito(); // Refrescar pantalla
    }

}
