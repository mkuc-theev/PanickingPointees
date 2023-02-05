package com.mkuc.pbgame.view.assets;

import com.mkuc.pbgame.model.Config;
import com.mkuc.pbgame.view.GameScreen;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.List;

/**
 * Provides the UI elements for the game screen
 */
public class GameUIElementFactory {

    /**
     * @return The background container for the other elements
     */
    public static VBox getBackgroundContainer() {
        VBox container = new VBox(10);
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(31));
        container.setBackground(Background.fill(Color.web("#0096fe")));
        return container;
    }

    /**
     * @param coupons The list of coupons redeemed by the player so far
     * @return UI bar containing the values of all owned coupons
     */
    public static BorderPane getTopBar(List<Integer> coupons) {
        BorderPane topBar = new BorderPane();

        HBox contents = new HBox();
        contents.setAlignment(Pos.CENTER_RIGHT);
        contents.setBackground(Background.fill(Color.web("#0069B1")));
        contents.setPrefWidth(Config.X_RES);
        contents.setPrefHeight(30);

        Text preCouponText = new Text();
        preCouponText.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        preCouponText.setFill(Color.WHITE);
        preCouponText.setText("Your coupons: | ");
        contents.getChildren().add(preCouponText);
        for (Integer coupon : coupons) {
            Text couponText = new Text();
            couponText.setText(String.format("%d pts. | ", coupon));
            couponText.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
            couponText.setFill(Color.WHITE);
            contents.getChildren().add(couponText);
        }

        topBar.setTop(contents);

        return topBar;
    }

    /**
     * @return A grid for squares to be placed into
     */
    public static GridPane getGameGrid() {
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(500, 500);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setBackground(Background.fill(Color.web("#4fb7ff")));
        gridPane.setHgap(1);
        gridPane.setVgap(1);
        return gridPane;
    }

    /**
     * @param round            Current round
     * @param redeemableRounds The rounds during which coupons can be redeemed
     * @return A progress bar with indicators of redeemable rounds
     */
    public static AnchorPane getProgressBar(Integer round, Integer[] redeemableRounds) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(500);
        ProgressBar progressBar = new ProgressBar((double) round / (double) redeemableRounds[redeemableRounds.length - 1]);
        progressBar.setPrefWidth(500);

        anchorPane.getChildren().add(progressBar);
        for (Integer rnd : redeemableRounds) {
            double pixelOffset = ((double) rnd / (double) redeemableRounds[redeemableRounds.length - 1]) * 500.0d;

            Text text = new Text();
            text.setText(rnd.toString());
            text.setX(pixelOffset - text.getLayoutBounds().getWidth() - 5);
            text.setY(-1);
            text.setFill(Color.WHITE);

            Line line = new Line(pixelOffset - 3, -12, pixelOffset - 3, progressBar.getHeight());
            line.setStroke(Color.WHITE);

            anchorPane.getChildren().add(text);
            anchorPane.getChildren().add(line);

        }
        return anchorPane;
    }

    /**
     * @param current The current round
     * @param max     The round at which the game ends
     * @return Text specifying the current and final round numbers
     */
    public static Label getRoundLabel(Integer current, Integer max) {
        Label roundLabel = new Label();
        roundLabel.setText(String.format("Round %d/%d", current, max));
        roundLabel.setFont(Font.font(30));
        roundLabel.setTextFill(Color.WHITE);
        return roundLabel;
    }

    /**
     * @return A light square to be placed on the grid in a checker pattern
     */
    public static Rectangle getLightSquareBackground() {
        Rectangle background = new Rectangle();
        background.setHeight(31);
        background.setWidth(31);
        background.setFill(Color.WHITE);
        background.setStroke(Color.TRANSPARENT);
        return background;
    }

    /**
     * @return A dark square to be placed on the grid in a checker pattern
     */
    public static Rectangle getDarkSquareBackground() {
        Rectangle background = new Rectangle();
        background.setHeight(31);
        background.setWidth(31);
        background.setFill(Color.web("#c5e7ff"));
        background.setStroke(Color.TRANSPARENT);
        return background;
    }

    /**
     * @return A gold square overlay for grid squares containing coupons
     */
    public static Rectangle getCouponIndicator() {
        Rectangle coupon = new Rectangle();
        coupon.setHeight(25);
        coupon.setWidth(25);
        coupon.setFill(Color.TRANSPARENT);
        coupon.setStrokeWidth(2);
        coupon.setStroke(Color.web("#d4af37"));
        return coupon;
    }

    /**
     * @param value The value of the square
     * @return A circle with the number of Pointees on a square
     */
    public static StackPane getPointeeGraphic(Integer value) {
        StackPane pane = new StackPane();

        Circle pointee = new Circle();
        pointee.setRadius(10);
        pointee.setFill(Color.web("#0069b1"));

        Text val = new Text();
        val.setText(value.toString());
        val.setFill(Color.WHITE);
        val.setFont(Font.font(16));

        pane.getChildren().add(pointee);
        pane.getChildren().add(val);
        return pane;
    }

    /**
     * @param screen The GameScreen object
     * @return A button UI element with an event filter to detect a click and advance the game forward
     */
    public static Button getNextRoundButton(GameScreen screen) {
        EventHandler<MouseEvent> eventHandler = e -> Platform.runLater(screen::nextRound);
        Button nextRound = new Button("Next Round");
        nextRound.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        nextRound.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        nextRound.setTextFill(Color.WHITE);
        nextRound.setStyle("-fx-color: #0069B1;");
        return nextRound;
    }
}
