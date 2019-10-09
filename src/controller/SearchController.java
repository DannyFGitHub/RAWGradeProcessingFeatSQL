package controller;

import dbconnection.DatabaseConnectionCredentials;
import dbconnection.DatabaseController;
import enums.ColumnNames;
import javafx.scene.control.Alert;
import view.elements.RecordViewerUI;
import view.elements.SearchResultEntry;

import java.sql.SQLException;
import java.util.ArrayList;

public class SearchController {

    /**
     * Perform the search and handle all returned data. Including showing alerts to the user to show what outcome occurred from the search.
     * Also update the TableView subclass RecordViewerUI with the details of the search results.
     * @param textToSearch String the text to search in the fields of the datatable.
     * @param fieldToSearch String the field to search the textToSearch on, it gets converted to a ColumnNames enum within the method.
     * @param recordViewerUI RecordViewerUI the record viewer UI to update once results return.
     * @param databaseConnectionCredentials DatabaseConnectionCredentials object passed to the controller in order to send credentials through when making call to the DatabaseController
     */
    public static void performSearch(String textToSearch, String fieldToSearch, RecordViewerUI recordViewerUI, DatabaseConnectionCredentials databaseConnectionCredentials) {
        String tableName = MainController.tableName;
        try {
            //Clear table to reset the items in the table, so it is easier to tell what items are new.
            recordViewerUI.getItems().clear();

            ArrayList<SearchResultEntry> results;
            results = DatabaseController.searchTableByField(tableName, ColumnNames.valueOf(fieldToSearch.toUpperCase()), textToSearch, databaseConnectionCredentials.getUsername(), databaseConnectionCredentials.getPassword(), databaseConnectionCredentials.getDatabaseName(), databaseConnectionCredentials.getServerTimeZone(), databaseConnectionCredentials.getServerDomainName());

            if (results.size() > 0) {
                new Alert(Alert.AlertType.INFORMATION, results.size() + " results found, expand window if necessary.").show();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "No results found, try searching the query differently.").show();
            }

            //Add the results to the table to show by sending them to the recordViewerUI
            recordViewerUI.getItems().addAll(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
