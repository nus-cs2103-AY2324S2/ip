
package config;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Objects;

public class Config {
  public final String dbConnectionUrl;
  public final String appName;

  /**
   * Loads the environment variables from the .env file.
   * @throws RuntimeException if necessary values are not set in .env file
   */
  public Config() throws RuntimeException {
    Dotenv dotenv = Dotenv.load();

    this.dbConnectionUrl = dotenv.get("DB_CONNECTION_URL");
    if (dbConnectionUrl == null) {
      throw new RuntimeException("DB_CONNECTION_URL is not set in .env file");
    }

    String appName = dotenv.get("APP_NAME");
    this.appName = Objects.requireNonNullElse(appName, "Duke");
  }
}