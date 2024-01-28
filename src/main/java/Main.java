import java.sql.SQLException;

import config.Config;
import database.Database;
import duke.Duke;

/**
 * The main class of the application.
 * This class is responsible for starting the application.
 * It also handles the creation of the database object and the migration of the
 * database.
 */
public class Main {
  public static void main(String[] args) {
    Config cfg = new Config();

    try {
      Database db = new Database();
      db.connect(cfg);
      db.autoMigrate();

      Duke app = new Duke(cfg.APP_NAME);
      app.run();

      db.disconnect();
    } catch (SQLException e) {
      System.out.println("Something went wrong while connecting and migrating the database:");
      throw new RuntimeException(e.getMessage()); // we should not continue if we cannot connect to the database
    } catch (Exception e) {
      System.out.println("Something went wrong while loading the app:");
      System.out.println(e.getMessage());
    }
  }
}
