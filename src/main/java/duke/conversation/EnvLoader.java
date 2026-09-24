package duke.conversation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvLoader {
    public static Properties loadEnvVariables(String filePath) throws IOException {
        Properties env = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            env.load(fis);
        }
        return env;
    }
}