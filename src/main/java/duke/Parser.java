package duke;

import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Parser class contains methods to check validity and decipher user input commands.
 */
public class Parser {
    private static HashSet<String> validCommands;

    /**
     * Constructor for creating a Parser object.
     */
    public Parser() {
        validCommands = new HashSet<String>();
        validCommands.addAll(Arrays.asList("bye",
                "list",
                "mark",
                "unmark",
                "todo",
                "deadline",
                "event",
                "delete"));
    }

    /**
     * Returns a boolean that specifies whether the provided input string contains a valid command.
     *
     * @param input User input string.
     * @return Boolean specifying validity of command.
     */
    public boolean checkValidCommand(String input) {
        StringTokenizer st = new StringTokenizer(input);
        String identifier = st.nextToken().toLowerCase();
        return validCommands.contains(identifier);
    }

    /**
     * Returns the command portion (first word) of the user input string in lower case.
     *
     * @param input User input string.
     * @return String containing a command.
     */
    public String parseCommand(String input) {
        StringTokenizer st = new StringTokenizer(input);
        return st.nextToken().toLowerCase();
    }

    /**
     * Returns an int that corresponds to the index of a task.
     *
     * @param input User input string.
     * @return Index of a task.
     */
    public int parseTaskIndex(String input) {
        return Integer.parseInt(input) - 1;
    }

    /**
     * Returns a string that contains the arguments of a command in the user input string.
     *
     * @param input User input string.
     * @return String containing arguments for a command.
     */
    public String parseArguments(String input) {
        StringTokenizer st = new StringTokenizer(input);
        st.nextToken();
        String arguments = "";
        while(st.hasMoreTokens()) {
            arguments += st.nextToken() + " ";
        }
        return arguments.trim();
    }

    /**
     * Returns a String array containing the arguments required for creating a deadline task.
     * Index 0 contains the description of the deadline task.
     * Index 1 contains the complete by date of the deadline task.
     *
     * @param input String containing command arguments.
     * @return String array containing arguments for creating deadline task.
     */
    public String[] parseDeadlineArguments(String input) {
        String[] deadlineInfo = new String[2];
        String[] inputSplit = input.split("/");
        StringTokenizer st = new StringTokenizer(inputSplit[0].trim());
        String deadlineDesc = "";
        while (st.hasMoreTokens()) {
            deadlineDesc += st.nextToken() + " ";
        }
        deadlineInfo[0] = deadlineDesc.trim();
        st = new StringTokenizer(inputSplit[1].trim());
        st.nextToken();
        String by = "";
        while (st.hasMoreTokens()) {
            by += st.nextToken() + " ";
        }
        deadlineInfo[1] = by.trim();
        return deadlineInfo;
    }

    /**
     * Returns a String array containing the arguments required for creating an event task.
     * Index 0 contains the description of the event task.
     * Index 1 contains the start date of the event task.
     * Index 2 contains the end date of the event task.
     *
     * @param input String containing command arguments.
     * @return String array containing arguments for creating event task.
     */
    public String[] parseEventArguments(String input) {
        String[] eventInfo = new String[3];
        String[] inputSplit = input.split("/");
        StringTokenizer st = new StringTokenizer(inputSplit[0].trim());
        String eventDesc = "";
        while(st.hasMoreTokens()) {
            eventDesc += st.nextToken() + " ";
        }
        eventInfo[0] = eventDesc.trim();
        st = new StringTokenizer(inputSplit[1].trim());
        st.nextToken();
        String from = "";
        while (st.hasMoreTokens()) {
            from += st.nextToken() + " ";
        }
        eventInfo[1] = from.trim();
        st = new StringTokenizer(inputSplit[2].trim());
        st.nextToken();
        String end = "";
        while (st.hasMoreTokens()) {
            end += st.nextToken() + " ";
        }
        eventInfo[2] = end.trim();
        return eventInfo;
    }


}
