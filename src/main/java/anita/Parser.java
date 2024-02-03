package anita;

public class Parser {
    private Ui ui;


    public Parser() {
        ui = new Ui();
    }

    /**
     * Takes in the raw user input and returns the command.
     *
     * @param description The raw user input.
     * @return Command or action to be done.
     */
    public String parseCommand(String description) {
        String[] tokens = description.split("/", 2);
        String command = tokens[0].split(" ")[0];
        return command;
    }

    /**
     * Used for specific commands where an index is expected.
     * Parses the String input into an int and returns it.
     *
     * @param description The raw user input.
     * @return Index specified by the user.
     */
    public int indexParser(String description) {
        String[] tokens = description.split("/", 2);
        return Integer.parseInt(tokens[0].split(" ")[1]);
    }

    /**
     * Custom parser for the Todo task.
     *
     * @param description The raw user input.
     * @return A String[] containing parts of the user input to be used for instantiation.
     */
    public String[] todoParser(String description) {
        String[] tokens = description.split(" ");
        String[] res = new String[1];
        String taskName = "";
        if (tokens.length == 1)
            throw new ArrayIndexOutOfBoundsException("The description of a todo cannot be empty.");

        for (int i = 1; i < tokens.length; i++) {
            taskName += tokens[i] + " ";
        }
        res[0] = taskName;
        return res;
    }
    /**
     * Custom parser for the Deadline task.
     *
     * @param description The raw user input.
     * @return A String[] containing parts of the user input to be used for instantiation.
     */
    public String[] deadlineParser(String description) {
        String[] tokens = description.split("/", 2);
        String[] tokens2 = tokens[0].split(" ", 2);
        String[] res = new String[2];
        String taskName = "";
        if (tokens2.length == 1)
            throw new ArrayIndexOutOfBoundsException("The description of a deadline cannot be empty.");

        for (int i = 1; i < tokens2.length; i++) {
            taskName += tokens2[i] + " ";
        }
        res[0] = taskName;
        res[1] = tokens[1].split(" ")[1];
        return res;
    }

    /**
     * Custom parser for the Event task.
     *
     * @param description The raw user input.
     * @return A String[] containing parts of the user input to be used for instantiation.
     */
    public String[] eventParser(String description) {
        String[] tokens = description.split("/", 3);
        String[] tokens2 = tokens[0].split(" ", 2);
        String[] res = new String[2];
        String taskName = "";

        if (tokens2.length == 1)
            throw new ArrayIndexOutOfBoundsException("The description of a deadline cannot be empty.");

        for (int i = 1; i < tokens2.length; i++) {
            taskName += tokens2[i] + " ";
        }
        res[0] = taskName;
        res[1] = tokens[1].split(" ")[1];
        res[2] = tokens[2].split(" ")[1];
        return res;
    }
}
