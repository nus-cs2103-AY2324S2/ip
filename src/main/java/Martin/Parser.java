package Martin;

public class Parser {
    public Parser() {
    }

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
            case "bye":
                return ChatbotKeyword.BYE;
            default:
                throw new IllegalArgumentException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public String[] deadlineParse(String input) {
        String[] deadlineArray = input.split(" /by ");
        String deadlineDescription = deadlineArray[0];

        return new String[] { deadlineDescription, deadlineArray[1] };
    }

    public String[] eventParse(String input) {
        String[] eventArray = input.split(" /at ");
        String eventDescription = eventArray[0];
        String[] eventTime = eventArray[1].split("-");
        String startTime = eventTime[0];
        String endTime = eventTime[1];

        return new String[] { eventDescription, startTime, endTime };
    }

    public String getRemainingWords(String input) {
        String[] inputArray = input.split(" ");
        return input.substring(inputArray[0].length()).strip();
    }
}
