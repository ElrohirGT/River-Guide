module com.river_guide.riverguide {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.river_guide.riverguide to javafx.fxml;
    exports com.river_guide.riverguide;
}