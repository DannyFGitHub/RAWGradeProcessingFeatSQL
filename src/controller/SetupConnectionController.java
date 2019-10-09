package controller;

import dbconnection.DatabaseConnectionCredentials;
import dbconnection.DatabaseController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import view.SetupConnectionUI;
import view.elements.ConnectionIndicatorUI;

public class SetupConnectionController {

    private SetupConnectionUI setupConnectionUI;
    private DatabaseConnectionCredentials databaseConnectionCredentials;

    public SetupConnectionController(ConnectionIndicatorUI connectionIndicatorUI) {
        setupConnectionUI = new SetupConnectionUI();

        setupConnectionUI.submitButton.setOnAction(e -> {
            if (allFieldsNotEmpty()) {
                createCredentialObject();
                if (canConnectToDatabase()) {
                    connectionIndicatorUI.setConnection(true);
                    setupConnectionUI.close();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Unable to connect to database please make sure the credentials and other data is correct", ButtonType.OK).show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Please make sure all fields are filled correctly", ButtonType.OK).show();
            }
        });

        setupConnectionUI.showAndWait();
    }

    public boolean canConnectToDatabase() {
        String username = databaseConnectionCredentials.getUsername();
        String password = databaseConnectionCredentials.getPassword();
        String databaseName = databaseConnectionCredentials.getDatabaseName();
        String serverTimeZone = databaseConnectionCredentials.getServerTimeZone();
        String serverName = databaseConnectionCredentials.getServerDomainName();

        return (DatabaseController.connectDatabaseTest(username, password, databaseName, serverTimeZone, serverName));
    }

    private void createCredentialObject() {
        String username = setupConnectionUI.usernameField.getText();
        String password = setupConnectionUI.passwordField.getText();
        String databaseName = setupConnectionUI.databaseNameField.getText();
        String serverTimeZone = setupConnectionUI.serverTimeZoneField.getText();
        String serverName = setupConnectionUI.serverNameField.getText();
        databaseConnectionCredentials = new DatabaseConnectionCredentials(username, password, databaseName, serverTimeZone, serverName);
    }

    private boolean allFieldsNotEmpty() {
        boolean allFieldsCorrect = true;
        if (setupConnectionUI.usernameField.getText().isEmpty()) {
            allFieldsCorrect = false;
        }
        if (setupConnectionUI.passwordField.getText().isEmpty()) {
            allFieldsCorrect = false;
        }
        if (setupConnectionUI.databaseNameField.getText().isEmpty()) {
            allFieldsCorrect = false;
        }
        if (setupConnectionUI.serverTimeZoneField.getText().isEmpty()) {
            allFieldsCorrect = false;
        }
        if (setupConnectionUI.serverNameField.getText().isEmpty()) {
            allFieldsCorrect = false;
        }

        return allFieldsCorrect;
    }

    public DatabaseConnectionCredentials getDatabaseConnectionCredentials() {
        return databaseConnectionCredentials;
    }

    public void setDatabaseConnectionCredentials(DatabaseConnectionCredentials databaseConnectionCredentials) {
        this.databaseConnectionCredentials = databaseConnectionCredentials;
    }

}
