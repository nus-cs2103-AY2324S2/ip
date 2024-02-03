import java.sql.SQLException;

import config.Config;
import database.Database;
import javafx.application.Application;
import view.App;

/**
 * The main class of the application.
 * This class is responsible for starting the application.
 * It also handles the creation of the database object and the migration of the
 * database.
 */
public class Main {
    public static void main(String[] args) {
        Config.loadConfig();
        Database db = new Database();

        try {
            db.connect();
            db.autoMigrate();
        } catch (SQLException e) {
            System.out.println("Something went wrong while connecting and migrating the database:");
            throw new RuntimeException(e.getMessage()); // we should not continue if we cannot connect to the database
        }

        try {
            Application.launch(App.class, args);
        } catch (Exception e) {
            System.out.println("Something went wrong while running the app:");
            System.out.println(e.getMessage());
        }

        try {
            db.disconnect();
        } catch (SQLException e) {
            System.out.println("Something went wrong while disconnecting the database:");
        }
    }
}
