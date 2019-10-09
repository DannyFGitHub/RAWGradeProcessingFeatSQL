package view.elements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ConnectionIndicatorUI extends HBox {

    private Color backgroundColor;
    private Color textColor;

    private String connectedString = "Connection to Server Successful";
    private String notConnectedString = "Connection to Server Unsuccessful";

    private Label connectionStatusLabel;

    public ConnectionIndicatorUI(Color backgroundColor, Color textColor) {
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;

        this.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));

        connectionStatusLabel = new Label(notConnectedString);
        connectionStatusLabel.setTextFill(Color.RED);

        this.getChildren().add(connectionStatusLabel);

        this.setAlignment(Pos.CENTER_RIGHT);
    }

    public void setConnection(boolean connected) {
        if (connected) {
            connectionStatusLabel.setText(connectedString);
            connectionStatusLabel.setTextFill(textColor);
        } else {
            connectionStatusLabel.setText(notConnectedString);
            connectionStatusLabel.setTextFill(Color.RED);
        }
    }

}
