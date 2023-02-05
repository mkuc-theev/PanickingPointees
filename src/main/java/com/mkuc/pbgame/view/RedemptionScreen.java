package com.mkuc.pbgame.view;

import com.mkuc.pbgame.model.Config;
import com.mkuc.pbgame.model.Grid;
import com.mkuc.pbgame.view.assets.RedemptionUIElementFactory;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A screen that interrupts the game and lets a player pick up one of the available coupons.
 */
public class RedemptionScreen {
    private final Grid grid;

    private final Stage stage;

    private final GameScreen screen;

    public RedemptionScreen(Grid grid, Stage stage, GameScreen screen) {
        this.grid = grid;
        this.stage = stage;
        this.screen = screen;
    }

    /**
     * @param col The column coordinate of the coupon to be redeemed
     * @param row The row coordinate of the coupon to be redeemed
     *            Picks up the specified coupon, adding it to the list and resuming the game.
     */
    public void redeemCoupon(Integer col, Integer row) {
        screen.addCoupon(grid.redeemCoupon(col, row));
        screen.continueGame();
    }

    /**
     * Assembles the UI elements for the redemption screen and displays it to the same stage as the GameScreen.
     */
    public void show() {

        VBox container = RedemptionUIElementFactory.getBackgroundContainer();

        container.getChildren().add(RedemptionUIElementFactory.getRedemptionLabel());
        container.getChildren().add(RedemptionUIElementFactory.getGridPane(grid, this));

        Scene scene = new Scene(container, Config.X_RES, Config.Y_RES);
        stage.setScene(scene);
        stage.show();
    }

}
