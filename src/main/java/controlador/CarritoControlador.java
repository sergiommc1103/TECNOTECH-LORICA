/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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
        } catch (Exception e) {
        }
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
        Parent root = FXMLLoader.load(getClass().getResource("/com/tiendavirtual/tienda.fxml"));
        listaItems.getScene().setRoot(root);
    }

    @FXML
    private void vaciarCarrito() {
        AlmacenDatos.carrito = new ListaCarrito();
        cargarCarrito();
    }

    //MÉTODO PARA GENERAR FACTURA TXT
    @FXML
    private void pagarAhora() {

        if (AlmacenDatos.carrito.getTamano() == 0) {
            mostrarAlerta("Carrito Vacío", "Agrega productos antes de pagar.");
            return;
        }

        try {

            String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String nombreArchivo = "Factura_" + fecha + ".txt";

            FileWriter archivo = new FileWriter(nombreArchivo);
            PrintWriter escritor = new PrintWriter(archivo);

            escritor.println("=========================================");
            escritor.println("          TECNOTECH LORICA");
            escritor.println("         Factura de Venta");
            escritor.println("=========================================");
            escritor.println("Fecha: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            escritor.println("-----------------------------------------");
            escritor.println(String.format("%-30s %15s", "PRODUCTO", "PRECIO"));
            escritor.println("-----------------------------------------");

            NodoCarrito actual = AlmacenDatos.carrito.getCabeza();
            double total = 0;
            DecimalFormat fmt = new DecimalFormat("#,###");

            while (actual != null) {
                Producto p = actual.producto;

                escritor.println(String.format("%-30s $ %s",
                        p.getNombre().length() > 28 ? p.getNombre().substring(0, 28) : p.getNombre(),
                        fmt.format(p.getPrecio()).replace(",", ".")));

                total += p.getPrecio();
                actual = actual.siguiente;
            }

            escritor.println("-----------------------------------------");
            escritor.println("TOTAL A PAGAR:               $ " + fmt.format(total).replace(",", "."));
            escritor.println("=========================================");
            escritor.println("      ¡Gracias por tu compra!");

            escritor.close();

            vaciarCarrito();
            mostrarAlerta("Compra Exitosa", "Se ha generado la factura: " + nombreArchivo);

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo generar la factura.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
