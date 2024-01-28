import java.time.LocalDateTime;

public class Parser {
    public static Command parseInput(String input) throws CommandException {
        input = input.trim();

        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("todo")) {
            return new ToDoCommand(input.substring(4));
        } else if (input.startsWith("deadline")) {
            return new DeadlineCommand(input.substring(8));
        } else if (input.startsWith("event")) {
            return new EventCommand(input.substring(5));
        } else if (input.startsWith("mark")) {
            return new MarkCommand(input.substring(4));
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(input.substring(6));
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input.substring(6));
        }

        throw new CommandException("Error. Unable to recognize command. Commands are CASE SENSITIVE.\n"
                + "Supported List of Commands:\n"
                + "- " +  ToDo.CREATE_TODO_FORMAT + ": Adds new ToDo task.\n"
                + "- " +  Deadline.CREATE_DEADLINE_FORMAT + ": Adds new Deadline task.\n"
                + "- " +  Event.CREATE_EVENT_FORMAT + ": Adds new Event task.\n"
                + "- mark <task-number>: Marks task at index <task-number> as done.\n"
                + "- unmark <task-number>: Marks task at index <task-number> as not done.\n"
                + "- delete <task-number>: Deletes task at index <task-number>.\n"
                + "- bye: exits the program.");
    }

    /**
     * Returns a Task based on given String.
     * String should follow convention based on respective subclass.
     *
     * @param taskData Data String of task.
     * @return Task specified by given String.
     * @throws LoadException Exception when data cannot be loaded due to incompatible format.
     */
    public static Task parseData(String taskData) throws LoadException {
        try {
            String[] tokens = taskData.split("\\|", 3);
            Task task = null;
            String description = "";
            boolean marked = Integer.parseInt(tokens[1].trim()) > 0;
            switch (tokens[0].trim()) {
            case "T":
                description = tokens[2].trim();
                if (description.isEmpty()) {
                    throw new LoadException("Description cannot be empty.");
                }
                task = new ToDo(description);
                break;
            case "D":
                tokens = tokens[2].trim().split("/by");
                description = tokens[0].trim();
                if (description.isEmpty()) {
                    throw new LoadException("Description cannot be empty.");
                }
                LocalDateTime by = LocalDateTime.parse(tokens[1].trim(), Task.INPUT_DATETIME_FORMAT);
                task = new Deadline(description, by);
                break;
            case "E":
                tokens = tokens[2].split("/from");
                description = tokens[0].trim();
                if (description.isEmpty()) {
                    throw new LoadException("Description cannot be empty.");
                }
                tokens = tokens[1].split("/to");
                LocalDateTime from = LocalDateTime.parse(tokens[0].trim(), Task.INPUT_DATETIME_FORMAT);
                LocalDateTime to = LocalDateTime.parse(tokens[1].trim(), Task.INPUT_DATETIME_FORMAT);
                task = new Event(description, from, to);
                break;
            }

            if (task != null) {
                if (marked) {
                    task.markAsDone();
                }
                return task;
            }
        } catch (Exception e) {
            throw new LoadException("Task cannot be loaded due to incorrect format. Skipping.");
        }
        throw new LoadException("Task cannot be loaded due to incorrect format. Skipping.");
    }
}
