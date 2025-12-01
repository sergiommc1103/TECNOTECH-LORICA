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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modelo.AlmacenDatos;
import modelo.Producto;
import modelo.NodoProducto;

/**
 *
 * @author ALVARO ANDRES
 */
public class FavoritosControlador {

    @FXML private GridPane gridFavoritos;

    @FXML
    public void initialize() {
        cargarFavoritos();
    }

    private void cargarFavoritos() {
        gridFavoritos.getChildren().clear();
        int columna = 0;
        int fila = 0;

        if (AlmacenDatos.favoritos == null) return;

        NodoProducto actual = AlmacenDatos.favoritos.getCabeza(); 

        if (actual == null) {
            Label vacio = new Label("No tienes favoritos aún.");
            vacio.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");
            gridFavoritos.add(vacio, 0, 0);
        }

        while (actual != null) {
            Producto p = actual.dato;
            VBox tarjeta = crearTarjetaFavorito(p);
            
            gridFavoritos.add(tarjeta, columna, fila);

            columna++;
            if (columna == 4) {
                columna = 0;
                fila++;
            }
            actual = actual.sig; // Respetando tu variable sig
        }
    }

    private VBox crearTarjetaFavorito(Producto p) {
        VBox card = new VBox();
        card.getStyleClass().add("card-producto");
        card.setAlignment(Pos.CENTER);
        card.setSpacing(10);

        ImageView img = new ImageView();
        try {
            img.setImage(new Image(getClass().getResourceAsStream("/com/tiendavirtual/" + p.getImagen())));
        } catch (Exception e) {}
        img.setFitHeight(150); img.setFitWidth(150); img.setPreserveRatio(true);

        Label lblNombre = new Label(p.getNombre());
        lblNombre.getStyleClass().add("nombre-producto");
        lblNombre.setWrapText(true);

        DecimalFormat formato = new DecimalFormat("#,###");
        Label lblPrecio = new Label("$ " + formato.format(p.getPrecio()).replace(",", "."));
        lblPrecio.getStyleClass().add("precio-producto");

        Button btnMover = new Button("Mover al Carrito");
        btnMover.getStyleClass().add("boton-carrito");
        btnMover.setMaxWidth(Double.MAX_VALUE);
        
        btnMover.setOnAction(e -> {
            AlmacenDatos.carrito.agregar(p);
            System.out.println("Movido al carrito: " + p.getNombre());
        });

        card.getChildren().addAll(img, lblNombre, lblPrecio, btnMover);
        return card;
    }

    @FXML
    private void volverTienda() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/tiendavirtual/tienda.fxml"));
        gridFavoritos.getScene().setRoot(root);
    }
    
    // --- ESTE ES EL MÉTODO QUE TE FALTABA ---
    @FXML
    private void vaciarFavoritos() {
        if (AlmacenDatos.favoritos != null) {
            AlmacenDatos.favoritos.vaciar();
            cargarFavoritos(); // Recargar para limpiar la pantalla
        }
    }
}