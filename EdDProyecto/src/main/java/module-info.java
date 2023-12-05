module ec.edu.espol.eddproyecto.fxml {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    requires transitive java.desktop;
    requires transitive javafx.base;
    requires transitive javafx.graphics;
    opens com.mycompany.javaproyect to javafx.fxml;
    exports com.mycompany.javaproyect;
}
