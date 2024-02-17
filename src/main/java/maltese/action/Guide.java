package maltese.action;

import maltese.Maltese;

public class Guide implements Action {
    /**
     * Constructs a Help action.
     * This action does not require any parameters.
     */
    public Guide() {
        // No constructor logic needed as the help message is static.
    }

    public static TaskList parse() {
        new Maltese("./data/sample.txt").run(); // use the change data path method
        return new TaskList();
    }

    /**
     * Retrieves the help message.
     *
     * @return A string representing the help message.
     */
    @Override
    public String response() {
        return "I loaded some sample data! Type 'list' to see the sample data";
    }

}
