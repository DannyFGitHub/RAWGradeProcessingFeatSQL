package view;

import controller.SetupConnectionController;
import dbconnection.DatabaseConnectionCredentials;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import view.elements.ConnectionIndicatorUI;
import view.elements.MenuUI;
import view.elements.RecordViewerUI;
import view.elements.SearchMenuUI;

public class MainWindowUI {

    //Colors
    public static final Color headingTextColor = Color.WHITE;
    public static final Color bodyTextColor = Color.LIGHTGREY;
    public static final Color labelsTextColor = Color.LIGHTGREY;
    public static final Color backgroundHeaderColor = Color.DARKSLATEBLUE;
    public static final Color buttonColor = Color.DARKBLUE;
    public static final Color buttonTextColor = Color.BLACK;
    public static final Color backgroundLeftPanelColor = Color.DARKSLATEBLUE;
    public DatabaseConnectionCredentials databaseConnectionCredentials;
    public RecordViewerUI recordViewerUI;
    public ConnectionIndicatorUI connectionIndicatorUI;
    public SearchMenuUI searchMenuUI;
    public MenuUI leftSideMenu;

    public Stage mainWindow;

    /**
     * Constructor to load the basic parts of the MainWindowUI also controls order.
     * @param mainWindow Stage input which is the default window created in Application JavaFX
     */
    public MainWindowUI(Stage mainWindow) {
        this.mainWindow = mainWindow;

        //Prepare notification area here as it is shared by SetupConnectionController
        //Bottom notification area
        connectionIndicatorUI = new ConnectionIndicatorUI(backgroundHeaderColor, labelsTextColor);

        //Setup Connection
        SetupConnectionController setupConnectionController = new SetupConnectionController(connectionIndicatorUI);
        this.databaseConnectionCredentials = setupConnectionController.getDatabaseConnectionCredentials();
        setupMainWindow();
        mainWindow.show();
    }

    /**
     * Setup of all the fields in this class, order matters.
     */
    public void setupMainWindow() {
        //Set window title, for cosmetic purposes.
        mainWindow.setTitle("Assignment 3 Task 1 - Grade Processing");

        /// HEADER

        //Label just for cosmetic purposes.
        Label programName = new Label("Grade Processor");
        //Set Text Color
        programName.setTextFill(headingTextColor);
        //Set font and size for cosmetic purposes
        programName.setFont(new Font("Arial", 24));
        //Some padding for the wording of the program name
        programName.setPadding(new Insets(10));
        //Set header HBox
        VBox header = new VBox();
        //Add the program name to the header
        header.getChildren().addAll(programName);

        //CENTER RECORD VIEW
        recordViewerUI = new RecordViewerUI();

        //SEARCH RECORD PANEL
        searchMenuUI = new SearchMenuUI(labelsTextColor, buttonTextColor, buttonColor, backgroundHeaderColor, recordViewerUI, databaseConnectionCredentials);
        searchMenuUI.setAlignment(Pos.CENTER_RIGHT);

        //add to header on the right
        header.getChildren().add(searchMenuUI);

        //Set Header Color
        header.setBackground(new Background(new BackgroundFill(backgroundHeaderColor, CornerRadii.EMPTY, Insets.EMPTY)));


        //LEFT MENU SIDE

        leftSideMenu = new MenuUI(buttonTextColor, buttonColor, backgroundLeftPanelColor, databaseConnectionCredentials);


        //Prepare a new borderPane (initiate)
        BorderPane borderPane = new BorderPane();

        //Set the top of the borderPane to the label containing the text for the programs descriptive name , positioned in center
        borderPane.setTop(header);
        BorderPane.setAlignment(programName, Pos.CENTER_LEFT);
        borderPane.setLeft(leftSideMenu);
        borderPane.setCenter(recordViewerUI);
        borderPane.setBottom(connectionIndicatorUI);

        Scene scene = new Scene(borderPane, 600, 600);
        mainWindow.setScene(scene);
    }

}
