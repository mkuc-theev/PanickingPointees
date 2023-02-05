package com.mkuc.pbgame;

import com.mkuc.pbgame.model.CommandLineRunner;
import com.mkuc.pbgame.model.Config;
import com.mkuc.pbgame.view.GameScreen;
import com.mkuc.pbgame.view.TitleScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class of the application, entrypoint for the jar.
 */
public class PanickingPointees extends Application {

    private static Stage pStage;

    /**
     * Initializes a fresh instance of the game, setting the window parameters and showing the game view.
     */
    public static void startGame() {
        GameScreen screen = new GameScreen(pStage);
        Scene scene = new Scene(screen.createRoot(), Config.X_RES, Config.Y_RES);
        pStage.setTitle("Panicking Pointees!");
        pStage.setScene(scene);
        pStage.show();
    }

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-c")) {
            CommandLineRunner clr = new CommandLineRunner();
            clr.run();
            System.exit(0);
        }
        launch(args);
    }

    /**
     * @param stage The Stage object created by the JavaFX library
     *              Sets the Stage object in the main class, for shared access by other classes. Shows the title screen.
     */
    @Override
    public void start(Stage stage) {
        pStage = stage;
        TitleScreen.show(pStage);
    }
}