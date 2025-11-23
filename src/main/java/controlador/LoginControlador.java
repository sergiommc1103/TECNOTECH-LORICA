package controlador;

import java.io.IOException;
import javafx.fxml.FXMLLoader; // Importante para cargar la nueva ventana
import javafx.scene.Parent;      // Importante
import javafx.scene.Scene;       // Importante
import javafx.stage.Stage;       // Importante
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

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblMensaje;

    @FXML
    private void ingresar() {
        String user = txtUsuario.getText();
        String pass = txtPassword.getText();

        Usuario encontrado = null;

        // Búsqueda en tu lista enlazada
        if (AlmacenDatos.usuarios != null) {
            encontrado = AlmacenDatos.usuarios.buscar(user, pass);
        }

        if (encontrado != null) {
            // 1. Mensaje de éxito
            lblMensaje.setStyle("-fx-text-fill: green;");
            lblMensaje.setText("¡Bienvenido " + encontrado.getRol() + "!");
            System.out.println("Login exitoso: " + encontrado.getUsername());

            // 2. LÓGICA PARA CAMBIAR A LA TIENDA
            try {
                // Cargamos el archivo tienda.fxml
                // Asegúrate de que la ruta sea correcta (/com/tiendavirtual/tienda.fxml)
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tiendavirtual/tienda.fxml"));
                Parent root = loader.load();

                // Creamos la nueva escena (Ventana Grande)
                Scene scene = new Scene(root, 1200, 700);

                // Obtenemos la ventana actual (Stage) usando el botón o texto
                Stage stage = (Stage) txtUsuario.getScene().getWindow();
                
                // Cambiamos la escena
                stage.setScene(scene);
                stage.centerOnScreen(); // Centrar en pantalla
                stage.show();

            } catch (IOException e) {
                // Si falla, muestra el error en la consola
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