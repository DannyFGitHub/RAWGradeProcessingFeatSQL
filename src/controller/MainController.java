package controller;

import dbconnection.DatabaseConnectionCredentials;
import dbconnection.DatabaseController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import view.MainWindowUI;

import java.sql.SQLException;
import java.util.Optional;

/**
 * The Main Controller class that performs the logic on behalf of the mainWindowUI interface class.
 */
public class MainController {

    public static final String tableName = "GradeProcessing";
    private String username;
    private String password;
    private String databaseName;
    private String serverTimeZone;
    private String serverName;
    private MainWindowUI mainWindowUI;

    public MainController(MainWindowUI mainWindowUI) {
        this.mainWindowUI = mainWindowUI;

        extractCredentials(mainWindowUI.databaseConnectionCredentials);

        //Automatically Disable the other menus if the table does not exist on start up.
        try {
            if (!DatabaseController.tableExistsCheck(tableName, username, password, databaseName, serverTimeZone, serverName)) {
                //Set the buttons to be disabled to prevent modifying whilst performing action.
                mainWindowUI.leftSideMenu.insertRecordButton.setDisable(true);
                mainWindowUI.leftSideMenu.updateRecordButton.setDisable(true);
                mainWindowUI.searchMenuUI.submitSearchButton.setDisable(true);
            }
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, "There was an error checking whether the table 'GradeProcessing' exists. Please make sure database is running, credentials are correct, and rerun this program.").showAndWait();
        }

        //Setup the action of the createTableButton - so it creates a table if it doesn't exist and replaces the existing one if it does. It also warns the user.
        mainWindowUI.leftSideMenu.createTableButton.setOnAction(e -> {
            try {
                //Check if it exists
                if (DatabaseController.tableExistsCheck(tableName, username, password, databaseName, serverTimeZone, serverName)) {
                    //Alert the user before replacing
                    Alert areYouSure = new Alert(Alert.AlertType.CONFIRMATION, "Table already exists. Are you sure you want to overwrite the table?");
                    Optional<ButtonType> result = areYouSure.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        createTable();
                    }
                } else {
                    createTable();
                }
            } catch (SQLException ex) {
                new Alert(Alert.AlertType.ERROR, "There was an error checking whether the table 'GradeProcessing' exists. Please make sure database is running, credentials are correct, and rerun this program.").showAndWait();
            }
        });

    }

    /**
     * Create the default table - call the controller to do it, and perform other UI actions.
     *
     * @throws SQLException
     */
    private void createTable() throws SQLException {
        DatabaseController.createTable(tableName, username, password, databaseName, serverTimeZone, serverName);
        mainWindowUI.leftSideMenu.insertRecordButton.setDisable(false);
        mainWindowUI.leftSideMenu.updateRecordButton.setDisable(false);
        mainWindowUI.searchMenuUI.submitSearchButton.setDisable(false);
        new Alert(Alert.AlertType.INFORMATION, "Empty Table named: " + tableName + " was created.").show();
    }

    private void extractCredentials(DatabaseConnectionCredentials databaseConnectionCredentials) {
        this.username = databaseConnectionCredentials.getUsername();
        this.password = databaseConnectionCredentials.getPassword();
        this.databaseName = databaseConnectionCredentials.getDatabaseName();
        this.serverTimeZone = databaseConnectionCredentials.getServerTimeZone();
        this.serverName = databaseConnectionCredentials.getServerDomainName();
    }

}
