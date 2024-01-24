import java.util.List;

import java.util.List;

public class CommandParser {
    public static Action parseCommand(String command, TaskList taskList) throws EmptyDescriptionException, NoIndexException, UnknownCommandException {
        try {
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
                    } else {
                        throw new NoIndexException();
                    }
                case "unmark":
                    if (words.length > 1) {
                        int index = Integer.parseInt(words[1]) - 1;
                        taskList.unmarkTask(index);
                        return new Unmark();
                    } else {
                        throw new NoIndexException();
                    }
                case "todo":
                    if (words.length > 1) {
                        String description = command.substring(5).trim();
                        if (description.isEmpty()) {
                            throw new EmptyDescriptionException();
                        }
                        ToDo todo = new ToDo(description);
                        taskList.addTask(todo);
                        return new Echo("Got it. I've added this task:\n  " + todo + "\nNow you have " + taskList.size() + " tasks in the list.");
                    } else {
                        throw new EmptyDescriptionException();
                    }

                case "delete":
                    if (words.length > 1) {
                        int index = Integer.parseInt(words[1]) - 1;
                        Task deletedTask = taskList.deleteTask(index);
                        if (deletedTask != null) {
                            return new Delete(deletedTask);
                        } else {
                            throw new NoIndexException();
                        }
                    } else {
                        throw new NoIndexException();
                    }
                case "deadline":
                    if (words.length > 1) {
                        String[] parts = command.split("/by", 2);
                        // extract out description without deadline
                        String description = parts[0].substring(9).trim();
                        if (description.isEmpty()) {
                            throw new EmptyDescriptionException();
                        }
                        String by = parts[1].trim();
                        Deadline deadline = new Deadline(description, by);
                        taskList.addTask(deadline);
                        return new Echo("Got it. I've added this task:\n  " + deadline + "\nNow you have " + taskList.size() + " tasks in the list.");
                    } else {
                        throw new EmptyDescriptionException();
                    }
                case "event":
                    if (words.length > 1) {
                        String[] parts = command.split("/from", 2);
                        // extract description without event
                        String description = parts[0].substring(6).trim();
                        if (description.isEmpty()) {
                            throw new EmptyDescriptionException();
                        }
                        String[] eventDetails = parts[1].split("/to", 2);
                        // extract out from
                        String from = eventDetails[0].trim();
                        // extract out to
                        String to = eventDetails[1].trim();
                        Event event = new Event(description, from, to);
                        taskList.addTask(event);
                        return new Echo("Got it. I've added this task:\n  " + event + "\nNow you have " + taskList.size() + " tasks in the list.");
                    } else {
                        throw new EmptyDescriptionException();
                    }
                default:
                    throw new UnknownCommandException();
            }
        } catch (Exception e) {
            throw e;
        }
    }
}

