import java.io.IOException;

import controller.Zero;

/**
 * Class containing the {@code main} method
 */
public class Main {
    public static void main(String[] args) {
        try {
            new Zero().run().close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
