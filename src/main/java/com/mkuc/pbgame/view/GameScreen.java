package com.mkuc.pbgame.view;

import com.mkuc.pbgame.model.Config;
import com.mkuc.pbgame.model.Grid;
import com.mkuc.pbgame.view.assets.GameUIElementFactory;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The main screen of the game, which shows the current state of the game and provides controls for advancing it.
 */
public class GameScreen {
    private final Stage stage;
    private final Grid grid;
    private final RedemptionScreen redemptionScreen;
    private final List<Integer> coupons;
    private final Integer[] redeemableRounds;
    private Integer round;

    public GameScreen(Stage stage) {
        this.stage = stage;
        grid = new Grid();
        coupons = new ArrayList<>();
        round = 1;
        redeemableRounds = Config.REDEEMABLE_ROUNDS;
        redemptionScreen = new RedemptionScreen(grid, stage, this);
    }

    /**
     * @param value The value of the coupon that was picked up
     *              Adds a coupon to the list at the top of the game screen.
     */
    public void addCoupon(Integer value) {
        coupons.add(value);
    }

    /**
     * Advances the round number, checks for eligibility for the redemption of a coupon
     */
    public void nextRound() {
        round++;
        if (Arrays.stream(redeemableRounds).toList().contains(round)) {
            redemptionScreen.show();
        } else {
            grid.scareAllPointees();
            continueGame();
        }
    }


    /**
     * Refreshes the Scene, creating a new Stage element with updated data, ends the game if the round limit was reached.
     */
    public void continueGame() {
        Scene scene = new Scene(createRoot(), Config.X_RES, Config.Y_RES);
        stage.setScene(scene);
        stage.show();
        if (round.equals(redeemableRounds[redeemableRounds.length - 1])) {
            ResultScreen.show(coupons, stage);
        }
    }

    /**
     * @return A new root object for a Scene to be built with
     * Assembles UI elements for the game screen, then returns the root object containing them to be used in the creation and display of a Stage.
     */
    public VBox createRoot() {
        BorderPane topBar = GameUIElementFactory.getTopBar(coupons);
        VBox container = GameUIElementFactory.getBackgroundContainer();
        Label currentRoundLabel = GameUIElementFactory.getRoundLabel(round, redeemableRounds[redeemableRounds.length - 1]);
        AnchorPane progressBar = GameUIElementFactory.getProgressBar(round, redeemableRounds);
        GridPane gridPane = GameUIElementFactory.getGameGrid();

        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                StackPane stackPane = new StackPane();
                stackPane.setAlignment(Pos.CENTER);
                stackPane.getChildren().add((row + col) % 2 == 0 ? GameUIElementFactory.getDarkSquareBackground() : GameUIElementFactory.getLightSquareBackground());
                if (grid.getSquares()[col][row].hasCoupon()) {
                    stackPane.getChildren().add(GameUIElementFactory.getCouponIndicator());
                }
                stackPane.getChildren().add(GameUIElementFactory.getPointeeGraphic(grid.value(col, row)));
                gridPane.add(stackPane, col, row);
            }
        }

        Button nextRound = GameUIElementFactory.getNextRoundButton(this);

        container.getChildren().add(topBar);
        container.getChildren().add(currentRoundLabel);
        container.getChildren().add(progressBar);
        container.getChildren().add(gridPane);
        container.getChildren().add(nextRound);


        return container;
    }

}
