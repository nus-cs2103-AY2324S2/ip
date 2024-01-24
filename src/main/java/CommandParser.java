import java.util.List;

public class CommandParser {
    public static Action parseCommand(String command, TaskList taskList) {
        String[] words = command.split(" ");

        switch (words[0]) {
            case "bye":
                taskList.goodBye();
                return new Farewell();
            case "list":
                taskList.listTasks();
                return new MyList(taskList);
            case "mark":
                if (words.length > 1) {
                    int index = Integer.parseInt(words[1]) - 1;
                    taskList.markTask(index);
                    return new Mark();
                }
                break;
            case "unmark":
                if (words.length > 1) {
                    int index = Integer.parseInt(words[1]) - 1;
                    taskList.unmarkTask(index);
                    return new Unmark();
                }
                break;
            case "todo":
                if (words.length > 1) {
                    String description = command.substring(5).trim();
                    ToDo todo = new ToDo(description);
                    taskList.addTask(todo);
                    return new Echo("Got it. I've added this task:\n  " + todo + "\nNow you have " + taskList.size() + " tasks in the list.");
                }
                break;
            case "deadline":
                if (words.length > 1) {
                    String[] parts = command.split("/by", 2);
                    // extract out description without deadline
                    String description = parts[0].substring(9).trim();
                    // extract out deadline
                    String by = parts[1].trim();
                    Deadline deadline = new Deadline(description, by);
                    taskList.addTask(deadline);
                    return new Echo("Got it. I've added this task:\n  " + deadline + "\nNow you have " + taskList.size() + " tasks in the list.");
                }
                break;
            case "event":
                if (words.length > 1) {
                    String[] parts = command.split("/from", 2);
                    // extract description without event
                    String description = parts[0].substring(6).trim();
                    String[] eventDetails = parts[1].split("/to", 2);
                    // extract out from
                    String from = eventDetails[0].trim();
                    // extract out to
                    String to = eventDetails[1].trim();
                    Event event = new Event(description, from, to);
                    taskList.addTask(event);
                    return new Echo("Got it. I've added this task:\n  " + event + "\nNow you have " + taskList.size() + " tasks in the list.");
                }
                break;
            default:
                return new Echo("I'm sorry, I didn't understand that command.");
        }

        // Default action if the command is not recognized
        return new Echo("I'm sorry, I didn't understand that command.");
    }
}
