package martin;

public class Parser {
    /**
     * This class represents a Parser object.
     * It is responsible for parsing user input and converting it into commands
     * that can be executed by the program.
     */
    public Parser() {
    }

    /**
     * Parses the input string and returns the corresponding ChatbotKeyword.
     *
     * @param input the input string
     * @return the corresponding ChatbotKeyword
     * @throws IllegalArgumentException if the command is not recognized
     */
    public ChatbotKeyword parse(String input) {
        String[] inputArray = input.split(" ");
        String command = inputArray[0].toLowerCase();
        switch (command) {
            case "list":
                return ChatbotKeyword.LIST;
            case "mark":
                return ChatbotKeyword.MARK;
            case "unmark":
                return ChatbotKeyword.UNMARK;
            case "delete":
                return ChatbotKeyword.DELETE;
            case "todo":
                return ChatbotKeyword.TODO;
            case "deadline":
                return ChatbotKeyword.DEADLINE;
            case "event":
                return ChatbotKeyword.EVENT;
            case "hi":
                return ChatbotKeyword.HI;
            case "bye":
                return ChatbotKeyword.BYE;
            case "find":
                return ChatbotKeyword.FIND;
            case "help":
                return ChatbotKeyword.HELP;
            default:
                throw new IllegalArgumentException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses the input string for a deadline task and returns an array containing
     * the task description and deadline.
     *
     * @param input the input string
     * @return an array where the first element is the task description and the
     *         second element is the deadline
     */
    public String[] parseDeadline(String input) {
        String[] deadlineArray = input.split(" /by ");
        String deadlineDescription = deadlineArray[0];

        return new String[] { deadlineDescription, deadlineArray[1] };
    }

    /**
     * Parses the input string for an event task and returns an array containing the
     * task description, start time, and end time.
     *
     * @param input the input string
     * @return an array where the first element is the task description, the second
     *         element is the start time, and the third element is the end time
     */
    public String[] parseEvent(String input) {
        String[] eventArray = input.split(" /at ");
        String eventDescription = eventArray[0];
        String[] eventTime = eventArray[1].split("-");
        String startTime = eventTime[0];
        String endTime = eventTime[1];

        return new String[] { eventDescription, startTime, endTime };
    }

    /**
     * Removes the command word from the input string and returns the remaining words.
     *
     * @param input the input string
     * @return the input string without the command word
     */
    public String removeCommandWord(String input) {
        String[] inputArray = input.split(" ");
        return input.substring(inputArray[0].length()).strip();
    }
}
