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
import javafx.geometry.Insets; // Importante
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane; // Importante
import javafx.scene.layout.VBox;
import modelo.AlmacenDatos;
import modelo.Producto;
import modelo.NodoProducto;

/**
 *
 * @author ALVARO ANDRES
 */
public class TiendaControlador {

    @FXML
    private GridPane gridProductos;
    @FXML
    private Button btnCarrito;

    @FXML
    private Label lblComputadores;
    @FXML
    private Label lblCelulares;
    @FXML
    private Label lblAccesorios;
    @FXML
    private Label lblTodo;

    @FXML
    public void initialize() {

        filtrarPorCategoria("Celulares");
        actualizarEstiloMenu(lblCelulares);

        actualizarContador();

        btnCarrito.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tiendavirtual/carrito.fxml"));
                Parent root = loader.load();
                btnCarrito.getScene().setRoot(root);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    @FXML
    private void verFavoritos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tiendavirtual/favoritos.fxml"));
            Parent root = loader.load();
            // Cambiamos la vista manteniendo la ventana
            btnCarrito.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Error al abrir favoritos.");
        }
    }

    @FXML
    private void verComputadores() {
        filtrarPorCategoria("Computadores");
        actualizarEstiloMenu(lblComputadores);
    }

    @FXML
    private void verCelulares() {
        filtrarPorCategoria("Celulares");
        actualizarEstiloMenu(lblCelulares);
    }

    @FXML
    private void verAccesorios() {
        filtrarPorCategoria("Accesorios");
        actualizarEstiloMenu(lblAccesorios);
    }

    @FXML
    private void verTodo() {
        filtrarPorCategoria("Todo");
        actualizarEstiloMenu(lblTodo);
    }

    private void actualizarEstiloMenu(Label labelSeleccionado) {
        lblComputadores.getStyleClass().setAll("categoria-item");
        lblCelulares.getStyleClass().setAll("categoria-item");
        lblAccesorios.getStyleClass().setAll("categoria-item");
        lblTodo.getStyleClass().setAll("categoria-item");
        labelSeleccionado.getStyleClass().setAll("categoria-activa");
    }

    private void filtrarPorCategoria(String categoriaDeseada) {
        gridProductos.getChildren().clear();
        int columna = 0;
        int fila = 0;
        NodoProducto actual = AlmacenDatos.productos.getCabeza();

        while (actual != null) {
            Producto p = actual.dato;

            if (categoriaDeseada.equals("Todo") || p.getCategoria().equals(categoriaDeseada)) {
                VBox tarjeta = crearTarjetaProducto(p);
                gridProductos.add(tarjeta, columna, fila);
                columna++;
                if (columna == 4) {
                    columna = 0;
                    fila++;
                }
            }
            actual = actual.sig;
        }
    }

    private VBox crearTarjetaProducto(Producto p) {
        VBox card = new VBox();
        card.getStyleClass().add("card-producto");
        card.setAlignment(Pos.CENTER);
        card.setSpacing(10);

        StackPane imageContainer = new StackPane();

        ImageView img = new ImageView();
        try {
            String ruta = "/com/tiendavirtual/" + p.getImagen();
            img.setImage(new Image(getClass().getResourceAsStream(ruta)));
        } catch (Exception e) {
        }
        img.setFitHeight(180);
        img.setFitWidth(200);
        img.setPreserveRatio(true);

        Label lblHeart = new Label("♡");
        lblHeart.setStyle("-fx-background-color: rgba(0,0,0,0.6); -fx-text-fill: white; -fx-background-radius: 50; -fx-padding: 5 8 5 8; -fx-cursor: hand; -fx-font-size: 16px;");
        StackPane.setAlignment(lblHeart, Pos.TOP_RIGHT);
        StackPane.setMargin(lblHeart, new Insets(5));

        lblHeart.setOnMouseClicked(e -> {
            lblHeart.setText("♥");
            lblHeart.setStyle("-fx-background-color: rgba(0,0,0,0.6); -fx-text-fill: #ff4444; -fx-background-radius: 50; -fx-padding: 5 8 5 8; -fx-cursor: hand; -fx-font-size: 16px;");

            if (!AlmacenDatos.favoritos.existe(p)) {
                AlmacenDatos.favoritos.agregar(p);
                System.out.println("Añadido a favoritos: " + p.getNombre());
            }
        });

        imageContainer.getChildren().addAll(img, lblHeart);

        Label lblNombre = new Label(p.getNombre());
        lblNombre.getStyleClass().add("nombre-producto");
        lblNombre.setWrapText(true);

        DecimalFormat formato = new DecimalFormat("#,###");
        String precioBonito = formato.format(p.getPrecio()).replace(",", ".");
        Label lblPrecio = new Label("$ " + precioBonito);
        lblPrecio.getStyleClass().add("precio-producto");

        Button btnAgregar = new Button("Agregar al carrito");
        btnAgregar.getStyleClass().add("boton-carrito");
        btnAgregar.setMaxWidth(Double.MAX_VALUE);

        btnAgregar.setOnAction(e -> {
            AlmacenDatos.carrito.agregar(p);
            actualizarContador();
            System.out.println("Agregado al carrito: " + p.getNombre());
        });

        card.getChildren().addAll(imageContainer, lblNombre, lblPrecio, btnAgregar);
        return card;
    }

    private void actualizarContador() {
        if (btnCarrito != null) {
            int cantidad = AlmacenDatos.carrito.getTamano();
            btnCarrito.setText("🛒 Carrito (" + cantidad + ")");
        }
    }
}
