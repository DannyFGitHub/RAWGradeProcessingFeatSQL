package controller;

import controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.MainWindowUI;

/**
 * DEPENDENCIES: mysql-connector-java-8.0.17.jar
 *
 * @Author Danny Falero
 * Assessment 3 Task 1
 * A JAVAFX interface for an SQL database stored locally or online. It inserts records calculating cumulative marks and grades and allows manual override (updating).
 * User can search for records or display all records by leaving the search field blank and clicking on search.
 * Users can also create a new table. The system validates and responds accordingly to make sure that the user enters data that is valid.
 */
public class GradeProcessingFeatSQL extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainWindow) throws Exception {
        //Prepare MainWindowUI
        MainWindowUI mainWindowUI = new MainWindowUI(mainWindow);
        //Load MainController for mainWindowUI
        MainController mainController = new MainController(mainWindowUI);
    }
}

