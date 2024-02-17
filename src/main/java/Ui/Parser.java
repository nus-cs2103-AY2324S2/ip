package Ui;

import java.util.List;

import Tasks.Task;

public class Parser {

    public enum Command {
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        BYE,
        LIST,
        FIND,
        UPDATE_TIME,
        HELP,
        UNKNOWN
    }

    public Parser() {}

    public Command parseUserMsg(String[] userMsg) {
        assert userMsg != null && userMsg.length > 0 : "User message should not be null or empty";

        switch (userMsg[0]) {
            case "bye":
                return Command.BYE;
            case "list":
                return Command.LIST;
            case "mark":
                return Command.MARK;
            case "unmark":
                return Command.UNMARK;
            case "todo":
                return Command.TODO;
            case "deadline":
                return Command.DEADLINE;
            case "event":
                return Command.EVENT;
            case "delete":
                return Command.DELETE;
            case "find":
                return Command.FIND;
            case "update_time":
                return Command.UPDATE_TIME;
            default:
                return Command.UNKNOWN;
        }
    }

    public boolean checkValidMarkCommand(String[] userMsg, List<Task> userTaskList) {
        return userMsg.length > 1 &&
            userMsg[1].chars().allMatch(Character::isDigit) &&
            Integer.parseInt(userMsg[1]) <= userTaskList.size() &&
            Integer.parseInt(userMsg[1]) > 0;
    }
}
