package controller;

import dbconnection.DatabaseConnectionCredentials;
import dbconnection.DatabaseController;
import enums.ColumnNames;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import view.RecordUpdateUI;

import java.sql.SQLException;

public class RecordUpdateController {

    /**
     * Initiate the UpdateRecordUIForm and show the UI
     *
     * @param caller                        button that made the call to open this user interface.
     * @param databaseConnectionCredentials the credentials required by the controller to send to the database controller when making the update record request.
     */
    public static void updateRecordFormShow(Button caller, DatabaseConnectionCredentials databaseConnectionCredentials) {
        //Deactivate the button that called this controller.
        caller.setDisable(true);

        //Show the form:
        RecordUpdateUI recordUpdateUIForm = new RecordUpdateUI();

        recordUpdateUIForm.submitButton.setOnAction(e -> {
            if (recordUpdateUIForm.allFieldsEnteredCorrectly()) {
                //Fetch the idToupdate from the field
                String idToUpdate = recordUpdateUIForm.idTextField.getText();
                //Fetch the column names from the dropdown
                ColumnNames columnToUpdate = ColumnNames.valueOf(recordUpdateUIForm.userChoiceFieldData.toUpperCase());
                //Fetch the data to update the column of the record with from the textfield in the record update ui form
                String dataToUpdateFieldWith = recordUpdateUIForm.updateFieldDataTextField.getText();
                //Call the query from this controller
                updateRecord(idToUpdate, columnToUpdate, dataToUpdateFieldWith, databaseConnectionCredentials);
                //Close the form
                recordUpdateUIForm.close();
                //Unlock the button to allow the user to update another record.
                caller.setDisable(false);
            }
        });

        //Set event so when form closes, the button that called it reactivates.
        recordUpdateUIForm.setOnCloseRequest(event -> {
            caller.setDisable(false);
        });
    }

    /**
     * Call the database controller to update Table record by ID. This method takes input from the caller in order to prepare a call to database controller and deal with the results accordingly.
     * It will prompt user letting the user know what is happening during execution.
     * @param idToUpdate String the id of the record the user wishes to update.
     * @param columnToUpdate ColumnNames enum value of the column to update the record on
     * @param dataToUpdateFieldWith String the data to update the record with on the given column name
     * @param databaseConnectionCredentials DatabaseConnectionCredentials the object that holds the credentials to perform the query and contact the sql server successfully.
     */
    public static void updateRecord(String idToUpdate, ColumnNames columnToUpdate, String dataToUpdateFieldWith, DatabaseConnectionCredentials databaseConnectionCredentials) {
        String tableName = MainController.tableName;

        //In try and catch as insertNewRecord throws SQLException
        try {
            //Send request to create record and save number returned
            int numberReturned = DatabaseController.updateTableRecordById(tableName, idToUpdate, columnToUpdate, dataToUpdateFieldWith, databaseConnectionCredentials.getUsername(), databaseConnectionCredentials.getPassword(), databaseConnectionCredentials.getDatabaseName(), databaseConnectionCredentials.getServerTimeZone(), databaseConnectionCredentials.getServerDomainName());

            if (numberReturned == 1) {
                //Notify the user the record could not be created
                new Alert(Alert.AlertType.WARNING, "Record was not updated because record with id: " + idToUpdate + " could not be found.").show();
            } else if (numberReturned == 0) {
                //Notify the user the record was created.
                new Alert(Alert.AlertType.INFORMATION, "Record was successfully updated.").show();
            } else if (numberReturned == 2) {
                //Notify the user the record was not created, as there was an error.
                new Alert(Alert.AlertType.ERROR, "Record could not be updated, there was a database error.").show();
            }
        } catch (SQLException e) {
            //Let the user know what the error was.
            new Alert(Alert.AlertType.ERROR, "Record could not be updated, there was a database error. " + e.getMessage()).show();
            e.printStackTrace();
        }
    }
}

