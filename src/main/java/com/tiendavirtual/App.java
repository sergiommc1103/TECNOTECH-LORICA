package com.tiendavirtual;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

// 1. IMPORTAR TU CLASE ALMACEN
import modelo.AlmacenDatos;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        // 2. AGREGAR ESTA LÍNEA AQUÍ:
        // Esto llena tus listas enlazadas con los datos de prueba
        AlmacenDatos.inicializar();

        // Carga la ventana (por ahora dejamos 'primary', luego la cambiaremos a 'login')
        scene = new Scene(loadFXML("login"),1200, 700);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        // Esta línea busca el archivo .fxml en la carpeta resources
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
