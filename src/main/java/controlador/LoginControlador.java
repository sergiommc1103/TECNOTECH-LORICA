/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modelo.AlmacenDatos;
import modelo.Usuario;

/**
 *
 * @author ALVARO ANDRES
 */
public class LoginControlador {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblMensaje;

    @FXML
    private void ingresar() {
        String user = txtUsuario.getText();
        String pass = txtPassword.getText();

        Usuario encontrado = null;

        if (AlmacenDatos.usuarios != null) {
            encontrado = AlmacenDatos.usuarios.buscar(user, pass);
        }

        if (encontrado != null) {

            lblMensaje.setStyle("-fx-text-fill: green;");
            lblMensaje.setText("¡Bienvenido " + encontrado.getRol() + "!");
            System.out.println("Login exitoso: " + encontrado.getUsername());

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tiendavirtual/tienda.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root, 1000, 800);

                Stage stage = (Stage) txtUsuario.getScene().getWindow();

                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                lblMensaje.setStyle("-fx-text-fill: red;");
                lblMensaje.setText("Error al cargar la tienda.");
            }

        } else {
            lblMensaje.setStyle("-fx-text-fill: red;");
            lblMensaje.setText("Datos incorrectos.");
        }
    }
}
