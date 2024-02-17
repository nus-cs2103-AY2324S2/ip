
package config;

import java.util.Objects;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Represents the configuration of the application.
 */
public class Config {
    private static final String DEFAULT_APP_NAME = "Duking Time";
    private static final String DEFAULT_DB_CONNECTION_URL = "jdbc:sqlite:./storage/sqlite.db";
    private static Config cfg;
    public final String dbConnectionUrl;
    public final String appName;

    /**
     * Loads the environment variables from the .env file.
     *
     * @throws RuntimeException
     *             if necessary values are not set in .env file
     */
    public Config() throws RuntimeException {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        String connectionUrl = dotenv.get("DB_CONNECTION_URL");
        this.dbConnectionUrl = Objects.requireNonNullElse(connectionUrl, DEFAULT_DB_CONNECTION_URL);

        String appName = dotenv.get("APP_NAME");
        this.appName = Objects.requireNonNullElse(appName, DEFAULT_APP_NAME);

    }


    /**
     * Loads the configuration of the application.
     *
     * @throws RuntimeException
     *             if necessary values are not set in .env file
     */
    public static void loadConfig() throws RuntimeException {
        cfg = new Config();
    }

    /**
     * Gets the configuration of the application.
     *
     * @return The configuration of the application.
     */
    public static Config getConfig() {
        return cfg;
    }
}
