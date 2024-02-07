/**
 * Class to initiate the LEMONA task manager
 */
public class Duke {

    /**
     * The main method to start the Duke application.
     * Creates an instance of Lemona task manager and runs it.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Lemona lemona = new Lemona("data/lemona.txt");
        lemona.run();
    }
}