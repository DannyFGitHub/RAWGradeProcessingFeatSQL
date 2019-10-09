package view;

import dbconnection.DatabaseEntry;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class InsertRecordFormUI extends Stage {

    public TextField idTextField;
    public TextField nameTextField;
    public TextField quizTextField;
    public TextField a1TextField;
    public TextField a2TextField;
    public TextField examTextField;
    public Button submitButton;
    private GridPane formGrid;
    private Label idLabel;
    private Label nameLabel;
    private Label quizLabel;
    private Label a1Label;
    private Label a2Label;
    private Label examLabel;


    public InsertRecordFormUI() {

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

        nameTextField = new TextField();
        nameTextField.setPromptText("Enter name here");

        quizTextField = new TextField() {
            final int maxChars = 3;
            final String restictToCharacters = "[0-9]*";

            //Override the replacetext to change the behaviour to check the validateText criterias
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
        //Listen for number if over 100 reset to 100
        quizTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (Integer.parseInt(newValue) > 100) {
                    //Alert user incorrect input
                    new Alert(Alert.AlertType.WARNING, "Please enter a number between 0 and 100", ButtonType.OK).show();
                    quizTextField.setText("100");
                }
            } catch (Exception ex) {
                //Attempted to parse integer. Catching exception only so app doesn't crash. No need to do anything.
            }
        });
        quizTextField.setPromptText("Enter Quiz Result Here");

        a1TextField = new TextField() {
            final int maxChars = 3;
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
        //Listen for number if over 100 reset to 100
        a1TextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (Integer.parseInt(newValue) > 100) {
                    //Alert user incorrect Input.
                    new Alert(Alert.AlertType.WARNING, "Please enter a number between 0 and 100", ButtonType.OK).show();
                    a1TextField.setText("100");
                }
            } catch (Exception ex) {
                //Attempted to parse integer. Catching exception only so app doesn't crash. No need to do anything.
            }
        });
        a1TextField.setPromptText("Enter Assessment 1 Result Here");

        a2TextField = new TextField() {
            final int maxChars = 3;
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
        //Listen for number if over 100 reset to 100
        a2TextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (Integer.parseInt(newValue) > 100) {
                    //Alert user incorrect input.
                    new Alert(Alert.AlertType.WARNING, "Please enter a number between 0 and 100", ButtonType.OK).show();
                    a2TextField.setText("100");
                }
            } catch (Exception ex) {
                //Attempted to parse integer. Catching exception only so app doesn't crash. No need to do anything.
            }
        });
        a2TextField.setPromptText("Enter Assessment 2 Result Here");

        examTextField = new TextField() {
            final int maxChars = 3;
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
        //Listen for number if over 100 reset to 100
        examTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (Integer.parseInt(newValue) > 100) {
                    //Alert User Incorrect input
                    new Alert(Alert.AlertType.WARNING, "Please enter a number between 0 and 100", ButtonType.OK).show();
                    examTextField.setText("100");
                }
            } catch (Exception ex) {
                //Attempted to parse integer. Catching exception only so app doesn't crash. No need to do anything.
            }
        });
        examTextField.setPromptText("Enter Exam Result Here");

        //Labels
        Label headingLabel = new Label("Insert New Record Form");
        headingLabel.setPadding(new Insets(5));
        headingLabel.setFont(new Font("Arial", 24));

        idLabel = new Label("ID:");
        nameLabel = new Label("Name:");
        quizLabel = new Label("Quiz Result:");
        a1Label = new Label("A1 Result:");
        a2Label = new Label("A2 Result:");
        examLabel = new Label("Exam Result:");


        //Fields
        formGrid = new GridPane();
        formGrid.addRow(0, idLabel, idTextField);
        formGrid.addRow(1, nameLabel, nameTextField);
        formGrid.addRow(2, quizLabel, quizTextField);
        formGrid.addRow(3, a1Label, a1TextField);
        formGrid.addRow(4, a2Label, a2TextField);
        formGrid.addRow(5, examLabel, examTextField);
        formGrid.setAlignment(Pos.CENTER);

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
     * Checks that all fields match the requirements
     *
     * @return boolean true if all is correct and false if a field does not meet criteria.
     */
    public boolean allFieldsEnteredCorrectly() {
        boolean allFieldsCorrect = true;

        //Set AllFieldsCorrect to false if the field does not match the criteria required also set the label corresponding to RED, if its correct set the label to black.
        if (idTextField.getText().isEmpty() || idTextField.getText().length() != 8 || !idTextField.getText().matches("[0-9]*")) {
            allFieldsCorrect = false;
            idLabel.setTextFill(Paint.valueOf("RED"));
        } else {
            idLabel.setTextFill(Paint.valueOf("BLACK"));
        }
        //Name Textfield check
        if (nameTextField.getText().isEmpty() || !nameTextField.getText().matches("[a-zA-Z\\s]*")) {
            allFieldsCorrect = false;
            nameLabel.setTextFill(Paint.valueOf("RED"));
        } else {
            nameLabel.setTextFill(Paint.valueOf("BLACK"));
        }
        //Quiz TextField check
        if (quizTextField.getText().isEmpty() || quizTextField.getText().length() > 3 || !quizTextField.getText().matches("[0-9]*")) {
            allFieldsCorrect = false;
            quizLabel.setTextFill(Paint.valueOf("RED"));
        } else {
            quizLabel.setTextFill(Paint.valueOf("BLACK"));
        }
        //A1 Textfield check
        if (a1TextField.getText().isEmpty() || a1TextField.getText().length() > 3 || !a1TextField.getText().matches("[0-9]*")) {
            allFieldsCorrect = false;
            a1Label.setTextFill(Paint.valueOf("RED"));
        } else {
            a1Label.setTextFill(Paint.valueOf("BLACK"));
        }
        //A2 Textfield check
        if (a2TextField.getText().isEmpty() || a2TextField.getText().length() > 3 || !a2TextField.getText().matches("[0-9]*")) {
            allFieldsCorrect = false;
            a2Label.setTextFill(Paint.valueOf("RED"));
        } else {
            a2Label.setTextFill(Paint.valueOf("BLACK"));
        }
        //Exam Textfield check
        if (examTextField.getText().isEmpty() || examTextField.getText().length() > 3 || !examTextField.getText().matches("[0-9]*")) {
            allFieldsCorrect = false;
            examLabel.setTextFill(Paint.valueOf("RED"));
        } else {
            examLabel.setTextFill(Paint.valueOf("BLACK"));
        }

        //Reset all colors if all fields are correct.
        if (allFieldsCorrect) {
            idLabel.setTextFill(Paint.valueOf("BLACK"));
            nameLabel.setTextFill(Paint.valueOf("BLACK"));
            quizLabel.setTextFill(Paint.valueOf("BLACK"));
            a1Label.setTextFill(Paint.valueOf("BLACK"));
            a2Label.setTextFill(Paint.valueOf("BLACK"));
            examLabel.setTextFill(Paint.valueOf("BLACK"));
        }

        return allFieldsCorrect;
    }

    /**
     * Generate a DatabaseEntry Object based on the form information
     *
     * @return DatabaseEntry returns a database entry object with the details of the fields.
     */
    public DatabaseEntry generateDatabaseEntryObject() {

        //Prepare values from the textfields into values and parse to integers.
        String id = idTextField.getText();
        String name = nameTextField.getText().trim();
        int quiz = Integer.parseInt(quizTextField.getText());
        int a1 = Integer.parseInt(a1TextField.getText());
        int a2 = Integer.parseInt(a2TextField.getText());
        int exam = Integer.parseInt(examTextField.getText());

        return new DatabaseEntry(id, name, quiz, a1, a2, exam);
    }

}
