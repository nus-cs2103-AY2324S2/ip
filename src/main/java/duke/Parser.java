package duke;

import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Parser {
    private static HashSet<String> validCommands;

    public Parser() {
        validCommands = new HashSet<String>();
        validCommands.addAll(Arrays.asList("bye",
                "list",
                "mark",
                "unmark",
                "todo",
                "deadline",
                "event",
                "delete",
                "find"));
    }

    public boolean checkValidCommand(String input) {
        StringTokenizer st = new StringTokenizer(input);
        String identifier = st.nextToken().toLowerCase();
        return validCommands.contains(identifier);
    }

    public String parseCommand(String input) {
        StringTokenizer st = new StringTokenizer(input);
        return st.nextToken().toLowerCase();
    }

    public int parseTaskIndex(String input) {
        return Integer.parseInt(input) - 1;
    }

    public String parseArguments(String input) {
        StringTokenizer st = new StringTokenizer(input);
        st.nextToken();
        String arguments = "";
        while(st.hasMoreTokens()) {
            arguments += st.nextToken() + " ";
        }
        return arguments.trim();
    }

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
