package com.mkuc.pbgame.view;

import com.mkuc.pbgame.PanickingPointees;
import com.mkuc.pbgame.model.Config;
import com.mkuc.pbgame.view.assets.TitleScreenUIElementFactory;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The title screen for the game, assembles UI elements and displays them.
 */
public class TitleScreen {


    /**
     * Calls the startGame method of the main class.
     */
    public static void startGame() {
        PanickingPointees.startGame();
    }

    /**
     * @param stage The window for the title screen to be displayed on.
     *              Assembles UI elements and displayes them to the provided Stage.
     */
    public static void show(Stage stage) {
        VBox container = TitleScreenUIElementFactory.getBackgroundContainer();
        container.getChildren().add(TitleScreenUIElementFactory.getTitle());
        container.getChildren().add(TitleScreenUIElementFactory.getInstructions());
        container.getChildren().add(TitleScreenUIElementFactory.getStartButton());

        stage.setScene(new Scene(container, Config.X_RES, Config.Y_RES));
        stage.show();
    }
}
