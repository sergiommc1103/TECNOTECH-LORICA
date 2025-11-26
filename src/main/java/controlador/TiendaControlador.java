/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.text.DecimalFormat;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
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
public class TiendaControlador {

    @FXML
    private GridPane gridProductos;

    @FXML
    public void initialize() {

        filtrarPorCategoria("Celulares");
    }

    @FXML
    private void verComputadores() {
        filtrarPorCategoria("Computadores");
    }

    @FXML
    private void verCelulares() {
        filtrarPorCategoria("Celulares");
    }

    @FXML
    private void verAccesorios() {
        filtrarPorCategoria("Accesorios");
    }

    @FXML
    private void verTodo() {
        filtrarPorCategoria("Todo");
    }

    private void filtrarPorCategoria(String categoriaDeseada) {
        // 1. Limpiamos la pantalla antes de agregar los nuevos productos
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

        //Imagen
        ImageView img = new ImageView();
        try {
            String ruta = "/com/tiendavirtual/" + p.getImagen();
            img.setImage(new Image(getClass().getResourceAsStream(ruta)));
        } catch (Exception e) {
            System.out.println("No se encontró imagen: " + p.getImagen());
        }
        img.setFitHeight(180);
        img.setFitWidth(200);
        img.setPreserveRatio(true);

        //Nombre
        Label lblNombre = new Label(p.getNombre());
        lblNombre.getStyleClass().add("nombre-producto");
        lblNombre.setWrapText(true);

        //Precio
        DecimalFormat formato = new DecimalFormat("#,###");
        String precioBonito = formato.format(p.getPrecio()).replace(",", ".");
        Label lblPrecio = new Label("$ " + precioBonito);
        lblPrecio.getStyleClass().add("precio-producto");

        //Botón
        Button btnAgregar = new Button("Agregar al carrito");
        btnAgregar.getStyleClass().add("boton-carrito");
        btnAgregar.setMaxWidth(Double.MAX_VALUE);

        card.getChildren().addAll(img, lblNombre, lblPrecio, btnAgregar);
        return card;
    }
}
