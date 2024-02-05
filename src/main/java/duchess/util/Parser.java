package duchess.util;

public class Parser {
    public Parser() {
    }

    public int getAction(String input) throws DuchessException {
        if (input.substring(0, 4).equalsIgnoreCase("list")) {
            return 1;
        }
        else if (input.substring(0, 6).toUpperCase().contains("UNMARK")) {
            return 2;
        }
        else if (input.substring(0, 4).toUpperCase().contains("MARK")) {
            return 3;
        }
        else if (input.substring(0, 6).toUpperCase().contains("DELETE")) {
            return 4;
        }
        else if (input.substring(0, 4).toUpperCase().contains("TODO")) {
            return 5;
        }
        else if (input.substring(0, 8).toUpperCase().contains("DEADLINE")) {
            return 6;
        }
        else if (input.substring(0, 5).toUpperCase().contains("EVENT")) {
            return 7;
        }
        else if (input.substring(0, 4).toUpperCase().contains("FIND")) {
            return 8;
        }
        else {
            throw new DuchessException();
        }
    }

    public String[] getToDoDetails(String input) {
        String[] details = input.split("todo ");
        return details;
    }

    public String[] getEventDetails(String input) {
        String[] shortenedInput = input.split("event ");
        String[] shortenedInputNew = shortenedInput[1].split(" /from ");
        String[] fromTo = shortenedInputNew[1].split(" /to ");
        String[] details = new String[3];
        details[0] = shortenedInputNew[0];
        details[1] = fromTo[0];
        details[2] = fromTo[1];
        return details;
    }

    public String[] getDeadlineDetails(String input) {
        String[] shortenedInput = input.split("deadline ");
        String[] details = shortenedInput[1].split(" /by ");
        return details;
    }

    public String getKeyword(String input) {
        return input.split("find ")[1];
    }
}
