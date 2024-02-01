package duke;

public class Parser {

    public Parser() {

    }

    public static int parseInt(String command) {
        int taskNum = Integer.valueOf(command.replaceAll("[^0-9]", ""));
        return taskNum;
    }

    public static String[] parseCommand(String command) {
        String[] cmd = command.split(" ", 2);
        return cmd;
    }

}
