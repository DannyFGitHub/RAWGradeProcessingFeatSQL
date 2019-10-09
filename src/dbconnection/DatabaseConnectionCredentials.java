package dbconnection;

/**
 * Credentials object to store the credentials required to make a successful connection to SQL Server
 * The class is created so and object can be passed on from class to class, without modifications as methods are only getters.
 */
public class DatabaseConnectionCredentials {

    private String username;
    private String password;
    private String databaseName;
    private String serverTimeZone;
    private String serverDomainName;

    /**
     * Constructor for the DatabaseConnectionCredentials
     * @param username String username to login
     * @param password String password to login with
     * @param databaseName String database name of the database to work with in SQL server, please make sure that the username given has the authority to make changes.
     * @param serverTimeZone String server time zone settings required by the version of SQL client used.
     * @param serverDomainName String server domain name or ip or loopback address or 'locahost'
     */
    public DatabaseConnectionCredentials(String username, String password, String databaseName, String serverTimeZone, String serverDomainName) {
        this.username = username;
        this.password = password;
        this.databaseName = databaseName;
        this.serverTimeZone = serverTimeZone;
        this.serverDomainName = serverDomainName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getServerTimeZone() {
        return serverTimeZone;
    }

    public String getServerDomainName() {
        return serverDomainName;
    }


}
