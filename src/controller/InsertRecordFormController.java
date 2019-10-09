package controller;

import dbconnection.DatabaseConnectionCredentials;
import dbconnection.DatabaseController;
import dbconnection.DatabaseEntry;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import view.InsertRecordFormUI;

import java.sql.SQLException;

/**
 * Performs the logic on behalf of the InsertRecordFormUI
 */
public class InsertRecordFormController {

    /**
     * Initiate the InsertRecordForm and show the UI
     *
     * @param caller                        button that made the call to open this user interface.
     * @param databaseConnectionCredentials the credentials required by the controller to send to the database controller when making the insert record request.
     */
    public static void insertRecordFormShow(Button caller, DatabaseConnectionCredentials databaseConnectionCredentials) {
        //Deactivate the button that called this controller.
        caller.setDisable(true);

        //Show the form:
        InsertRecordFormUI insertRecordFormUI = new InsertRecordFormUI();

        insertRecordFormUI.submitButton.setOnAction(e -> {
            if (insertRecordFormUI.allFieldsEnteredCorrectly()) {
                DatabaseEntry databaseEntry = insertRecordFormUI.generateDatabaseEntryObject();
                insertRecord(databaseEntry, databaseConnectionCredentials);
                insertRecordFormUI.close();
                caller.setDisable(false);
            }
        });

        //Set event so when form closes, the button that called it reactivates.
        insertRecordFormUI.setOnCloseRequest(event -> {
            caller.setDisable(false);
        });
    }

    /**
     * Insert record into the database using the DatabaseController, map the output of the "insertNewRecord" to an alert corresponding to the response.
     * So the user is aware of what happened.
     *
     * @param databaseEntry                 entry object with all the details to create the entry with.
     * @param databaseConnectionCredentials the credentials required to make the connection to the SQL server and modify.
     */
    public static void insertRecord(DatabaseEntry databaseEntry, DatabaseConnectionCredentials databaseConnectionCredentials) {
        String tableName = MainController.tableName;

        //In try and catch as insertNewRecord throws SQLException
        try {
            //Send request to create record and save number returned
            int numberReturned = DatabaseController.insertNewRecord(tableName, databaseEntry, databaseConnectionCredentials.getUsername(), databaseConnectionCredentials.getPassword(),
                    databaseConnectionCredentials.getDatabaseName(), databaseConnectionCredentials.getServerTimeZone(), databaseConnectionCredentials.getServerDomainName());

            if (numberReturned == 1) {
                //Notify the user the record could not be created
                new Alert(Alert.AlertType.WARNING, "Record was not created because another record with that key already exists.").show();
            } else if (numberReturned == 0) {
                //Notify the user the record was created.
                new Alert(Alert.AlertType.INFORMATION, "Record was successfully created.").show();
            } else if (numberReturned == 2) {
                //Notify the user the record was not created, as there was an error.
                new Alert(Alert.AlertType.ERROR, "Record could not be added, there was a database error.").show();
            }
        } catch (SQLException e) {
            //Let the user know what the error was.
            new Alert(Alert.AlertType.ERROR, "Record could not be added, there was a database error. " + e.getMessage()).show();
            e.printStackTrace();
        }

    }

}
