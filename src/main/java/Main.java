import config.config;
import java.sql.*;
public class Main {
  public static void main(String[] args) {
    config.loadEnv();

    try {
      database.DB.connect();
      database.DB.AutoMigrate();
    } catch (SQLException e) {
      System.out.println("Something went wrong while connecting and migrating the database:");
      throw new RuntimeException(e.getMessage()); // we should not continue if we cannot connect to the database
    }

    Duke app = new Duke("Aiken Dueet");
    app.run();

    database.DB.disconnect();
  }
}
