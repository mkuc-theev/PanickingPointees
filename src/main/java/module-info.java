module com.mkuc.pbgame {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires com.almasb.fxgl.all;

    opens com.mkuc.pbgame to javafx.fxml;
    exports com.mkuc.pbgame;
    exports com.mkuc.pbgame.view;
    opens com.mkuc.pbgame.view to javafx.fxml;
    exports com.mkuc.pbgame.model;
    opens com.mkuc.pbgame.model to javafx.fxml;
    exports com.mkuc.pbgame.view.assets;
    opens com.mkuc.pbgame.view.assets to javafx.fxml;
}