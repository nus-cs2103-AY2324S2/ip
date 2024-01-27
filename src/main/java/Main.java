import java.sql.*;
import config.Config;
import duke.Duke;

public class Main {
  public static void main(String[] args) {
    Config.loadEnv();

    try {
      database.DB.connect();
      database.DB.AutoMigrate();
    } catch (SQLException e) {
      System.out.println("Something went wrong while connecting and migrating the database:");
      throw new RuntimeException(e.getMessage()); // we should not continue if we cannot connect to the database
    }

    Duke app = new Duke("Aiken Dueet");

    try {
      app.run();
    } catch (Exception e) {
      System.out.println("Something went wrong while running the app:");
      System.out.println(e.getMessage());
    }

    database.DB.disconnect();
  }
}
