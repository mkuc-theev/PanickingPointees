package com.mkuc.pbgame.view;

import com.mkuc.pbgame.model.Config;
import com.mkuc.pbgame.view.assets.ResultScreenUIElementFactory;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

/**
 * The result screen that appears after the game has ended, displays the results of the game.
 */
public class ResultScreen {
    /**
     * @param coupons The list of values of coupons picked up by the player.
     * @param stage   The window for the result screen to be displayed on.
     *                Assembles the UI elements for the result screen and displays it to provided Stage.
     */
    public static void show(List<Integer> coupons, Stage stage) {
        VBox container = ResultScreenUIElementFactory.getBackgroundContainer();
        container.getChildren().add(ResultScreenUIElementFactory.getCongrats());
        for (Integer coupon : coupons) {
            container.getChildren().add(ResultScreenUIElementFactory.getCouponGraphic(coupon));
        }
        container.getChildren().add(ResultScreenUIElementFactory.getExitButton());
        Scene scene = new Scene(container, Config.X_RES, Config.Y_RES);
        stage.setScene(scene);
        stage.show();
    }
}
