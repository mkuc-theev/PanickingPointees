package com.mkuc.pbgame.view.assets;

import com.mkuc.pbgame.view.TitleScreen;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Provides the UI elements to be displayed on the title screen
 */
public class TitleScreenUIElementFactory {
    /**
     * @return The background container for the title screen
     */
    public static VBox getBackgroundContainer() {
        VBox container = new VBox(25);
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(31));
        container.setBackground(Background.fill(Color.web("#0096fe")));
        return container;
    }

    /**
     * @return The title text to be displayed
     */
    public static Text getTitle() {
        Text title = new Text();
        title.setFill(Color.WHITE);
        title.setText("Panicking Pointees!");
        title.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 30));

        return title;
    }

    /**
     * @return The gameplay instructions to be displayed under the title
     */
    public static Text getInstructions() {
        Text instructions = new Text();
        instructions.setFill(Color.WHITE);
        instructions.setFont(Font.font("Verdana", FontWeight.MEDIUM, 16));
        instructions.setWrappingWidth(500);
        instructions.setTextAlignment(TextAlignment.JUSTIFY);
        instructions.setText(
                """
                        Each square on the board has a Pointee standing on it;
                        Send a bird over the board using the 'Next' button to make them panic;
                        Every Pointee on the board will jump to an adjacent square;
                        After certain rounds, you can pick a coupon to redeem;
                        The coupon's value depends on the number of Pointees standing on the square;
                        Once a coupon has been picked up, that square no longer has a coupon for the next rounds;
                        The squares that still have a coupon on them are marked with gold squares."""
        );
        return instructions;
    }

    /**
     * @return The Button UI element with an event filter to detect a click and begin the game
     */
    public static Button getStartButton() {
        EventHandler<MouseEvent> eventHandler = e -> Platform.runLater(TitleScreen::startGame);
        Button start = new Button("Start");
        start.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        start.setTextFill(Color.WHITE);
        start.setStyle("-fx-color: #0069B1;");
        start.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        return start;
    }
}
