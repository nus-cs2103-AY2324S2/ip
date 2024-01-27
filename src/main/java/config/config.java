
package config;

import io.github.cdimascio.dotenv.Dotenv;


public class config {
  public static String dbConnectionUrl;

  public static void loadEnv() throws RuntimeException {
    Dotenv dotenv = Dotenv.load();

    dbConnectionUrl = dotenv.get("DB_CONNECTION_URL");
    if (dbConnectionUrl == null) {
      throw new RuntimeException("DB_CONNECTION_URL is not set in .env file");
    }
  }
}