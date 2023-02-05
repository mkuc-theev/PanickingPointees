package com.mkuc.pbgame.view.assets;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Provides the UI elements to be displayed on the result screen.
 */
public class ResultScreenUIElementFactory {
    /**
     * @return The background container that houses the other elements
     */
    public static VBox getBackgroundContainer() {
        VBox container = new VBox(10);
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(31));
        container.setBackground(Background.fill(Color.web("#0096fe")));
        return container;
    }

    /**
     * @return Text object containing a congratulatory message
     */
    public static Text getCongrats() {
        Text congrats = new Text();
        congrats.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        congrats.setFill(Color.WHITE);
        congrats.setText("Good job! Here are your coupons:");
        return congrats;
    }

    /**
     * @param val The value of the coupon
     * @return A visual representation of a coupon with its value
     */
    public static StackPane getCouponGraphic(Integer val) {
        StackPane coupon = new StackPane();
        Rectangle background = new Rectangle(125, 90, Color.WHITE);
        Rectangle rim = new Rectangle(125, 90, Color.TRANSPARENT);
        rim.setStroke(Color.web("#0069B1"));
        rim.setStrokeWidth(10);
        Text value = new Text();
        value.setFill(Color.web("#0069B1"));
        value.setText(String.format("%d pts.", val));
        value.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 20));
        coupon.getChildren().add(background);
        coupon.getChildren().add(rim);
        coupon.getChildren().add(value);
        return coupon;
    }

    /**
     * @return A button UI element with an event filter to detect a click and terminate the application
     */
    public static Button getExitButton() {
        EventHandler<MouseEvent> eventHandler = mouseEvent -> {
            Platform.exit();
            System.exit(0);
        };
        Button button = new Button("Exit");
        button.setStyle("-fx-color: #ff0000;");
        button.setTextFill(Color.WHITE);
        button.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        return button;
    }
}
