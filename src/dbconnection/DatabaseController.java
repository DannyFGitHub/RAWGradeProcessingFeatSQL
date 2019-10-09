package dbconnection;

import enums.ColumnNames;
import view.elements.SearchResultEntry;

import java.sql.*;
import java.util.ArrayList;

/**
 * This class interacts directly with the SQL server to accomplish the tasks requested from other controllers.
 */
public class DatabaseController {

    /**
     * Method to test the connection to the database
     *
     * @param username         String username to login to the SQL server
     * @param password         String password to login to the SQL Server
     * @param dataBaseName     String databaseName to access
     * @param serverTimezone   String timezone settings to send to the server e.g. Australia/Sydney
     * @param serverDomainName String server domain name to connect to, recommend to use hostlocal for local database.
     * @return
     */
    public static boolean connectDatabaseTest(String username, String password, String dataBaseName, String serverTimezone, String serverDomainName) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverDomainName + "/" + dataBaseName + "?serverTimezone=" + serverTimezone, username, password)) {
            //Do nothing
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

    //Check if Table Exists
    public static boolean tableExistsCheck(String tableName, String username, String password, String dataBaseName, String serverTimezone, String serverDomainName) throws SQLException {
        // Connect to a database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverDomainName + "/" + dataBaseName + "?serverTimezone=" + serverTimezone, username, password)) {
            //Get database metadata
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            //Query the meta data results for a table matching tableName
            ResultSet tables = databaseMetaData.getTables(null, null, tableName, null);
            return tables.next();
        }
    }

    /**
     * Create a default table as per assignment requirements.
     *
     * @param tableName        String the name of the table.
     * @param username         String the username to login as.
     * @param password         String the password to login with.
     * @param dataBaseName     String the database name to use.
     * @param serverTimezone   String the timezone settings to use.
     * @param serverDomainName String the ip or server name (localhost if hosted locally)
     * @return boolean value indicating whether the table exists after creation. (Confirmation)
     * @throws SQLException
     */
    public static boolean createTable(String tableName, String username, String password, String dataBaseName, String serverTimezone, String serverDomainName) throws SQLException {
        // Connect to a database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverDomainName + "/" + dataBaseName + "?serverTimezone=" + serverTimezone, username, password)) {
            // Create a statement
            Statement statement = connection.createStatement();
            //Drop the table if it exists:
            if (tableExistsCheck(tableName, username, password, dataBaseName, serverTimezone, serverDomainName)) {
                //Request the table to be dropped.
                statement.executeUpdate("drop table " + tableName);
            }
            //Create the default table, created from the ColumnNames enum so that ColumnNames enum is the source of truth for all other classes and also setting the data types
            statement.executeUpdate("create table " + tableName + " (" + ColumnNames.ID.getDisplayName() + " char(8), " + ColumnNames.NAME.getDisplayName() + " varchar(50), " + ColumnNames.QUIZ.getDisplayName() + " integer, " + ColumnNames.A1.getDisplayName() +
                    " integer, " + ColumnNames.A2.getDisplayName() + " integer, " + ColumnNames.EXAM.getDisplayName() + " integer, " + ColumnNames.CUMULATIVEMARK.getDisplayName() + " integer, " + ColumnNames.GRADE.getDisplayName() + " char(2), primary key (" + ColumnNames.ID.getDisplayName() + "))");
        }
        //Return a check of whether the table exists:
        return tableExistsCheck(tableName, username, password, dataBaseName, serverTimezone, serverDomainName);
    }

    /**
     * Insert a record onto the given tableName table. It uses username, password, databaseName, serverTimezone, serverDomainName to initiate the connection.
     * Before creating it will check to make sure that the key does not already exist.
     *
     * @param tableName        String name of the table to create the record in
     * @param databaseEntry    DatabaseEntry object containing all the information to put in the insert update query.
     * @param username         String username to login to the SQL server
     * @param password         String password to login to the SQL Server
     * @param dataBaseName     String databaseName to access
     * @param serverTimezone   String timezone settings to send to the server e.g. Australia/Sydney
     * @param serverDomainName String server domain name to connect to, recommend to use hostlocal for local database.
     * @return int of either value of 0, 1, or 2. 0 indicates there was no issues and insert was successful, 1 indicates a record already exists and 2 indicates there was an error.
     * @throws SQLException
     */
    public static int insertNewRecord(String tableName, DatabaseEntry databaseEntry, String username, String password, String dataBaseName, String serverTimezone, String serverDomainName) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverDomainName + "/" + dataBaseName + "?serverTimezone=" + serverTimezone, username, password)) {
            //Create a statement
            Statement statement = connection.createStatement();
            ResultSet countFound = statement.executeQuery("SELECT COUNT(1)\n" +
                    "FROM " + tableName + "\n" +
                    "WHERE " + ColumnNames.ID.getDisplayName() + " = " + databaseEntry.getId() + ";");
            while (countFound.next()) {
                if (countFound.getInt(1) > 0) {
                    return 1;
                }
            }
            statement.executeUpdate("insert into " + tableName + " values ('" + databaseEntry.getId() + "', '" + databaseEntry.getName() + "', " + databaseEntry.getQuiz() + ", " + databaseEntry.getA1() + ", " + databaseEntry.getA2() + ", " + databaseEntry.getExam() + ", " + databaseEntry.getCumulativeMark() + ", '" + databaseEntry.getGrade() + "')");
            ResultSet newCountFound = statement.executeQuery("SELECT COUNT(1)\n" +
                    "FROM " + tableName + "\n" +
                    "WHERE " + ColumnNames.ID.getDisplayName() + " = " + databaseEntry.getId() + ";");
            while (newCountFound.next()) {
                if (newCountFound.getInt(1) == 1) {
                    return 0;
                }
            }
        }
        return 2;
    }

    /**
     * Updates the record by ID by first confirming that the record exists, in order to prevent SQLErrors when the record does not exist. It returns to the caller with
     * an integer code system 0 for success, 1 for record does not exist, 2 for another error.
     *
     * @param tableName        String the name of the table to make the changes to.
     * @param idToUpdate       String 8 digit id to update and retrieve records
     * @param fieldToUpdate    String the field name to update in the database, it can be lowercase or uppercase.
     * @param dataToUpdate     String with the information to update the database field with.
     * @param username         String username to login to the SQL server
     * @param password         String password to login to the SQL Server
     * @param dataBaseName     String databaseName to access
     * @param serverTimezone   String timezone settings to send to the server e.g. Australia/Sydney
     * @param serverDomainName String server domain name to connect to, recommend to use hostlocal for local database.
     * @return int code system 0 for success, 1 for record does not exist, 2 for another error.
     * @throws SQLException
     */
    public static int updateTableRecordById(String tableName, String idToUpdate, ColumnNames fieldToUpdate, String dataToUpdate, String username, String password, String dataBaseName, String serverTimezone, String serverDomainName) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverDomainName + "/" + dataBaseName + "?serverTimezone=" + serverTimezone, username, password)) {
            //Create a statement
            Statement statement = connection.createStatement();
            //Check if the record to update exists otherwise return 1. (Record not found).
            ResultSet countFound = statement.executeQuery("SELECT COUNT(1)\n" +
                    "FROM " + tableName + "\n" +
                    "WHERE " + ColumnNames.ID.getDisplayName() + " = " + idToUpdate + ";");
            while (countFound.next()) {
                if (!(countFound.getInt(1) > 0)) {
                    return 1;
                }
            }

            statement.executeUpdate("UPDATE " + tableName + " SET " + fieldToUpdate.getDisplayName() + " = '" + dataToUpdate + "' WHERE " + ColumnNames.ID + " = " + idToUpdate);

            ResultSet queryResult = statement.executeQuery("SELECT " + fieldToUpdate.getDisplayName() + "\n" +
                    "FROM " + tableName + "\n" +
                    "WHERE " + ColumnNames.ID.getDisplayName() + " = " + idToUpdate + " AND " + fieldToUpdate.getDisplayName() + " = '" + dataToUpdate + "';");
            while (queryResult.next()) {
                if ((queryResult.getString(1).matches(dataToUpdate))) {
                    return 0;
                }
            }
        }
        return 2;
    }


    /**
     * Method to perform search in database for any columns that match the search query, it returns a ArrayList of results.
     *
     * @param tableName        String the name of the table to make the changes to.
     * @param fieldToSearch    ColumnNames enum value of the field name to search
     * @param searchQuery      String the search query to perform the search with on the records of the database.
     * @param username         String username to login to the SQL server
     * @param password         String password to login to the SQL Server
     * @param dataBaseName     String databaseName to access
     * @param serverTimezone   String timezone settings to send to the server e.g. Australia/Sydney
     * @param serverDomainName String server domain name to connect to, recommend to use hostlocal for local database.
     * @return ArrayList<SearchResultEntry> this is an arrayList with SearchResultEntry objects initiated from the reulsts of the search query from the server. Each SearchResultEntry object corresponds with a result.
     * if there are no results returned, it simply returns an empty list.
     * @throws SQLException
     */
    public static ArrayList<SearchResultEntry> searchTableByField(String tableName, ColumnNames fieldToSearch, String searchQuery, String username, String password, String dataBaseName, String serverTimezone, String serverDomainName) throws SQLException {
        ArrayList<SearchResultEntry> resultEntries = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverDomainName + "/" + dataBaseName + "?serverTimezone=" + serverTimezone, username, password)) {
            //Create a statement
            Statement statement = connection.createStatement();
            //Check if the record to update exists otherwise return 1. (Record not found).
            ResultSet queryResults = statement.executeQuery("SELECT *\n" +
                    "FROM " + tableName + "\n" +
                    "WHERE " + fieldToSearch.getDisplayName() + " LIKE '%" + searchQuery + "%';");
            while (queryResults.next()) {
                String id = queryResults.getString(ColumnNames.ID.getDisplayName());
                String name = queryResults.getString(ColumnNames.NAME.getDisplayName());
                String quiz = queryResults.getString(ColumnNames.QUIZ.getDisplayName());
                String a1 = queryResults.getString(ColumnNames.A1.getDisplayName());
                String a2 = queryResults.getString(ColumnNames.A2.getDisplayName());
                String exam = queryResults.getString(ColumnNames.EXAM.getDisplayName());
                String cumulativeMark = queryResults.getString(ColumnNames.CUMULATIVEMARK.getDisplayName());
                String grade = queryResults.getString(ColumnNames.GRADE.getDisplayName());

                SearchResultEntry searchResultEntry = new SearchResultEntry(id, name, quiz, a1, a2, exam, cumulativeMark, grade);
                resultEntries.add(searchResultEntry);
            }
        }
        return resultEntries;
    }

}
