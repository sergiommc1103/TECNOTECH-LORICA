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
// Ya no necesitamos 'Scene' ni 'Stage' para cambiar la vista
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
    private Button btnCarrito; // Botón del menú superior

    @FXML
    public void initialize() {
        // 1. Cargar productos iniciales
        filtrarPorCategoria("Celulares");
        
        // 2. Actualizar el número del carrito
        actualizarContador();

        // 3. ACCIÓN CORREGIDA: IR AL CARRITO SIN CAMBIAR TAMAÑO
        btnCarrito.setOnAction(e -> {
            try {
                // Cargar el archivo del carrito
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tiendavirtual/carrito.fxml"));
                Parent root = loader.load();
                
                // --- EL CAMBIO CLAVE ESTÁ AQUÍ ---
                // En lugar de crear una Scene nueva, cambiamos la raíz de la actual.
                // Esto MANTIENE el tamaño y si está maximizada.
                btnCarrito.getScene().setRoot(root);
                // ---------------------------------
                
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Error al abrir la ventana del carrito.");
            }
        });
    }

    @FXML private void verComputadores() { filtrarPorCategoria("Computadores"); }
    @FXML private void verCelulares() { filtrarPorCategoria("Celulares"); }
    @FXML private void verAccesorios() { filtrarPorCategoria("Accesorios"); }
    @FXML private void verTodo() { filtrarPorCategoria("Todo"); }

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

            actual = actual.sig; // Respetando tu variable
        }
    }

    private VBox crearTarjetaProducto(Producto p) {
        VBox card = new VBox();
        card.getStyleClass().add("card-producto");
        card.setAlignment(Pos.CENTER);
        card.setSpacing(10);

        // Imagen
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

        // Nombre
        Label lblNombre = new Label(p.getNombre());
        lblNombre.getStyleClass().add("nombre-producto");
        lblNombre.setWrapText(true);

        // Precio
        DecimalFormat formato = new DecimalFormat("#,###");
        String precioBonito = formato.format(p.getPrecio()).replace(",", ".");
        Label lblPrecio = new Label("$ " + precioBonito);
        lblPrecio.getStyleClass().add("precio-producto");

        // Botón
        Button btnAgregar = new Button("Agregar al carrito");
        btnAgregar.getStyleClass().add("boton-carrito");
        btnAgregar.setMaxWidth(Double.MAX_VALUE);

        // Acción del botón de agregar
        btnAgregar.setOnAction(e -> {
            AlmacenDatos.carrito.agregar(p);
            actualizarContador();
            System.out.println("Agregado: " + p.getNombre());
        });

        card.getChildren().addAll(img, lblNombre, lblPrecio, btnAgregar);
        return card;
    }
    
    // Método para cambiar el texto del botón de arriba
    private void actualizarContador() {
        if (btnCarrito != null) {
            int cantidad = AlmacenDatos.carrito.getTamano();
            btnCarrito.setText("🛒 Carrito (" + cantidad + ")");
        }
    }
}