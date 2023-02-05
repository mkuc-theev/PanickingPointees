package com.mkuc.pbgame.view.assets;

import com.mkuc.pbgame.model.Grid;
import com.mkuc.pbgame.view.RedemptionScreen;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Provides the UI elements to be displayed on the coupon redemption screen
 */
public class RedemptionUIElementFactory {

    /**
     * @return The background container that other elements will be inserted into
     */
    public static VBox getBackgroundContainer() {
        VBox container = new VBox(10);
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(31));
        container.setBackground(Background.fill(Color.web("#0096fe")));
        return container;
    }

    /**
     * @param n The StackPane, representing a grid square, to apply the transform to
     * @return Event handler that makes a square grow when it is moused over
     */
    private static EventHandler<MouseEvent> growOnMouseOver(StackPane n) {
        return mouseEvent -> {
            n.setScaleX(1.1);
            n.setScaleY(1.1);
        };
    }

    /**
     * @param n The StackPane, representing a grid square, to apply the transform to
     * @return Event handler that makes a square return to its original size when the mouse leaves its bounds
     */
    private static EventHandler<MouseEvent> shrinkOnMouseLeave(StackPane n) {
        return mouseEvent -> {
            n.setScaleX(1);
            n.setScaleY(1);
        };
    }

    /**
     * @param screen The RedemptionScreen object
     * @param col    The column coordinate of the square to redeem the coupon on
     * @param row    The row coordinate of the square to redeem the coupon on
     * @return An event handler to detect a mouse click confirming the redemption of a coupon
     */
    private static EventHandler<MouseEvent> grabCouponOnClick(RedemptionScreen screen, Integer col, Integer row) {
        return mouseEvent -> screen.redeemCoupon(col, row);
    }


    /**
     * @return Text explaining the purpose of the redemption screen
     */
    public static Text getRedemptionLabel() {
        Text label = new Text();
        label.setText("Click one of the squares to get your coupon!");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        label.setFill(Color.WHITE);

        return label;
    }

    /**
     * @param grid   The game grid containing the board's current state
     * @param screen The RedemptionScreen object
     * @return An interactable version of the game board
     */
    public static GridPane getGridPane(Grid grid, RedemptionScreen screen) {
        GridPane pane = new GridPane();
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                StackPane stackPane = new StackPane();
                EventHandler<MouseEvent> grow = growOnMouseOver(stackPane);
                EventHandler<MouseEvent> shrink = shrinkOnMouseLeave(stackPane);
                EventHandler<MouseEvent> redeem = grabCouponOnClick(screen, col, row);
                stackPane.addEventFilter(MouseEvent.MOUSE_ENTERED, grow);
                stackPane.addEventFilter(MouseEvent.MOUSE_EXITED, shrink);
                stackPane.addEventFilter(MouseEvent.MOUSE_CLICKED, redeem);
                stackPane.setAlignment(Pos.CENTER);
                stackPane.getChildren().add((row + col) % 2 == 0 ? GameUIElementFactory.getDarkSquareBackground() : GameUIElementFactory.getLightSquareBackground());
                if (grid.getSquares()[col][row].hasCoupon()) {
                    stackPane.getChildren().add(GameUIElementFactory.getCouponIndicator());
                }
                if (!grid.getSquares()[col][row].hasCoupon() || grid.value(col, row) == 0) {
                    stackPane.removeEventFilter(MouseEvent.MOUSE_ENTERED, grow);
                    stackPane.removeEventFilter(MouseEvent.MOUSE_EXITED, shrink);
                    stackPane.removeEventFilter(MouseEvent.MOUSE_CLICKED, redeem);
                    stackPane.setEffect(new ColorAdjust(0, -1, 0, 0));
                }
                stackPane.getChildren().add(GameUIElementFactory.getPointeeGraphic(grid.value(col, row)));
                pane.add(stackPane, col, row);
            }
        }
        return pane;
    }
}
