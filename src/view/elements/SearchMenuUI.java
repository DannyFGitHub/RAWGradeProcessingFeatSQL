package view.elements;

import controller.SearchController;
import dbconnection.DatabaseConnectionCredentials;
import enums.ColumnNames;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class SearchMenuUI extends HBox {

    public ComboBox columnNameDropDown;
    public TextField searchQueryField;
    public Button submitSearchButton;
    public String columnNameDropDownChoice;
    private Label searchLabel;
    private Label byColumnLabel;
    private Color textColor;
    private Color labelTextColor;
    private Color buttonBackgroundColor;
    private Color backgroundColor;

    /**
     * Constructor to initiate the SearchMenuUI class, this constructor also displays it and loads the items within.
     * @param labelTextColor Color for label text
     * @param textColor Color for text color outside of label
     * @param buttonBackground Color for button backgrounds
     * @param backgroundColor Color used for menu background
     * @param recordViewerUI RecordViewerUI to pass onto the buttons within for control
     * @param databaseConnectionCredentials DatabaseConnectionCredentials credentials object to pass onto the buttons to use with the corresponding Controller
     */
    public SearchMenuUI(Color labelTextColor, Color textColor, Color buttonBackground, Color backgroundColor, RecordViewerUI recordViewerUI, DatabaseConnectionCredentials databaseConnectionCredentials) {

        //Store given colors;
        this.labelTextColor = labelTextColor;
        this.textColor = textColor;
        this.buttonBackgroundColor = buttonBackground;
        this.backgroundColor = backgroundColor;

        //Initiate Values (colors for cosmetic purposes too).
        searchLabel = new Label("Search for:");
        searchLabel.setTextFill(labelTextColor);

        searchQueryField = new TextField();
        searchQueryField.setPromptText("Type Search Here");

        byColumnLabel = new Label("By Column:");
        byColumnLabel.setTextFill(labelTextColor);

        //Setup the column name dropdown with all the values required from the ColumnNames Enum
        columnNameDropDown = new ComboBox();
        columnNameDropDown.getItems().addAll(
                ColumnNames.ID.getDisplayName(),
                ColumnNames.NAME.getDisplayName(),
                ColumnNames.QUIZ.getDisplayName(),
                ColumnNames.A1.getDisplayName(),
                ColumnNames.A2.getDisplayName(),
                ColumnNames.EXAM.getDisplayName(),
                ColumnNames.CUMULATIVEMARK.getDisplayName(),
                ColumnNames.GRADE.getDisplayName());
        //Make it select the first dropdown result by default:
        columnNameDropDown.getSelectionModel().selectFirst();
        columnNameDropDownChoice = ColumnNames.ID.getDisplayName();


        //Listen for changes to the ComboBox dropdown and update userChoiceFieldData accordingly.
        columnNameDropDown.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue oldValue, String t, String newValue) {
                columnNameDropDownChoice = newValue;
            }
        });

        submitSearchButton = new Button("Search");

        submitSearchButton.setOnAction((event) -> {
            // Button was clicked, do something...
            String textToSearch = searchQueryField.getText();
            String fieldToSearch = columnNameDropDownChoice;
            SearchController.performSearch(textToSearch, fieldToSearch, recordViewerUI, databaseConnectionCredentials);
        });

        //Set submit button text color
        submitSearchButton.setTextFill(textColor);

        //Position the items of this SearchMenuUI HBox element
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(1));
        //Set spacing for cosmetic
        this.setSpacing(3);

        //Set the background color of the menu
        this.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));

        //Place in VBox
        this.getChildren().addAll(searchLabel, searchQueryField, byColumnLabel, columnNameDropDown, submitSearchButton);
    }

}
