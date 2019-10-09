package view.elements;

import controller.InsertRecordFormController;
import controller.RecordUpdateController;
import dbconnection.DatabaseConnectionCredentials;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class MenuUI extends VBox {

    public Button createTableButton;
    public Button insertRecordButton;
    public Button updateRecordButton;

    private Color textColor;
    private Color buttonBackgroundColor;
    private Color backgroundColor;

    private DatabaseConnectionCredentials databaseConnectionCredentials;

    public MenuUI(Color textColor, Color buttonBackground, Color backgroundColor, DatabaseConnectionCredentials databaseConnectionCredentials) {

        this.databaseConnectionCredentials = databaseConnectionCredentials;

        //Store given colors;
        this.textColor = textColor;
        this.buttonBackgroundColor = buttonBackground;
        this.backgroundColor = backgroundColor;

        //Initiate Values (colors for cosmetic purposes too).
        createTableButton = new Button("Create Table");
        createTableButton.setTextFill(textColor);

        insertRecordButton = new Button("Insert Record");
        insertRecordButton.setTextFill(textColor);

        updateRecordButton = new Button("Update Record");
        updateRecordButton.setTextFill(textColor);


        //Create Functionality:
        insertRecordButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //Let the InsertFormController show the form, send this button as a parameter for the controller to control.
                InsertRecordFormController.insertRecordFormShow((Button) e.getSource(), databaseConnectionCredentials);
            }
        });

        //Update Functionality:
        updateRecordButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //Let the InsertFormController show the form, send this button as a parameter for the controller to control.
                RecordUpdateController.updateRecordFormShow((Button) e.getSource(), databaseConnectionCredentials);
            }
        });


        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(10));
        this.setSpacing(50);

        this.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));

        //Place in VBox
        this.getChildren().addAll(createTableButton, insertRecordButton, updateRecordButton);
    }
}
