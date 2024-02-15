package simpli.configs;

/**
 * Configurations for the chatbot.
 */
public final class SimpliConfiguration {
    // Chatbot response placeholder
    public static final String PLACEHOLDER =
            "\t------------------------------------------------------------\n"
                    + "\t\t%s\n"
                    + "\t------------------------------------------------------------";

    public static final String DATAPATH = "./data/simpli.csv";

    /**
     * Class cannot be initialized.
     */
    private SimpliConfiguration() {
    }
}
