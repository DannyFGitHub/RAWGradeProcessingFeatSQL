package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Optional;

public class SetupConnectionUI extends Stage {

    private static final String suggestedUsername = "admin";
    private static final String suggestedPassword = "12345678";
    private static final String suggestedDatabaseName = "myDatabase";
    private static final String suggestedServerTimeZone = "Australia/Sydney";
    private static final String suggestedServerName = "localhost";

    public GridPane formGrid;

    public TextField usernameField;
    public TextField passwordField;
    public TextField databaseNameField;
    public TextField serverTimeZoneField;
    public TextField serverNameField;

    public Button submitButton;
    public Button quitButton;

    /**
     * Constructor for Connection to SQL server setup Form, this form gathers the credentials required, to be used during the running of the program.
     */
    public SetupConnectionUI() {
        //Remove Close buttons to force the user to use the ones in the pane.
        this.initStyle(StageStyle.UNDECORATED);

        //Prepare Items
        usernameField = new TextField();
        usernameField.setText(suggestedUsername);
        usernameField.setPromptText(suggestedUsername);
        passwordField = new TextField();
        passwordField.setText(suggestedPassword);
        passwordField.setPromptText(suggestedPassword);
        databaseNameField = new TextField();
        databaseNameField.setText(suggestedDatabaseName);
        databaseNameField.setPromptText(suggestedDatabaseName);
        serverTimeZoneField = new TextField();
        serverTimeZoneField.setPromptText(suggestedServerTimeZone);
        serverTimeZoneField.setText(suggestedServerTimeZone);
        serverNameField = new TextField();
        serverNameField.setText(suggestedServerName);
        serverNameField.setPromptText(suggestedServerName);

        //Labels
        Label headingLabel = new Label("SQL Server Connection Settings");
        headingLabel.setPadding(new Insets(5));
        headingLabel.setFont(new Font("Arial", 24));

        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        Label databaseNameLabel = new Label("Database Name:");
        Label serverTimeZoneLabel = new Label("Server TimeZone:");
        Label serverNameLabel = new Label("Server Name or IP:");

        //Fields
        formGrid = new GridPane();
        formGrid.addRow(0, usernameLabel, usernameField);
        formGrid.addRow(1, passwordLabel, passwordField);
        formGrid.addRow(2, databaseNameLabel, databaseNameField);
        formGrid.addRow(3, serverTimeZoneLabel, serverTimeZoneField);
        formGrid.addRow(4, serverNameLabel, serverNameField);
        formGrid.setAlignment(Pos.CENTER);

        //Button
        submitButton = new Button("SUBMIT");
        quitButton = new Button("QUIT");
        quitButton.setOnAction(e -> {
            Alert areYouSure = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to quit?");
            Optional<ButtonType> result = areYouSure.showAndWait();
            if (result.get() == ButtonType.OK) {
                //If user is sure, quit.
                System.exit(0);
            }
        });

        HBox buttons = new HBox(submitButton, quitButton);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER);

        //Create Form Structure
        VBox form = new VBox(headingLabel, formGrid, buttons);
        form.setSpacing(10);
        form.setPadding(new Insets(10));
        form.setAlignment(Pos.CENTER);

        Scene formScene = new Scene(form);
        this.setResizable(false);
        this.setScene(formScene);
    }

}

