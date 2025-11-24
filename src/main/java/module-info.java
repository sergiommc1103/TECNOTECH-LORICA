module com.tiendavirtual {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.tiendavirtual to javafx.fxml;
    exports com.tiendavirtual;
    
    opens controlador to javafx.fxml;
    exports controlador;
}
