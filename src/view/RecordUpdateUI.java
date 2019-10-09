package view;

import enums.ColumnNames;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RecordUpdateUI extends Stage {

    public TextField idTextField;
    public TextField updateFieldDataTextField;
    public ComboBox dropdownFieldToUpdate;
    public Button submitButton;
    public String userChoiceFieldData;
    private GridPane formGrid;
    private Label idLabel;
    private Label updateFieldDataLabel;
    private Label fieldToUpdateLabel;

    public RecordUpdateUI() {

        dropdownFieldToUpdate = new ComboBox();
        dropdownFieldToUpdate.getItems().addAll(ColumnNames.NAME.getDisplayName(), ColumnNames.QUIZ.getDisplayName(), ColumnNames.A1.getDisplayName(), ColumnNames.A2.getDisplayName(), ColumnNames.EXAM.getDisplayName(), ColumnNames.CUMULATIVEMARK.getDisplayName(), ColumnNames.GRADE.getDisplayName());
        //Make it select the first dropdown result by default:
        dropdownFieldToUpdate.getSelectionModel().selectFirst();
        userChoiceFieldData = ColumnNames.NAME.getDisplayName();


        //Prepare Items
        idTextField = new TextField() {
            final int maxChars = 8;
            final String restictToCharacters = "[0-9]*";

            //Override the replacetext to replace the text in the field as it is typed.
            @Override
            public void replaceText(int start, int end, String text) {
                if (validateText(text)) {
                    super.replaceText(start, end, text);
                }
            }

            //override the replaceSelection to replace the text in the field as it is typed.
            @Override
            public void replaceSelection(String text) {
                if (validateText(text)) {
                    super.replaceSelection(text);
                }
            }

            //Logic to replace the text with valid text that matches the criteria of maxChars and character restrictions.
            private boolean validateText(String text) {
                return text.isEmpty() || (text.matches(restictToCharacters) && getText().length() < maxChars);
            }
        };
        idTextField.setPromptText("Enter ID - Max 8 Digits");

        updateFieldDataTextField = new TextField() {
            final String restictToCharacters = "[0-9a-zA-Z\\s\\.]*";

            //Override the replacetext to replace the text in the field as it is typed.
            @Override
            public void replaceText(int start, int end, String text) {
                if (validateText(text)) {
                    super.replaceText(start, end, text);
                }
            }

            //override the replaceSelection to replace the text in the field as it is typed.
            @Override
            public void replaceSelection(String text) {
                if (validateText(text)) {
                    super.replaceSelection(text);
                }
            }

            //Logic to replace the text with valid text that matches the criteria of maxChars and character restrictions.
            private boolean validateText(String text) {
                return text.isEmpty() || (text.matches(restictToCharacters));
            }
        };
        updateFieldDataTextField.setPromptText("Enter data to update chosen field for record");


        //Labels
        Label headingLabel = new Label("Update Record Form");
        headingLabel.setPadding(new Insets(5));
        headingLabel.setFont(new Font("Arial", 24));

        idLabel = new Label("ID:");
        updateFieldDataLabel = new Label("Data to update field with:");
        fieldToUpdateLabel = new Label("Choose a field to update:");

        //Fields
        formGrid = new GridPane();
        formGrid.addRow(0, idLabel, idTextField);
        formGrid.addRow(1, fieldToUpdateLabel, dropdownFieldToUpdate);
        formGrid.addRow(2, updateFieldDataLabel, updateFieldDataTextField);
        formGrid.setAlignment(Pos.CENTER);

        //Listen for changes to the ComboBox dropdown and update userChoiceFieldData accordingly.
        dropdownFieldToUpdate.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue oldValue, String t, String newValue) {
                userChoiceFieldData = newValue;
            }
        });

        //Button
        submitButton = new Button("SUBMIT");

        //Create Form Structure
        VBox form = new VBox(headingLabel, formGrid, submitButton);
        form.setSpacing(10);
        form.setPadding(new Insets(10));
        form.setAlignment(Pos.CENTER);

        Scene formScene = new Scene(form);
        this.setResizable(false);
        this.setScene(formScene);
        this.show();
    }

    /**
     * Method for checking that all fields match criteria prior to submitting to controller.
     *
     * @return boolean indicating whether fields are all meeting criteria.
     */
    public boolean allFieldsEnteredCorrectly() {

        boolean allFieldsCorrect = true;

        //Set AllFieldsCorrect to false if the field does not match the criteria required also set the label corresponding to RED, if its correct set the label to black.
        if (idTextField.getText().isEmpty() || idTextField.getText().length() != 8 || !idTextField.getText().matches("[0-9]*")) {
            allFieldsCorrect = false;
            new Alert(Alert.AlertType.ERROR, "Please make sure name id field is valid (8 digits)").show();
            idLabel.setTextFill(Paint.valueOf("RED"));
        } else {
            idLabel.setTextFill(Paint.valueOf("BLACK"));
        }

        if (userChoiceFieldData.matches(ColumnNames.NAME.getDisplayName())) {
            //updateFieldDataTextField Textfield check
            if (updateFieldDataTextField.getText().isEmpty() || !updateFieldDataTextField.getText().matches("[a-zA-Z\\s]*")) {
                allFieldsCorrect = false;
                new Alert(Alert.AlertType.ERROR, "Please make sure name field is not empty or contains symbols.").show();
                updateFieldDataLabel.setTextFill(Paint.valueOf("RED"));
            } else {
                updateFieldDataLabel.setTextFill(Paint.valueOf("BLACK"));
            }
        }

        //This field is treated different in the sense that it needs to be an integer and below 100
        if (userChoiceFieldData.matches(ColumnNames.QUIZ.getDisplayName()) || userChoiceFieldData.matches(ColumnNames.A1.getDisplayName()) || userChoiceFieldData.matches(ColumnNames.A2.getDisplayName()) || userChoiceFieldData.matches(ColumnNames.EXAM.getDisplayName())) {
            //updateFieldDataTextField Textfield check
            if (updateFieldDataTextField.getText().isEmpty() || updateFieldDataTextField.getText().length() > 3 || !updateFieldDataTextField.getText().matches("[0-9]*") || Integer.parseInt(updateFieldDataTextField.getText()) > 100) {
                allFieldsCorrect = false;
                new Alert(Alert.AlertType.ERROR, "Please make sure the data to update the field record with is not more than 3 characters, is a number from 0 to 100").show();
                updateFieldDataLabel.setTextFill(Paint.valueOf("RED"));
            } else {
                updateFieldDataLabel.setTextFill(Paint.valueOf("BLACK"));
            }
        }

        //This field is treated differnet in the sense that it needs to be a double (parsed onto a double too)
        if (userChoiceFieldData.matches(ColumnNames.CUMULATIVEMARK.getDisplayName())) {
            //updateFieldDataTextField Textfield check
            if (updateFieldDataTextField.getText().isEmpty() || updateFieldDataTextField.getText().length() > 6 || !updateFieldDataTextField.getText().matches("[0-9\\.]*") || Double.parseDouble(updateFieldDataTextField.getText()) > 100) {
                allFieldsCorrect = false;
                new Alert(Alert.AlertType.ERROR, "Please make sure the data to update the field record with is not more than 3 characters, is a double type of number from 0 to 100").show();
                updateFieldDataLabel.setTextFill(Paint.valueOf("RED"));
            } else {
                updateFieldDataLabel.setTextFill(Paint.valueOf("BLACK"));
            }
        }

        if (userChoiceFieldData.matches(ColumnNames.GRADE.getDisplayName())) {
            //updateFieldDataTextField Textfield check
            if (updateFieldDataTextField.getText().isEmpty() || updateFieldDataTextField.getText().length() > 2 || !updateFieldDataTextField.getText().matches("(HD|DI|CR|PS|FL)")) {
                allFieldsCorrect = false;
                new Alert(Alert.AlertType.ERROR, "Incorrect data to update the field, please make sure the data contains either HD|DI|CR|PS|FL ").show();
                updateFieldDataLabel.setTextFill(Paint.valueOf("RED"));
            } else {
                updateFieldDataLabel.setTextFill(Paint.valueOf("BLACK"));
            }
        }

        //Reset all colors if all fields are correct.
        if (allFieldsCorrect) {
            idLabel.setTextFill(Paint.valueOf("BLACK"));
            updateFieldDataLabel.setTextFill(Paint.valueOf("BLACK"));
        }

        return allFieldsCorrect;
    }

}
