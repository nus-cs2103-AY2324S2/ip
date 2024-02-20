package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import config.Config;

/** A class that handles generic database operations. */
public class Database {
    private static Connection conn;

    /**
     * Connects to the database.
     */
    public void connect() throws SQLException {
        Config cfg = Config.getConfig();
        String connectionUrl = cfg.dbConnectionUrl;

        String[] connectionUrlParts = connectionUrl.split("/");
        String databaseFolderName = connectionUrlParts[1];

        File databaseFolder = new File("." + File.separator + databaseFolderName);
        if (!databaseFolder.exists()) {
            databaseFolder.mkdir();
        }

        conn = DriverManager.getConnection(connectionUrl);
    }

    /**
     * Disconnects from the database.
     */
    public void disconnect() throws SQLException {
        conn.close();
    }

    /**
     * Executes a SQL statement.
     *
     * @param sql
     *            the SQL statement to execute
     * @return a ResultSet object that contains the data produced by the given
     *         query.
     * @throws SQLException
     *             if a database error occurs
     */
    protected ResultSet execute(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        return stmt.getResultSet();
    }

    /**
     * Executes a SQL statement.
     *
     * @param sql
     *            the SQL statement to execute
     * @param values
     *            the values to be set in the statement
     * @return a ResultSet object that contains the data produced by the given
     *         query.
     * @throws SQLException
     *             if a database error occurs
     */
    protected ResultSet execute(String sql, String... values) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (int i = 0; i < values.length; i++) {
            pstmt.setString(i + 1, values[i]);
        }
        pstmt.executeUpdate();
        return pstmt.getResultSet();
    }

    /**
     * Executes a SQL insert statement.
     *
     * @param sql
     *            the SQL statement to execute
     * @return a ResultSet object that contains the data produced by the given
     *         query.
     * @throws SQLException
     *             if a database error occurs
     */
    protected ResultSet insert(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        return stmt.getGeneratedKeys();
    }

    /**
     * Executes a SQL insert statement.
     *
     * @param sql
     *            the SQL statement to execute
     * @param values
     *            the values to be set in the statement
     * @return a ResultSet object that contains the data produced by the given
     *         query.
     * @throws SQLException
     *             if a database error occurs
     */
    protected ResultSet insert(String sql, String... values) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < values.length; i++) {
            pstmt.setString(i + 1, values[i]);
        }
        pstmt.executeUpdate();
        return pstmt.getGeneratedKeys();
    }

    /**
     * Executes a SQL select statement.
     *
     * @param sql
     *            the SQL statement to execute
     * @return a ResultSet object that contains the data produced by the given
     *         query.
     * @throws SQLException
     *             if a database error occurs
     */
    protected ResultSet select(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        return stmt.getResultSet();
    }

    /**
     * Executes a SQL select statement.
     *
     * @param sql
     *            the SQL statement to execute
     * @param values
     *            the values to be set in the statement
     * @return a ResultSet object that contains the data produced by the given
     *         query.
     * @throws SQLException
     *             if a database error occurs
     */
    protected ResultSet select(String sql, String... values) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (int i = 0; i < values.length; i++) {
            pstmt.setString(i + 1, values[i]);
        }
        pstmt.execute();
        return pstmt.getResultSet();
    }

    /**
     * Migrates the database with the necessary tables, if they don't exist.
     * This method should be called after connecting to the database.
     *
     * @throws SQLException
     *             if a database error occurs
     */
    public void autoMigrate() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS tasks ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "type TEXT NOT NULL,"
                + "description TEXT NOT NULL,"
                + "deadline TEXT,"
                + "startDate TEXT,"
                + "endDate TEXT,"
                + "isDone INTEGER DEFAULT 0"
                + ");";

        this.execute(sql);
    }
}
