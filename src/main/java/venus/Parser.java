package venus;
public class Parser {
    public static TaskList.TYPES findType(String input) {
        String[] listType = input.toUpperCase().split(" ");
        TaskList.TYPES type = TaskList.TYPES.valueOf(listType[0]);
        ;
        if (listType.length == 1) {
            type = TaskList.TYPES.ALL;
        }
        return type;
    }

    public static int findMarkIndex(String input) {
        return Integer.valueOf(input.substring(5)) - 1;
    }

    public static int findUnmarkIndex(String input) {
        return Integer.valueOf(input.substring(7)) - 1;
    }

    public static String findTodoContent(String input) {
        return input.substring(5);
    }

    public static String[] findDeadlineContent(String input) throws DukeException {
        String dString = input.substring(9);
        String[] parts = dString.split("/by");
        if (parts.length != 2) {
            throw new DukeException("Incorrect, choose a specific deadline only please");
        }
        parts[0] = parts[0].trim();
        parts[1] = parts[1].trim();
        return parts;
    }

    public static String[] findEventParts(String input) throws DukeException {
        String dString = input.substring(6);
        String[] parts = dString.split("/");
        if (parts.length != 3) {
            throw new DukeException("Incorrect arguments for events");
        }
        for (int j = 0; j < 3; j++) {
            parts[j] = parts[j].trim();
        }
        return parts;
    }

    public static int findDeleteIndex(String input) {
        return Integer.valueOf(input.substring(7)) - 1;
    }
}
