import haro.Haro;

/**
 * The Duke class represents the main entry point for the Duke application.
 *
 */
public class Duke {
    public Duke() {
        // New duke instance
    }

    /**
     * Initializes the Duke application with an instance of the Haro class
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Haro haro = new Haro("data/saveList.txt", "data/");
        haro.initialise();
    }
}
